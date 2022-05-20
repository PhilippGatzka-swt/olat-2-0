package ch.bbw.olat.views.admin.user;

import ch.bbw.olat.data.Role;
import ch.bbw.olat.data.entity.OlatPersonEntity;
import ch.bbw.olat.data.entity.OlatUserEntity;
import ch.bbw.olat.data.repository.IOlatUserRepository;
import ch.bbw.olat.data.service.OlatDataService;
import ch.bbw.olat.security.SecurityConfiguration;
import ch.bbw.olat.views.admin.AForm;
import ch.bbw.olat.views.admin.AMaster;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.ItemLabelGenerator;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.textfield.TextField;

public class AdminUserMasterForm extends AForm<OlatUserEntity, IOlatUserRepository> {

    private TextField username;
    public ComboBox<OlatPersonEntity> person;
    public ComboBox<Role> role;

    public AdminUserMasterForm(OlatDataService dataService, AMaster<OlatUserEntity, IOlatUserRepository, ?> master) {
        super(dataService, master);
    }

    @Override
    protected Class<OlatUserEntity> getBeanClass() {
        return OlatUserEntity.class;
    }

    @Override
    protected void performSave(ClickEvent<Button> event) {

        OlatUserEntity user;
        if (entity.getId() == 0) {
            String username_ = username.getValue();
            OlatPersonEntity person_ = person.getValue();
            String defaultPassword = SecurityConfiguration.generatePassword();
            String password_ = dataService.getPasswordEncoder().encode(defaultPassword);
            Role role_ = role.getValue();

            user = dataService.getOlatUserService().buildEntity(username_, password_, person_, role_);
        } else {
            Role role_ = role.getValue();
            user = dataService.getOlatUserService().updateEntity(entity, role_);
        }

        String consistency = dataService.getOlatUserService().checkConsistency(user);
        Notification notification = new Notification(consistency);
        if (consistency.equals("Successful")) {
            dataService.getOlatUserService().save(user);
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
        // Won't be implemented
    }

    @Override
    protected void performCancel(ClickEvent<Button> event) {
        setEntity(null);
        setVisible(false);
    }

    @Override
    protected void createInputLayout() {
        username = new TextField("Username");
        person = new ComboBox<>("Person");
        role = new ComboBox<>("Role");

        add(username, person, role);
    }

    @Override
    protected void configureInputLayout() {
        username.setPlaceholder("Username");
        username.setReadOnly(true);

        role.setItems(Role.values());
        role.setValue(Role.STUDENT);

        person.setItems(dataService.getOlatPersonService().getWithoutUser());
        person.setItemLabelGenerator((ItemLabelGenerator<OlatPersonEntity>) item -> item.getFirstname() + " " + item.getLastname());
        person.addValueChangeListener((HasValue.ValueChangeListener<AbstractField.ComponentValueChangeEvent<ComboBox<OlatPersonEntity>, OlatPersonEntity>>) event -> {
            if (event.getValue() == null) return;

            String name = event.getValue().getFirstname() + " " + event.getValue().getLastname();
            name = name.replace(" ", ".");
            name = name.toLowerCase();

            username.setValue(name);
        });
    }

    @Override
    public void setEntity(OlatUserEntity entity) {
        super.setEntity(entity);
        buttonDelete.setVisible(false);
        if (entity != null)
            person.setReadOnly(entity.getId() != 0);
    }

    @Override
    protected void configureButtonLayout() {
        super.configureButtonLayout();
        buttonDelete.setVisible(false);
    }
}
