package ca.nanometrics.gflot.client.options;

import ca.nanometrics.gflot.client.Series;
import ca.nanometrics.gflot.client.jsni.JsonObject;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Options for pie series
 * 
 * @author Nicolas Morel
 */
public class PieSeriesOptions
    extends AbstractSeriesOptions<PieSeriesOptions>
{
    public static class Offset
        extends JsonObject
    {
        /**
         * Creates a {@link Offset}
         */
        public static final Offset create()
        {
            return JavaScriptObject.createObject().cast();
        }

        private static final String TOP_KEY = "top";
        private static final String LEFT_KEY = "left";

        protected Offset()
        {
        }

        /**
         * Set the top. integer value to move the pie up or down
         */
        public final Offset setTop( int top )
        {
            put( TOP_KEY, top );
            return this;
        }

        /**
         * @return the top
         */
        public final Integer getTop()
        {
            return getInteger( TOP_KEY );
        }

        /**
         * Clear the top option
         */
        public final Offset clearTop()
        {
            clear( TOP_KEY );
            return this;
        }

        /**
         * Set the left. integer value to move the pie left or right
         */
        public final Offset setLeft( int left )
        {
            put( LEFT_KEY, left );
            return this;
        }

        /**
         * @return the left
         */
        public final Integer getLeft()
        {
            return getInteger( LEFT_KEY );
        }

        /**
         * Clear the left option
         */
        public final Offset clearLeft()
        {
            clear( LEFT_KEY );
            return this;
        }
    }

    public static class Stroke
        extends JsonObject
    {
        /**
         * Creates a {@link Stroke}
         */
        public static final Stroke create()
        {
            return JavaScriptObject.createObject().cast();
        }

        private static final String COLOR_KEY = "color";
        private static final String WIDTH_KEY = "width";

        protected Stroke()
        {
        }

        /**
         * Set the color. any hexidecimal color value (other formats may or may not work, so best to stick with
         * something like '#FFF')
         */
        public final Stroke setColor( String color )
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
        public final Stroke clearColor()
        {
            clear( COLOR_KEY );
            return this;
        }

        /**
         * Set the integer pixel width of the stroke
         */
        public final Stroke setWidth( int width )
        {
            put( WIDTH_KEY, width );
            return this;
        }

        /**
         * @return the width
         */
        public final Integer getWidth()
        {
            return getInteger( WIDTH_KEY );
        }

        /**
         * Clear the width
         */
        public final Stroke clearWidth()
        {
            clear( WIDTH_KEY );
            return this;
        }
    }

    public static class Label
        extends JsonObject
    {
        public static class Background
            extends JsonObject
        {
            /**
             * Creates a {@link Background}
             */
            public static final Background create()
            {
                return JavaScriptObject.createObject().cast();
            }

            private static final String COLOR_KEY = "color";
            private static final String OPACITY_KEY = "opacity";

            protected Background()
            {
            }

            /**
             * Set the color. any hexidecimal color value (other formats may or may not work, so best to stick with
             * something like '#000')
             */
            public final Background setColor( String color )
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
            public final Background clearColor()
            {
                clear( COLOR_KEY );
                return this;
            }

            /**
             * Set the background opacity. Opacity range from 0.0 to 1.0.
             */
            public final Background setOpacity( double opacity )
            {
                assert opacity >= 0 && opacity <= 1 : "opacity range from 0.0 to 1.0";

                put( OPACITY_KEY, opacity );
                return this;
            }

            /**
             * @return the opacity
             */
            public final Double getOpacity()
            {
                return getDouble( OPACITY_KEY );
            }

            /**
             * Clear the opacity
             */
            public final Background clearOpacity()
            {
                clear( OPACITY_KEY );
                return this;
            }
        }

        public interface Formatter
        {
            String format( String label, Series series );
        }

        /**
         * Creates a {@link Label}
         */
        public static final Label create()
        {
            return JavaScriptObject.createObject().cast();
        }

        private static final String SHOW_KEY = "show";
        private static final String FORMATTER_KEY = "formatter";
        private static final String RADIUS_KEY = "radius";
        private static final String BACKGROUND_KEY = "background";
        private static final String THRESHOLD_KEY = "threshold";

        protected Label()
        {
        }

        /**
         * Set the visibility of the label.
         */
        public final Label setShow( boolean show )
        {
            put( SHOW_KEY, show );
            return this;
        }

        /**
         * @return the visibility of the label
         */
        public final Boolean getShow()
        {
            return getBoolean( SHOW_KEY );
        }

        /**
         * Clear the visibility of the label
         */
        public final Label clearShow()
        {
            clear( SHOW_KEY );
            return this;
        }

        /**
         * Set the labelFormatter if you want to format the labels in some way, e.g. make them to links.
         */
        public final Label setFormatter( Formatter formatter )
        {
            assert null != formatter : "formatter can't be null";

            setFormatterNative( formatter );
            return this;
        }

        private native void setFormatterNative( Formatter formatter )
        /*-{
            this.formatter = function(label, series) {
                return formatter.@ca.nanometrics.gflot.client.options.PieSeriesOptions.Label.Formatter::format(Ljava/lang/String;Lca/nanometrics/gflot/client/Series;)(label, series);
            };
        }-*/;

        /**
         * Clear the label formatter
         */
        public final Label clearLabelFormatter()
        {
            clear( "formatter" );
            return this;
        }

        /**
         * Set the radius. 0-1 for percentage of fullsize, or a specified pixel length
         */
        public final Label setRadius( double radius )
        {
            put( RADIUS_KEY, radius );
            return this;
        }

        /**
         * @return the radius
         */
        public final Double getRadius()
        {
            return getDouble( RADIUS_KEY );
        }

        /**
         * Clear the radius
         */
        public final Label clearRadius()
        {
            clear( RADIUS_KEY );
            return this;
        }

        /**
         * Set the background
         */
        public final Label setBackground( Background background )
        {
            put( BACKGROUND_KEY, background );
            return this;
        }

        /**
         * @return the background
         */
        public final Background getBackground()
        {
            return getJsObject( BACKGROUND_KEY );
        }

        /**
         * Clear the background
         */
        public final Label clearBackground()
        {
            clear( BACKGROUND_KEY );
            return this;
        }

        /**
         * Set the threshold. 0-1 for the percentage value at which to hide labels (if they're too small)
         */
        public final Label setThreshold( double threshold )
        {
            assert threshold >= 0 && threshold <= 1 : "threshold range from 0.0 to 1.0";

            put( THRESHOLD_KEY, threshold );
            return this;
        }

        /**
         * @return the threshold
         */
        public final Double getThreshold()
        {
            return getDouble( THRESHOLD_KEY );
        }

        /**
         * Clear the threshold
         */
        public final Label clearThreshold()
        {
            clear( THRESHOLD_KEY );
            return this;
        }
    }

    public static class Combine
        extends JsonObject
    {
        /**
         * Creates a {@link Combine}
         */
        public static final Combine create()
        {
            return JavaScriptObject.createObject().cast();
        }

        private static final String THRESHOLD_KEY = "threshold";
        private static final String COLOR_KEY = "color";
        private static final String LABEL_KEY = "label";

        protected Combine()
        {
        }

        /**
         * Set the threshold. 0-1 for the percentage value at which to combine slices (if they're too small)
         */
        public final Combine setThreshold( double threshold )
        {
            assert threshold >= 0 && threshold <= 1 : "threshold range from 0.0 to 1.0";

            put( THRESHOLD_KEY, threshold );
            return this;
        }

        /**
         * @return the threshold
         */
        public final Double getThreshold()
        {
            return getDouble( THRESHOLD_KEY );
        }

        /**
         * Clear the threshold
         */
        public final Combine clearThreshold()
        {
            clear( THRESHOLD_KEY );
            return this;
        }

        /**
         * Set the color. any hexidecimal color value (other formats may or may not work, so best to stick with
         * something like '#CCC'), if null, the plugin will automatically use the color of the first slice to be
         * combined
         */
        public final Combine setColor( String color )
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
        public final Combine clearColor()
        {
            clear( COLOR_KEY );
            return this;
        }

        /**
         * Set the label. Any text value of what the combined slice should be labeled
         */
        public final Combine setLabel( String label )
        {
            put( LABEL_KEY, label );
            return this;
        }

        /**
         * @return the label
         */
        public final String getLabel()
        {
            return getString( LABEL_KEY );
        }

        /**
         * Clear the label
         */
        public final Combine clearLabel()
        {
            clear( LABEL_KEY );
            return this;
        }
    }

    public static class Highlight
        extends JsonObject
    {
        /**
         * Creates a {@link Highlight}
         */
        public static final Highlight create()
        {
            return JavaScriptObject.createObject().cast();
        }

        private static final String OPACITY_KEY = "opacity";

        protected Highlight()
        {
        }

        /**
         * Set the opacity. Opacity range from 0.0 to 1.0.
         */
        public final Highlight setOpacity( double opacity )
        {
            assert opacity >= 0 && opacity <= 1 : "opacity range from 0.0 to 1.0";

            put( OPACITY_KEY, opacity );
            return this;
        }

        /**
         * @return the opacity
         */
        public final Double getOpacity()
        {
            return getDouble( OPACITY_KEY );
        }

        /**
         * Clear the opacity
         */
        public final Highlight clearOpacity()
        {
            clear( OPACITY_KEY );
            return this;
        }
    }

    /**
     * Creates a {@link PieSeriesOptions}
     */
    public static final PieSeriesOptions create()
    {
        return JavaScriptObject.createObject().cast();
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

    protected PieSeriesOptions()
    {
    }

    /**
     * Set the radius. 0-1 for percentage of fullsize, or a specified pixel length
     */
    public final PieSeriesOptions setRadius( double radius )
    {
        put( RADIUS_KEY, radius );
        return this;
    }

    /**
     * @return the radius
     */
    public final Double getRadius()
    {
        return getDouble( RADIUS_KEY );
    }

    /**
     * Clear the radius
     */
    public final PieSeriesOptions clearRadius()
    {
        clear( RADIUS_KEY );
        return this;
    }

    /**
     * Set the inner radius to create a donut effect. 0-1 for percentage of fullsize or a specified pixel length
     */
    public final PieSeriesOptions setInnerRadius( double innerRadius )
    {
        put( INNER_RADIUS_KEY, innerRadius );
        return this;
    }

    /**
     * @return the inner radius
     */
    public final Double getInnerRadius()
    {
        return getDouble( INNER_RADIUS_KEY );
    }

    /**
     * Clear the inner radius
     */
    public final PieSeriesOptions clearInnerRadius()
    {
        clear( INNER_RADIUS_KEY );
        return this;
    }

    /**
     * Set the start angle. 0-2 factor of PI used for starting angle (in radians) i.e 3/2 starts at the top, 0 and 2
     * have the same result
     */
    public final PieSeriesOptions setStartAngle( double startAngle )
    {
        put( START_ANGLE_KEY, startAngle );
        return this;
    }

    /**
     * @return the start angle
     */
    public final Double getStartAngle()
    {
        return getDouble( START_ANGLE_KEY );
    }

    /**
     * Clear the start angle
     */
    public final PieSeriesOptions clearStartAngle()
    {
        clear( START_ANGLE_KEY );
        return this;
    }

    /**
     * Set the tilt. 0-1 for percentage to tilt the pie, where 1 is no tilt, and 0 is completely flat (nothing will
     * show)
     */
    public final PieSeriesOptions setTilt( double tilt )
    {
        put( TILT_KEY, tilt );
        return this;
    }

    /**
     * @return the tilt
     */
    public final Double getTilt()
    {
        return getDouble( TILT_KEY );
    }

    /**
     * Clear the tilt
     */
    public final PieSeriesOptions clearTilt()
    {
        clear( TILT_KEY );
        return this;
    }

    /**
     * Set the offset.
     */
    public final PieSeriesOptions setOffset( Offset offset )
    {
        put( OFFSET_KEY, offset );
        return this;
    }

    /**
     * @return the offset
     */
    public final Offset getOffset()
    {
        return getJsObject( OFFSET_KEY );
    }

    /**
     * Clear the offset
     */
    public final PieSeriesOptions clearOffset()
    {
        clear( OFFSET_KEY );
        return this;
    }

    /**
     * Set the stroke.
     */
    public final PieSeriesOptions setStroke( Stroke stroke )
    {
        put( STROKE_KEY, stroke );
        return this;
    }

    /**
     * @return the stroke
     */
    public final Stroke getStroke()
    {
        return getJsObject( STROKE_KEY );
    }

    /**
     * Set the label.
     */
    public final PieSeriesOptions setLabel( Label label )
    {
        put( LABEL_KEY, label );
        return this;
    }

    /**
     * @return the label
     */
    public final Label getLabel()
    {
        return getJsObject( LABEL_KEY );
    }

    /**
     * Set the combine.
     */
    public final PieSeriesOptions setCombine( Combine combine )
    {
        put( COMBINE_KEY, combine );
        return this;
    }

    /**
     * @return the combine
     */
    public final Combine getCombine()
    {
        return getJsObject( COMBINE_KEY );
    }

    /**
     * Set the Highlight.
     */
    public final PieSeriesOptions setHighlight( Highlight highlight )
    {
        put( HIGHLIGHT_KEY, highlight );
        return this;
    }

    /**
     * @return the highlight
     */
    public final Highlight getHighlight()
    {
        return getJsObject( HIGHLIGHT_KEY );
    }
}
