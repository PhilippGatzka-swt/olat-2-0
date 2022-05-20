package ch.bbw.olat.views.student.absence;

import ch.bbw.olat.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;

@PageTitle("Student | Absence Master")
@Route(value = "student/absence-master", layout = MainLayout.class)
@RolesAllowed("STUDENT")
public class StudentAbsenceMaster extends Div {
}
