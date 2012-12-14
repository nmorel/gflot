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
     * @param x1 X coordinate for top left corner
     * @param y1 Y coordinate for top left corner
     * @param x2 X coordinate for bottom right corner
     * @param y2 Y coordinate for bottom right corner
     */
    public static final native ImageDataPoint of( String url, double x1, double y1, double x2, double y2 )
    /*-{
        return [url, x1, y1, x2, y2];
    }-*/;

    protected ImageDataPoint()
    {
    }
}
