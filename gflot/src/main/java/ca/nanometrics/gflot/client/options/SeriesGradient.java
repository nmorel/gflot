package ca.nanometrics.gflot.client.options;

import ca.nanometrics.gflot.client.util.JSONHelper;
import ca.nanometrics.gflot.client.util.JSONObjectWrapper;
import ca.nanometrics.gflot.client.util.JSONWrapper;

import com.google.gwt.json.client.JSONObject;

public class SeriesGradient
    extends JSONObjectWrapper
{
    public class Gradient
        extends JSONObjectWrapper
    {
        public Gradient( Double opacity, Double brightness )
        {
            super();
            if ( null != opacity )
            {
                put( OPACITY_KEY, opacity );
            }
            if ( null != brightness )
            {
                put( BRIGHTNESS_KEY, brightness );
            }
        }

        Gradient( JSONObject jsonObj )
        {
            super( jsonObj );
        }

        public Double getOpacity()
        {
            return getDouble( OPACITY_KEY );
        }

        public Double getBrightness()
        {
            return getDouble( BRIGHTNESS_KEY );
        }
    }

    protected static final String FILL_COLOR_COLORS_KEY = "colors";

    protected static final String OPACITY_KEY = "opacity";

    protected static final String BRIGHTNESS_KEY = "brightness";

    private Gradient from;

    private Gradient to;

    public SeriesGradient( Double fromOpacity, Double fromBrightness, Double toOpacity, Double toBrightness )
    {
        from = new Gradient( fromOpacity, fromBrightness );
        to = new Gradient( toOpacity, toBrightness );
        put( FILL_COLOR_COLORS_KEY, JSONHelper.wrapArray( new JSONWrapper[] { from, to } ) );
    }

    SeriesGradient( JSONObject jsonObj )
    {
        super( jsonObj );
        from = new Gradient( getArray( FILL_COLOR_COLORS_KEY ).get( 0 ).isObject() );
        to = new Gradient( getArray( FILL_COLOR_COLORS_KEY ).get( 1 ).isObject() );
    }

    public Gradient getFrom()
    {
        return from;
    }

    public Gradient getTo()
    {
        return to;
    }
}
