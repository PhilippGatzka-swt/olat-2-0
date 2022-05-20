package ch.bbw.olat.views.teacher.absence;

import ch.bbw.olat.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;

@PageTitle("Admin | Absence Master")
@Route(value = "admin/absence-master", layout = MainLayout.class)
@RolesAllowed("ADMIN")
public class TeacherAbsenceMaster extends Div {
}
