package ca.nanometrics.gflot.sample.client;

import ca.nanometrics.gflot.client.DataPoint;
import ca.nanometrics.gflot.client.PlotModelStrategy;
import ca.nanometrics.gflot.client.PlotWidget;
import ca.nanometrics.gflot.client.PlotWithOverview;
import ca.nanometrics.gflot.client.PlotWithOverviewModel;
import ca.nanometrics.gflot.client.SeriesHandler;
import ca.nanometrics.gflot.client.options.GlobalSeriesOptions;
import ca.nanometrics.gflot.client.options.LegendOptions;
import ca.nanometrics.gflot.client.options.LineSeriesOptions;
import ca.nanometrics.gflot.client.options.PlotOptions;
import ca.nanometrics.gflot.client.options.PointsSeriesOptions;
import ca.nanometrics.gflot.client.options.SelectionOptions;
import ca.nanometrics.gflot.client.options.TimeSeriesAxisOptions;
import ca.nanometrics.gflot.client.options.SelectionOptions.SelectionMode;

import com.google.gwt.core.client.Duration;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Alexander De Leon
 */
public class SlidingWindowExample
    implements GFlotExample
{

    public Widget createExample()
    {
        PlotWithOverviewModel model = new PlotWithOverviewModel( PlotModelStrategy.slidingWindowStrategy( 20 ) );
        PlotOptions plotOptions = new PlotOptions();
        plotOptions.setGlobalSeriesOptions( new GlobalSeriesOptions()
            .setLineSeriesOptions( new LineSeriesOptions().setLineWidth( 1 ).setShow( true ) )
            .setPointsOptions( new PointsSeriesOptions().setRadius( 2 ).setShow( true ) ).setShadowSize( 0d ) );
        plotOptions.setXAxisOptions( new TimeSeriesAxisOptions() );

        PlotOptions overviewPlotOptions =
            new PlotOptions()
                .setLegendOptions( new LegendOptions().setShow( false ) )
                .setGlobalSeriesOptions(
                    new GlobalSeriesOptions().setLineSeriesOptions( new LineSeriesOptions().setLineWidth( 1 ).setFill( true ) )
                        .setShadowSize( 0d ) )
                .setSelectionOptions( new SelectionOptions().setMode( SelectionMode.X ) )
                .setXAxisOptions( new TimeSeriesAxisOptions() );

        final SeriesHandler series = model.addSeries( "Random Series", "#FF9900" );

        // create the plot
        final PlotWithOverview plot = new PlotWithOverview( model, plotOptions, overviewPlotOptions );

        // pull the "fake" RPC service for new data
        final Timer updater = new Timer()
        {
            @Override
            public void run()
            {
                update( series, plot );
            }
        };

        // put it on a panel
        FlowPanel panel = new FlowPanel();
        panel.add( plot );
        HorizontalPanel buttonsPanel = new HorizontalPanel();
        buttonsPanel.setSpacing( 5 );
        buttonsPanel.add( new Button( "Start", new ClickHandler()
        {
            public void onClick( ClickEvent event )
            {
                updater.scheduleRepeating( 1000 );
            }
        } ) );
        buttonsPanel.add( new Button( "Stop", new ClickHandler()
        {
            public void onClick( ClickEvent event )
            {
                updater.cancel();
            }
        } ) );
        panel.add( buttonsPanel );
        return panel;
    }

    public String getName()
    {
        return "Sliding Window";
    }

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

    private FakeRpcServiceAsync getRpcService()
    {
        return new FakeRpcServiceAsync()
        {
            public void getNewData( final AsyncCallback<DataPoint[]> callback )
            {
                callback.onSuccess( new DataPoint[] { new DataPoint( Duration.currentTimeMillis(), Random.nextDouble() ) } );
            }
        };
    }

    /** The Async interface of the service */
    interface FakeRpcServiceAsync
    {

        void getNewData( AsyncCallback<DataPoint[]> callback );

    }
}
