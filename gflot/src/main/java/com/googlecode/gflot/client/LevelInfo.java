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
package com.googlecode.gflot.client;


import com.google.gwt.core.client.JavaScriptObject;
import com.googlecode.gflot.client.jsni.JsonObject;

/**
 * Options for a level of the fillArea plugin. LevelInfo is displayed an area around a central line.
 *
 * @author Ana Rita Loureiro
 */
public class LevelInfo
        extends JsonObject
{
    public enum LevelRepresentation
    {
        ASYMMETRIC("asymmetric"), SYMMETRIC("symmetric");

        private final String flotValue;

        private LevelRepresentation( String flotValue )
        {
            this.flotValue = flotValue;
        }

        public String getFlotValue()
        {
            return flotValue;
        }

        static LevelRepresentation findByFlotValue( String flotValue )
        {
            if ( null != flotValue && !"".equals(flotValue) )
            {
                for ( LevelRepresentation mode : values() )
                {
                    if ( mode.getFlotValue().equals(flotValue) )
                    {
                        return mode;
                    }
                }
            }
            return null;
        }
    }

    /**
     * Creates a {@link LevelInfo}
     *
     * @return a {@link LevelInfo}
     */
    public static LevelInfo create()
    {
        return JavaScriptObject.createObject().cast();
    }

    /**
     * Creates a {@link LevelInfo}
     *
     * @param representation the level representation
     *
     * @return a {@link LevelInfo}
     */
    public static LevelInfo of( LevelRepresentation representation )
    {
        return create().setRepresentation(representation);
    }

    private static final String COLOR_KEY = "color";
    private static final String REPRESENTATION_KEY = "representation";
    private static final String OPACITY_KEY = "opacity";

    protected LevelInfo() {}

    /**
     * @return the color of area.
     */
    public final String getColor()
    {
        return getString(COLOR_KEY);
    }

    /**
     * Defines the color of the area to draw around the line, when none is
     * defined the line color is used.
     *
     * @param color the color
     *
     * @return this instance of {@link LevelInfo}
     */
    public final LevelInfo setColor( String color )
    {
        put(COLOR_KEY, color);
        return this;
    }

    /**
     * Clear the color
     *
     * @return this instance of {@link LevelInfo}
     */
    public final LevelInfo clearColor()
    {
        clear(COLOR_KEY);
        return this;
    }

    /**
     * @return the opacity of area.
     */
    public final Double getOpacity()
    {
        return getDouble(OPACITY_KEY);
    }

    /**
     * Defines the opacity of the area, when none is defined the folloing
     * formula is used: (number of levels - position)/(number of levels + 1).
     *
     * @param opacity the opacity
     *
     * @return this instance of {@link LevelInfo}
     */
    public final LevelInfo setOpacity( double opacity )
    {
        put(OPACITY_KEY, opacity);
        return this;
    }

    /**
     * Clear the opacity
     *
     * @return this instance of {@link LevelInfo}
     */
    public final LevelInfo clearOpacity()
    {
        clear(OPACITY_KEY);
        return this;
    }

    /**
     * Provides the data representation format for the current level. When it is
     * "symmetric", the level requires a single value that represents an offset
     * around the central line, when "asymmetric" is used the level will require
     * two absolute values, the minimum and maximum.
     *
     * @return the representation format.
     */
    public final LevelRepresentation getRepresentation()
    {
        return LevelRepresentation.findByFlotValue(getString(REPRESENTATION_KEY));
    }

    /**
     * Defines the representation format as either "symmetric" or "asymmetric".
     * When it is "symmetric", the level requires a single value that represents
     * an offset around the central line, when "asymmetric" is used the level
     * will require two absolute values, the minimum and maximum.
     *
     * @param representation the representation
     *
     * @return this instance of {@link LevelInfo}
     */
    public final LevelInfo setRepresentation( LevelRepresentation representation )
    {
        assert null != representation : "representation can't be null";

        put(REPRESENTATION_KEY, representation.getFlotValue());
        return this;
    }

    /**
     * Clear the representation
     *
     * @return this instance of {@link LevelInfo}
     */
    public final LevelInfo clearRepresentation()
    {
        clear(REPRESENTATION_KEY);
        return this;
    }
}
