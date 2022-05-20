package ch.bbw.olat.views.admin.person;

import ch.bbw.olat.data.entity.OlatPersonEntity;
import ch.bbw.olat.data.repository.IOlatPersonRepository;
import ch.bbw.olat.data.service.OlatDataService;
import ch.bbw.olat.views.admin.AForm;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;

public class AdminPersonMasterForm extends AForm<OlatPersonEntity, IOlatPersonRepository> {

    private TextField firstname;
    private TextField lastname;
    private EmailField email;

    public AdminPersonMasterForm(OlatDataService dataService, AdminPersonMaster master) {
        super(dataService, master);
    }

    @Override
    protected Class<OlatPersonEntity> getBeanClass() {
        return OlatPersonEntity.class;
    }

    @Override
    protected void performSave(ClickEvent<Button> event) {
        OlatPersonEntity person;
        if (entity.getId() == 0) {
            person = dataService.getOlatPersonService().buildEntity(firstname.getValue(), lastname.getValue(), email.getValue());
        } else {
            person = dataService.getOlatPersonService().updateEntity(entity, firstname.getValue(), lastname.getValue(), email.getValue());
        }

        String consistency = dataService.getOlatPersonService().checkConsistency(person);
        Notification notification = new Notification(consistency);
        if (consistency.equals("Successful")) {
            dataService.getOlatPersonService().save(person);
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
        if (dataService.getOlatPersonService().canBeDeleted(entity)) {

            createDeleteDialog("Delete Person?", (ComponentEventListener<ClickEvent<Button>>) event1 -> {
                dataService.getOlatPersonService().delete(entity);
                setEntity(null);
                setVisible(false);

                Notification notification = new Notification("Person has been deleted");
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

    @Override
    protected void configureInputLayout() {
        firstname.setPlaceholder("Firstname");
        firstname.setRequired(true);
        firstname.setAutofocus(true);
        firstname.setMaxLength(255);

        lastname.setPlaceholder("Lastname");
        lastname.setRequired(true);
        lastname.setMaxLength(255);

        email.setPlaceholder("Email");
        email.setRequiredIndicatorVisible(true);
        email.setMaxLength(255);
    }


    @Override
    protected void createInputLayout() {
        firstname = new TextField("Firstname");
        lastname = new TextField("Lastname");
        email = new EmailField("Email");

        add(firstname, lastname, email);
    }

}
