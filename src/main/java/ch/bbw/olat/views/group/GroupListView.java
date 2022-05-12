package ch.bbw.olat.views.group;

import ch.bbw.olat.data.entity.OlatGroupEntity;
import ch.bbw.olat.data.service.OlatGroupService;
import ch.bbw.olat.views.MainLayout;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.html.OrderedList;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.PermitAll;
import java.util.List;

@PageTitle("Groups")
@Route(value = "groups", layout = MainLayout.class)
@PermitAll
public class GroupListView extends Main implements HasComponents, HasStyle {

    private OrderedList groupContainer;

    @Autowired
    public GroupListView(OlatGroupService groupService) {
        constructUI();

        List<OlatGroupEntity> groups = groupService.getAll();

        for (OlatGroupEntity group : groups) {
            groupContainer.add(new GroupListCard(group));
        }

    }

    private void constructUI() {
        addClassNames("image-list-view", "max-w-screen-lg", "mx-auto", "pb-l", "px-l");

        HorizontalLayout container = new HorizontalLayout();
        container.addClassNames("items-center", "justify-between");

        VerticalLayout headerContainer = new VerticalLayout();
        H2 header = new H2("Groups");
        header.addClassNames("mb-0", "mt-xl", "text-3xl");
        Paragraph description = new Paragraph("Royalty free photos and pictures, courtesy of Unsplash");
        description.addClassNames("mb-xl", "mt-0", "text-secondary");
        headerContainer.add(header, description);

        groupContainer = new OrderedList();
        groupContainer.addClassNames("gap-m", "grid", "list-none", "m-0", "p-0");

        container.add(header);
        add(container, groupContainer);

    }
}