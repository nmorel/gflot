package ca.nanometrics.gflot.client;

import ca.nanometrics.gflot.client.options.Range;
import ca.nanometrics.gflot.client.util.JSONObjectWrapper;

import com.google.gwt.json.client.JSONObject;

/**
 * @author Nicolas Morel
 */
public class PlotSelectionArea
    extends JSONObjectWrapper
{
    private static final String X_AXIS_KEY_PREFIX = "x";
    private static final String X_AXIS_KEY_SUFFIX = "axis";
    private static final String Y_AXIS_KEY_PREFIX = "y";
    private static final String Y_AXIS_KEY_SUFFIX = "axis";

    public PlotSelectionArea()
    {
        super();
    }

    protected PlotSelectionArea( JSONObject jsonObj )
    {
        super( jsonObj );
    }

    public PlotSelectionArea setX( Range xRange )
    {
        return setX( xRange, 1 );
    }

    public PlotSelectionArea setX( Range xRange, int xAxisNumber )
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
    public Range getX()
    {
        return getX( 1 );
    }

    /**
     * @return the range for x axis
     */
    public Range getX( int xAxisNumber )
    {
        assert xAxisNumber > 0 : "xAxisNumber starts at 1";
        JSONObject obj = null;
        if ( xAxisNumber == 1 )
        {
            obj = getObject( X_AXIS_KEY_PREFIX + X_AXIS_KEY_SUFFIX );
        }
        else
        {
            obj = getObject( X_AXIS_KEY_PREFIX + xAxisNumber + X_AXIS_KEY_SUFFIX );
        }
        if ( null == obj )
        {
            return null;
        }
        else
        {
            return new Range( obj );
        }
    }

    public PlotSelectionArea setY( Range yRange )
    {
        return setY( yRange, 1 );
    }

    public PlotSelectionArea setY( Range yRange, int yAxisNumber )
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
    public Range getY()
    {
        return getY( 1 );
    }

    /**
     * @return the range for y axis
     */
    public Range getY( int yAxisNumber )
    {
        assert yAxisNumber > 0 : "yAxisNumber starts at 1";
        JSONObject obj = null;
        if ( yAxisNumber == 1 )
        {
            obj = getObject( Y_AXIS_KEY_PREFIX + Y_AXIS_KEY_SUFFIX );
        }
        else
        {
            obj = getObject( Y_AXIS_KEY_PREFIX + yAxisNumber + Y_AXIS_KEY_SUFFIX );
        }
        if ( null == obj )
        {
            return null;
        }
        else
        {
            return new Range( obj );
        }
    }

}
