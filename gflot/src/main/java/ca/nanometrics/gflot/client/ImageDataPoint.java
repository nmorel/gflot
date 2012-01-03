package ca.nanometrics.gflot.client;

/**
 * @author Nicolas Morel
 */
public class ImageDataPoint
    extends DataPoint
{
    public ImageDataPoint( String url, double x1, double y1, double x2, double y2 )
    {
        set( 0, url );
        set( 1, x1 );
        set( 2, y1 );
        set( 3, x2 );
        set( 4, y2 );
    }
}
