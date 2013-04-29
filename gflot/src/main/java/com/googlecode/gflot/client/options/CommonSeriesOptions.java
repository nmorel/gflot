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
package com.googlecode.gflot.client.options;


import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.googlecode.gflot.client.jsni.JsonObject;

/**
 * Options common to global series options and specific series options
 *
 * @author Nicolas Morel
 */
@SuppressWarnings( "unchecked" )
public abstract class CommonSeriesOptions<T extends CommonSeriesOptions<?>>
    extends JsonObject
{

    public static class Threshold
        extends JsonObject
    {
        private static final String BELOW_KEY = "below";
        private static final String COLOR_KEY = "color";

        /**
         * Creates a {@link Threshold}
         */
        public static final Threshold create()
        {
            return JavaScriptObject.createObject().cast();
        }

        protected Threshold()
        {
        }

        /**
         * The data points below "below" are drawn with the specified color.
         */
        public final Threshold setBelow( double below )
        {
            put( BELOW_KEY, below );
            return this;
        }

        /**
         * @return the below number
         */
        public final Double getBelow()
        {
            return getDouble( BELOW_KEY );
        }

        /**
         * Clear the below number
         */
        public final Threshold clearBelowNumber()
        {
            clear( BELOW_KEY );
            return this;
        }

        /**
         * Set the color.
         */
        public final Threshold setColor( String color )
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
        public final Threshold clearColor()
        {
            clear( COLOR_KEY );
            return this;
        }
    }

    private static final String LINE_SERIES_KEY = "lines";
    private static final String BAR_SERIES_KEY = "bars";
    private static final String POINTS_SERIES_KEY = "points";
    private static final String IMAGES_SERIES_KEY = "images";
    private static final String PIE_SERIES_KEY = "pie";
    private static final String SHADOW_SIZE_KEY = "shadowSize";
    private static final String STACK_KEY = "stack";
    private static final String THRESHOLD_KEY = "threshold";
    private static final String HIGHLIGHT_COLOR_KEY = "highlightColor";

    protected CommonSeriesOptions()
    {
    }

    /**
     * Set global Line series options that will be used unless options are set directly to the series
     */
    public final T setLineSeriesOptions( LineSeriesOptions lineSeriesOptions )
    {
        put( LINE_SERIES_KEY, lineSeriesOptions );
        return (T) this;
    }

    /**
     * @return global Line series options
     */
    public final LineSeriesOptions getLineSeriesOptions()
    {
        return getJsObject( LINE_SERIES_KEY );
    }

    /**
     * Set global Bar series options that will be used unless options are set directly to the series
     */
    public final T setBarsSeriesOptions( BarSeriesOptions barSeriesOptions )
    {
        put( BAR_SERIES_KEY, barSeriesOptions );
        return (T) this;
    }

    /**
     * @return global Bar series options
     */
    public final BarSeriesOptions getBarSeriesOptions()
    {
        return getJsObject( BAR_SERIES_KEY );
    }

    /**
     * Set global Points series options that will be used unless options are set directly to the series
     */
    public final T setPointsOptions( PointsSeriesOptions pointsSeriesOptions )
    {
        put( POINTS_SERIES_KEY, pointsSeriesOptions );
        return (T) this;
    }

    /**
     * @return global Points series options
     */
    public final PointsSeriesOptions getPointsSeriesOptions()
    {
        return getJsObject( POINTS_SERIES_KEY );
    }

    /**
     * Set global Image series options that will be used unless options are set directly to the series
     */
    public final T setImageSeriesOptions( ImageSeriesOptions imageSeriesOptions )
    {
        put( IMAGES_SERIES_KEY, imageSeriesOptions );
        return (T) this;
    }

    /**
     * @return global Image series options
     */
    public final ImageSeriesOptions getImageSeriesOptions()
    {
        return getJsObject( IMAGES_SERIES_KEY );
    }

    /**
     * Set global Pie series options that will be used unless options are set directly to the series
     */
    public final T setPieSeriesOptions( PieSeriesOptions pieSeriesOptions )
    {
        put( PIE_SERIES_KEY, pieSeriesOptions );
        return (T) this;
    }

    /**
     * @return global Pie series options
     */
    public final PieSeriesOptions getPieSeriesOptions()
    {
        return getJsObject( PIE_SERIES_KEY );
    }

    /**
     * Set the size of shadows in pixels for all series. Set it to 0 to remove shadows.
     */
    public final T setShadowSize( double shadow )
    {
        assert shadow >= 0 : "shadowSize must be >= 0";

        put( SHADOW_SIZE_KEY, shadow );
        return (T) this;
    }

    /**
     * @return the size of shadows in pixels
     */
    public final Double getShadowSize()
    {
        return getDouble( SHADOW_SIZE_KEY );
    }

    /**
     * Clear the size of the shadows
     */
    public final T clearShadowSize()
    {
        clear( SHADOW_SIZE_KEY );
        return (T) this;
    }

    /**
     * Set the stack key option. Two or more series are stacked when their "stack" attribute is set to the same key
     * (which can be any number or string or just "true"). The stacking order is determined by the order of the data
     * series in the array (later series end up on top of the previous).
     */
    public final T setStack( String key )
    {
        put( STACK_KEY, key );
        return (T) this;
    }

    /**
     * Set the stack key option. Two or more series are stacked when their "stack" attribute is set to the same key
     * (which can be any number or string or just "true"). The stacking order is determined by the order of the data
     * series in the array (later series end up on top of the previous).
     */
    public final T setStack( int key )
    {
        put( STACK_KEY, key );
        return (T) this;
    }

    /**
     * Set the stack key option. Two or more series are stacked when their "stack" attribute is set to the same key
     * (which can be any number or string or just "true"). The stacking order is determined by the order of the data
     * series in the array (later series end up on top of the previous).
     */
    public final T setStack( boolean stack )
    {
        put( STACK_KEY, stack );
        return (T) this;
    }

    /**
     * @return the stack option
     */
    public final String getStackAsKeyString()
    {
        return getString( STACK_KEY );
    }

    /**
     * @return the stack option
     */
    public final Integer getStackAsKeyNumber()
    {
        return getInteger( STACK_KEY );
    }

    /**
     * @return the stack option
     */
    public final Boolean getStackAsBoolean()
    {
        return getBoolean( STACK_KEY );
    }

    /**
     * Clear the stack option
     */
    public final T clearStack()
    {
        clear( STACK_KEY );
        return (T) this;
    }

    /**
     * Set the threshold options.
     */
    public final T setThreshold( Threshold threshold )
    {
        JsArray<Threshold> array = JavaScriptObject.createArray().cast();
        array.push( threshold );
        return setThreshold( array );
    }

    /**
     * Set the threshold options.
     */
    public final T setThreshold( JsArray<Threshold> threshold )
    {
        put( THRESHOLD_KEY, threshold );
        return (T) this;
    }

    /**
     * @return the threshold options
     */
    public final JsArray<Threshold> getThreshold()
    {
        return getJsObject( THRESHOLD_KEY );
    }

    /**
     * Clear the threshold option
     */
    public final T clearThreshold()
    {
        clear( THRESHOLD_KEY );
        return (T) this;
    }

    /**
     * Set the color of the translucent overlay used
     * to highlight the series when the mouse hovers over it
     */
    public final T setHighlightColor( String color )
    {
        put( HIGHLIGHT_COLOR_KEY, color );
        return (T) this;
    }

    /**
     * Set the color of the translucent overlay used
     * to highlight the series when the mouse hovers over it
     */
    public final T setHighlightColor( double color )
    {
        put( HIGHLIGHT_COLOR_KEY, color );
        return (T) this;
    }

    /**
     * @return the color of the translucent overlay used
     *         to highlight the series when the mouse hovers over it
     */
    public final String getHighlightColorAsString()
    {
        return getString( HIGHLIGHT_COLOR_KEY );
    }

    /**
     * @return the color of the translucent overlay used
     *         to highlight the series when the mouse hovers over it
     */
    public final Double getHighlightColorAsNumber()
    {
        return getDouble( HIGHLIGHT_COLOR_KEY );
    }

    /**
     * Clear the color of the translucent overlay used
     * to highlight the series when the mouse hovers over it
     */
    public final T clearHighlightColor()
    {
        clear( HIGHLIGHT_COLOR_KEY );
        return (T) this;
    }
}
