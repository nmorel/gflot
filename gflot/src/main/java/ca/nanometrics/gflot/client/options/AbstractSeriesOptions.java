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

import ca.nanometrics.gflot.client.util.JSONHelper;
import ca.nanometrics.gflot.client.util.JSONObjectWrapper;
import ca.nanometrics.gflot.client.util.JSONWrapper;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;

/**
 * @author AlexanderDeleon
 */
@SuppressWarnings( "unchecked" )
public abstract class AbstractSeriesOptions<T extends AbstractSeriesOptions<?>>
    extends JSONObjectWrapper
{

    /**
     * Set the visibility of the series. By default, the series is shown.
     */
    public T setShow( boolean show )
    {
        put( "show", show );
        return (T) this;
    }

    /**
     * Set the thickness of the line or outline in pixels. You can set it to 0 to prevent a line or outline from being
     * drawn; this will also hide the shadow.
     */
    public T setLineWidth( double lineWidth )
    {
        put( "lineWidth", new Double( lineWidth ) );
        return (T) this;
    }

    /**
     * Set if the shape should be filled. For lines, this produces area graphs. You can use setFillColor "fillColor" to
     * specify the color of the fill.
     */
    public T setFill( boolean fill )
    {
        put( "fill", fill );
        return (T) this;
    }

    /**
     * Set if the shape should be filled. For lines, this produces area graphs. You can use setFillColor to specify the
     * color of the fill. You can adjust the opacity of the fill by setting fill to a number between 0 (fully
     * transparent) and 1 (fully opaque).
     */
    public T setFill( double opacity )
    {
        assert opacity >= 0 && opacity <= 1 : "opacity range from 0.0 to 1.0";

        put( "fill", opacity );
        return (T) this;
    }

    /**
     * Set the color to fill. If "fillColor" evaluates to false (default for everything except points which are filled
     * with white), the fill color is auto-set to the color of the data series.
     */
    public T setFillColor( String cssColor )
    {
        put( "fillColor", cssColor );
        return (T) this;
    }

    /**
     * Set the color to fill. If "fillColor" evaluates to false (default for everything except points which are filled
     * with white), the fill color is auto-set to the color of the data series.
     */
    public T setFillColor( Double fromOpacity, Double fromBrightness, Double toOpacity, Double toBrightness )
    {
        assert ( null != fromOpacity || null != fromBrightness ) && ( null != toOpacity || null != toBrightness ) : "at least of the parameters must not be null";
        assert null == fromOpacity || fromOpacity >= 0 && fromOpacity <= 1 : "fromOpacity range from 0.0 to 1.0";
        assert null == fromBrightness || fromBrightness >= 0 && fromBrightness <= 1 : "fromBrightness range from 0.0 to 1.0";
        assert null == toOpacity || toOpacity >= 0 && toOpacity <= 1 : "toOpacity range from 0.0 to 1.0";
        assert null == toBrightness || toBrightness >= 0 && toBrightness <= 1 : "toBrightness range from 0.0 to 1.0";

        put(
            "fillColor",
            JSONHelper.wrapArrayIntoObject( "colors", new JSONWrapper[] { buildOpacityBrightnessObject( fromOpacity, fromBrightness ),
                buildOpacityBrightnessObject( toOpacity, toBrightness ) } ) );

        return (T) this;
    }

    private JSONObjectWrapper buildOpacityBrightnessObject( Double opacity, Double brightness )
    {
        JSONObject obj = new JSONObject();
        if ( null != opacity )
        {
            obj.put( "opacity", new JSONNumber( opacity ) );
        }
        if ( null != brightness )
        {
            obj.put( "brightness", new JSONNumber( brightness ) );
        }
        return JSONHelper.wrapObject( obj );
    }
}
