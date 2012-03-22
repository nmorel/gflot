package com.googlecode.gflot.examples.client.examples.pie;

import ca.nanometrics.gflot.client.PieDataPoint;
import ca.nanometrics.gflot.client.PlotModel;
import ca.nanometrics.gflot.client.Series;
import ca.nanometrics.gflot.client.SeriesHandler;
import ca.nanometrics.gflot.client.SimplePlot;
import ca.nanometrics.gflot.client.options.GlobalSeriesOptions;
import ca.nanometrics.gflot.client.options.LegendOptions;
import ca.nanometrics.gflot.client.options.PieSeriesOptions;
import ca.nanometrics.gflot.client.options.PieSeriesOptions.Label;
import ca.nanometrics.gflot.client.options.PieSeriesOptions.Label.Background;
import ca.nanometrics.gflot.client.options.PieSeriesOptions.Label.Formatter;
import ca.nanometrics.gflot.client.options.PlotOptions;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
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
@GFlotExamplesRaw( PiePlace.UI_RAW_SOURCE_FILENAME )
public class PieExample
    extends DefaultActivity
{

    private static Binder binder = GWT.create( Binder.class );

    interface Binder
        extends UiBinder<Widget, PieExample>
    {
    }

    /**
     * Plot
     */
    @GFlotExamplesData
    @UiField( provided = true )
    SimplePlot plot;

    public PieExample( Resources resources )
    {
        super( resources );
    }

    /**
     * Create plot
     */
    @GFlotExamplesSource
    public Widget createPlot()
    {
        final PlotModel model = new PlotModel();
        final PlotOptions plotOptions = new PlotOptions();

        // activate the pie
        plotOptions.setGlobalSeriesOptions( new GlobalSeriesOptions().setPieSeriesOptions( new PieSeriesOptions()
            .setShow( true )
            .setRadius( 1 )
            .setInnerRadius( 0.2 )
            .setLabel(
                new Label().setShow( true ).setRadius( 3d / 4d ).setBackground( new Background().setOpacity( 0.8 ) )
                    .setFormatter( new Formatter()
                    {
                        @Override
                        public String format( String label, Series series )
                        {
                            return "<div style=\"font-size:8pt;text-align:center;padding:2px;color:white;\">" + label
                                + "<br/>" + series.getPercent() + "%</div>";
                        }
                    } ) ) ) );
        plotOptions.setLegendOptions( new LegendOptions().setShow( false ) );

        // create series
        int nbSeries = Random.nextInt( 4 ) + 2;
        for ( int i = 0; i < nbSeries; i++ )
        {
            SeriesHandler handlerLine = model.addSeries( "Series " + ( i + 1 ) );
            handlerLine.add( new PieDataPoint( Random.nextInt( 100 ) + 1 ) );
        }

        // create the plot
        plot = new SimplePlot( model, plotOptions );

        return binder.createAndBindUi( this );
    }
}
