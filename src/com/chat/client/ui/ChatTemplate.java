package com.chat.client.ui;

import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;

/*
 * Created by asus on 14.04.2014.
 */
public interface ChatTemplate extends SafeHtmlTemplates {

    @Template("<div style=\"color:{1}\">{0}</div>")
    SafeHtml message(String message, String color);
}
