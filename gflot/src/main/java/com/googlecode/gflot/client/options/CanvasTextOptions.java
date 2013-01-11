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
import com.google.gwt.core.client.JsArrayInteger;
import com.googlecode.gflot.client.jsni.JsonObject;

/**
 * @author Nicolas Morel
 */
public class CanvasTextOptions
    extends JsonObject
{
    public static class LineBreaksOptions
        extends JsonObject
    {
        /**
         * Creates a {@link LineBreaksOptions}
         */
        public static final LineBreaksOptions create()
        {
            return JavaScriptObject.createObject().cast();
        }

        private static final String SHOW_KEY = "show";
        private static final String MARGIN_TOP_KEY = "marginTop";
        private static final String MARGIN_BOTTOM_KEY = "marginBottom";
        private static final String LINE_SPACING_KEY = "lineSpacing";

        protected LineBreaksOptions()
        {
        }

        /**
         * Enable/Disable the line breaks options
         */
        public final LineBreaksOptions setShow( boolean show )
        {
            put( SHOW_KEY, show );
            return this;
        }

        /**
         * @return true if the line breaks options are activated
         */
        public final Boolean getShow()
        {
            return getBoolean( SHOW_KEY );
        }

        /**
         * Clear if the line breaks option is activated
         */
        public final LineBreaksOptions clearShow()
        {
            clear( SHOW_KEY );
            return this;
        }

        /**
         * Set the margin top
         */
        public final LineBreaksOptions setMarginTop( int marginTop )
        {
            put( MARGIN_TOP_KEY, marginTop );
            return this;
        }

        /**
         * @return the margin top
         */
        public final Integer getMarginTop()
        {
            return getInteger( MARGIN_TOP_KEY );
        }

        /**
         * Clear the margin top
         */
        public final LineBreaksOptions clearMarginTop()
        {
            clear( MARGIN_TOP_KEY );
            return this;
        }

        /**
         * Set the margin bottom
         */
        public final LineBreaksOptions setMarginBottom( int marginBottom )
        {
            put( MARGIN_BOTTOM_KEY, marginBottom );
            return this;
        }

        /**
         * @return the margin bottom
         */
        public final Integer getMarginBottom()
        {
            return getInteger( MARGIN_BOTTOM_KEY );
        }

        /**
         * Clear the margin bottom
         */
        public final LineBreaksOptions clearMarginBottom()
        {
            clear( MARGIN_BOTTOM_KEY );
            return this;
        }

        /**
         * Set the line spacing
         */
        public final LineBreaksOptions setLineSpacing( int lineSpacing )
        {
            put( LINE_SPACING_KEY, lineSpacing );
            return this;
        }

        /**
         * @return the line spacing
         */
        public final Integer getLineSpacing()
        {
            return getInteger( LINE_SPACING_KEY );
        }

        /**
         * Clear the line spacing
         */
        public final LineBreaksOptions clearLineSpacing()
        {
            clear( LINE_SPACING_KEY );
            return this;
        }
    }

    /**
     * Creates a {@link CanvasTextOptions}
     */
    public static final CanvasTextOptions create()
    {
        return JavaScriptObject.createObject().cast();
    }

    private static final String SHOW_KEY = "show";
    private static final String FONT_KEY = "font";
    private static final String FONT = "sans ";
    private static final String SERIES_KEY = "series";
    private static final String SERIES_FONT_KEY = "seriesFont";
    private static final String LINE_BREAKS_KEY = "lineBreaks";

    protected CanvasTextOptions()
    {
    }

    /**
     * Enable/Disable the canvas text plugin
     */
    public final CanvasTextOptions setShow( boolean show )
    {
        put( SHOW_KEY, show );
        return this;
    }

    /**
     * @return true if the canvas text plugin is activated
     */
    public final Boolean getShow()
    {
        return getBoolean( SHOW_KEY );
    }

    /**
     * Clear if the canvas text plugin is activated
     */
    public final CanvasTextOptions clearShow()
    {
        clear( SHOW_KEY );
        return this;
    }

    /**
     * Set the font size. Default is "8px"
     */
    public final CanvasTextOptions setFontSize( String fontSize )
    {
        put( FONT_KEY, FONT + fontSize );
        return this;
    }

    /**
     * @return the font size
     */
    public final String getFontSize()
    {
        String font = getString( FONT_KEY );
        if ( null == font || !font.startsWith( FONT ) )
        {
            return null;
        }
        return font.substring( FONT.length() );
    }

    /**
     * Clear the font size
     */
    public final CanvasTextOptions clearFontSize()
    {
        clear( FONT_KEY );
        return this;
    }

    /**
     * Set the series font size. Default is "8px"
     */
    public final CanvasTextOptions setSeriesFontSize( String fontSize )
    {
        put( SERIES_FONT_KEY, FONT + fontSize );
        return this;
    }

    /**
     * @return the series font size
     */
    public final String getSeriesFontSize()
    {
        String font = getString( SERIES_FONT_KEY );
        if ( null == font || !font.startsWith( FONT ) )
        {
            return null;
        }
        return font.substring( FONT.length() );
    }

    /**
     * Clear the series font size
     */
    public final CanvasTextOptions clearSeriesFontSize()
    {
        clear( SERIES_FONT_KEY );
        return this;
    }

    /**
     * Set the series to plot Y-AXIS values on the graph
     */
    public final CanvasTextOptions setSeries( JsArrayInteger series )
    {
        put( SERIES_KEY, series );
        return this;
    }

    /**
     * @return the series to plot Y-AXIS values on the graph
     */
    public final JsArrayInteger getSeries()
    {
        return getIntegerArray( SERIES_KEY );
    }

    /**
     * Clear the series to plot Y-AXIS values on the graph
     */
    public final CanvasTextOptions clearSeries()
    {
        clear( SERIES_KEY );
        return this;
    }

    /**
     * Set the line breaks options
     */
    public final CanvasTextOptions setLineBreaks( LineBreaksOptions lineBreaks )
    {
        put( LINE_BREAKS_KEY, lineBreaks );
        return this;
    }

    /**
     * @return the line breaks options
     */
    public final LineBreaksOptions getLineBreaks()
    {
        return getJsObject( LINE_BREAKS_KEY );
    }

}
