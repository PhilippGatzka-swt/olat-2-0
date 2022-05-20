package ch.bbw.olat.views.teacher.group;

import ch.bbw.olat.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;

@PageTitle("Teacher | Group Master")
@Route(value = "teacher/group-master", layout = MainLayout.class)
@RolesAllowed("TEACHER")
public class TeacherGroupMaster extends Component {
}
