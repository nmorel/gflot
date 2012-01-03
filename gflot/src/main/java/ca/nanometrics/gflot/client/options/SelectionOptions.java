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

        static SelectionMode findByFlotValue( String flotValue )
        {
            if ( null != flotValue && !"".equals( flotValue ) )
            {
                for ( SelectionMode mode : values() )
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

    public SelectionOptions()
    {
        super();
    }

    SelectionOptions( JSONObject jsonObj )
    {
        super( jsonObj );
    }

    /**
     * Set the selection mode.
     */
    public SelectionOptions setMode( SelectionMode mode )
    {
        assert null != mode : "mode can't be null";

        put( MODE_KEY, mode.getFlotValue() );
        return this;
    }

    /**
     * @return the selection mode
     */
    public SelectionMode getMode()
    {
        return SelectionMode.findByFlotValue( getString( MODE_KEY ) );
    }

    /**
     * Set the selection color
     */
    public SelectionOptions setColor( String color )
    {
        put( COLOR_KEY, color );
        return this;
    }

    /**
     * @return the selection color
     */
    public String getColor()
    {
        return getString( COLOR_KEY );
    }
}
