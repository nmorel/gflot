package ca.nanometrics.gflot.client.resources;

import ca.nanometrics.gflot.client.util.JavaScriptInjector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

public class FlotAxisLabelsLoader
    extends DefaultLoader
{
    interface Bundle
        extends ClientBundle
    {
        @Source( "jquery.flot.axislabels.min.js" )
        TextResource flotAxisLabels();
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
            JavaScriptInjector.inject( getBundle().flotAxisLabels().getText() );
            loaded = true;
        }
    }

}
