package ch.bbw.olat.views.admin;

import ch.bbw.olat.data.entity.AOlatEntity;
import ch.bbw.olat.data.repository.IOlatRepository;
import ch.bbw.olat.data.service.OlatDataService;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;

public abstract class AForm<Entity extends AOlatEntity, Repository extends IOlatRepository<Entity>> extends FormLayout {

    protected Binder<Entity> binder = new BeanValidationBinder<>(getBeanClass());

    protected Button buttonSave;
    protected Button buttonDelete;
    protected Button buttonCancel;
    protected Entity entity;
    protected OlatDataService dataService;
    private final AMaster<Entity, Repository, ?> master;

    public AForm(OlatDataService dataService, AMaster<Entity, Repository, ?> master) {
        this.dataService = dataService;
        this.master = master;

        createInputLayout();
        configureInputLayout();

        createButtonLayout();
        configureButtonLayout();

        bind();
    }

    protected void updateGrid() {
        master.updateGrid();
    }

    protected abstract void createInputLayout();

    protected abstract void configureInputLayout();

    protected abstract Class<Entity> getBeanClass();

    private void bind() {
        binder.bindInstanceFields(this);
    }

    protected void configureButtonLayout() {
        buttonSave.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonDelete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        buttonCancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        buttonSave.addClickListener(this::performSave);
        buttonDelete.addClickListener(this::performDelete);
        buttonCancel.addClickListener(this::performCancel);

        buttonCancel.addClickShortcut(Key.ESCAPE);
        buttonSave.addClickShortcut(Key.ENTER);
    }

    protected abstract void performSave(ClickEvent<Button> event);

    protected abstract void performDelete(ClickEvent<Button> event);

    protected abstract void performCancel(ClickEvent<Button> event);

    protected void createDeleteDialog(String caption, ComponentEventListener<ClickEvent<Button>> successEvent) {
        Dialog dialog = new Dialog();
        dialog.add(new H3(caption));

        Button dialogConfirm = new Button("Confirm");
        dialogConfirm.addThemeVariants(ButtonVariant.LUMO_ERROR);
        Button dialogCancel = new Button("Cancel");
        dialogCancel.addThemeVariants(ButtonVariant.LUMO_SUCCESS);

        HorizontalLayout buttons = new HorizontalLayout(dialogConfirm, dialogCancel);
        dialog.add(buttons);

        dialogConfirm.addClickListener(successEvent);

        dialogConfirm.addClickListener((ComponentEventListener<ClickEvent<Button>>) event -> dialog.close());

        dialogCancel.addClickListener((ComponentEventListener<ClickEvent<Button>>) event -> dialog.close());

        dialog.open();

    }


    private void createButtonLayout() {
        buttonSave = new Button("Save");
        buttonDelete = new Button("Delete");
        buttonCancel = new Button("Cancel");
        HorizontalLayout layout = new HorizontalLayout(buttonSave, buttonDelete, buttonCancel);
        add(layout);
    }


    public void setEntity(Entity entity) {
        this.entity = entity;
        binder.readBean(entity);
        if (entity == null)
            buttonDelete.setVisible(false);
        else buttonDelete.setVisible(entity.getId() != 0);
    }

    public void setService(OlatDataService dataService) {
        this.dataService = dataService;
    }
}
