package ch.bbw.olat.views.admin.subject;

import ch.bbw.olat.data.entity.OlatSubjectEntity;
import ch.bbw.olat.data.repository.IOlatSubjectRepository;
import ch.bbw.olat.data.service.OlatDataService;
import ch.bbw.olat.views.admin.AForm;
import ch.bbw.olat.views.admin.AMaster;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.textfield.TextField;

public class AdminSubjectMasterForm extends AForm<OlatSubjectEntity, IOlatSubjectRepository> {

    private TextField name;
    private TextField details;


    public AdminSubjectMasterForm(OlatDataService dataService, AMaster<OlatSubjectEntity, IOlatSubjectRepository, ?> master) {
        super(dataService, master);
    }

    @Override
    protected void createInputLayout() {
        name = new TextField("Name");
        details = new TextField("Details");

        add(name, details);
    }

    @Override
    protected void configureInputLayout() {
        name.setRequired(true);
        name.setPlaceholder("Name");
        name.setAutofocus(true);
        name.setMaxLength(255);

        details.setRequired(false);
        details.setPlaceholder("Details");
        details.setMaxLength(512);
    }

    @Override
    protected Class<OlatSubjectEntity> getBeanClass() {
        return OlatSubjectEntity.class;
    }

    @Override
    protected void performSave(ClickEvent<Button> event) {
        OlatSubjectEntity subject;
        if (entity.getId() == 0) {
            subject = dataService.getOlatSubjectService().buildEntity(name.getValue(), details.getValue());
        } else {
            subject = dataService.getOlatSubjectService().updateEntity(entity, name.getValue(), details.getValue());
        }

        String consistency = dataService.getOlatSubjectService().checkConsistency(subject);
        Notification notification = new Notification(consistency);
        if (consistency.equals("Successful")) {
            dataService.getOlatSubjectService().save(subject);
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
        if (dataService.getOlatSubjectService().canBeDeleted(entity)) {

            createDeleteDialog("Delete Subject?", (ComponentEventListener<ClickEvent<Button>>) event1 -> {
                dataService.getOlatSubjectService().delete(entity);
                setEntity(null);
                setVisible(false);

                Notification notification = new Notification("Subject has been deleted");
                notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                notification.setDuration(5000);
                notification.open();

                updateGrid();
            });

        } else {
            Notification notification = new Notification("Subject can not be deleted");
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
