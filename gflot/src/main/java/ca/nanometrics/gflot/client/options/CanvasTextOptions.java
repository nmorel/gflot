package ca.nanometrics.gflot.client.options;

import ca.nanometrics.gflot.client.util.JSONHelper;
import ca.nanometrics.gflot.client.util.JSONObjectWrapper;

import com.google.gwt.json.client.JSONObject;

/**
 * @author Nicolas Morel
 */
public class CanvasTextOptions
    extends JSONObjectWrapper
{
    public static class LineBreaksOptions
        extends JSONObjectWrapper
    {
        private static final String SHOW_KEY = "show";
        private static final String MARGIN_TOP_KEY = "marginTop";
        private static final String MARGIN_BOTTOM_KEY = "marginBottom";
        private static final String LINE_SPACING_KEY = "lineSpacing";

        public LineBreaksOptions()
        {
            super();
        }

        LineBreaksOptions( JSONObject jsonObj )
        {
            super( jsonObj );
        }

        /**
         * Enable/Disable the line breaks options
         */
        public LineBreaksOptions setShow( boolean show )
        {
            put( SHOW_KEY, show );
            return this;
        }

        /**
         * @return true if the line breaks options are activated
         */
        public Boolean getShow()
        {
            return getBoolean( SHOW_KEY );
        }

        /**
         * Clear if the line breaks option is activated
         */
        public LineBreaksOptions clearShow()
        {
            clear( SHOW_KEY );
            return this;
        }

        /**
         * Set the margin top
         */
        public LineBreaksOptions setMarginTop( int marginTop )
        {
            put( MARGIN_TOP_KEY, marginTop );
            return this;
        }

        /**
         * @return the margin top
         */
        public Integer getMarginTop()
        {
            return getInteger( MARGIN_TOP_KEY );
        }

        /**
         * Clear the margin top
         */
        public LineBreaksOptions clearMarginTop()
        {
            clear( MARGIN_TOP_KEY );
            return this;
        }

        /**
         * Set the margin bottom
         */
        public LineBreaksOptions setMarginBottom( int marginBottom )
        {
            put( MARGIN_BOTTOM_KEY, marginBottom );
            return this;
        }

        /**
         * @return the margin bottom
         */
        public Integer getMarginBottom()
        {
            return getInteger( MARGIN_BOTTOM_KEY );
        }

        /**
         * Clear the margin bottom
         */
        public LineBreaksOptions clearMarginBottom()
        {
            clear( MARGIN_BOTTOM_KEY );
            return this;
        }

        /**
         * Set the line spacing
         */
        public LineBreaksOptions setLineSpacing( int lineSpacing )
        {
            put( LINE_SPACING_KEY, lineSpacing );
            return this;
        }

        /**
         * @return the line spacing
         */
        public Integer getLineSpacing()
        {
            return getInteger( LINE_SPACING_KEY );
        }

        /**
         * Clear the line spacing
         */
        public LineBreaksOptions clearLineSpacing()
        {
            clear( LINE_SPACING_KEY );
            return this;
        }
    }

    private static final String SHOW_KEY = "show";
    private static final String FONT_KEY = "font";
    private static final String FONT = "sans ";
    private static final String SERIES_KEY = "series";
    private static final String SERIES_FONT_KEY = "seriesFont";
    private static final String LINE_BREAKS_KEY = "lineBreaks";

    public CanvasTextOptions()
    {
        super();
    }

    CanvasTextOptions( JSONObject jsonObj )
    {
        super( jsonObj );
    }

    /**
     * Enable/Disable the canvas text plugin
     */
    public CanvasTextOptions setShow( boolean show )
    {
        put( SHOW_KEY, show );
        return this;
    }

    /**
     * @return true if the canvas text plugin is activated
     */
    public Boolean getShow()
    {
        return getBoolean( SHOW_KEY );
    }

    /**
     * Clear if the canvas text plugin is activated
     */
    public CanvasTextOptions clearShow()
    {
        clear( SHOW_KEY );
        return this;
    }

    /**
     * Set the font size. Default is "8px"
     */
    public CanvasTextOptions setFontSize( String fontSize )
    {
        put( FONT_KEY, FONT + fontSize );
        return this;
    }

    /**
     * @return the font size
     */
    public String getFontSize()
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
    public CanvasTextOptions clearFontSize()
    {
        clear( FONT_KEY );
        return this;
    }

    /**
     * Set the series font size. Default is "8px"
     */
    public CanvasTextOptions setSeriesFontSize( String fontSize )
    {
        put( SERIES_FONT_KEY, FONT + fontSize );
        return this;
    }

    /**
     * @return the series font size
     */
    public String getSeriesFontSize()
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
    public CanvasTextOptions clearSeriesFontSize()
    {
        clear( SERIES_FONT_KEY );
        return this;
    }

    /**
     * Set the series to plot Y-AXIS values on the graph
     */
    public CanvasTextOptions setSeries( Integer[] series )
    {
        put( SERIES_KEY, JSONHelper.wrapArray( series ) );
        return this;
    }

    /**
     * @return the series to plot Y-AXIS values on the graph
     */
    public Integer[] getSeries()
    {
        return getIntegerArray( SERIES_KEY );
    }

    /**
     * Clear the series to plot Y-AXIS values on the graph
     */
    public CanvasTextOptions clearSeries()
    {
        clear( SERIES_KEY );
        return this;
    }

    /**
     * Set the line breaks options
     */
    public CanvasTextOptions setLineBreaks( LineBreaksOptions lineBreaks )
    {
        put( LINE_BREAKS_KEY, lineBreaks );
        return this;
    }

    /**
     * @return the line breaks options
     */
    public LineBreaksOptions getLineBreaks()
    {
        JSONObject obj = getObject( LINE_BREAKS_KEY );
        if ( null == obj )
        {
            return null;
        }
        return new LineBreaksOptions( obj );
    }

}
