package ca.nanometrics.gflot.client.resources;

import ca.nanometrics.gflot.client.util.JavaScriptInjector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

public class FlotCrosshairLoader
    extends DefaultLoader
{

    interface Bundle
        extends ClientBundle
    {
        @Source( "jquery.flot-0.7.crosshair.min.js" )
        TextResource flotCrosshair();
    }

    private Bundle bundle;

    private Bundle getBundle()
    {
        if ( null == bundle )
        {
            bundle = GWT.create( FlotCrosshairLoader.Bundle.class );
        }
        return bundle;
    }

    private boolean loaded;

    @Override
    public void load()
    {
        if ( !loaded )
        {
            JavaScriptInjector.inject( getBundle().flotCrosshair().getText() );
            loaded = true;
        }
    }
}
