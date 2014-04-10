package com.chat.client.bundels;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;

/**
 * Created by asus on 11.04.2014.
 */
public interface Resources  extends ClientBundle{
    Resources IMPL = GWT.create(Resources.class);

    @Source("basic.css")
    Basic basic();
}
