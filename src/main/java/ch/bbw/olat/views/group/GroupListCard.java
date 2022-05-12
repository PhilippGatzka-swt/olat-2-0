package ch.bbw.olat.views.group;

import ch.bbw.olat.data.entity.OlatGroupEntity;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;

public class GroupListCard extends ListItem {

    public GroupListCard(OlatGroupEntity group) {
        String groupName = group.getName();
        String groupTeacherName = group.getTeacher().getName();

        addClassNames("bg-contrast-5", "flex", "flex-col", "items-start", "p-m", "rounded-l");

        Div div = new Div();
        div.addClassNames("bg-contrast", "flex items-center", "justify-center", "mb-m", "overflow-hidden",
                "rounded-m w-full");
        div.setHeight("160px");

        Span header = new Span();
        header.addClassNames("text-xl", "font-semibold");
        header.setText(groupName);

        Span subtitle = new Span();
        subtitle.addClassNames("text-s", "text-secondary");
        subtitle.setText(groupTeacherName);

        Paragraph description = new Paragraph(
                "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut.");
        description.addClassName("my-m");

        Button button = new Button("Get Started");
        button.addClickListener(buttonClickEvent -> {
            UI.getCurrent().navigate("group/" + groupName);
        });
        //button.getElement().setAttribute("theme", "badge");

        add(div, header, subtitle, description, button);

    }
}
