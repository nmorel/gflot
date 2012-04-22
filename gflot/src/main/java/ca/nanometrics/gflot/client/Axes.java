package ca.nanometrics.gflot.client;

import ca.nanometrics.gflot.client.util.JSONObjectWrapper;

import com.google.gwt.json.client.JSONObject;

/**
 * @author Nicolas Morel
 */
public class Axes
    extends JSONObjectWrapper
{
    private static final String X_AXIS_KEY_PREFIX = "x";
    private static final String X_AXIS_KEY_SUFFIX = "axis";
    private static final String Y_AXIS_KEY_PREFIX = "y";
    private static final String Y_AXIS_KEY_SUFFIX = "axis";

    public Axes()
    {
        super();
    }

    protected Axes( JSONObject jsonObj )
    {
        super( jsonObj );
    }

    /**
     * @return the x axis
     */
    public Axis getX()
    {
        return getX( 1 );
    }

    /**
     * @return the x axis
     */
    public Axis getX( int xAxisNumber )
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
            return new Axis( obj );
        }
    }

    /**
     * @return the y axis
     */
    public Axis getY()
    {
        return getY( 1 );
    }

    /**
     * @return the y axis
     */
    public Axis getY( int yAxisNumber )
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
            return new Axis( obj );
        }
    }

}
