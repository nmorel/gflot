package com.googlecode.gflot.examples.client.examples.decimation;

import ca.nanometrics.gflot.client.DataPoint;
import ca.nanometrics.gflot.client.PlotModelStrategy;
import ca.nanometrics.gflot.client.PlotWidget;
import ca.nanometrics.gflot.client.PlotWithOverview;
import ca.nanometrics.gflot.client.PlotWithOverviewModel;
import ca.nanometrics.gflot.client.Series;
import ca.nanometrics.gflot.client.SeriesHandler;
import ca.nanometrics.gflot.client.options.AxisOptions;
import ca.nanometrics.gflot.client.options.GlobalSeriesOptions;
import ca.nanometrics.gflot.client.options.LegendOptions;
import ca.nanometrics.gflot.client.options.LegendOptions.LabelFormatter;
import ca.nanometrics.gflot.client.options.LineSeriesOptions;
import ca.nanometrics.gflot.client.options.PlotOptions;
import ca.nanometrics.gflot.client.options.PointsSeriesOptions;

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
import com.googlecode.gflot.examples.client.examples.DefaultActivity;
import com.googlecode.gflot.examples.client.resources.Resources;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesSource;

/**
 * @author Nicolas Morel
 */
public class DecimationExample
    extends DefaultActivity
{

    private double previous = 0;

    private int timeCounter = 0;

    public DecimationExample( Resources resources )
    {
        super( resources );
    }

    /**
     * Create plot
     */
    @GFlotExamplesSource
    public Widget createWidget()
    {
        PlotWithOverviewModel model = new PlotWithOverviewModel( PlotModelStrategy.downSamplingStrategy( 20 ) );
        PlotOptions plotOptions = new PlotOptions();
        plotOptions.setGlobalSeriesOptions( new GlobalSeriesOptions()
            .setLineSeriesOptions( new LineSeriesOptions().setLineWidth( 1 ).setShow( true ) )
            .setPointsOptions( new PointsSeriesOptions().setRadius( 2 ).setShow( true ) ).setShadowSize( 0d ) );
        plotOptions.setLegendOptions( new LegendOptions().setLabelFormatter( new LabelFormatter()
        {
            @Override
            public String formatLabel( String label, Series series )
            {
                return label + " formated";
            }
        } ) );
        plotOptions.addXAxisOptions( new AxisOptions().setShow( false ) );

        final SeriesHandler series = model.addSeries( "Random Series", "#003366" );

        // create the plot
        final PlotWithOverview plot = new PlotWithOverview( model, plotOptions );

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
                double up = Random.nextDouble();
                double down = Random.nextDouble();
                callback
                    .onSuccess( new DataPoint[] { new DataPoint( timeCounter++, previous - down ), new DataPoint( timeCounter++, previous + up ) } );
                previous = previous + up;
            }
        };
    }

    /** The Async interface of the service */
    interface FakeRpcServiceAsync
    {
        void getNewData( AsyncCallback<DataPoint[]> callback );
    }
}
