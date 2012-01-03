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
package ca.nanometrics.gflot.client;

import ca.nanometrics.gflot.client.options.AbstractSeriesOptions;
import ca.nanometrics.gflot.client.util.JSONObjectWrapper;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

/**
 * @author AlexanderDeleon
 */
public class Series
    extends JSONObjectWrapper
{

    public Series()
    {
        super();
    }

    protected Series( JSONObject obj )
    {
        super( obj );
    }

    void setData( SeriesData data )
    {
        put( "data", data );
    }

    /**
     * Returns the data associated to this series.
     * <p>
     * Be careful, the data can be null if the method setVisible(false) has been called on its associated SeriesHandler.
     * In this case, use the method getDate() of the SeriesHandler
     * <p>
     */
    SeriesData getData()
    {
        JSONArray data = getArray( "data" );
        if ( null == data )
        {
            return null;
        }
        else
        {
            return new SeriesData( data );
        }
    }

    /**
     * Set the color. If you don't specify color, the series will get a color from the auto-generated colors.
     */
    public void setColor( String color )
    {
        put( "color", color );
    }

    /**
     * Set which of auto-generated colors to select, e.g. 0 will get color no. 0, etc. It is mostly useful if you let
     * the user add and remove series, in which case you can hard-code the color index to prevent the colors from
     * jumping around between the series.
     */
    public void setColor( int color )
    {
        put( "color", color );
    }

    /**
     * @return the color
     */
    public String getColor()
    {
        JSONValue value = get( "color" );
        if ( value == null )
        {
            return null;
        }
        JSONString str = value.isString();
        if ( str != null )
        {
            return str.stringValue();
        }
        JSONNumber number = value.isNumber();
        if ( number != null )
        {
            return new Double( number.doubleValue() ).toString();
        }
        return null;
    }

    /**
     * Set the label. The label is used for the legend, if you don't specify one, the series will not show up in the
     * legend.
     */
    public void setLabel( String label )
    {
        put( "label", label );
    }

    /**
     * @return the label
     */
    public String getLabel()
    {
        return getString( "label" );
    }

    /**
     * Set the size of shadows in pixels. Set it to 0 to remove shadows.
     */
    public void setShadowSize( int shadowSize )
    {
        put( "shadowSize", shadowSize );
    }

    public Integer getShadowSize()
    {
        return getInteger( "shadowSize" );
    }

    public void setSeriesOptions( SeriesType type, AbstractSeriesOptions<?> options )
    {
        put( type.toString(), options );
    }

    /**
     * Set if this series will listen for click events.
     */
    public void setClickable( boolean clickable )
    {
        put( "clickable", clickable );
    }

    /**
     * Set if this series will listen for mouse move events.
     */
    public void setHoverable( boolean hoverable )
    {
        put( "hoverable", hoverable );
    }

}
