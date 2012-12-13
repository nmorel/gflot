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

import com.google.gwt.core.client.JavaScriptObject;

import ca.nanometrics.gflot.client.jsni.JsonObject;

/**
 * This class gives the properties of zoom
 * 
 * @author Ana Rita Loureiro
 */
public final class ZoomOptions
    extends JsonObject
{
    public enum ZoomTrigger
    {
        SINGLE_CLICK( "click" ), DOUBLE_CLICK( "dblclick" );

        private String flotValue;

        ZoomTrigger( String flotValue )
        {
            this.flotValue = flotValue;
        }

        String getFlotValue()
        {
            return flotValue;
        }

        static ZoomTrigger findByFlotValue( String flotValue )
        {
            if ( null != flotValue && !"".equals( flotValue ) )
            {
                for ( ZoomTrigger mode : values() )
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
    
    /**
     * Creates a {@link ZoomOptions}
     */
    public static final ZoomOptions create()
    {
        return JavaScriptObject.createObject().cast();
    }

    private static final String INTERACTIVE_KEY = "interactive";
    private static final String TRIGGER_KEY = "trigger";
    private static final String AMOUNT_KEY = "amount";

    protected ZoomOptions()
    {
    }

    /**
     * Defines the built-in drag/click behaviour. If you enable interactive then you'll have a basic plot that supports
     * zooming.
     */
    public final ZoomOptions setInteractive( boolean value )
    {
        put( INTERACTIVE_KEY, value );
        return this;
    }

    /**
     * @return whether or not there is a built-in drag/click behaviour.
     */
    public final boolean isInteractive()
    {
        Boolean interactive = getBoolean( INTERACTIVE_KEY );
        if ( interactive == null )
        {
            return false;
        }

        return interactive;
    }

    /**
     * Clear the interactive option
     */
    public final ZoomOptions clearInteractive()
    {
        clear( INTERACTIVE_KEY );
        return this;
    }

    /**
     * Sets the number of mouse button clicks used to zoom the current viewport. Use TRIGGER_DOUBLE_CLICK for double
     * click or TRIGGER_SINGLE_CLICK for single click.
     */
    public final ZoomOptions setTrigger( ZoomTrigger trigger )
    {
        assert null != trigger : "trigger can't be null";

        put( TRIGGER_KEY, trigger.getFlotValue() );
        return this;
    }

    /**
     * @return a string identifying the number of mouse button clicks used to zoom the current viewport. "dblclick" for
     * double click or "click" for single click.
     */
    public final ZoomTrigger getTrigger()
    {
        return ZoomTrigger.findByFlotValue( getString( TRIGGER_KEY ) );
    }

    /**
     * Clear the trigger option
     */
    public final ZoomOptions clearTrigger()
    {
        clear( TRIGGER_KEY );
        return this;
    }

    /**
     * Sets the default amount to zoom the viewport relative to the current viewport. 1 is 100% (i.e. no change), 1.5 is
     * 150% (zoom in), 0.7 is 70% (zoom out).
     */
    public final ZoomOptions setAmount( Double value )
    {
        put( AMOUNT_KEY, value );
        return this;
    }

    /**
     * @return the default amount to zoom the viewport relative to the current viewport. 1 is 100% (i.e. no change), 1.5
     * is 150% (zoom in), 0.7 is 70% (zoom out).
     */
    public final Double getAmount()
    {
        return getDouble( AMOUNT_KEY );
    }

    /**
     * Clear the amount
     */
    public final ZoomOptions clearAmount()
    {
        clear( AMOUNT_KEY );
        return this;
    }
}
