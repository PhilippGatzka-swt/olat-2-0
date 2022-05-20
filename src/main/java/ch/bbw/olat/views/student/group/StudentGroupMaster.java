package ch.bbw.olat.views.student.group;

import ch.bbw.olat.data.entity.OlatGroupEntity;
import ch.bbw.olat.data.service.OlatDataService;
import ch.bbw.olat.data.service.OlatGroupService;
import ch.bbw.olat.views.MainLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.OrderedList;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.RolesAllowed;
import java.util.List;


@PageTitle("Student | Group Master")
@Route(value = "student/group-master", layout = MainLayout.class)
@RolesAllowed("STUDENT")
public class StudentGroupMaster extends Div {

    private OrderedList groupContainer;

    @Autowired
    public StudentGroupMaster(OlatGroupService groupService) {
        constructUI();

        List<OlatGroupEntity> groups = groupService.getAll();

        for (OlatGroupEntity group : groups) {
            groupContainer.add(new StudentGroupContainer(group));
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
