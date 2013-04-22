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
package com.googlecode.gflot.client.options.errorbars;

import com.google.gwt.core.client.JavaScriptObject;
import com.googlecode.gflot.client.jsni.JsonObject;


/**
 * @author Nicolas Morel
 */
public class ErrorBarsOptions
    extends JsonObject
{
    public static final String CAP = "-";
    private static final String SHOW_KEY = "show";
    private static final String ASYMMETRIC_KEY = "asymmetric";
    private static final String UPPER_CAP_KEY = "upperCap";
    private static final String LOWER_CAP_KEY = "lowerCap";
    private static final String COLOR_KEY = "color";
    private static final String RADIUS_KEY = "radius";

    /**
     * Creates a {@link ErrorBarsOptions}
     */
    public static final ErrorBarsOptions create()
    {
        return JavaScriptObject.createObject().cast();
    }

    protected ErrorBarsOptions()
    {
    }

    /**
     * Set the visibility
     */
    public final ErrorBarsOptions setShow( boolean show )
    {
        put( SHOW_KEY, show );
        return this;
    }

    /**
     * @return the visibility
     */
    public final Boolean getShow()
    {
        return getBoolean( SHOW_KEY );
    }

    /**
     * Clear the visibility
     */
    public final ErrorBarsOptions clearShow()
    {
        clear( SHOW_KEY );
        return this;
    }

    /**
     * Set the asymmetric option
     */
    public final ErrorBarsOptions setAsymmetric( boolean asymmetric )
    {
        put( ASYMMETRIC_KEY, asymmetric );
        return this;
    }

    /**
     * @return the asymmetric option
     */
    public final Boolean getAsymmetric()
    {
        return getBoolean( ASYMMETRIC_KEY );
    }

    /**
     * Clear the asymmetric option
     */
    public final ErrorBarsOptions clearAsymmetric()
    {
        clear( ASYMMETRIC_KEY );
        return this;
    }

    /**
     * Set the upper cap
     */
    public final ErrorBarsOptions setUpperCap( String upperCap )
    {
        assert CAP.equals( upperCap ) : "Only '-' is authorized. Set a DrawCap if you need something else.";

        put( UPPER_CAP_KEY, upperCap );
        return this;
    }

    /**
     * Set the upper cap function
     */
    public final ErrorBarsOptions setUpperCap( DrawCap drawCapFunction )
    {
        assert null != drawCapFunction : "drawCapFunction can't ne bull";

        setUpperCapNative( drawCapFunction );
        return this;
    }

    private final native void setUpperCapNative( DrawCap drawCapFunction )
    /*-{
        this.upperCap = function (ctx, x, y, radius) {
            return drawCapFunction.@com.googlecode.gflot.client.options.errorbars.DrawCap::draw(Lcom/google/gwt/canvas/dom/client/Context2d;DDD)(ctx, x, y, radius);
        };
    }-*/;

    /**
     * @return the upper cap
     */
    public final String getUpperCap()
    {
        return getString( UPPER_CAP_KEY );
    }

    /**
     * Clear the upper cap
     */
    public final ErrorBarsOptions clearUpperCap()
    {
        clear( UPPER_CAP_KEY );
        return this;
    }

    /**
     * Set the lower cap
     */
    public final ErrorBarsOptions setLowerCap( String lowerCap )
    {
        assert CAP.equals( lowerCap ) : "Only '-' is authorized. Set a DrawCap if you need something else.";

        put( LOWER_CAP_KEY, lowerCap );
        return this;
    }

    /**
     * Set the lower cap function
     */
    public final ErrorBarsOptions setLowerCap( DrawCap drawCapFunction )
    {
        assert null != drawCapFunction : "drawCapFunction can't ne bull";

        setLowerCapNative( drawCapFunction );
        return this;
    }

    private final native void setLowerCapNative( DrawCap drawCapFunction )
    /*-{
        this.lowerCap = function (ctx, x, y, radius) {
            return drawCapFunction.@com.googlecode.gflot.client.options.errorbars.DrawCap::draw(Lcom/google/gwt/canvas/dom/client/Context2d;DDD)(ctx, x, y, radius);
        };
    }-*/;

    /**
     * @return the lower cap
     */
    public final String getLowerCap()
    {
        return getString( LOWER_CAP_KEY );
    }

    /**
     * Clear the lower cap
     */
    public final ErrorBarsOptions clearLowerCap()
    {
        clear( LOWER_CAP_KEY );
        return this;
    }

    /**
     * Set the color
     */
    public final ErrorBarsOptions setColor( String color )
    {
        put( COLOR_KEY, color );
        return this;
    }

    /**
     * @return the color
     */
    public final String getColor()
    {
        return getString( COLOR_KEY );
    }

    /**
     * Clear the color
     */
    public final ErrorBarsOptions clearColor()
    {
        clear( COLOR_KEY );
        return this;
    }

    /**
     * Set the radius
     */
    public final ErrorBarsOptions setRadius( double radius )
    {
        put( RADIUS_KEY, radius );
        return this;
    }

    /**
     * @return the radius
     */
    public final Double getRadius()
    {
        return getDouble( RADIUS_KEY );
    }

    /**
     * Clear the radius
     */
    public final ErrorBarsOptions clearRadius()
    {
        clear( RADIUS_KEY );
        return this;
    }
}
