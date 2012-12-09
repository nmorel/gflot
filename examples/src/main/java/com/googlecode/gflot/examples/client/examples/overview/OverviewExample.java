package com.googlecode.gflot.examples.client.examples.overview;

import ca.nanometrics.gflot.client.DataPoint;
import ca.nanometrics.gflot.client.PlotModelStrategy;
import ca.nanometrics.gflot.client.PlotWithOverview;
import ca.nanometrics.gflot.client.PlotWithOverviewModel;
import ca.nanometrics.gflot.client.SeriesHandler;
import ca.nanometrics.gflot.client.options.GlobalSeriesOptions;
import ca.nanometrics.gflot.client.options.LineSeriesOptions;
import ca.nanometrics.gflot.client.options.PlotOptions;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gflot.examples.client.examples.DefaultActivity;
import com.googlecode.gflot.examples.client.resources.Resources;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesData;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesRaw;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesSource;

/**
 * @author Nicolas Morel
 */
@GFlotExamplesRaw( OverviewPlace.UI_RAW_SOURCE_FILENAME )
public class OverviewExample
    extends DefaultActivity
{

    private static Binder binder = GWT.create( Binder.class );

    interface Binder
        extends UiBinder<Widget, OverviewExample>
    {
    }

    /**
     * Plot
     */
    @GFlotExamplesData
    @UiField( provided = true )
    PlotWithOverview plot;

    public OverviewExample( Resources resources )
    {
        super( resources );
    }

    /**
     * Create plot
     */
    @GFlotExamplesSource
    public Widget createPlot()
    {
        PlotWithOverviewModel model = new PlotWithOverviewModel( PlotModelStrategy.defaultStrategy() );
        PlotOptions plotOptions = new PlotOptions();
        plotOptions.setGlobalSeriesOptions( new GlobalSeriesOptions().setLineSeriesOptions( new LineSeriesOptions()
            .setLineWidth( 0 ).setShow( true ).setFill( true ) ) );

        // create the plot
        plot = new PlotWithOverview( model, plotOptions );

        generateRandomData();

        plot.setLinearSelection( 150, 199 );

        return binder.createAndBindUi( this );
    }

    /**
     * On click on generate button
     *
     * @param e event
     */
    @GFlotExamplesSource
    @UiHandler( "generate" )
    void onClickGenerate( ClickEvent e )
    {
        plot.getModel().removeAllSeries();
        generateRandomData();
        plot.redraw();
    }

    /**
     * Generate random data
     */
    @GFlotExamplesSource
    private void generateRandomData()
    {
        int nbSeries = Random.nextInt( 3 ) + 1;
        for ( int i = 0; i < nbSeries; i++ )
        {
            plot.getModel().addSeries( "Random Series " + i );
        }
        for ( int i = 1; i < 200; i++ )
        {
            for ( SeriesHandler series : plot.getModel().getHandlers() )
            {
                series.add( DataPoint.of( i, 1.5 + Random.nextDouble(), 1.5 - Random.nextDouble() ) );
            }
        }
    }

}
