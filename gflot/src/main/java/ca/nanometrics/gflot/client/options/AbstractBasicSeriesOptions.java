package ca.nanometrics.gflot.client.options;


/**
 * @author Nicolas Morel
 */
@SuppressWarnings( "unchecked" )
public abstract class AbstractBasicSeriesOptions<T extends AbstractBasicSeriesOptions<?>>
    extends AbstractSeriesOptions<T>
{

    protected static final String LINE_WIDTH_KEY = "lineWidth";
    protected static final String FILL_KEY = "fill";
    protected static final String FILL_COLOR_KEY = "fillColor";

    protected AbstractBasicSeriesOptions()
    {
    }

    /**
     * Set the thickness of the line or outline in pixels. You can set it to 0 to prevent a line or outline from being
     * drawn; this will also hide the shadow.
     */
    public final T setLineWidth( double lineWidth )
    {
        put( LINE_WIDTH_KEY, new Double( lineWidth ) );
        return (T) this;
    }

    /**
     * @return the thickness of the line or outline in pixels
     */
    public final Double getLineWidth()
    {
        return getDouble( LINE_WIDTH_KEY );
    }

    /**
     * Clear the thickness of the line or outline
     */
    public final T clearLineWidth()
    {
        clear( LINE_WIDTH_KEY );
        return (T) this;
    }

    /**
     * Set if the shape should be filled. For lines, this produces area graphs. You can use setFillColor "fillColor" to
     * specify the color of the fill.
     */
    public final T setFill( boolean fill )
    {
        put( FILL_KEY, fill );
        return (T) this;
    }

    /**
     * @return true if the shape is filled
     */
    public final Boolean getFillBoolean()
    {
        return getBoolean( FILL_KEY );
    }

    /**
     * Set the opacity of the fill by setting fill to a number between 0 (fully transparent) and 1 (fully opaque).
     */
    public final T setFill( double opacity )
    {
        assert opacity >= 0 && opacity <= 1 : "opacity range from 0.0 to 1.0";

        put( FILL_KEY, opacity );
        return (T) this;
    }

    /**
     * @return the opacity of the fill
     */
    public final Double getFillOpacity()
    {
        return getDouble( FILL_KEY );
    }

    /**
     * Clear the fill property and/or opacity
     */
    public final T clearFill()
    {
        clear( FILL_KEY );
        return (T) this;
    }

    /**
     * Set the color to fill. If "fillColor" evaluates to false (default for everything except points which are filled
     * with white), the fill color is auto-set to the color of the data series.
     */
    public final T setFillColor( String cssColor )
    {
        put( FILL_COLOR_KEY, cssColor );
        return (T) this;
    }

    /**
     * @return the color to fill
     */
    public final String getFillColor()
    {
        return getString( FILL_COLOR_KEY );
    }

    /**
     * Set the scaling of the brightness and the opacity of the series fill color
     */
    public final T setFillColor( Double fromOpacity, Double fromBrightness, Double toOpacity, Double toBrightness )
    {
        assert ( null != fromOpacity || null != fromBrightness ) && ( null != toOpacity || null != toBrightness ) : "at least of the parameters must not be null";
        assert null == fromOpacity || fromOpacity >= 0 && fromOpacity <= 1 : "fromOpacity range from 0.0 to 1.0";
        assert null == fromBrightness || fromBrightness >= 0 && fromBrightness <= 1 : "fromBrightness range from 0.0 to 1.0";
        assert null == toOpacity || toOpacity >= 0 && toOpacity <= 1 : "toOpacity range from 0.0 to 1.0";
        assert null == toBrightness || toBrightness >= 0 && toBrightness <= 1 : "toBrightness range from 0.0 to 1.0";

        put( FILL_COLOR_KEY, SeriesGradient.of( fromOpacity, fromBrightness, toOpacity, toBrightness ) );

        return (T) this;
    }

    /**
     * @return the scaling of the brightness and the opacity of the series fill color
     */
    public final SeriesGradient getFillColorGradient()
    {
        return getJsObject( FILL_COLOR_KEY );
    }

    /**
     * Clear the fill color
     */
    public final T clearFillColor()
    {
        clear( FILL_COLOR_KEY );
        return (T) this;
    }
}
