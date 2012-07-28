package ca.nanometrics.gflot.client.options;

import ca.nanometrics.gflot.client.Axis;
import ca.nanometrics.gflot.client.Tick;
import ca.nanometrics.gflot.client.util.JSONArrayWrapper;
import ca.nanometrics.gflot.client.util.JSONHelper;
import ca.nanometrics.gflot.client.util.JSONObjectWrapper;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

@SuppressWarnings( "unchecked" )
public abstract class AbstractAxisOptions<T extends AbstractAxisOptions<?>>
    extends JSONObjectWrapper
{
    static AbstractAxisOptions<?> createAxisOptions( JSONObject jsonObj )
    {
        JSONValue modeValue = jsonObj.get( MODE_KEY );
        if ( null != modeValue )
        {
            JSONString modeString = modeValue.isString();
            if ( null != modeString && modeString.stringValue().equals( TIME_MODE_KEY ) )
            {
                return new TimeSeriesAxisOptions( jsonObj );
            }
        }
        return new AxisOptions( jsonObj );
    }

    public interface TransformAxis
    {
        double transform( double value );

        double inverseTransform( double value );
    }

    public interface TickGenerator
    {
        Tick[] generate( Axis axis );
    }

    public enum AxisPosition
    {
        TOP( "top" ), BOTTOM( "bottom" ), LEFT( "left" ), RIGHT( "right" );

        private String flotValue;

        AxisPosition( String flotValue )
        {
            this.flotValue = flotValue;
        }

        String getFlotValue()
        {
            return flotValue;
        }

        static AxisPosition findByFlotValue( String flotValue )
        {
            if ( null != flotValue && !"".equals( flotValue ) )
            {
                for ( AxisPosition mode : values() )
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

    public enum AxisLabelRenderingMode
    {
        CSS, CANVAS, HTML;
    }

    private static final String SHOW_KEY = "show";

    private static final String POSITION_KEY = "position";

    private static final String COLOR_KEY = "color";

    private static final String TICK_COLOR_KEY = "tickColor";

    private static final String MIN_KEY = "min";

    private static final String MAX_KEY = "max";

    private static final String AUTOSCALE_MARGIN_KEY = "autoscaleMargin";

    private static final String LABEL_WIDTH_KEY = "labelWidth";

    private static final String LABEL_HEIGHT_KEY = "labelHeight";

    private static final String RESERVE_SPACE_KEY = "reserveSpace";

    private static final String TICKS_KEY = "ticks";

    private static final String TICK_LENGTH_KEY = "tickLength";

    private static final String ALIGN_TICKS_KEY = "alignTicksWithAxis";

    protected static final String TICK_SIZE_KEY = "tickSize";

    protected static final String MIN_TICK_SIZE_KEY = "minTickSize";

    protected static final String MODE_KEY = "mode";

    protected static final String TIME_MODE_KEY = "time";

    private static final String AXIS_LABEL_KEY = "axisLabel";

    private static final String AXIS_LABEL_PADDING_KEY = "axisLabelPadding";

    private static final String AXIS_LABEL_RENDERING_MODE_CANVAS_KEY = "axisLabelUseCanvas";

    private static final String AXIS_LABEL_RENDERING_MODE_HTML_KEY = "axisLabelUseHtml";

    private static final String AXIS_LABEL_CANVAS_FONT_SIZE_KEY = "axisLabelFontSizePixels";

    private static final String AXIS_LABEL_CANVAS_FONT_FAMILY_KEY = "axisLabelFontFamily";

    private static final String ZOOM_RANGE_KEY = "zoomRange";

    private static final String PAN_RANGE_KEY = "panRange";

    public AbstractAxisOptions()
    {
        super();
    }

    AbstractAxisOptions( JSONObject jsonObj )
    {
        super( jsonObj );
    }

    /**
     * Set the visibility of the axis. By default, visibility is auto-detected, i.e. the axis will show up if there's
     * data associated with it.
     */
    public T setShow( boolean show )
    {
        put( SHOW_KEY, show );
        return (T) this;
    }

    /**
     * @return the visibility of the axis
     */
    public Boolean getShow()
    {
        return getBoolean( SHOW_KEY );
    }

    /**
     * Clear the visibility of the axis
     */
    public T clearShow()
    {
        clear( SHOW_KEY );
        return (T) this;
    }

    /**
     * Set the overall placement of the legend within the plot ({@link AxisPosition#BOTTOM BOTTOM},
     * {@link AxisPosition#TOP TOP}, {@link AxisPosition#LEFT LEFT}, {@link AxisPosition#RIGHT RIGHT}). By default, the
     * placement is {@link AxisPosition#BOTTOM BOTTOM} for x axis and {@link AxisPosition#LEFT LEFT} for y axis.
     */
    public T setPosition( AxisPosition position )
    {
        assert null != position : "position can't be null";

        put( POSITION_KEY, position.getFlotValue() );
        return (T) this;
    }

    /**
     * @return the overall placement of the legend within the plot
     */
    public AxisPosition getPosition()
    {
        return AxisPosition.findByFlotValue( getString( POSITION_KEY ) );
    }

    /**
     * Clear the overall placement of the legend within the plot
     */
    public T clearPosition()
    {
        clear( POSITION_KEY );
        return (T) this;
    }

    /**
     * Set the color of the labels and ticks for the axis (default is the grid color). For more fine-grained control you
     * can also set the color of the ticks separately with {@link #setTickColor(String)} (otherwise it's autogenerated
     * as the base color with some transparency).
     */
    public T setColor( String color )
    {
        put( COLOR_KEY, color );
        return (T) this;
    }

    /**
     * @return the color of the labels and ticks for the axis
     */
    public String getColor()
    {
        return getString( COLOR_KEY );
    }

    /**
     * Clear the color of the labels and ticks for the axis
     */
    public T clearColor()
    {
        clear( COLOR_KEY );
        return (T) this;
    }

    /**
     * Set the color of the ticks. By default, it's the color of the label with some transparency.
     */
    public T setTickColor( String tickColor )
    {
        put( TICK_COLOR_KEY, tickColor );
        return (T) this;
    }

    /**
     * @return the color of the ticks
     */
    public String getTickColor()
    {
        return getString( TICK_COLOR_KEY );
    }

    /**
     * Clear the color of the ticks
     */
    public T clearTickColor()
    {
        clear( TICK_COLOR_KEY );
        return (T) this;
    }

    /**
     * Set the precise minimum value on the scale. If you don't specify it, a value will automatically be chosen based
     * on the minimum data values. Note that Flot always examines all the data values you feed to it, even if a
     * restriction on another axis may make some of them invisible (this makes interactive use more stable).
     */
    public T setMinimum( double min )
    {
        put( MIN_KEY, new Double( min ) );
        return (T) this;
    }

    /**
     * @return the precise minimum value on the scale
     */
    public Double getMinimum()
    {
        return getDouble( MIN_KEY );
    }

    /**
     * Clear the precise minimum value on the scale
     */
    public T clearMinimum()
    {
        clear( MIN_KEY );
        return (T) this;
    }

    /**
     * Set the precise maximum value on the scale. If you don't specify it, a value will automatically be chosen based
     * on the maximum data values. Note that Flot always examines all the data values you feed to it, even if a
     * restriction on another axis may make some of them invisible (this makes interactive use more stable).
     */
    public T setMaximum( double max )
    {
        put( MAX_KEY, new Double( max ) );
        return (T) this;
    }

    /**
     * @return the precise maximum value on the scale
     */
    public Double getMaximum()
    {
        return getDouble( MAX_KEY );
    }

    /**
     * Clear the precise maximum value on the scale
     */
    public T clearMaximum()
    {
        clear( MAX_KEY );
        return (T) this;
    }

    /**
     * Set the autoscaleMargin. The "autoscaleMargin" is a bit esoteric: it's the fraction of margin that the scaling
     * algorithm will add to avoid that the outermost points ends up on the grid border. Note that this margin is only
     * applied when a min or max value is not explicitly set. If a margin is specified, the plot will furthermore extend
     * the axis end-point to the nearest whole tick. The default value is "null" for the x axes and 0.02 for y axes
     * which seems appropriate for most cases.
     */
    public T setAutoscaleMargin( double margin )
    {
        put( AUTOSCALE_MARGIN_KEY, new Double( margin ) );
        return (T) this;
    }

    /**
     * @return the autoscaleMargin. The "autoscaleMargin" is a bit esoteric: it's the fraction of margin that the
     * scaling algorithm will add to avoid that the outermost points ends up on the grid border. Note that this margin
     * is only applied when a min or max value is not explicitly set. If a margin is specified, the plot will
     * furthermore extend the axis end-point to the nearest whole tick. The default value is "null" for the x axes and
     * 0.02 for y axes which seems appropriate for most cases.
     */
    public Double getAutoscaleMargin()
    {
        return getDouble( AUTOSCALE_MARGIN_KEY );
    }

    /**
     * Clear the autoscaleMargin
     */
    public T clearAutoscaleMargin()
    {
        clear( AUTOSCALE_MARGIN_KEY );
        return (T) this;
    }

    /**
     * Set the transform and inverseTransform functions. </br>You can design a function to compress or expand certain
     * parts of the axis non-linearly, e.g. suppress weekends or compress far away points with a logarithm or some other
     * means. When Flot draws the plot, each value is first put through the transform function. Here's an example, the x
     * axis can be turned into a natural logarithm axis with the following code:
     *
     * <pre>
     *   xaxis: {
     *     transform: function (v) { return Math.log(v); },
     *     inverseTransform: function (v) { return Math.exp(v); }
     *   }
     * </pre>
     *
     * Similarly, for reversing the y axis so the values appear in inverse order:
     *
     * <pre>
     *   yaxis: {
     *     transform: function (v) { return -v; },
     *     inverseTransform: function (v) { return -v; }
     *   }
     * </pre>
     *
     * Note that for finding extrema, Flot assumes that the transform function does not reorder values (it should be
     * monotone). </br> The inverseTransform is simply the inverse of the transform function (so v ==
     * inverseTransform(transform(v)) for all relevant v). It is required for converting from canvas coordinates to data
     * coordinates, e.g. for a mouse interaction where a certain pixel is clicked. If you don't use any interactive
     * features of Flot, you may not need it.
     */
    public T setTransform( TransformAxis transform )
    {
        assert null != transform : "transform can't be null";

        setTransformNative( getWrappedObj().getJavaScriptObject(), transform );
        return (T) this;
    }

    private static native void setTransformNative( JavaScriptObject axisOptions, TransformAxis transform )
    /*-{
    	axisOptions.transform = function(val) {
    		return transform.@ca.nanometrics.gflot.client.options.AbstractAxisOptions.TransformAxis::transform(D)(val);
    	};
    	axisOptions.inverseTransform = function(val) {
    		return transform.@ca.nanometrics.gflot.client.options.AbstractAxisOptions.TransformAxis::inverseTransform(D)(val);
    	};
    }-*/;

    /**
     * Clear the transform generator
     */
    public T clearTransform()
    {
        clear( "transform" );
        clear( "inverseTransform" );
        return (T) this;
    }

    /**
     * Set a fixed width on the tick label in pixels.
     */
    public T setLabelWidth( double labelWidth )
    {
        put( LABEL_WIDTH_KEY, new Double( labelWidth ) );
        return (T) this;
    }

    /**
     * @return the width on the tick label in pixels
     */
    public Double getLabelWidth()
    {
        return getDouble( LABEL_WIDTH_KEY );
    }

    /**
     * Clear the width on the tick label
     */
    public T clearLabelWidth()
    {
        clear( LABEL_WIDTH_KEY );
        return (T) this;
    }

    /**
     * Set a fixed height on the tick label in pixels.
     */
    public T setLabelHeight( double labelHeight )
    {
        put( LABEL_HEIGHT_KEY, new Double( labelHeight ) );
        return (T) this;
    }

    /**
     * @return the height on the tick label in pixels
     */
    public Double getLabelHeight()
    {
        return getDouble( LABEL_HEIGHT_KEY );
    }

    /**
     * Clear the height on the tick label
     */
    public T clearLabelHeight()
    {
        clear( LABEL_HEIGHT_KEY );
        return (T) this;
    }

    /**
     * Set if Flot should reserve space for axis even if it's not shown. It is useful in combination with labelWidth and
     * labelHeight for aligning multi-axis charts.
     */
    public T setReserveSpace( boolean reserveSpace )
    {
        put( RESERVE_SPACE_KEY, reserveSpace );
        return (T) this;
    }

    /**
     * @return true if Flot should reserve space for axis even if it's not shown
     */
    public Boolean getReserveSpace()
    {
        return getBoolean( RESERVE_SPACE_KEY );
    }

    /**
     * Clear the reserve space
     */
    public T clearReserveSpace()
    {
        clear( RESERVE_SPACE_KEY );
        return (T) this;
    }

    /**
     * Set how many ticks the tick generator algorithm aims for. The algorithm has two passes. It first estimates how
     * many ticks would be reasonable and uses this number to compute a nice round tick interval size. The algorithm
     * always tries to generate reasonably round tick values so even if you ask for three ticks, you might get five if
     * that fits better with the rounding. If you don't want any ticks at all, set to 0.
     */
    public T setTicks( double ticks )
    {
        put( TICKS_KEY, new Double( ticks ) );
        return (T) this;
    }

    /**
     * @return the number of ticks the tick generator algorithm aims for
     */
    public Double getTicksDouble()
    {
        return getDouble( TICKS_KEY );
    }

    /**
     * Set the ticks you want to show.
     */
    public T setTicks( Tick[] ticks )
    {
        put( TICKS_KEY, JSONHelper.wrapArray( ticks ) );
        return (T) this;
    }

    /**
     * @return the ticks
     */
    public Tick[] getTicksArray()
    {
        JSONArray array = getArray( TICKS_KEY );
        if ( null == array )
        {
            return null;
        }
        Tick[] ticks = new Tick[array.size()];
        for ( int i = 0; i < array.size(); i++ )
        {
            ticks[i] = new Tick( array.get( i ).isArray() );
        }
        return ticks;
    }

    /**
     * Set the tick generator.
     */
    public T setTicks( TickGenerator generator )
    {
        assert null != generator : "generator can't be null";

        setTickGeneratorNative( getWrappedObj().getJavaScriptObject(), generator );
        return (T) this;
    }

    private static native void setTickGeneratorNative( JavaScriptObject axisOptions, TickGenerator generator )
    /*-{
    	axisOptions.ticks = function(axis) {
    		var jsonAxisObject = @com.google.gwt.json.client.JSONObject::new(Lcom/google/gwt/core/client/JavaScriptObject;)(axis);
    		var javaAxisObject = @ca.nanometrics.gflot.client.Axis::new(Lcom/google/gwt/json/client/JSONObject;)(jsonAxisObject);

    		var generated = generator.@ca.nanometrics.gflot.client.options.AbstractAxisOptions.TickGenerator::generate(Lca/nanometrics/gflot/client/Axis;)(javaAxisObject);

    		var jsonArrayWrapper = @ca.nanometrics.gflot.client.util.JSONHelper::wrapArray([Lca/nanometrics/gflot/client/util/JSONWrapper;)(generated);
    		var jsonArray = @ca.nanometrics.gflot.client.util.JSONHelper::getJSONArray(Lca/nanometrics/gflot/client/util/JSONArrayWrapper;)(jsonArrayWrapper);
    		return jsonArray.@com.google.gwt.json.client.JSONArray::getJavaScriptObject()();
    	};
    }-*/;

    /**
     * Clear the ticks
     */
    public T clearTicks()
    {
        clear( TICKS_KEY );
        return (T) this;
    }

    /**
     * Set the tick formatter.
     */
    public T setTickFormatter( TickFormatter tickFormatter )
    {
        setTickFormatterNative( getWrappedObj().getJavaScriptObject(), tickFormatter );
        return (T) this;
    }

    private static native void setTickFormatterNative( JavaScriptObject axisOptions, TickFormatter tickFormatter )
    /*-{
    	axisOptions.tickFormatter = function(val, axis) {
    		var jsonAxisObject = @com.google.gwt.json.client.JSONObject::new(Lcom/google/gwt/core/client/JavaScriptObject;)(axis);
    		var javaAxisObject = @ca.nanometrics.gflot.client.Axis::new(Lcom/google/gwt/json/client/JSONObject;)(jsonAxisObject);
    		return tickFormatter.@ca.nanometrics.gflot.client.options.TickFormatter::formatTickValue(DLca/nanometrics/gflot/client/Axis;)(val, javaAxisObject);
    	};
    }-*/;

    /**
     * Clear the tick formatter
     */
    public T clearTickFormatter()
    {
        clear( "tickFormatter" );
        return (T) this;
    }

    /**
     * Set the length of the tick lines in pixels. By default, the innermost axes will have ticks that extend all across
     * the plot, while any extra axes use small ticks. A value of null means use the default, while a number means small
     * ticks of that length - set it to 0 to hide the lines completely.
     */
    public T setTickLength( double tickLength )
    {
        put( TICK_LENGTH_KEY, new Double( tickLength ) );
        return (T) this;
    }

    /**
     * @return the length of the tick lines in pixels.
     */
    public Double getTickLength()
    {
        return getDouble( TICK_LENGTH_KEY );
    }

    /**
     * Clear the length of the tick lines
     */
    public T clearTickLength()
    {
        clear( TICK_LENGTH_KEY );
        return (T) this;
    }

    /**
     * If you set "alignTicksWithAxis" to the number of another axis, e.g. alignTicksWithAxis: 1, Flot will ensure that
     * the autogenerated ticks of this axis are aligned with the ticks of the other axis. This may improve the looks,
     * e.g. if you have one y axis to the left and one to the right, because the grid lines will then match the ticks in
     * both ends. The trade-off is that the forced ticks won't necessarily be at natural places.
     */
    public T setAlignTicksWithAxis( int axisNumber )
    {
        put( ALIGN_TICKS_KEY, axisNumber );
        return (T) this;
    }

    /**
     * @return the number of the axis aligned with this one
     */
    public Integer getAlignTicksWithAxis()
    {
        return getInteger( ALIGN_TICKS_KEY );
    }

    /**
     * Clear the number of the axis aligned with this one
     */
    public T clearAlignTicksWithAxis()
    {
        clear( ALIGN_TICKS_KEY );
        return (T) this;
    }

    /**
     * Clear the tick size
     */
    public T clearTickSize()
    {
        clear( TICK_SIZE_KEY );
        return (T) this;
    }

    /**
     * Clear the minimum tick size key
     */
    public T clearMinTickSize()
    {
        clear( MIN_TICK_SIZE_KEY );
        return (T) this;
    }

    /**
     * Set the label of the axis
     */
    public T setLabel( String label )
    {
        put( AXIS_LABEL_KEY, label );
        return (T) this;
    }

    /**
     * @return the label of the axis
     */
    public String getLabel()
    {
        return getString( AXIS_LABEL_KEY );
    }

    /**
     * Clear the label
     */
    public T clearLabel()
    {
        clear( AXIS_LABEL_KEY );
        return (T) this;
    }

    /**
     * Set the padding, in pixels, between the tick labels and the axis label (default: 2)
     */
    public T setLabelPadding( int labelPadding )
    {
        put( AXIS_LABEL_PADDING_KEY, labelPadding );
        return (T) this;
    }

    /**
     * @return the padding, in pixels, between the tick labels and the axis label (default: 2)
     */
    public Integer getLabelPadding()
    {
        return getInteger( AXIS_LABEL_PADDING_KEY );
    }

    /**
     * Clear the padding between the tick labels and the aixs label
     */
    public T clearLabelPadding()
    {
        clear( AXIS_LABEL_PADDING_KEY );
        return (T) this;
    }

    /**
     * By default, if supported, flot-axislabels uses CSS transforms to render label. You can force either canvas or
     * HTML mode.
     */
    public T setLabelRenderingMode( AxisLabelRenderingMode mode )
    {
        assert null != mode : "mode can't be null";

        switch ( mode )
        {
            case CSS:
                put( AXIS_LABEL_RENDERING_MODE_CANVAS_KEY, false );
                put( AXIS_LABEL_RENDERING_MODE_HTML_KEY, false );
                break;
            case CANVAS:
                put( AXIS_LABEL_RENDERING_MODE_CANVAS_KEY, true );
                put( AXIS_LABEL_RENDERING_MODE_HTML_KEY, false );
                break;
            case HTML:
                put( AXIS_LABEL_RENDERING_MODE_CANVAS_KEY, false );
                put( AXIS_LABEL_RENDERING_MODE_HTML_KEY, true );
                break;
        }
        return (T) this;
    }

    /**
     * @return the axis label rendering mode
     */
    public AxisLabelRenderingMode getLabelRenderingMode()
    {
        Boolean mode = getBoolean( AXIS_LABEL_RENDERING_MODE_HTML_KEY );
        if ( null != mode && mode )
        {
            return AxisLabelRenderingMode.HTML;
        }

        mode = getBoolean( AXIS_LABEL_RENDERING_MODE_CANVAS_KEY );
        if ( null != mode && mode )
        {
            return AxisLabelRenderingMode.CANVAS;
        }

        return AxisLabelRenderingMode.CSS;
    }

    /**
     * Clear the axis label rendering mode
     */
    public T clearLabelRenderingMode()
    {
        clear( AXIS_LABEL_RENDERING_MODE_CANVAS_KEY );
        clear( AXIS_LABEL_RENDERING_MODE_HTML_KEY );
        return (T) this;
    }

    /**
     * Set the font family of the font (default: sans-serif). Only the canvas mode supports this option.
     */
    public T setLabelFontFamily( String label )
    {
        put( AXIS_LABEL_CANVAS_FONT_FAMILY_KEY, label );
        return (T) this;
    }

    /**
     * @return the font family of the font (default: sans-serif). Only the canvas mode supports this option.
     */
    public String getLabelFontFamily()
    {
        return getString( AXIS_LABEL_CANVAS_FONT_FAMILY_KEY );
    }

    /**
     * Clear the font family
     */
    public T clearLabelFontFamily()
    {
        clear( AXIS_LABEL_CANVAS_FONT_FAMILY_KEY );
        return (T) this;
    }

    /**
     * Set the size, in pixels, of the font (default: 14). Only the canvas mode supports this option.
     */
    public T setLabelFontSize( int labelPadding )
    {
        put( AXIS_LABEL_CANVAS_FONT_SIZE_KEY, labelPadding );
        return (T) this;
    }

    /**
     * @return the size, in pixels, of the font (default: 14). Only the canvas mode supports this option.
     */
    public Integer getLabelFontSize()
    {
        return getInteger( AXIS_LABEL_CANVAS_FONT_SIZE_KEY );
    }

    /**
     * Clear the size of the font
     */
    public T clearLabelFontSize()
    {
        clear( AXIS_LABEL_CANVAS_FONT_SIZE_KEY );
        return (T) this;
    }

    /**
     * Sets the property of ZoomRange to false to disable it
     */
    public T setZoomRange( boolean zoomEnabled )
    {
        put( ZOOM_RANGE_KEY, zoomEnabled );
        return (T) this;
    }

    /**
     * Sets the property of ZoomRange, the interval which zooming can happen, receives as parameters the min and max
     * values
     */
    public T setZoomRange( double min, double max )
    {
        return setZoomRange( new Double[] { min, max } );
    }

    /**
     * Sets the property of ZoomRange, the interval in which zooming can happen, receives as a parameter an array or a
     * null value
     */
    public T setZoomRange( Double[] range )
    {
        if ( range != null )
        {
            assert range.length == 2 : "Array length must be 2";
            put( ZOOM_RANGE_KEY, JSONHelper.wrapArray( range ) );
        }
        else
        {
            put( ZOOM_RANGE_KEY, (JSONArrayWrapper) null );
        }
        return (T) this;
    }

    /**
     * @return the interval in which zooming can happen
     */
    public Double[] getZoomRange()
    {
        JSONArray array = getArray( ZOOM_RANGE_KEY );
        if ( null != array )
        {
            return new Double[] { array.get( 0 ).isNumber().doubleValue(), array.get( 1 ).isNumber().doubleValue() };
        }

        return null;
    }

    /**
     * Clear the zoom range
     */
    public T clearZoomRange()
    {
        clear( ZOOM_RANGE_KEY );
        return (T) this;
    }

    /**
     * Sets the property of PanRange to false to disable it
     */
    public T setPanRange( boolean panEnabled )
    {
        put( PAN_RANGE_KEY, panEnabled );
        return (T) this;
    }

    /**
     * Sets the property PanRange, confines the panning to stay within a range, receives as parameters the min and max
     * values
     */
    public T setPanRange( double min, double max )
    {
        return setPanRange( new Double[] { min, max } );
    }

    /**
     * Sets the property PanRange, confines the panning to stay within a range, receives as a parameter an array or a
     * null value
     */
    public T setPanRange( Double[] range )
    {
        if ( range != null )
        {
            assert range.length == 2 : "Array length must be 2";
            put( PAN_RANGE_KEY, JSONHelper.wrapArray( range ) );
        }
        else
        {
            put( PAN_RANGE_KEY, (JSONArrayWrapper) null );
        }
        return (T) this;
    }

    /**
     * @return an array with minimum and maximum values for panning
     */
    public Double[] getPanRange()
    {
        JSONArray array = getArray( PAN_RANGE_KEY );
        if ( null != array )
        {
            return new Double[] { array.get( 0 ).isNumber().doubleValue(), array.get( 1 ).isNumber().doubleValue() };
        }

        return null;
    }

    /**
     * Clear the pan range
     */
    public T clearPanRange()
    {
        clear( PAN_RANGE_KEY );
        return (T) this;
    }
}
