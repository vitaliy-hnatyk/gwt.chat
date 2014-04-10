package com.chat.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;

/**
 * Created by asus on 10.04.2014.
 */
public class LoginWidget extends Composite {

    interface LoginWidgetUiBinder extends UiBinder<Widget, LoginWidget> {
    }

    private static LoginWidgetUiBinder uiBinder = GWT.create(LoginWidgetUiBinder.class);

    @UiField
    DialogBox dialog;

    @UiField
    Button button;

    @UiField
    TextBox textBox;

    public LoginWidget() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    public String getText(){
        return textBox.getText();
    }

    public Button getButton(){
        return button;
    }

    public void show(){
        dialog.center();
        dialog.show();
    }

    public void hide(){
        dialog.hide();
    }
}