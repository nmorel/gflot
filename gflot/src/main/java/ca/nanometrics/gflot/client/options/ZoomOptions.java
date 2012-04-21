/*
 * Copyright (c) 2012 Ciengis, SA
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
 * This class gives the properties of zoom
 *
 * @author Ana Rita Loureiro
 */
public class ZoomOptions
    extends JSONObjectWrapper
{

    public static final String TRIGGER_SINGLE_CLICK = "click";

    public static final String TRIGGER_DOUBLE_CLICK = "dblclick";

    private static final String INTERACTIVE = "interactive";

    private static final String TRIGGER = "trigger";

    private static final String AMOUNT = "amount";

    public ZoomOptions()
    {
    }

    ZoomOptions( JSONObject jsonObj )
    {
        super( jsonObj );
    }

    /**
     * Defines the built-in drag/click behaviour. If you enable interactive then you'll have a basic plot that supports
     * zooming.
     */
    public ZoomOptions setInteractive( boolean value )
    {
        put( INTERACTIVE, value );
        return this;
    }

    /**
     * @return whether or not there is a built-in drag/click behaviour.
     */
    public boolean isInteractive()
    {
        Boolean interactive = getBoolean( INTERACTIVE );
        if ( interactive == null )
        {
            return false;
        }

        return interactive;
    }

    /**
     * Sets the number of mouse button clicks used to zoom the current viewport. Use TRIGGER_DOUBLE_CLICK for double
     * click or TRIGGER_SINGLE_CLICK for single click.
     */
    public ZoomOptions setTrigger( String value )
    {
        put( TRIGGER, value );
        return this;
    }

    /**
     * @return a string identifying the number of mouse button clicks used to zoom the current viewport. "dblclick" for
     * double click or "click" for single click.
     */
    public String getTrigger()
    {
        return getString( TRIGGER );
    }

    /**
     * Sets the default amount to zoom the viewport relative to the current viewport. 1 is 100% (i.e. no change), 1.5 is
     * 150% (zoom in), 0.7 is 70% (zoom out).
     */
    public ZoomOptions setAmount( Double value )
    {
        put( AMOUNT, value );
        return this;
    }

    /**
     * @return the default amount to zoom the viewport relative to the current viewport. 1 is 100% (i.e. no change), 1.5
     * is 150% (zoom in), 0.7 is 70% (zoom out).
     */
    public Double getAmount()
    {
        return getDouble( AMOUNT );
    }
}
