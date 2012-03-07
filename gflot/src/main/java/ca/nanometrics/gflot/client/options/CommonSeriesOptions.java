package ca.nanometrics.gflot.client.options;

import ca.nanometrics.gflot.client.util.JSONObjectWrapper;

import com.google.gwt.json.client.JSONObject;

/**
 * Options common to global series options and specific series options
 *
 * @author Nicolas Morel
 */
@SuppressWarnings( "unchecked" )
public abstract class CommonSeriesOptions<T extends CommonSeriesOptions<?>>
    extends JSONObjectWrapper
{
    private static final String LINE_SERIES_KEY = "lines";

    private static final String BAR_SERIES_KEY = "bars";

    private static final String POINTS_SERIES_KEY = "points";

    private static final String IMAGES_SERIES_KEY = "images";

    private static final String PIE_SERIES_KEY = "pie";

    private static final String SHADOW_SIZE_KEY = "shadowSize";

    private static final String STACK_KEY = "stack";

    private LineSeriesOptions lineSeriesOptions;

    private BarSeriesOptions barSeriesOptions;

    private PointsSeriesOptions pointsSeriesOptions;

    private ImageSeriesOptions imageSeriesOptions;

    private PieSeriesOptions pieSeriesOptions;

    public CommonSeriesOptions()
    {
    }

    public CommonSeriesOptions( JSONObject jsonObj )
    {
        super( jsonObj );
        lineSeriesOptions = new LineSeriesOptions( getObject( LINE_SERIES_KEY ) );
        barSeriesOptions = new BarSeriesOptions( getObject( BAR_SERIES_KEY ) );
        pointsSeriesOptions = new PointsSeriesOptions( getObject( POINTS_SERIES_KEY ) );
        imageSeriesOptions = new ImageSeriesOptions( getObject( IMAGES_SERIES_KEY ) );
        pieSeriesOptions = new PieSeriesOptions( getObject( PIE_SERIES_KEY ) );
    }

    /**
     * Set global Line series options that will be used unless options are set directly to the series
     */
    public T setLineSeriesOptions( LineSeriesOptions lineSeriesOptions )
    {
        this.lineSeriesOptions = lineSeriesOptions;
        put( LINE_SERIES_KEY, lineSeriesOptions );
        return (T) this;
    }

    /**
     * @return global Line series options
     */
    public LineSeriesOptions getLineSeriesOptions()
    {
        return lineSeriesOptions;
    }

    /**
     * Set global Bar series options that will be used unless options are set directly to the series
     */
    public T setBarsSeriesOptions( BarSeriesOptions barSeriesOptions )
    {
        this.barSeriesOptions = barSeriesOptions;
        put( BAR_SERIES_KEY, barSeriesOptions );
        return (T) this;
    }

    /**
     * @return global Bar series options
     */
    public BarSeriesOptions getBarSeriesOptions()
    {
        return barSeriesOptions;
    }

    /**
     * Set global Points series options that will be used unless options are set directly to the series
     */
    public T setPointsOptions( PointsSeriesOptions pointsSeriesOptions )
    {
        this.pointsSeriesOptions = pointsSeriesOptions;
        put( POINTS_SERIES_KEY, pointsSeriesOptions );
        return (T) this;
    }

    /**
     * @return global Points series options
     */
    public PointsSeriesOptions getPointsSeriesOptions()
    {
        return pointsSeriesOptions;
    }

    /**
     * Set global Image series options that will be used unless options are set directly to the series
     */
    public T setImageSeriesOptions( ImageSeriesOptions imageSeriesOptions )
    {
        this.imageSeriesOptions = imageSeriesOptions;
        put( IMAGES_SERIES_KEY, imageSeriesOptions );
        return (T) this;
    }

    /**
     * @return global Image series options
     */
    public ImageSeriesOptions getImageSeriesOptions()
    {
        return imageSeriesOptions;
    }

    /**
     * Set global Pie series options that will be used unless options are set directly to the series
     */
    public T setPieSeriesOptions( PieSeriesOptions pieSeriesOptions )
    {
        this.pieSeriesOptions = pieSeriesOptions;
        put( PIE_SERIES_KEY, pieSeriesOptions );
        return (T) this;
    }

    /**
     * @return global Pie series options
     */
    public PieSeriesOptions getPieSeriesOptions()
    {
        return pieSeriesOptions;
    }

    /**
     * Set the size of shadows in pixels for all series. Set it to 0 to remove shadows.
     */
    public T setShadowSize( double shadow )
    {
        assert shadow >= 0 : "shadowSize must be >= 0";

        put( SHADOW_SIZE_KEY, shadow );
        return (T) this;
    }

    /**
     * @return the size of shadows in pixels
     */
    public Double getShadowSize()
    {
        return getDouble( SHADOW_SIZE_KEY );
    }

    /**
     * Set the stack key option. Two or more series are stacked when their "stack" attribute is set to the same key
     * (which can be any number or string or just "true"). The stacking order is determined by the order of the data
     * series in the array (later series end up on top of the previous).
     */
    public T setStack( String key )
    {
        put( STACK_KEY, key );
        return (T) this;
    }

    /**
     * Set the stack key option. Two or more series are stacked when their "stack" attribute is set to the same key
     * (which can be any number or string or just "true"). The stacking order is determined by the order of the data
     * series in the array (later series end up on top of the previous).
     */
    public T setStack( int key )
    {
        put( STACK_KEY, key );
        return (T) this;
    }

    /**
     * Set the stack key option. Two or more series are stacked when their "stack" attribute is set to the same key
     * (which can be any number or string or just "true"). The stacking order is determined by the order of the data
     * series in the array (later series end up on top of the previous).
     */
    public T setStack( boolean stack )
    {
        put( STACK_KEY, stack );
        return (T) this;
    }

    /**
     * @return the stack option
     */
    public String getStackAsKeyString()
    {
        return getString( STACK_KEY );
    }

    /**
     * @return the stack option
     */
    public Integer getStackAsKeyNumber()
    {
        return getInteger( STACK_KEY );
    }

    /**
     * @return the stack option
     */
    public Boolean getStackAsBoolean()
    {
        return getBoolean( STACK_KEY );
    }
}
