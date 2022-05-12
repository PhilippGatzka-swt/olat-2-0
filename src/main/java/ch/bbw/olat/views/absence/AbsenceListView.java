package ch.bbw.olat.views.absence;

import ch.bbw.olat.data.entity.OlatAbsenceEntity;
import ch.bbw.olat.data.entity.OlatUserEntity;
import ch.bbw.olat.data.service.OlatAbsenceService;
import ch.bbw.olat.security.AuthenticatedUser;
import ch.bbw.olat.views.MainLayout;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.gridpro.GridPro;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.LocalDateRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.PermitAll;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@PageTitle("Absences")
@Route(value = "absences", layout = MainLayout.class)
@PermitAll
public class AbsenceListView extends Div {

    private final OlatAbsenceService absenceService;
    private final AuthenticatedUser authenticatedUser;
    private GridPro<OlatAbsenceEntity> grid;
    private GridListDataView<OlatAbsenceEntity> gridListDataView;

    private Grid.Column<OlatAbsenceEntity> dateColumn;
    private Grid.Column<OlatAbsenceEntity> subjectColumn;
    private Grid.Column<OlatAbsenceEntity> commentColumn;
    private Grid.Column<OlatAbsenceEntity> signedColumn;

    @Autowired
    public AbsenceListView(OlatAbsenceService absenceService, AuthenticatedUser authenticatedUser) {
        this.absenceService = absenceService;
        this.authenticatedUser = authenticatedUser;
        addClassName("list-view");
        setSizeFull();
        createGrid();
        add(grid);
    }

    private void createGrid() {
        createGridComponent();
        addColumnsToGrid();
        addFiltersToGrid();
    }

    private void createGridComponent() {
        grid = new GridPro<>();
        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_COLUMN_BORDERS);
        grid.setHeight("100%");

        OlatUserEntity user = authenticatedUser.get().orElseThrow();

        List<OlatAbsenceEntity> absences = absenceService.getRepository().getByStudent(user.getPerson());
        gridListDataView = grid.setItems(absences);
    }

    private void addColumnsToGrid() {
        createDateColumn();
        createSubjectColumn();
        createSignedColumn();
        createCommentColumn();
    }

    private void createSubjectColumn() {
        subjectColumn = grid.addColumn(absence -> absence.getOlatSubjectEntity().getName()).setHeader("Subject").setFlexGrow(0).setWidth("180px");
    }

    private void createCommentColumn() {
        commentColumn = grid.addColumn(OlatAbsenceEntity::getComment).setHeader("Comment").setWidth("180px");
    }

    private void createSignedColumn() {
        signedColumn = grid.addColumn(absence -> absence.getSigned() ? "Closed" : "Open").setHeader("Signed").setWidth("180px");
    }

    private void createDateColumn() {

        dateColumn = grid
                .addColumn(
                        new LocalDateRenderer<>(
                                absence -> convertToLocalDate(absence.getDate()),
                                DateTimeFormatter.ofPattern("MM/dd/yyyy")
                        )
                )
                .setComparator(OlatAbsenceEntity::getDate).setHeader("Date").setWidth("180px").setFlexGrow(0);
    }

    private void addFiltersToGrid() {
        HeaderRow filterRow = grid.appendHeaderRow();

        TextField subjectFilter = new TextField();
        subjectFilter.setPlaceholder("Filter");
        subjectFilter.setClearButtonVisible(true);
        subjectFilter.setWidth("100%");
        subjectFilter.setValueChangeMode(ValueChangeMode.EAGER);
        subjectFilter.addValueChangeListener(event -> gridListDataView
                .addFilter(absence -> StringUtils.containsIgnoreCase(absence.getOlatSubjectEntity().getName(), subjectFilter.getValue())));
        filterRow.getCell(subjectColumn).setComponent(subjectFilter);

        TextField commentFilter = new TextField();
        commentFilter.setPlaceholder("Filter");
        commentFilter.setClearButtonVisible(true);
        commentFilter.setWidth("100%");
        commentFilter.setValueChangeMode(ValueChangeMode.EAGER);
        commentFilter.addValueChangeListener(event -> gridListDataView.addFilter(absence -> StringUtils
                .containsIgnoreCase(absence.getComment(), commentFilter.getValue())));
        filterRow.getCell(commentColumn).setComponent(commentFilter);

        ComboBox<String> signedFilter = new ComboBox<>();
        signedFilter.setItems(Arrays.asList("Open", "Closed"));
        signedFilter.setPlaceholder("Filter");
        signedFilter.setClearButtonVisible(true);
        signedFilter.setWidth("100%");
        signedFilter.addValueChangeListener(
                event -> gridListDataView.addFilter(absence -> isSigned(absence, signedFilter)));
        filterRow.getCell(signedColumn).setComponent(signedFilter);

        DatePicker dateFilter = new DatePicker();
        dateFilter.setPlaceholder("Filter");
        dateFilter.setClearButtonVisible(true);
        dateFilter.setWidth("100%");
        dateFilter.addValueChangeListener(
                event -> gridListDataView.addFilter(absence -> areDatesEqual(absence, dateFilter)));
        filterRow.getCell(dateColumn).setComponent(dateFilter);
    }

    private boolean isSigned(OlatAbsenceEntity absence, ComboBox<String> signedFilter) {
        String signedFilterValue = signedFilter.getValue();
        if (signedFilterValue == null) {
            return true;
        }
        if (signedFilterValue.equals("Open")) {
            if (!absence.getSigned()) return true;
        }
        if (signedFilterValue.equals("Closed")) {
            if (absence.getSigned()) return true;
        }
        return false;
    }

    public LocalDate convertToLocalDate(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    private boolean areDatesEqual(OlatAbsenceEntity absence, DatePicker dateFilter) {
        LocalDate dateFilterValue = dateFilter.getValue();
        if (dateFilterValue != null) {
            LocalDate clientDate = convertToLocalDate(absence.getDate());
            return dateFilterValue.equals(clientDate);
        }
        return true;
    }

}
