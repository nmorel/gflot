package com.googlecode.gflot.examples.client.examples.overview;

import ca.nanometrics.gflot.client.DataPoint;
import ca.nanometrics.gflot.client.PlotModelStrategy;
import ca.nanometrics.gflot.client.PlotWithOverview;
import ca.nanometrics.gflot.client.PlotWithOverviewModel;
import ca.nanometrics.gflot.client.SeriesHandler;
import ca.nanometrics.gflot.client.options.GlobalSeriesOptions;
import ca.nanometrics.gflot.client.options.LineSeriesOptions;
import ca.nanometrics.gflot.client.options.PlotOptions;
import ca.nanometrics.gflot.client.options.PointsSeriesOptions;

import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gflot.examples.client.mvp.DefaultActivity;
import com.googlecode.gflot.examples.client.resources.Resources;

/**
 * @author Nicolas Morel
 */
public class OverviewExample
    extends DefaultActivity
{

    public OverviewExample( Resources resources )
    {
        super( resources );
    }

    public Widget createWidget()
    {
        PlotWithOverviewModel model = new PlotWithOverviewModel( PlotModelStrategy.defaultStrategy() );
        PlotOptions plotOptions = new PlotOptions();
        plotOptions.setGlobalSeriesOptions( new GlobalSeriesOptions()
            .setLineSeriesOptions( new LineSeriesOptions().setLineWidth( 1 ).setShow( true ) )
            .setPointsOptions( new PointsSeriesOptions().setRadius( 2 ).setShow( true ) ).setShadowSize( 0d ) );

        SeriesHandler series = model.addSeries( "Random Series", "#2c1d54" );

        // generate random data
        for ( int i = 0; i < 200; i++ )
        {
            series.add( new DataPoint( i, Random.nextDouble() ) );
        }

        // create the plot
        final PlotWithOverview plot = new PlotWithOverview( model, plotOptions );
        plot.setLinearSelection( 150, 199 );

        // put it on a panel
        FlowPanel panel = new FlowPanel();
        panel.add( plot );
        panel.add( new Label( "Click on the overview to change the selection." ) );

        return panel;
    }

}
