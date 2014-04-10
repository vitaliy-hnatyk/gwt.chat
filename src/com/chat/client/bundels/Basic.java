package com.chat.client.bundels;

import com.google.gwt.resources.client.CssResource;

/**
 * Created by asus on 11.04.2014.
 */
public interface Basic extends CssResource {

    String textArea();

    @ClassName("btn-primary")
    String btnPrimary();

    String btn();

    @ClassName("form-control")
    String formControl();
}
