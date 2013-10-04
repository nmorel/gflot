package com.googlecode.gflot.examples.client.examples;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gflot.client.SeriesData;
import com.googlecode.gflot.examples.client.resources.Resources;

public abstract class DefaultActivity extends AbstractActivity implements IsWidget
{
    protected static abstract class SeriesDataCallback
    {
        protected abstract void onSuccess( SeriesData data );
    }

    private Resources resources;
    private Widget widget;

    public DefaultActivity( Resources resources )
    {
        this.resources = resources;
    }

    @Override
    public void start( final AcceptsOneWidget panel, EventBus eventBus )
    {
        if ( null == widget )
        {
            widget = createPlot();
            widget.addStyleName( resources.style().mainContainer() );
        }
        panel.setWidget( widget );
        Scheduler.get().scheduleDeferred( new ScheduledCommand()
        {
            @Override
            public void execute()
            {
                // trick under IE6 to force a dom reconstruction
                PopupPanel pop = new PopupPanel();
                pop.show();
                pop.hide();
            }
        } );
    }

    @UiFactory
    public Resources getResources()
    {
        return resources;
    }

    protected abstract Widget createPlot();

    @Override
    public Widget asWidget()
    {
        return widget;
    }

    protected void loadData( String path, final SeriesDataCallback callback )
    {
        RequestBuilder builder = new RequestBuilder( RequestBuilder.GET, "data/" + path );
        builder.setCallback( new RequestCallback()
        {
            @Override
            public void onResponseReceived( Request request, Response response )
            {
                callback.onSuccess( JsonUtils.<SeriesData>safeEval( response.getText() ) );
            }

            @Override
            public void onError( Request request, Throwable exception )
            {
                Window.alert( exception.getMessage() );
            }
        } );
        try
        {
            builder.send();
        }
        catch ( RequestException e )
        {
            Window.alert( e.getMessage() );
        }
    }
}
