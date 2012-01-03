package ca.nanometrics.gflot.client.options;

import com.google.gwt.json.client.JSONObject;

import ca.nanometrics.gflot.client.util.JSONObjectWrapper;

/**
 * options for all series. If you need to define specific option for your series, use the options on SeriesHandler and
 * Series
 *
 * @author Nicolas Morel
 */
public class GlobalSeriesOptions
    extends JSONObjectWrapper
{
    private static final String LINE_SERIES_KEY = "lines";

    private static final String BAR_SERIES_KEY = "bars";

    private static final String POINTS_SERIES_KEY = "points";

    private static final String SHADOW_SIZE_KEY = "shadowSize";

    private LineSeriesOptions lineSeriesOptions;

    private BarSeriesOptions barSeriesOptions;

    private PointsSeriesOptions pointsSeriesOptions;

    public GlobalSeriesOptions()
    {
    }

    GlobalSeriesOptions( JSONObject jsonObj )
    {
        super( jsonObj );
        lineSeriesOptions = new LineSeriesOptions( getObject( LINE_SERIES_KEY ) );
        barSeriesOptions = new BarSeriesOptions( getObject( BAR_SERIES_KEY ) );
        pointsSeriesOptions = new PointsSeriesOptions( getObject( POINTS_SERIES_KEY ) );
    }

    /**
     * Set global Line series options that will be used unless options are set directly to the series
     */
    public GlobalSeriesOptions setLineSeriesOptions( LineSeriesOptions lineSeriesOptions )
    {
        this.lineSeriesOptions = lineSeriesOptions;
        put( LINE_SERIES_KEY, lineSeriesOptions );
        return this;
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
    public GlobalSeriesOptions setBarsSeriesOptions( BarSeriesOptions barSeriesOptions )
    {
        this.barSeriesOptions = barSeriesOptions;
        put( BAR_SERIES_KEY, barSeriesOptions );
        return this;
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
    public GlobalSeriesOptions setPointsOptions( PointsSeriesOptions pointsSeriesOptions )
    {
        this.pointsSeriesOptions = pointsSeriesOptions;
        put( POINTS_SERIES_KEY, pointsSeriesOptions );
        return this;
    }

    /**
     * @return global Points series options
     */
    public PointsSeriesOptions getPointsSeriesOptions()
    {
        return pointsSeriesOptions;
    }

    /**
     * Set the size of shadows in pixels for all series. Set it to 0 to remove shadows.
     */
    public GlobalSeriesOptions setShadowSize( double shadow )
    {
        assert shadow >= 0 : "shadowSize must be >= 0";

        put( SHADOW_SIZE_KEY, shadow );
        return this;
    }

    /**
     * @return the size of shadows in pixels
     */
    public Double getShadowSize()
    {
        return getDouble( SHADOW_SIZE_KEY );
    }
}
