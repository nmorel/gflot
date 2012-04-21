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
 * This class gives the properties of pan
 *
 * @author Ana Rita Loureiro
 */
public class PanOptions
    extends JSONObjectWrapper
{

    public static final String INTERACTIVE = "interactive";

    public static final String CURSOR = "cursor";

    public static final String FRAME_RATE = "frameRate";

    public PanOptions()
    {
    }

    PanOptions( JSONObject jsonObj )
    {
        super( jsonObj );
    }

    /**
     * Defines the built-in drag/click behaviour. If you enable interactive then you'll have a basic plot that supports
     * moving around.
     */
    public PanOptions setInteractive( boolean value )
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
     * Sets a standard CSS mouse cursor string used for visual feedback to the user when dragging.
     */
    public PanOptions setCursor( String value )
    {
        put( CURSOR, value );
        return this;
    }

    /**
     * @return a standard CSS mouse cursor string used for visual feedback to the user when dragging.
     */
    public String getCursor()
    {
        return getString( CURSOR );
    }

    /**
     * Sets the maximum frame rate, the maximum number of times per second the plot will update itself while the user is
     * panning around on it (set to null to disable intermediate pans, the plot will then not update until the mouse
     * button is released).
     */
    public PanOptions setFrameRate( int value )
    {
        put( FRAME_RATE, value );
        return this;
    }

    /**
     * @return the maximum frame rate, the maximum number of times per second the plot will update itself while the user
     * is panning around on it (set to null to disable intermediate pans, the plot will then not update until the mouse
     * button is released).
     */
    public Integer getFrameRate()
    {
        return getInteger( FRAME_RATE );
    }
}
