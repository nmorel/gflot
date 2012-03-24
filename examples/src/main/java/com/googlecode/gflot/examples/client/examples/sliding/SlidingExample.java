package com.googlecode.gflot.examples.client.examples.sliding;

import java.util.Date;

import ca.nanometrics.gflot.client.DataPoint;
import ca.nanometrics.gflot.client.PlotModel;
import ca.nanometrics.gflot.client.PlotModelStrategy;
import ca.nanometrics.gflot.client.PlotWidget;
import ca.nanometrics.gflot.client.SeriesHandler;
import ca.nanometrics.gflot.client.SimplePlot;
import ca.nanometrics.gflot.client.options.GlobalSeriesOptions;
import ca.nanometrics.gflot.client.options.LineSeriesOptions;
import ca.nanometrics.gflot.client.options.PlotOptions;
import ca.nanometrics.gflot.client.options.PointsSeriesOptions;
import ca.nanometrics.gflot.client.options.TimeSeriesAxisOptions;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gflot.examples.client.examples.DefaultActivity;
import com.googlecode.gflot.examples.client.resources.Resources;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesData;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesRaw;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesSource;

/**
 * @author Nicolas Morel
 */
@GFlotExamplesRaw( SlidingPlace.UI_RAW_SOURCE_FILENAME )
public class SlidingExample
    extends DefaultActivity
{

    private static Binder binder = GWT.create( Binder.class );

    interface Binder
        extends UiBinder<Widget, SlidingExample>
    {
    }

    /**
     * The Async interface of the service
     */
    @GFlotExamplesSource
    interface FakeRpcServiceAsync
    {
        void getNewData( AsyncCallback<DataPoint[]> callback );
    }

    /**
     * Plot
     */
    @GFlotExamplesData
    @UiField( provided = true )
    SimplePlot plot;

    @UiField
    Button startStop;

    /**
     * Timer
     */
    @GFlotExamplesData
    private Timer updater;

    public SlidingExample( Resources resources )
    {
        super( resources );
    }

    /**
     * Create plot
     */
    @GFlotExamplesSource
    public Widget createPlot()
    {
        PlotModel model = new PlotModel( PlotModelStrategy.slidingWindowStrategy( 20 ) );
        PlotOptions plotOptions = new PlotOptions();
        plotOptions.setGlobalSeriesOptions( new GlobalSeriesOptions()
            .setLineSeriesOptions( new LineSeriesOptions().setLineWidth( 1 ).setShow( true ) )
            .setPointsOptions( new PointsSeriesOptions().setRadius( 2 ).setShow( true ) ).setShadowSize( 0d ) );
        plotOptions.addXAxisOptions( new TimeSeriesAxisOptions() );

        final SeriesHandler series = model.addSeries( "Random Series", "#FF9900" );

        // pull the "fake" RPC service for new data
        updater = new Timer()
        {
            @Override
            public void run()
            {
                update( series, plot );
            }
        };

        // create the plot
        plot = new SimplePlot( model, plotOptions );

        return binder.createAndBindUi( this );
    }

    /**
     * Start the timer when the activity starts
     */
    @GFlotExamplesSource
    @Override
    public void start( AcceptsOneWidget panel, EventBus eventBus )
    {
        super.start( panel, eventBus );
        start();
    }

    /**
     * Stop the timer when the activity stops
     */
    @GFlotExamplesSource
    @Override
    public void onStop()
    {
        stop();
        super.onStop();
    }

    /**
     * On click on the start/stop button
     */
    @GFlotExamplesSource
    @UiHandler( "startStop" )
    void onClickStartStop( ClickEvent e )
    {
        if ( "Stop".equals( startStop.getText() ) )
        {
            stop();
        }
        else
        {
            start();
        }
    }

    /**
     * Start the timer
     */
    @GFlotExamplesSource
    private void start()
    {
        startStop.setText( "Stop" );
        updater.scheduleRepeating( 1000 );
    }

    /**
     * Stop the timer
     */
    @GFlotExamplesSource
    private void stop()
    {
        startStop.setText( "Start" );
        updater.cancel();
    }

    /**
     * Fake a rpc call and update the data
     */
    @GFlotExamplesSource
    private void update( final SeriesHandler series, final PlotWidget plot )
    {
        FakeRpcServiceAsync service = getRpcService();
        service.getNewData( new AsyncCallback<DataPoint[]>()
        {
            public void onFailure( Throwable caught )
            {
                GWT.log( "Something went wrong", caught );
            }

            public void onSuccess( DataPoint[] result )
            {
                for ( DataPoint dataPoint : result )
                {
                    series.add( dataPoint );
                }
                plot.redraw();
            }
        } );
    }

    /**
     * @return a fake rpc service
     */
    @GFlotExamplesSource
    private FakeRpcServiceAsync getRpcService()
    {
        return new FakeRpcServiceAsync()
        {
            @SuppressWarnings( "deprecation" )
            public void getNewData( final AsyncCallback<DataPoint[]> callback )
            {
                Date currentDate = new Date();
                callback.onSuccess( new DataPoint[] { new DataPoint( Date.UTC( currentDate.getYear(),
                    currentDate.getMonth(), currentDate.getDate(), currentDate.getHours(), currentDate.getMinutes(),
                    currentDate.getSeconds() ), Random.nextDouble() ) } );
            }
        };
    }
}
