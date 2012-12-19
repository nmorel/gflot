package com.googlecode.gflot.examples.client;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.googlecode.gflot.examples.client.examples.line.LinePlace;
import com.googlecode.gflot.examples.client.mvp.AppActivityMapper;
import com.googlecode.gflot.examples.client.mvp.AppPlaceHistoryMapper;
import com.googlecode.gflot.examples.client.resources.Resources;

public class GFlotExamples
    implements EntryPoint, UncaughtExceptionHandler
{
    /**
     * The type passed into the
     * {@link com.googlecode.gflot.examples.generator.SourceGenerator}.
     */
    private static final class GeneratorInfo {
    }

    public void onModuleLoad()
    {
        GWT.setUncaughtExceptionHandler( this );
        
        // Generate the source code for the examples
        GWT.create(GeneratorInfo.class);

        // Inject styles
        Resources resources = GWT.create( Resources.class );
        resources.style().ensureInjected();

        // Initialize the history handler and activity manager
        EventBus eventBus = new SimpleEventBus();

        ActivityMapper activityMapper = new AppActivityMapper( resources );
        PlaceHistoryMapper placeHistoryMapper = new AppPlaceHistoryMapper();

        PlaceController placeController = new PlaceController( eventBus );
        PlaceHistoryHandler placeHistoryHandler = new PlaceHistoryHandler( placeHistoryMapper );
        placeHistoryHandler.register( placeController, eventBus, new LinePlace() );

        MainView mainView = new MainView( eventBus, placeController, resources );
        RootLayoutPanel.get().add( mainView );

        ActivityManager activityManager = new ActivityManager( activityMapper, eventBus );
        activityManager.setDisplay( mainView.getContainer() );

        placeHistoryHandler.handleCurrentHistory();
    }

    @Override
    public void onUncaughtException( Throwable e )
    {
        GWT.log( "Uncaught exception", e );
    }

}
