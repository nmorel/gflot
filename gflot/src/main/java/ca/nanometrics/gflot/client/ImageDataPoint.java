package ca.nanometrics.gflot.client;

/**
 * @author Nicolas Morel
 */
public class ImageDataPoint
    extends DataPoint
{
    /**
     * Creates an {@link ImageDataPoint} with the specified url and coordinates.
     * 
     * @param url Url of the image
     * @param xTopLeftCorner X coordinate for top left corner
     * @param yTopLeftCorner Y coordinate for top left corner
     * @param xBottomRightCorner X coordinate for bottom right corner
     * @param yBottomRightCorner Y coordinate for bottom right corner
     */
    public static final native ImageDataPoint of( String url, double xTopLeftCorner, double yTopLeftCorner,
                                                  double xBottomRightCorner, double yBottomRightCorner )
    /*-{
        return [ url, xTopLeftCorner, yTopLeftCorner, xBottomRightCorner,
                yBottomRightCorner ];
    }-*/;

    protected ImageDataPoint()
    {
    }

    public final String getUrl()
    {
        return getString( 0 );
    }

    public final ImageDataPoint setUrl( String url )
    {
        set( 0, url );
        return this;
    }

    public final double getXTopLeftCorner()
    {
        return getNumber( 1 );
    }

    public final ImageDataPoint setXTopLeftCorner( double xTopLeftCorner )
    {
        set( 1, xTopLeftCorner );
        return this;
    }

    public final double getYTopLeftCorner()
    {
        return getNumber( 2 );
    }

    public final ImageDataPoint setYTopLeftCorner( double yTopLeftCorner )
    {
        set( 2, yTopLeftCorner );
        return this;
    }

    public final double getXBottomRightCorner()
    {
        return getNumber( 3 );
    }

    public final ImageDataPoint setXBottomRightCorner( double xBottomRightCorner )
    {
        set( 3, xBottomRightCorner );
        return this;
    }

    public final double getYBottomRightCorner()
    {
        return getNumber( 4 );
    }

    public final ImageDataPoint setYBottomRightCorner( double yBottomRightCorner )
    {
        set( 4, yBottomRightCorner );
        return this;
    }
}
