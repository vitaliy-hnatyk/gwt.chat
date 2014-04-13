package com.chat.client.utils;

import com.google.gwt.i18n.client.DateTimeFormat;

import java.util.Date;

/*
 * Created by asus on 14.04.2014.
 */
public class DateCreator {

    private static final String DATE_FIELD_FORMAT = "hh:mm";

    public static String now() {
        Date date = new Date();
        return DateTimeFormat.getFormat(DATE_FIELD_FORMAT).format(date);
    }
}
