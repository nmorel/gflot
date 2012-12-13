/*
 * Copyright (c) 2008 Nanometrics Inc.
 *
 *	Permission is hereby granted, free of charge, to any person obtaining a copy
 *	of this software and associated documentation files (the "Software"), to deal
 *	in the Software without restriction, including without limitation the rights
 *	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *	copies of the Software, and to permit persons to whom the Software is
 *	furnished to do so, subject to the following conditions:
 *
 *	The above copyright notice and this permission notice shall be included in
 *	all copies or substantial portions of the Software.
 *
 *	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *	THE SOFTWARE.
 */
package ca.nanometrics.gflot.client.options;

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

        private String flotValue;

        TickTimeUnit( String flotValue )
        {
            this.flotValue = flotValue;
        }

        String getFlotValue()
        {
            return flotValue;
        }

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

    /**
     * Creates a {@link TimeSeriesAxisOptions}
     */
    public static final TimeSeriesAxisOptions create()
    {
        TimeSeriesAxisOptions axis = JavaScriptObject.createObject().cast();
        axis.put( MODE_KEY, TIME_MODE_KEY );
        return axis;
    }

    private static final String TIME_FORMAT_KEY = "timeformat";
    private static final String MONTH_NAMES_KEY = "monthNames";
    private static final String TWELVE_HOUR_CLOCK_KEY = "twelveHourClock";

    protected TimeSeriesAxisOptions()
    {
    }

    /**
     * Set the format of the tick label.
     * <p>
     * The following specifiers are supported :
     * <ul>
     * <li>%h: hours</li>
     * <li>%H: hours (left-padded with a zero)</li>
     * <li>%M: minutes (left-padded with a zero)</li>
     * <li>%S: seconds (left-padded with a zero)</li>
     * <li>%d: day of month (1-31), use %0d for zero-padding</li>
     * <li>%m: month (1-12), use %0m for zero-padding</li>
     * <li>%y: year (four digits)</li>
     * <li>%b: month name (customizable)</li>
     * <li>%p: am/pm, additionally switches %h/%H to 12 hour instead of 24</li>
     * <li>%P: AM/PM (uppercase version of %p)</li>
     * </ul>
     * </p>
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
     * @return the format of the tick label
     */
    public final String getTimeFormat()
    {
        return getString( TIME_FORMAT_KEY );
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
     * Set the label used for month.
     */
    public final TimeSeriesAxisOptions setMonthNames( JsArrayString monthNames )
    {
        assert null != monthNames : "monthNames can't bu null";
        assert monthNames.length() == 12 : "monthNames must have all 12 month names";

        put( MONTH_NAMES_KEY, monthNames );
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
     * Clear the labels used for month
     */
    public final TimeSeriesAxisOptions clearMonthNames()
    {
        clear( MONTH_NAMES_KEY );
        return this;
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
     * @return true if the autogenerated timestamps use 12 hour AM/PM timestamps instead of 24 hour
     */
    public final Boolean getTwelveHourClock()
    {
        return getBoolean( TWELVE_HOUR_CLOCK_KEY );
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
     * Set the tick interval size. If you set it to 2, you'll get ticks at 2, 4, 6, etc.
     */
    public final TimeSeriesAxisOptions setTickSize( double tickSize, TickTimeUnit unit )
    {
        assert null != unit : "unit can't be null";

        put( TICK_SIZE_KEY, TickSize.of( tickSize, unit ) );
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
     * Set that you don't want ticks at a size less than the specified one
     */
    public final TimeSeriesAxisOptions setMinTickSize( double minTickSize, TickTimeUnit unit )
    {
        assert null != unit : "unit can't be null";

        put( MIN_TICK_SIZE_KEY, TickSize.of( minTickSize, unit ) );
        return this;
    }

    /**
     * @return the minimum tick size
     */
    public final TickSize getMinTickSize()
    {
        return getJsObject( MIN_TICK_SIZE_KEY );
    }

}
