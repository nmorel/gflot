package ca.nanometrics.gflot.sample.client;

import ca.nanometrics.gflot.sample.client.mvp.AppActivityMapper;
import ca.nanometrics.gflot.sample.client.mvp.AppPlaceHistoryMapper;
import ca.nanometrics.gflot.sample.client.resources.Resources;
import ca.nanometrics.gflot.sample.client.samples.simple.SimplePlace;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

public class GFlotSample
    implements EntryPoint
{
    public void onModuleLoad()
    {
        Resources resources = GWT.create( Resources.class );
        resources.style().ensureInjected();

        EventBus eventBus = new SimpleEventBus();

        MainView mainView = new MainView( eventBus, resources );
        RootLayoutPanel.get().add( mainView );

        ActivityMapper activityMapper = new AppActivityMapper(resources);
        PlaceHistoryMapper placeHistoryMapper = new AppPlaceHistoryMapper();

        PlaceController placeController = new PlaceController( eventBus );
        PlaceHistoryHandler placeHistoryHandler = new PlaceHistoryHandler( placeHistoryMapper );
        placeHistoryHandler.register( placeController, eventBus, new SimplePlace() );

        ActivityManager activityManager = new ActivityManager( activityMapper, eventBus );
        activityManager.setDisplay( mainView.getContainer() );

        placeHistoryHandler.handleCurrentHistory();
    }

}
