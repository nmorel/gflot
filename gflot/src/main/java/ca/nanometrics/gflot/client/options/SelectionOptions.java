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

/**
 * @author AlexanderDeleon
 */
public class SelectionOptions
    extends JSONObjectWrapper
{
    public enum SelectionMode
    {
        X( "x" ), Y( "y" ), XY( "xy" );

        private String flotValue;

        SelectionMode( String flotValue )
        {
            this.flotValue = flotValue;
        }

        String getFlotValue()
        {
            return flotValue;
        }
    }

    public SelectionOptions setMode( SelectionMode mode )
    {
        assert null != mode : "mode can't be null";

        put( "mode", mode.getFlotValue() );
        return this;
    }

    public SelectionOptions setDragging( boolean dragging )
    {
        put( "dragging", dragging );
        return this;
    }

    public SelectionOptions setColor( String color )
    {
        put( "color", color );
        return this;
    }
}
