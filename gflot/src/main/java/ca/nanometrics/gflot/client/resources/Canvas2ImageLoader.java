package ca.nanometrics.gflot.client.resources;

import ca.nanometrics.gflot.client.util.JavaScriptInjector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

public class Canvas2ImageLoader
    extends DefaultLoader
{
    interface Bundle
        extends ClientBundle
    {
        @Source( "canvas2image.min.js" )
        TextResource canvasToImage();

        @Source( "base64.min.js" )
        TextResource base64();
    }

    private Bundle bundle;

    private Bundle getBundle()
    {
        if ( null == bundle )
        {
            bundle = GWT.create( Bundle.class );
        }
        return bundle;
    }

    private boolean loaded;

    @Override
    public void load()
    {
        if ( !loaded )
        {
            JavaScriptInjector.inject( getBundle().canvasToImage().getText() );
            JavaScriptInjector.inject( getBundle().base64().getText() );
            loaded = true;
        }
    }

}
