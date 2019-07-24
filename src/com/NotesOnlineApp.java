package com;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;

public class NotesOnlineApp extends UI {
    @Override
    public void init(VaadinRequest request) {
        VerticalLayout layout = new VerticalLayout();
        setContent(layout);
        layout.addComponent(new Label("Hello, world!"));
    }
}
