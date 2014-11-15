/*
 * Copyright (c) 2012 Nicolas Morel
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */
package com.googlecode.gflot.client.options;

import java.util.Collection;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayMixed;
import com.google.gwt.core.client.JsArrayString;

/**
 * <p>
 * Flot always displays timestamps according to UTC. To get UTC time, you can use in GWT the function
 * java.util.Date.UTC(). Look at Flot API for more info about Time series.
 * </p>
 *
 * @author AlexanderDeleon
 */
public class TimeSeriesAxisOptions
    extends AbstractAxisOptions<TimeSeriesAxisOptions>
{
    public enum TickTimeUnit
    {
        SECOND( "second" ), MINUTE( "minute" ), HOUR( "hour" ), DAY( "day" ), MONTH( "month" ), YEAR( "year" );

        static TickTimeUnit findByFlotValue( String flotValue )
        {
            if ( null != flotValue && !"".equals( flotValue ) )
            {
                for ( TickTimeUnit mode : values() )
                {
                    if ( mode.getFlotValue().equals( flotValue ) )
                    {
                        return mode;
                    }
                }
            }
            return null;
        }

        private String flotValue;

        TickTimeUnit( String flotValue )
        {
            this.flotValue = flotValue;
        }

        String getFlotValue()
        {
            return flotValue;
        }
    }

    public static class TickSize
        extends JsArrayMixed
    {
        /**
         * Creates a {@link TickSize}
         */
        public static final TickSize of( double size, TickTimeUnit unit )
        {
            TickSize tickSize = JavaScriptObject.createObject().cast();
            tickSize.set( 0, size );
            tickSize.set( 1, unit.getFlotValue() );
            return tickSize;
        }

        protected TickSize()
        {
        }

        public final double getSize()
        {
            return getNumber( 0 );
        }

        public final TickTimeUnit getUnit()
        {
            return TickTimeUnit.findByFlotValue( getString( 1 ) );
        }
    }

    public static final String TIME_ZONE_BROWSER_KEY = "browser";
    private static final String TIME_ZONE_KEY = "timezone";
    private static final String TIME_FORMAT_KEY = "timeformat";
    private static final String DAY_NAMES_KEY = "dayNames";
    private static final String MONTH_NAMES_KEY = "monthNames";
    private static final String TWELVE_HOUR_CLOCK_KEY = "twelveHourClock";

    /**
     * Creates a {@link TimeSeriesAxisOptions}
     */
    public static final TimeSeriesAxisOptions create()
    {
        TimeSeriesAxisOptions axis = JavaScriptObject.createObject().cast();
        axis.put( MODE_KEY, TIME_MODE_KEY );
        return axis;
    }

    protected TimeSeriesAxisOptions()
    {
    }

    /**
     * @return the time zone
     */
    public final String getTimeZone()
    {
        return getString( TIME_ZONE_KEY );
    }

    /**
     * Set how the dates are displayed. If null, the dates are displayed as UTC. If
     * "browser", the dates are displayed in the time zone of the user's browser. You can also give a time zone (e.g. "America/New_York") if you include the script <a href="https://github.com/mde/timezone-js">timezone-js</a>
     */
    public final TimeSeriesAxisOptions setTimeZone( String timeZone )
    {
        put( TIME_ZONE_KEY, timeZone );
        return this;
    }

    /**
     * Clear the time zone
     */
    public final TimeSeriesAxisOptions clearTimeZone()
    {
        clear( TIME_ZONE_KEY );
        return this;
    }

    /**
     * @return the format of the tick label
     */
    public final String getTimeFormat()
    {
        return getString( TIME_FORMAT_KEY );
    }

    /**
     * Set the format of the tick label.
     * <p></p>
     * The following specifiers are supported :
     * <ul>
     * <li>%a: weekday name (customizable)</li>
     * <li>%b: month name (customizable)</li>
     * <li>%d: day of month, zero-padded (01-31)</li>
     * <li>%e: day of month, space-padded ( 1-31)</li>
     * <li>%H: hours, 24-hour time, zero-padded (00-23)</li>
     * <li>%I: hours, 12-hour time, zero-padded (01-12)</li>
     * <li>%m: month, zero-padded (01-12)</li>
     * <li>%M: minutes, zero-padded (00-59)</li>
     * <li>%q: quarter (1-4)</li>
     * <li>%S: seconds, zero-padded (00-59)</li>
     * <li>%y: year (two digits)</li>
     * <li>%Y: year (four digits)</li>
     * <li>%p: am/pm</li>
     * <li>%P: AM/PM (uppercase version of %p)</li>
     * <li>%w: weekday as number (0-6, 0 being Sunday)</li>
     * </ul>
     * <p>
     * The timeformat "%y/%m/%d" will results in tick labels like "2000/12/24".
     * </p>
     */
    public final TimeSeriesAxisOptions setTimeFormat( String timeFormat )
    {
        put( TIME_FORMAT_KEY, timeFormat );
        return this;
    }

    /**
     * Clear the format of the tick label
     */
    public final TimeSeriesAxisOptions clearTimeFormat()
    {
        clear( TIME_FORMAT_KEY );
        return this;
    }

    /**
     * @return the label used for month
     */
    public final JsArrayString getMonthNames()
    {
        return getStringArray( MONTH_NAMES_KEY );
    }

    /**
     * Set the label used for month.
     */
    public final TimeSeriesAxisOptions setMonthNames( String... monthNames )
    {
        assert null != monthNames : "monthNames can't be null";
        assert monthNames.length == 12 : "monthNames must have all 12 month names";

        JsArrayString array = JavaScriptObject.createArray().cast();
        for ( String monthName : monthNames )
        {
            array.push( monthName );
        }
        return setMonthNames( array );
    }

    /**
     * Set the label used for month.
     */
    public final TimeSeriesAxisOptions setMonthNames( Collection<String> monthNames )
    {
        assert null != monthNames : "monthNames can't be null";
        assert monthNames.size() == 12 : "monthNames must have all 12 month names";

        JsArrayString array = JavaScriptObject.createArray().cast();
        for ( String monthName : monthNames )
        {
            array.push( monthName );
        }
        return setMonthNames( array );
    }

    /**
     * Set the label used for month.
     */
    public final TimeSeriesAxisOptions setMonthNames( JsArrayString monthNames )
    {
        assert null != monthNames : "monthNames can't be null";
        assert monthNames.length() == 12 : "monthNames must have all 12 month names";

        put( MONTH_NAMES_KEY, monthNames );
        return this;
    }

    /**
     * Clear the labels used for month
     */
    public final TimeSeriesAxisOptions clearMonthNames()
    {
        clear( MONTH_NAMES_KEY );
        return this;
    }

    /**
     * @return the label used for days
     */
    public final JsArrayString getDayNames()
    {
        return getStringArray( DAY_NAMES_KEY );
    }

    /**
     * Set the label used for days.
     */
    public final TimeSeriesAxisOptions setDayNames( String... dayNames )
    {
        assert null != dayNames : "dayNames can't be null";
        assert dayNames.length == 7 : "dayNames must have all 7 days names";

        JsArrayString array = JavaScriptObject.createArray().cast();
        for ( String dayName : dayNames )
        {
            array.push( dayName );
        }
        return setDayNames( array );
    }

    /**
     * Set the label used for days.
     */
    public final TimeSeriesAxisOptions setDayNames( Collection<String> dayNames )
    {
        assert null != dayNames : "dayNames can't be null";
        assert dayNames.size() == 7 : "dayNames must have all 7 days names";

        JsArrayString array = JavaScriptObject.createArray().cast();
        for ( String dayName : dayNames )
        {
            array.push( dayName );
        }
        return setDayNames( array );
    }

    /**
     * Set the label used for days.
     */
    public final TimeSeriesAxisOptions setDayNames( JsArrayString dayNames )
    {
        assert null != dayNames : "dayNames can't be null";
        assert dayNames.length() == 7 : "dayNames must have all 7 days names";

        put( DAY_NAMES_KEY, dayNames );
        return this;
    }

    /**
     * Clear the labels used for days
     */
    public final TimeSeriesAxisOptions clearDayNames()
    {
        clear( DAY_NAMES_KEY );
        return this;
    }

    /**
     * @return true if the autogenerated timestamps use 12 hour AM/PM timestamps instead of 24 hour
     */
    public final Boolean getTwelveHourClock()
    {
        return getBoolean( TWELVE_HOUR_CLOCK_KEY );
    }

    /**
     * Set if the autogenerated timestamps will use 12 hour AM/PM timestamps instead of 24 hour.
     */
    public final TimeSeriesAxisOptions setTwelveHourClock( boolean twelveHourClock )
    {
        put( TWELVE_HOUR_CLOCK_KEY, twelveHourClock );
        return this;
    }

    /**
     * Clear the twelve hour clock option
     */
    public final TimeSeriesAxisOptions clearTwelveHourClock()
    {
        clear( TWELVE_HOUR_CLOCK_KEY );
        return this;
    }

    /**
     * @return the tick interval size
     */
    public final TickSize getTickSize()
    {
        return getJsObject( TICK_SIZE_KEY );
    }

    /**
     * Set the tick interval size. If you set it to 2, you'll get ticks at 2, 4, 6, etc.
     */
    public final TimeSeriesAxisOptions setTickSize( double tickSize, TickTimeUnit unit )
    {
        assert null != unit : "unit can't be null";

        put( TICK_SIZE_KEY, TickSize.of( tickSize, unit ) );
        return this;
    }

    /**
     * @return the minimum tick size
     */
    public final TickSize getMinTickSize()
    {
        return getJsObject( MIN_TICK_SIZE_KEY );
    }

    /**
     * Set that you don't want ticks at a size less than the specified one
     */
    public final TimeSeriesAxisOptions setMinTickSize( double minTickSize, TickTimeUnit unit )
    {
        assert null != unit : "unit can't be null";

        put( MIN_TICK_SIZE_KEY, TickSize.of( minTickSize, unit ) );
        return this;
    }

}
