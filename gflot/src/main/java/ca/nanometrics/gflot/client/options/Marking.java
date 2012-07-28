package ca.nanometrics.gflot.client.options;

import ca.nanometrics.gflot.client.util.JSONObjectWrapper;

import com.google.gwt.json.client.JSONObject;

/**
 * @author Mohamed M. El-Kalioby
 * @since September 21, 2009
 */
public class Marking
    extends JSONObjectWrapper
{
    private static final String X_AXIS_KEY_PREFIX = "x";
    private static final String X_AXIS_KEY_SUFFIX = "axis";
    private static final String Y_AXIS_KEY_PREFIX = "y";
    private static final String Y_AXIS_KEY_SUFFIX = "axis";
    private static final String COLOR_KEY = "color";
    private static final String LINE_WIDTH_KEY = "lineWidth";

    public Marking()
    {
        super();
    }

    Marking( JSONObject jsonObj )
    {
        super( jsonObj );
    }

    /**
     * Set the range for the first x axis
     */
    public Marking setX( Range xRange )
    {
        return setX( xRange, 1 );
    }

    /**
     * Set the range for x axis number xAxisNumber
     *
     * @param xAxisNumber number of the x axis, starting at 1
     */
    public Marking setX( Range xRange, int xAxisNumber )
    {
        assert xAxisNumber > 0 : "xAxisNumber starts at 1";
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

    /**
     * Set the range for the first y axis
     */
    public Marking setY( Range yRange )
    {
        return setY( yRange, 1 );
    }

    /**
     * Set the range for y axis number yAxisNumber
     *
     * @param yAxisNumber number of the y axis, starting at 1
     */
    public Marking setY( Range yRange, int yAxisNumber )
    {
        assert yAxisNumber > 0 : "yAxisNumber starts at 1";
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

    /**
     * Set the marking color
     */
    public Marking setColor( String color )
    {
        put( COLOR_KEY, color );
        return this;
    }

    /**
     * @return the marking color
     */
    public String getColor()
    {
        return getString( COLOR_KEY );
    }

    /**
     * Clear the marking color
     */
    public Marking clearColor()
    {
        clear( COLOR_KEY );
        return this;
    }

    /**
     * Set the line width
     */
    public Marking setLineWidth( int lineWidth )
    {
        put( LINE_WIDTH_KEY, lineWidth );
        return this;
    }

    /**
     * @return the line width
     */
    public Integer getLineWidth()
    {
        return getInteger( LINE_WIDTH_KEY );
    }

    /**
     * Clear the line width
     */
    public Marking clearLineWidth()
    {
        clear( LINE_WIDTH_KEY );
        return this;
    }

}
