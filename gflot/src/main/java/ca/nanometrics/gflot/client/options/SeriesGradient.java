package ca.nanometrics.gflot.client.options;

import ca.nanometrics.gflot.client.jsni.JsonObject;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class SeriesGradient
    extends JsonObject
{
    public static class Gradient
        extends JsonObject
    {
        /**
         * Creates a {@link Gradient} with specified gradient
         */
        public static final Gradient of( Double opacity, Double brightness )
        {
            Gradient gradient = JavaScriptObject.createObject().cast();
            if ( null != opacity )
            {
                gradient.put( OPACITY_KEY, opacity );
            }
            if ( null != brightness )
            {
                gradient.put( BRIGHTNESS_KEY, brightness );
            }
            return gradient;
        }

        protected Gradient( Double opacity, Double brightness )
        {
        }

        public final Double getOpacity()
        {
            return getDouble( OPACITY_KEY );
        }

        public final Double getBrightness()
        {
            return getDouble( BRIGHTNESS_KEY );
        }
    }

    
    /**
     * Creates a {@link SeriesGradient} with specified gradient
     */
    public static SeriesGradient of( Double fromOpacity, Double fromBrightness, Double toOpacity, Double toBrightness )
    {
        SeriesGradient gradient = JavaScriptObject.createObject().cast();
        JsArray<Gradient> array = JavaScriptObject.createArray().cast();
        array.push( Gradient.of( fromOpacity, fromBrightness ) );
        array.push( Gradient.of( toOpacity, toBrightness ) );
        gradient.put( FILL_COLOR_COLORS_KEY, array );
        return gradient;
    }

    private static final String FILL_COLOR_COLORS_KEY = "colors";
    private static final String OPACITY_KEY = "opacity";
    private static final String BRIGHTNESS_KEY = "brightness";

    protected SeriesGradient()
    {
    }

    public final Gradient getFrom()
    {
        return getArray( FILL_COLOR_COLORS_KEY ).getObject( 0 );
    }

    public final Gradient getTo()
    {
        return getArray( FILL_COLOR_COLORS_KEY ).getObject( 1 );
    }
}
