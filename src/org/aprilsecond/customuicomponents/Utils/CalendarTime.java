package org.aprilsecond.customuicomponents.Utils;

import java.text.DecimalFormat;
import java.util.Calendar;

/**
 * Stores methods to manipulate the time for the 
 * components
 * @author Reagan Mbitiru <reaganmbitiru@gmail.com>
 */
public class CalendarTime {
         
    /**
     * stores the days of the week
     */
    public static final String[] DAYS_OF_WEEK = {"Sunday", "Monday", 
        "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"} ;
    
    /**
     * stores the months for the year
     */
    public final static String[] MONTHS_OF_THE_YEAR = { "January" , "February", "March",
        "April", "May", "June", "July", "August", "September", "October", 
        "November", "December" } ;
    
    /**
     * gets the current day in the format
     * {Day} e.g. Saturday 
     * @return Day of the week
     */
    public static String getCurrentDay() {
        int currDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) ;
        return DAYS_OF_WEEK[currDay - 1] ;
    }
    
    /**
     * returns the index (from 0) for the current day
     * e.g. {0} for Sunday
     * @return Day index
     */
    public static int getCurrentDayIndex() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH) ;
    }
    
    /**
     * returns the current time in 24hrs format
     * e.g. 13:04 for 1:04 PM
     * @return time in 24 hrs format
     */
    public static String getCurrentTime24hrs() {
        String currTime = "" ;
        
        // get the 24 hrs time 
        currTime += Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
                + ":"
                + Calendar.getInstance().get(Calendar.MINUTE) ;
        
        return currTime ;
    }
    
    /**
     * returns the time in 12hrs format
     * e.g. 1:04 PM
     * @return time in 12hrs format
     */
    public static String getCurrentTime12hrs() {
        String currTime = "" ;
        
        // get the time in 12 hrs format
        currTime += Calendar.getInstance().get(Calendar.HOUR)
                + ":"
                + Calendar.getInstance().get(Calendar.MINUTE)
                + " " ;
        
        // add the AM or PM suffix
        currTime += (Calendar.getInstance().get(Calendar.AM_PM)
                == Calendar.AM) ? "AM" : "PM" ;
        
        return currTime ;
    }
    
    /**
     * returns the date in the format
     * {Month String} {date}, {year} e.g. March 1, 2012
     * @return  date (without the month)
     */
    public static String getCurrentDate() {
        return MONTHS_OF_THE_YEAR[Calendar.getInstance().get(Calendar.MONTH)]
                + " "
                + Calendar.getInstance().get(Calendar.DATE)
                + ", "
                + Calendar.getInstance().get(Calendar.YEAR) ;
    }
    
    public static String convertTo2Digits(int value) {
        DecimalFormat format = new DecimalFormat("00") ;
        return format.format(value) ;
    }    
     
    public static String displayPrettyStartAndStopTime(Calendar startTime, Calendar stopTime) {
        
        String startAndStopTime = startTime.get(Calendar.HOUR)
                    + ":"
                    + CalendarTime.convertTo2Digits(startTime.get(Calendar.MINUTE))
                    + isAMorPM(startTime)
                    + " - " 
                    + stopTime.get(Calendar.HOUR)
                    + ":"
                    + CalendarTime.convertTo2Digits(stopTime.get(Calendar.MINUTE))
                    + isAMorPM(stopTime)     ;
        
        return startAndStopTime ;
    }
    
    public static String isAMorPM(Calendar time) {
        return (time.get(Calendar.AM_PM) == 0) ? "am" : "pm" ;
    }
}
