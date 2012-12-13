package ca.nanometrics.gflot.client;

import ca.nanometrics.gflot.client.jsni.JsonObject;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * @author Nicolas Morel
 */
public class Axes
    extends JsonObject
{
    /**
     * Creates a {@link Axes}
     */
    public static final Axes create()
    {
        return JavaScriptObject.createObject().cast();
    }

    private static final String X_AXIS_KEY_PREFIX = "x";
    private static final String X_AXIS_KEY_SUFFIX = "axis";
    private static final String Y_AXIS_KEY_PREFIX = "y";
    private static final String Y_AXIS_KEY_SUFFIX = "axis";

    public Axes()
    {
    }

    /**
     * @return the x axis
     */
    public final Axis getX()
    {
        return getX( 1 );
    }

    /**
     * @return the x axis
     */
    public final Axis getX( int xAxisNumber )
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

    /**
     * @return the y axis
     */
    public final Axis getY()
    {
        return getY( 1 );
    }

    /**
     * @return the y axis
     */
    public final Axis getY( int yAxisNumber )
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
