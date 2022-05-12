package ch.bbw.olat.views.group;

import ch.bbw.olat.data.entity.OlatGroupEntity;
import ch.bbw.olat.data.entity.OlatUserEntity;
import ch.bbw.olat.data.service.OlatGroupService;
import ch.bbw.olat.security.AuthenticatedUser;
import ch.bbw.olat.views.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;

@PageTitle("Groups")
@Route(value = "group", layout = MainLayout.class)
@PermitAll
public class GroupView extends VerticalLayout implements HasUrlParameter<String> {

    private final AuthenticatedUser authenticatedUser;
    private final OlatGroupService groupService;

    public GroupView(AuthenticatedUser authenticatedUser, OlatGroupService groupService) {
        this.authenticatedUser = authenticatedUser;
        this.groupService = groupService;
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String groupName) {
        OlatGroupEntity group = groupService.getRepository().findByName(groupName);

        OlatUserEntity user = authenticatedUser.get().orElseThrow();

        if (user.getPerson().getOlatGroupEntities().contains(group)) {
            configureLayout(group);
        }else{
            Notification notification = new Notification();
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            notification.setText("Oops, you dont have access to this data");
            notification.setDuration(4000);
            notification.open();

            UI.getCurrent().navigate("groups");
        }

    }

    private void configureLayout(OlatGroupEntity group) {
        add(new H1(group.getName()));
    }


}
