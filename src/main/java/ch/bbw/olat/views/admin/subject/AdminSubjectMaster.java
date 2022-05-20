package ch.bbw.olat.views.admin.subject;

import ch.bbw.olat.data.entity.OlatPersonEntity;
import ch.bbw.olat.data.entity.OlatSubjectEntity;
import ch.bbw.olat.data.repository.IOlatSubjectRepository;
import ch.bbw.olat.data.service.OlatDataService;
import ch.bbw.olat.views.MainLayout;
import ch.bbw.olat.views.admin.AMaster;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;
import java.util.List;


@PageTitle("Admin | Subject Master")
@Route(value = "admin/subject-master", layout = MainLayout.class)
@RolesAllowed("ADMIN")
public class AdminSubjectMaster extends AMaster<OlatSubjectEntity, IOlatSubjectRepository, AdminSubjectMasterForm> {
    public AdminSubjectMaster(OlatDataService dataService) {
        super(dataService);
    }

    @Override
    protected OlatSubjectEntity createEntity() {
        return new OlatSubjectEntity();
    }

    @Override
    protected AdminSubjectMasterForm instantiateForm(OlatDataService dataService) {
        return new AdminSubjectMasterForm(dataService, this);
    }

    @Override
    protected Grid<OlatSubjectEntity> instantiateGrid() {
        return new Grid<>(OlatSubjectEntity.class, true);
    }

    @Override
    protected void configureGrid() {
        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        grid.asSingleSelect().addValueChangeListener((HasValue.ValueChangeListener<AbstractField.ComponentValueChangeEvent<Grid<OlatSubjectEntity>, OlatSubjectEntity>>) event -> select(event.getValue()));
    }

    @Override
    protected void configureGridColumns() {
        grid.setSizeFull();
    }

    @Override
    protected List<OlatSubjectEntity> gatherData() {
        return dataService.getOlatSubjectService().getAll();
    }

    @Override
    protected List<OlatSubjectEntity> filter() {
        return dataService.getOlatSubjectService().filterAll(filterText.getValue());
    }
}
