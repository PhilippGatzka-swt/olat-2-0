package ch.bbw.olat.views;

import ch.bbw.olat.data.Role;
import ch.bbw.olat.data.entity.OlatUserEntity;
import ch.bbw.olat.security.AuthenticatedUser;
import ch.bbw.olat.views.admin.group.AdminGroupMaster;
import ch.bbw.olat.views.admin.person.AdminPersonMaster;
import ch.bbw.olat.views.admin.subject.AdminSubjectMaster;
import ch.bbw.olat.views.admin.user.AdminUserMaster;
import ch.bbw.olat.views.student.absence.StudentAbsenceMaster;
import ch.bbw.olat.views.student.fileexplorer.StudentFileExplorer;
import ch.bbw.olat.views.student.group.StudentGroupMaster;
import ch.bbw.olat.views.teacher.absence.TeacherAbsenceMaster;
import ch.bbw.olat.views.teacher.group.TeacherGroupMaster;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.auth.AccessAnnotationChecker;

import java.util.Optional;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {

    /**
     * A simple navigation item component, based on ListItem element.
     */
    public static class MenuItemInfo extends ListItem {

        private final Class<? extends Component> view;

        public MenuItemInfo(String menuTitle, String iconClass, Class<? extends Component> view) {
            this.view = view;
            RouterLink link = new RouterLink();
            link.addClassNames("menu-item-link");
            link.setRoute(view);

            Span text = new Span(menuTitle);
            text.addClassNames("menu-item-text");

            link.add(new LineAwesomeIcon(iconClass), text);
            add(link);
        }

        public Class<?> getView() {
            return view;
        }

        /**
         * Simple wrapper to create icons using LineAwesome iconset. See
         * <a href="https://icons8.com/line-awesome">...</a>
         */
        @NpmPackage(value = "line-awesome", version = "1.3.0")
        public static class LineAwesomeIcon extends Span {
            public LineAwesomeIcon(String lineawesomeClassnames) {
                addClassNames("menu-item-icon");
                if (!lineawesomeClassnames.isEmpty()) {
                    addClassNames(lineawesomeClassnames);
                }
            }
        }

    }

    private H1 viewTitle;

    private final AuthenticatedUser authenticatedUser;
    private final AccessAnnotationChecker accessChecker;

    public MainLayout(AuthenticatedUser authenticatedUser, AccessAnnotationChecker accessChecker) {
        this.authenticatedUser = authenticatedUser;
        this.accessChecker = accessChecker;

        setPrimarySection(Section.DRAWER);
        addToNavbar(true, createHeaderContent());
        addToDrawer(createDrawerContent());
    }


    private Component createHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.addClassNames("view-toggle");
        toggle.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        toggle.getElement().setAttribute("aria-label", "Menu toggle");

        viewTitle = new H1();
        viewTitle.addClassNames("view-title");

        Header header = new Header(toggle, viewTitle);
        header.addClassNames("view-header");
        return header;
    }

    private Component createDrawerContent() {
        H2 appName = new H2("Olat 2.0");
        appName.addClassNames("app-name");

        com.vaadin.flow.component.html.Section section = new com.vaadin.flow.component.html.Section(appName,
                createNavigation(), createFooter());
        section.addClassNames("drawer-section");
        return section;
    }

    private Nav createNavigation() {
        Nav nav = new Nav();
        nav.addClassNames("menu-item-container");
        nav.getElement().setAttribute("aria-labelledby", "views");

        // Wrap the links in a list; improves accessibility
        UnorderedList list = new UnorderedList();
        list.addClassNames("navigation-list");
        nav.add(list);

        for (MenuItemInfo menuItem : createMenuItems()) {
            if (accessChecker.hasAccess(menuItem.getView())) {
                list.add(menuItem);
            }

        }
        return nav;
    }

    private MenuItemInfo[] createMenuItems() {
        Optional<OlatUserEntity> userOptional = authenticatedUser.get();

        if (userOptional.isPresent()) {
            OlatUserEntity user = userOptional.get();

            if (user.getRoles().contains(Role.ADMIN)) {
                return new MenuItemInfo[]{
                        new MenuItemInfo("Admin | Person Master", "la la-globe", AdminPersonMaster.class),
                        new MenuItemInfo("Admin | User Master", "la la-globe", AdminUserMaster.class),
                        new MenuItemInfo("Admin | Subject Master", "la la-globe", AdminSubjectMaster.class),
                        new MenuItemInfo("Admin | Group Master", "la la-globe", AdminGroupMaster.class),
                        new MenuItemInfo("Admin | About", "la la-globe", About.class),
                };
            }

            if (user.getRoles().contains(Role.TEACHER)) {
                return new MenuItemInfo[]{
                        new MenuItemInfo("Teacher | Absence Master", "la la-globe", TeacherAbsenceMaster.class),
                        new MenuItemInfo("Teacher | Group Master", "la la-globe", TeacherGroupMaster.class),
                        new MenuItemInfo("Teacher | About", "la la-globe", About.class),
                };
            }

            if (user.getRoles().contains(Role.STUDENT)) {
                return new MenuItemInfo[]{
                        new MenuItemInfo("Student | Absence Master", "la la-globe", StudentAbsenceMaster.class),
                        new MenuItemInfo("Student | Group Master", "la la-globe", StudentGroupMaster.class),
                        new MenuItemInfo("Student | File Explorer", "la la-globe", StudentFileExplorer.class),
                        new MenuItemInfo("Student | About", "la la-globe", About.class),
                };
            }
        }

        return new MenuItemInfo[]{};
    }

    private Footer createFooter() {
        Footer layout = new Footer();
        layout.addClassNames("footer");

        Optional<OlatUserEntity> maybeUser = authenticatedUser.get();
        if (maybeUser.isPresent()) {
            OlatUserEntity user = maybeUser.get();

            Avatar avatar = new Avatar(user.getName(), user.getProfilePictureUrl());
            avatar.addClassNames("me-xs");

            ContextMenu userMenu = new ContextMenu(avatar);
            userMenu.setOpenOnClick(true);
            userMenu.addItem("Logout", e -> authenticatedUser.logout());

            Span name = new Span(user.getName());
            name.addClassNames("font-medium", "text-s", "text-secondary");

            layout.add(avatar, name);
        } else {
            Anchor loginLink = new Anchor("login", "Sign in");
            layout.add(loginLink);
        }

        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}
