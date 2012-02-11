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

    private static final String SHADOW_SIZE_KEY = "shadowSize";

    private LineSeriesOptions lineSeriesOptions;

    private BarSeriesOptions barSeriesOptions;

    private PointsSeriesOptions pointsSeriesOptions;

    private ImageSeriesOptions imageSeriesOptions;

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
     * @return global Points series options
     */
    public ImageSeriesOptions getImageSeriesOptions()
    {
        return imageSeriesOptions;
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
}
