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

import com.google.gwt.core.client.JavaScriptObject;

/**
 * @author AlexanderDeleon
 */
public class PointsSeriesOptions
    extends AbstractBasicSeriesOptions<PointsSeriesOptions>
{
    public enum PointSymbol
    {
        CIRCLE( "circle" ), SQUARE( "square" ), DIAMOND( "diamond" ), TRIANGLE( "triangle" ), CROSS( "cross" );

        private String flotValue;

        PointSymbol( String flotValue )
        {
            this.flotValue = flotValue;
        }

        String getFlotValue()
        {
            return flotValue;
        }

        static PointSymbol findByFlotValue( String flotValue )
        {
            if ( null != flotValue && !"".equals( flotValue ) )
            {
                for ( PointSymbol mode : values() )
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
     * Creates a {@link PointsSeriesOptions}
     */
    public static final PointsSeriesOptions create()
    {
        return JavaScriptObject.createObject().cast();
    }

    private static final String RADIUS_KEY = "radius";
    private static final String SYMBOL_KEY = "symbol";

    protected PointsSeriesOptions()
    {
    }

    /**
     * Set the radius of the symbol
     */
    public final PointsSeriesOptions setRadius( double radius )
    {
        put( RADIUS_KEY, new Double( radius ) );
        return this;
    }

    /**
     * @return the radius of the symbol
     */
    public final Double getRadius()
    {
        return getDouble( RADIUS_KEY );
    }

    /**
     * Clear the radius of the symbol
     */
    public final PointsSeriesOptions clearRadius()
    {
        clear( RADIUS_KEY );
        return this;
    }

    /**
     * Set the symbol to represents the points
     */
    public final PointsSeriesOptions setSymbol( PointSymbol symbol )
    {
        assert null != symbol : "symbol can't be null";

        put( SYMBOL_KEY, symbol.getFlotValue() );
        return this;
    }

    /**
     * @return the symbol to represents the points
     */
    public final PointSymbol getSymbol()
    {
        return PointSymbol.findByFlotValue( getString( SYMBOL_KEY ) );
    }

    /**
     * Clear the symbol
     */
    public final PointsSeriesOptions clearSymbol()
    {
        clear( SYMBOL_KEY );
        return this;
    }

}
