package com.chat.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

/**
 * Created by asus on 10.04.2014.
 */
public class ChatWidget extends Composite {
    interface ChatWidgetUiBinder extends UiBinder<HTMLPanel, ChatWidget> {
    }

    private static ChatWidgetUiBinder uiBinder = GWT.create(ChatWidgetUiBinder.class);

    public ChatWidget() {
        initWidget(uiBinder.createAndBindUi(this));
    }
}