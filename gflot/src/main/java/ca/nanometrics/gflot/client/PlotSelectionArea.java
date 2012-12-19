package ca.nanometrics.gflot.client;

import ca.nanometrics.gflot.client.jsni.JsonObject;
import ca.nanometrics.gflot.client.options.Range;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * @author Nicolas Morel
 */
public class PlotSelectionArea
    extends JsonObject
{
    /**
     * Creates a {@link PlotSelectionArea}
     */
    public static final PlotSelectionArea create()
    {
        return JavaScriptObject.createObject().cast();
    }

    private static final String X_AXIS_KEY_PREFIX = "x";
    private static final String X_AXIS_KEY_SUFFIX = "axis";
    private static final String Y_AXIS_KEY_PREFIX = "y";
    private static final String Y_AXIS_KEY_SUFFIX = "axis";

    protected PlotSelectionArea()
    {
    }

    public final PlotSelectionArea setX( Range xRange )
    {
        return setX( xRange, 1 );
    }

    public final PlotSelectionArea setX( Range xRange, int xAxisNumber )
    {
        if ( xAxisNumber == 1 )
        {
            put( X_AXIS_KEY_PREFIX + X_AXIS_KEY_SUFFIX, xRange );
        }
        else
        {
            put( X_AXIS_KEY_PREFIX + xAxisNumber + X_AXIS_KEY_SUFFIX, xRange );
        }
        return this;
    }

    /**
     * @return the range for x axis
     */
    public final Range getX()
    {
        return getX( 1 );
    }

    /**
     * @return the range for x axis
     */
    public final Range getX( int xAxisNumber )
    {
        assert xAxisNumber > 0 : "xAxisNumber starts at 1";
        if ( xAxisNumber == 1 )
        {
            return getJsObject( X_AXIS_KEY_PREFIX + X_AXIS_KEY_SUFFIX );
        }
        else
        {
            return getJsObject( X_AXIS_KEY_PREFIX + xAxisNumber + X_AXIS_KEY_SUFFIX );
        }
    }

    public final PlotSelectionArea setY( Range yRange )
    {
        return setY( yRange, 1 );
    }

    public final PlotSelectionArea setY( Range yRange, int yAxisNumber )
    {
        if ( yAxisNumber == 1 )
        {
            put( Y_AXIS_KEY_PREFIX + Y_AXIS_KEY_SUFFIX, yRange );
        }
        else
        {
            put( Y_AXIS_KEY_PREFIX + yAxisNumber + Y_AXIS_KEY_SUFFIX, yRange );
        }
        return this;
    }

    /**
     * @return the range for y axis
     */
    public final Range getY()
    {
        return getY( 1 );
    }

    /**
     * @return the range for y axis
     */
    public final Range getY( int yAxisNumber )
    {
        assert yAxisNumber > 0 : "yAxisNumber starts at 1";
        if ( yAxisNumber == 1 )
        {
            return getJsObject( Y_AXIS_KEY_PREFIX + Y_AXIS_KEY_SUFFIX );
        }
        else
        {
            return getJsObject( Y_AXIS_KEY_PREFIX + yAxisNumber + Y_AXIS_KEY_SUFFIX );
        }
    }

}
