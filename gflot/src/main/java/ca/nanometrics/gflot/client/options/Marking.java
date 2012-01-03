package ca.nanometrics.gflot.client.options;

import ca.nanometrics.gflot.client.util.JSONObjectWrapper;

import com.google.gwt.json.client.JSONObject;

/**
 * @author Mohamed M. El-Kalioby
 * @since Septmeber 21, 2009
 */
public class Marking
    extends JSONObjectWrapper
{
    private static final String X_AXIS_KEY = "xaxis";

    private static final String Y_AXIS_KEY = "yaxis";

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
     * Set the range for x axis
     */
    public Marking setX( Range X )
    {
        put( X_AXIS_KEY, X );
        return this;
    }

    /**
     * @return the range for x axis
     */
    public Range getX()
    {
        JSONObject obj = getObject( X_AXIS_KEY );
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
     * Set the range for y axis
     */
    public Marking setY( Range Y )
    {
        put( Y_AXIS_KEY, Y );
        return this;
    }

    /**
     * @return the range for y axis
     */
    public Range getY()
    {
        JSONObject obj = getObject( Y_AXIS_KEY );
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

}
