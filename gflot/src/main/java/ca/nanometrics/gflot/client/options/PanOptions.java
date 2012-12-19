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

import ca.nanometrics.gflot.client.jsni.JsonObject;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * This class gives the properties of pan
 * 
 * @author Ana Rita Loureiro
 */
public class PanOptions
    extends JsonObject
{
    /**
     * Creates a {@link PanOptions}
     */
    public static final PanOptions create()
    {
        return JavaScriptObject.createObject().cast();
    }

    private static final String INTERACTIVE_KEY = "interactive";
    private static final String CURSOR_KEY = "cursor";
    private static final String FRAME_RATE_KEY = "frameRate";

    protected PanOptions()
    {
    }

    /**
     * Defines the built-in drag/click behaviour. If you enable interactive then you'll have a basic plot that supports
     * moving around.
     */
    public final PanOptions setInteractive( boolean value )
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
    public final PanOptions clearInteractive()
    {
        clear( INTERACTIVE_KEY );
        return this;
    }

    /**
     * Sets a standard CSS mouse cursor string used for visual feedback to the user when dragging.
     */
    public final PanOptions setCursor( String value )
    {
        put( CURSOR_KEY, value );
        return this;
    }

    /**
     * @return a standard CSS mouse cursor string used for visual feedback to the user when dragging.
     */
    public final String getCursor()
    {
        return getString( CURSOR_KEY );
    }

    /**
     * Clear the cursor option
     */
    public final PanOptions clearCursor()
    {
        clear( CURSOR_KEY );
        return this;
    }

    /**
     * Sets the maximum frame rate, the maximum number of times per second the plot will update itself while the user is
     * panning around on it (set to null to disable intermediate pans, the plot will then not update until the mouse
     * button is released).
     */
    public final PanOptions setFrameRate( int value )
    {
        put( FRAME_RATE_KEY, value );
        return this;
    }

    /**
     * @return the maximum frame rate, the maximum number of times per second the plot will update itself while the user
     * is panning around on it (set to null to disable intermediate pans, the plot will then not update until the mouse
     * button is released).
     */
    public final Integer getFrameRate()
    {
        return getInteger( FRAME_RATE_KEY );
    }

    /**
     * Clear the framerate option
     */
    public final PanOptions clearFrameRate()
    {
        clear( FRAME_RATE_KEY );
        return this;
    }
}
