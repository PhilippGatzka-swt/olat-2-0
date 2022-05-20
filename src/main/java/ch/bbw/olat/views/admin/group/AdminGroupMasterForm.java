package ch.bbw.olat.views.admin.group;

import ch.bbw.olat.data.Role;
import ch.bbw.olat.data.entity.OlatGroupEntity;
import ch.bbw.olat.data.entity.OlatUserEntity;
import ch.bbw.olat.data.repository.IOlatGroupRepository;
import ch.bbw.olat.data.service.OlatDataService;
import ch.bbw.olat.views.admin.AForm;
import ch.bbw.olat.views.admin.AMaster;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.ItemLabelGenerator;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.textfield.TextField;

public class AdminGroupMasterForm extends AForm<OlatGroupEntity, IOlatGroupRepository> {

    private TextField name;
    private ComboBox<OlatUserEntity> teacher;

    public AdminGroupMasterForm(OlatDataService dataService, AMaster<OlatGroupEntity, IOlatGroupRepository, ?> master) {
        super(dataService, master);
    }

    @Override
    protected void createInputLayout() {
        name = new TextField("Name");
        teacher = new ComboBox<>("Teacher");

        add(name, teacher);
    }

    @Override
    protected void configureInputLayout() {
        name.setRequired(true);
        name.setPlaceholder("Name");
        name.setAutofocus(true);
        name.setMaxLength(255);

        teacher.setRequired(true);
        teacher.setItemLabelGenerator((ItemLabelGenerator<OlatUserEntity>) OlatUserEntity::getName);
        teacher.setItems(dataService.getOlatUserService().filterByRole(Role.TEACHER));
    }

    @Override
    protected Class<OlatGroupEntity> getBeanClass() {
        return OlatGroupEntity.class;
    }

    @Override
    protected void performSave(ClickEvent<Button> event) {
        OlatGroupEntity group;
        if (entity.getId() == 0) {
            group = dataService.getOlatGroupService().buildEntity(name.getValue(), teacher.getValue());
        } else {
            group = dataService.getOlatGroupService().updateEntity(entity, name.getValue(), teacher.getValue());
        }
        String consistency = dataService.getOlatGroupService().checkConsistency(group);
        Notification notification = new Notification(consistency);
        if (consistency.equals("Successful")) {
            dataService.getOlatGroupService().save(group);
            setEntity(null);
            setVisible(false);
            updateGrid();

            notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        } else {
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        }

        notification.setDuration(5000);
        notification.open();

    }

    @Override
    protected void performDelete(ClickEvent<Button> event) {
        if (dataService.getOlatGroupService().canBeDeleted(entity)) {

            createDeleteDialog("Delete Group?", (ComponentEventListener<ClickEvent<Button>>) event1 -> {
                dataService.getOlatGroupService().delete(entity);
                setEntity(null);
                setVisible(false);

                Notification notification = new Notification("Group has been deleted");
                notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                notification.setDuration(5000);
                notification.open();

                updateGrid();
            });

        } else {
            Notification notification = new Notification("Group can not be deleted");
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            notification.setDuration(5000);
            notification.open();
        }
    }

    @Override
    protected void performCancel(ClickEvent<Button> event) {
        setEntity(null);
        setVisible(false);
    }
}
