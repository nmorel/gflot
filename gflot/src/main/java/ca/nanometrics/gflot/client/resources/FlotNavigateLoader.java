package ca.nanometrics.gflot.client.resources;

import ca.nanometrics.gflot.client.util.JavaScriptInjector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

public class FlotNavigateLoader
    extends DefaultLoader
{

    interface Bundle
        extends ClientBundle
    {
        @Source( "jquery.event.drag-2.0-modified.min.js" )
        TextResource jQueryEventDrag();

        @Source( "jquery.mousewheel-3.0.6.min.js" )
        TextResource jQueryMouseWheel();

        @Source( "jquery.flot-0.7.navigate-modified.min.js" )
        TextResource flotNavigate();
    }

    private Bundle bundle;

    private Bundle getBundle()
    {
        if ( null == bundle )
        {
            bundle = GWT.create( FlotNavigateLoader.Bundle.class );
        }
        return bundle;
    }

    private boolean loaded;

    @Override
    public void load()
    {
        if ( !loaded )
        {
            JavaScriptInjector.inject( getBundle().jQueryEventDrag().getText() );
            JavaScriptInjector.inject( getBundle().jQueryMouseWheel().getText() );
            JavaScriptInjector.inject( getBundle().flotNavigate().getText() );
            loaded = true;
        }
    }
}
