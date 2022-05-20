package ch.bbw.olat.views.admin;

import ch.bbw.olat.data.entity.AOlatEntity;
import ch.bbw.olat.data.repository.IOlatRepository;
import ch.bbw.olat.data.service.OlatDataService;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;

import java.util.List;
import java.util.Objects;

public abstract class AMaster<Entity extends AOlatEntity, Repository extends IOlatRepository<Entity>, Form extends AForm<Entity, Repository>> extends Div {

    protected TextField filterText = new TextField();

    protected HorizontalLayout toolbar;
    protected Form form;
    protected Entity entity;
    protected OlatDataService dataService;

    public AMaster(OlatDataService dataService) {
        this.dataService = dataService;

        addClassNames("list-view");

        configureToolbar();
        configureGrid();
        configureGridColumns();
        configureForm();

        updateGrid();
        select(null);

        setSizeFull();

        add(getPage());
    }

    private void configureForm() {
        form = instantiateForm(dataService);
        form.setWidth("25em");
    }

    private VerticalLayout getPage() {
        VerticalLayout verticalLayout = new VerticalLayout(toolbar, getContent());
        verticalLayout.setSizeFull();
        return verticalLayout;
    }

    private void configureToolbar() {
        filterText.setAutofocus(true);
        filterText.setPlaceholder("Filter");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener((HasValue.ValueChangeListener<AbstractField.ComponentValueChangeEvent<TextField, String>>) textFieldStringComponentValueChangeEvent -> updateGrid());

        Button create = new Button("Create");
        create.addClickListener(this::createEntity);

        toolbar = new HorizontalLayout(filterText, create);

        toolbar.addClassNames("toolbar");
    }

    private void createEntity(ClickEvent<Button> event) {
        select(createEntity());
    }

    protected abstract Entity createEntity();

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.addClassNames("content");
        content.setSizeFull();

        return content;
    }

    protected void select(Entity entity) {
        this.entity = entity;
        if (entity == null) {
            form.setVisible(false);
        } else {
            form.setVisible(true);
            form.setEntity(entity);
        }

    }

    protected abstract Form instantiateForm(OlatDataService dataService);

    protected Grid<Entity> grid = instantiateGrid();

    protected abstract Grid<Entity> instantiateGrid();

    protected abstract void configureGrid();

    protected abstract void configureGridColumns();

    protected abstract List<Entity> gatherData();

    protected void updateGrid() {
        if (filterText.getValue() == null || Objects.equals(filterText.getValue(), "")) {
            grid.setItems(gatherData());
            return;
        }
        grid.setItems(filter());
    }

    protected abstract List<Entity> filter();

}
