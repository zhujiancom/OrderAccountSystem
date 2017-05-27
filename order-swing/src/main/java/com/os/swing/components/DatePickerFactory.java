package com.os.swing.components;

import org.jdesktop.swingx.JXDatePicker;

import java.awt.Dimension;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by jian zhu on 05/27/2017.
 */
public class DatePickerFactory {
    public static JXDatePicker getDefaultDatePicker(){
        JXDatePicker datepicker = new JXDatePicker(new Date());
        Calendar calendar = datepicker.getMonthView().getCalendar();
        calendar.add(Calendar.YEAR,1);
        datepicker.getMonthView().setUpperBound(calendar.getTime());
        datepicker.setPreferredSize(new Dimension(150,25));
        datepicker.setFormats("yyyyMMdd");
        return datepicker;
    }

    public static JXDatePicker getEmptyDatePicker(){
        JXDatePicker datepicker = new JXDatePicker();
        Calendar calendar = datepicker.getMonthView().getCalendar();
        calendar.add(Calendar.YEAR,1);
        datepicker.getMonthView().setUpperBound(calendar.getTime());
        datepicker.setPreferredSize(new Dimension(150,25));
        datepicker.setFormats("yyyyMMdd");
        return datepicker;
    }

    public static JXDatePicker getAssignedDatePicker(Date date){
        JXDatePicker datepicker = new JXDatePicker();
        datepicker.setDate(date);
        Calendar calendar = datepicker.getMonthView().getCalendar();
        calendar.add(Calendar.YEAR,1);
        datepicker.getMonthView().setUpperBound(calendar.getTime());
        datepicker.setPreferredSize(new Dimension(150,25));
        datepicker.setFormats("yyyyMMdd");
        return datepicker;
    }
}
