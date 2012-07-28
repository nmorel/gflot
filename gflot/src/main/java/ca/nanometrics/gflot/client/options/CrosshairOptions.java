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

import ca.nanometrics.gflot.client.util.JSONObjectWrapper;

import com.google.gwt.json.client.JSONObject;

/**
 * @author Nicolas Morel
 */
public class CrosshairOptions
    extends JSONObjectWrapper
{
    public enum Mode
    {
        X( "x" ), Y( "y" ), XY( "xy" );

        private final String flotValue;

        private Mode( String flotValue )
        {
            this.flotValue = flotValue;
        }

        public String getFlotValue()
        {
            return flotValue;
        }

        static Mode findByFlotValue( String flotValue )
        {
            if ( null != flotValue && !"".equals( flotValue ) )
            {
                for ( Mode mode : values() )
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

    private static final String MODE_KEY = "mode";

    private static final String COLOR_KEY = "color";

    private static final String LINE_WIDTH_KEY = "lineWidth";

    public CrosshairOptions()
    {
        super();
    }

    CrosshairOptions( JSONObject jsonObj )
    {
        super( jsonObj );
    }

    /**
     * Set the mode to one of "x", "y" or "xy". The "x" mode enables a vertical crosshair that lets you trace the values
     * on the x axis, "y" enables a horizontal crosshair and "xy" enables them both.
     */
    public CrosshairOptions setMode( Mode mode )
    {
        assert null != mode : "mode can't be null";

        put( MODE_KEY, mode.getFlotValue() );
        return this;
    }

    /**
     * @return the mode
     */
    public Mode getMode()
    {
        return Mode.findByFlotValue( getString( MODE_KEY ) );
    }

    /**
     * Clear the mode
     */
    public CrosshairOptions clearMode()
    {
        clear( MODE_KEY );
        return this;
    }

    /**
     * Set the color of the crosshair. Default is "rgba(170, 0, 0, 0.80)"
     */
    public CrosshairOptions setColor( String color )
    {
        put( COLOR_KEY, color );
        return this;
    }

    /**
     * @return the color of the crosshair
     */
    public String getColor()
    {
        return getString( COLOR_KEY );
    }

    /**
     * Clear the color
     */
    public CrosshairOptions clearColor()
    {
        clear( COLOR_KEY );
        return this;
    }

    /**
     * Set the width of the drawn lines. Default is 1.
     */
    public CrosshairOptions setLineWidth( double width )
    {
        put( LINE_WIDTH_KEY, width );
        return this;
    }

    /**
     * @return the width of the drawn lines
     */
    public Double getLineWidth()
    {
        return getDouble( LINE_WIDTH_KEY );
    }

    /**
     * Clear the width of the drawn lines
     */
    public CrosshairOptions clearLineWidth()
    {
        clear( LINE_WIDTH_KEY );
        return this;
    }
}
