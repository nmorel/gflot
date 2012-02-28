package ca.nanometrics.gflot.client.options;

import ca.nanometrics.gflot.client.Series;
import ca.nanometrics.gflot.client.util.JSONObjectWrapper;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONObject;

/**
 * Options for pie series
 *
 * @author Nicolas Morel
 */
public class PieSeriesOptions
    extends AbstractSeriesOptions<PieSeriesOptions>
{
    public static class Offset
        extends JSONObjectWrapper
    {
        private static final String TOP_KEY = "top";
        private static final String LEFT_KEY = "left";

        public Offset()
        {
        }

        Offset( JSONObject obj )
        {
            super( obj );
        }

        /**
         * Set the top. integer value to move the pie up or down
         */
        public Offset setTop( int top )
        {
            put( TOP_KEY, top );
            return this;
        }

        /**
         * @return the top
         */
        public Integer getTop()
        {
            return getInteger( TOP_KEY );
        }

        /**
         * Set the left. integer value to move the pie left or right
         */
        public Offset setLeft( int left )
        {
            put( LEFT_KEY, left );
            return this;
        }

        /**
         * @return the left
         */
        public Integer getLeft()
        {
            return getInteger( LEFT_KEY );
        }
    }

    public static class Stroke
        extends JSONObjectWrapper
    {
        private static final String COLOR_KEY = "color";
        private static final String WIDTH_KEY = "width";

        public Stroke()
        {
        }

        Stroke( JSONObject obj )
        {
            super( obj );
        }

        /**
         * Set the color. any hexidecimal color value (other formats may or may not work, so best to stick with
         * something like '#FFF')
         */
        public Stroke setColor( String color )
        {
            put( COLOR_KEY, color );
            return this;
        }

        /**
         * @return the color
         */
        public String getColor()
        {
            return getString( COLOR_KEY );
        }

        /**
         * Set the integer pixel width of the stroke
         */
        public Stroke setWidth( int width )
        {
            put( WIDTH_KEY, width );
            return this;
        }

        /**
         * @return the width
         */
        public Integer getWidth()
        {
            return getInteger( WIDTH_KEY );
        }
    }

    public static class Label
        extends JSONObjectWrapper
    {
        public static class Background
            extends JSONObjectWrapper
        {
            private static final String COLOR_KEY = "color";
            private static final String OPACITY_KEY = "opacity";

            public Background()
            {
            }

            Background( JSONObject obj )
            {
                super( obj );
            }

            /**
             * Set the color. any hexidecimal color value (other formats may or may not work, so best to stick with
             * something like '#000')
             */
            public Background setColor( String color )
            {
                put( COLOR_KEY, color );
                return this;
            }

            /**
             * @return the color
             */
            public String getColor()
            {
                return getString( COLOR_KEY );
            }

            /**
             * Set the background opacity. Opacity range from 0.0 to 1.0.
             */
            public Background setOpacity( double opacity )
            {
                assert opacity >= 0 && opacity <= 1 : "opacity range from 0.0 to 1.0";

                put( OPACITY_KEY, opacity );
                return this;
            }

            /**
             * @return the opacity
             */
            public Double getOpacity()
            {
                return getDouble( OPACITY_KEY );
            }
        }

        public interface Formatter
        {
            String format( String label, Series series );
        }

        private static final String SHOW_KEY = "show";
        private static final String FORMATTER_KEY = "formatter";
        private static final String RADIUS_KEY = "radius";
        private static final String BACKGROUND_KEY = "background";
        private static final String THRESHOLD_KEY = "threshold";

        public Label()
        {
        }

        Label( JSONObject obj )
        {
            super( obj );
        }

        /**
         * Set the visibility of the label.
         */
        public Label setShow( boolean show )
        {
            put( SHOW_KEY, show );
            return this;
        }

        /**
         * @return the visibility of the series
         */
        public Boolean getShow()
        {
            return getBoolean( SHOW_KEY );
        }

        /**
         * Set the labelFormatter if you want to format the labels in some way, e.g. make them to links.
         */
        public Label setFormatter( Formatter formatter )
        {
            assert null != formatter : "formatter can't be null";

            setFormatterNative( getWrappedObj().getJavaScriptObject(), formatter );
            return this;
        }

        private static native void setFormatterNative( JavaScriptObject labelOptions, Formatter formatter )
        /*-{
			labelOptions.formatter = function(label, series) {
				var jsonSeriesObject = @com.google.gwt.json.client.JSONObject::new(Lcom/google/gwt/core/client/JavaScriptObject;)(series);
				var javaSeriesObject = @ca.nanometrics.gflot.client.Series::new(Lcom/google/gwt/json/client/JSONObject;)(jsonSeriesObject);
				return formatter.@ca.nanometrics.gflot.client.options.PieSeriesOptions.Label.Formatter::format(Ljava/lang/String;Lca/nanometrics/gflot/client/Series;)(label, javaSeriesObject);
			};
        }-*/;

        /**
         * Set the radius. 0-1 for percentage of fullsize, or a specified pixel length
         */
        public Label setRadius( double radius )
        {
            put( RADIUS_KEY, radius );
            return this;
        }

        /**
         * @return the radius
         */
        public Double getRadius()
        {
            return getDouble( RADIUS_KEY );
        }

        /**
         * Set the background
         */
        public Label setBackground( Background background )
        {
            put( BACKGROUND_KEY, background );
            return this;
        }

        /**
         * @return the background
         */
        public Background getBackground()
        {
            JSONObject obj = getObject( BACKGROUND_KEY );
            if ( null == obj )
            {
                return null;
            }
            else
            {
                return new Background( obj );
            }
        }

        /**
         * Set the threshold. 0-1 for the percentage value at which to hide labels (if they're too small)
         */
        public Label setThreshold( double threshold )
        {
            assert threshold >= 0 && threshold <= 1 : "threshold range from 0.0 to 1.0";

            put( THRESHOLD_KEY, threshold );
            return this;
        }

        /**
         * @return the threshold
         */
        public Double getThreshold()
        {
            return getDouble( THRESHOLD_KEY );
        }
    }

    public static class Combine
        extends JSONObjectWrapper
    {

        private static final String THRESHOLD_KEY = "threshold";
        private static final String COLOR_KEY = "color";
        private static final String LABEL_KEY = "label";

        public Combine()
        {
        }

        Combine( JSONObject obj )
        {
            super( obj );
        }

        /**
         * Set the threshold. 0-1 for the percentage value at which to combine slices (if they're too small)
         */
        public Combine setThreshold( double threshold )
        {
            assert threshold >= 0 && threshold <= 1 : "threshold range from 0.0 to 1.0";

            put( THRESHOLD_KEY, threshold );
            return this;
        }

        /**
         * @return the threshold
         */
        public Double getThreshold()
        {
            return getDouble( THRESHOLD_KEY );
        }

        /**
         * Set the color. any hexidecimal color value (other formats may or may not work, so best to stick with
         * something like '#CCC'), if null, the plugin will automatically use the color of the first slice to be
         * combined
         */
        public Combine setColor( String color )
        {
            put( COLOR_KEY, color );
            return this;
        }

        /**
         * @return the color
         */
        public String getColor()
        {
            return getString( COLOR_KEY );
        }

        /**
         * Set the label. Any text value of what the combined slice should be labeled
         */
        public Combine setLabel( String label )
        {
            put( LABEL_KEY, label );
            return this;
        }

        /**
         * @return the label
         */
        public String getLabel()
        {
            return getString( LABEL_KEY );
        }
    }

    public static class Highlight
        extends JSONObjectWrapper
    {
        private static final String OPACITY_KEY = "opacity";

        public Highlight()
        {
        }

        Highlight( JSONObject obj )
        {
            super( obj );
        }

        /**
         * Set the opacity. Opacity range from 0.0 to 1.0.
         */
        public Highlight setOpacity( double opacity )
        {
            assert opacity >= 0 && opacity <= 1 : "opacity range from 0.0 to 1.0";

            put( OPACITY_KEY, opacity );
            return this;
        }

        /**
         * @return the opacity
         */
        public Double getOpacity()
        {
            return getDouble( OPACITY_KEY );
        }
    }

    private static final String RADIUS_KEY = "radius";
    private static final String INNER_RADIUS_KEY = "innerRadius";
    private static final String START_ANGLE_KEY = "startAngle";
    private static final String TILT_KEY = "tilt";
    private static final String OFFSET_KEY = "startAngle";
    private static final String STROKE_KEY = "stroke";
    private static final String LABEL_KEY = "label";
    private static final String COMBINE_KEY = "combine";
    private static final String HIGHLIGHT_KEY = "highlight";

    public PieSeriesOptions()
    {
        super();
    }

    PieSeriesOptions( JSONObject jsonObj )
    {
        super( jsonObj );
    }

    /**
     * Set the radius. 0-1 for percentage of fullsize, or a specified pixel length
     */
    public PieSeriesOptions setRadius( double radius )
    {
        put( RADIUS_KEY, radius );
        return this;
    }

    /**
     * @return the radius
     */
    public Double getRadius()
    {
        return getDouble( RADIUS_KEY );
    }

    /**
     * Set the inner radius to create a donut effect. 0-1 for percentage of fullsize or a specified pixel length
     */
    public PieSeriesOptions setInnerRadius( double innerRadius )
    {
        put( INNER_RADIUS_KEY, innerRadius );
        return this;
    }

    /**
     * @return the inner radius
     */
    public Double getInnerRadius()
    {
        return getDouble( INNER_RADIUS_KEY );
    }

    /**
     * Set the start angle. 0-2 factor of PI used for starting angle (in radians) i.e 3/2 starts at the top, 0 and 2
     * have the same result
     */
    public PieSeriesOptions setStartAngle( double startAngle )
    {
        put( START_ANGLE_KEY, startAngle );
        return this;
    }

    /**
     * @return the start angle
     */
    public Double getStartAngle()
    {
        return getDouble( START_ANGLE_KEY );
    }

    /**
     * Set the tilt. 0-1 for percentage to tilt the pie, where 1 is no tilt, and 0 is completely flat (nothing will
     * show)
     */
    public PieSeriesOptions setTilt( double tilt )
    {
        put( TILT_KEY, tilt );
        return this;
    }

    /**
     * @return the tilt
     */
    public Double getTilt()
    {
        return getDouble( TILT_KEY );
    }

    /**
     * Set the offset.
     */
    public PieSeriesOptions setOffset( Offset offset )
    {
        put( OFFSET_KEY, offset );
        return this;
    }

    /**
     * @return the offset
     */
    public Offset getOffset()
    {
        JSONObject obj = getObject( OFFSET_KEY );
        if ( null == obj )
        {
            return null;
        }
        else
        {
            return new Offset( obj );
        }
    }

    /**
     * Set the stroke.
     */
    public PieSeriesOptions setStroke( Stroke stroke )
    {
        put( STROKE_KEY, stroke );
        return this;
    }

    /**
     * @return the stroke
     */
    public Stroke getStroke()
    {
        JSONObject obj = getObject( STROKE_KEY );
        if ( null == obj )
        {
            return null;
        }
        else
        {
            return new Stroke( obj );
        }
    }

    /**
     * Set the label.
     */
    public PieSeriesOptions setLabel( Label label )
    {
        put( LABEL_KEY, label );
        return this;
    }

    /**
     * @return the label
     */
    public Label getLabel()
    {
        JSONObject obj = getObject( LABEL_KEY );
        if ( null == obj )
        {
            return null;
        }
        else
        {
            return new Label( obj );
        }
    }

    /**
     * Set the combine.
     */
    public PieSeriesOptions setCombine( Combine combine )
    {
        put( COMBINE_KEY, combine );
        return this;
    }

    /**
     * @return the combine
     */
    public Combine getCombine()
    {
        JSONObject obj = getObject( COMBINE_KEY );
        if ( null == obj )
        {
            return null;
        }
        else
        {
            return new Combine( obj );
        }
    }

    /**
     * Set the Highlight.
     */
    public PieSeriesOptions setHighlight( Highlight highlight )
    {
        put( HIGHLIGHT_KEY, highlight );
        return this;
    }

    /**
     * @return the highlight
     */
    public Highlight getHighlight()
    {
        JSONObject obj = getObject( HIGHLIGHT_KEY );
        if ( null == obj )
        {
            return null;
        }
        else
        {
            return new Highlight( obj );
        }
    }
}
