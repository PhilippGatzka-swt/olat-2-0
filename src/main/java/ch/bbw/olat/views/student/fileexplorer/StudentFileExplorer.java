package ch.bbw.olat.views.student.fileexplorer;

import ch.bbw.olat.views.MainLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;

@PageTitle("Student | File explorer")
@Route(value = "student/file-explorer", layout = MainLayout.class)
@RolesAllowed("STUDENT")
public class StudentFileExplorer extends Div {
}
