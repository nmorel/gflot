package com.googlecode.gflot.examples.client.source;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.i18n.client.HasDirection.Direction;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.googlecode.gflot.examples.client.mvp.ActivityWithPlace;

/**
 * This activity shows source and raw source code.
 *
 * @author Nicolas Morel
 */
public class SourceActivity
    extends ActivityWithPlace<PlaceWithSources<?>>
{
    /**
     * Generic callback used for asynchronously loaded data.
     *
     * @param <T> the data type
     */
    private static interface Callback<T>
    {
        void onError();

        void onSuccess( T value );
    }

    /**
     * The unique ID assigned to the next callback.
     */
    private static int nextCallbackId = 0;

    /**
     * The callback used when retrieving source code.
     */
    private class CustomCallback
        implements Callback<String>
    {

        private int id;

        public CustomCallback()
        {
            id = ++nextCallbackId;
        }

        public void onError()
        {
            if ( id == nextCallbackId )
            {
                contentSource.setHTML( "Cannot find resource", Direction.LTR );
            }
        }

        public void onSuccess( String value )
        {
            if ( id == nextCallbackId )
            {
                contentSource.setHTML( value, Direction.LTR );
            }
        }
    }

    /**
     * A mapping of filenames to their source or raw source code. The map is populated as source is loaded.
     */
    private final Map<String, String> source = new HashMap<String, String>();

    /**
     * Widget containing the source code
     */
    private HTML contentSource;

    @Override
    public void start( AcceptsOneWidget panel, EventBus eventBus )
    {
        ScrollPanel scroll = new ScrollPanel();
        scroll.getElement().getStyle().setMargin( 10.0, Unit.PX );
        scroll.getElement().getStyle().setBackgroundColor( "#eee" );
        scroll.getElement().getStyle().setProperty( "border", "1px solid #c3c3c3" );

        contentSource = new HTML( "loading" );
        scroll.add( contentSource );

        if ( getPlace().isRawSource() )
        {
            getRawSource( getPlace().getFilename(), new CustomCallback() );
        }
        else
        {
            getSource( getPlace().getFilename(), new CustomCallback() );
        }

        panel.setWidget( scroll );
    }

    /**
     * Get the source code for a raw file.
     *
     * @param filename the filename to load
     * @param callback the callback to call when loaded
     */
    private void getRawSource( final String filename, final Callback<String> callback )
    {
        getSource( SourceConstants.DST_SOURCE_RAW, filename, callback );
    }

    /**
     * Get the source code for an example widget.
     *
     * @param filename the filename to load
     * @param callback the callback to call when loaded
     */
    private void getSource( final String filename, final Callback<String> callback )
    {
        getSource( SourceConstants.DST_SOURCE_EXAMPLE, filename, callback );
    }

    /**
     * Request the source code associated with the widget.
     *
     * @param folder the folder containing the file
     * @param filename the filename to load
     * @param callback the callback used when the source become available
     */
    private void getSource( final String folder, final String filename, final Callback<String> callback )
    {
        if ( source.containsKey( filename ) )
        {
            callback.onSuccess( source.get( filename ) );
        }
        else
        {
            RequestCallback rc = new RequestCallback()
            {
                public void onError( Request request, Throwable exception )
                {
                    callback.onError();
                }

                public void onResponseReceived( Request request, Response response )
                {
                    String text = response.getText();
                    source.put( filename, text );
                    callback.onSuccess( text );
                }
            };

            sendSourceRequest( rc, folder + filename + ".html" );
        }
    }

    /**
     * Send a request for source code.
     *
     * @param callback the {@link RequestCallback} to send
     * @param url the URL to target
     */
    private void sendSourceRequest( RequestCallback callback, String url )
    {
        RequestBuilder builder = new RequestBuilder( RequestBuilder.GET, GWT.getModuleBaseURL() + url );
        builder.setCallback( callback );
        try
        {
            builder.send();
        }
        catch ( RequestException e )
        {
            callback.onError( null, e );
        }
    }
}
