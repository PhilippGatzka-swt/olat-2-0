package ch.bbw.olat.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;

@PageTitle("Olat | About")
@Route(value = "", layout = MainLayout.class)
@RolesAllowed({"ADMIN","TEACHER","STUDENT"})
public class About extends Div {
}
