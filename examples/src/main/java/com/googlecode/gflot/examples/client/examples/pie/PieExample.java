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
    @Override
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
                    .setThreshold( 0.05 ).setFormatter( new Formatter() {
                        @Override
                        public String format( String label, Series series )
                        {
                            return "<div style=\"font-size:8pt;text-align:center;padding:2px;color:white;\">" + label
                                + "<br/>" + series.getData().getY( 0 ) + " / " + series.getPercent() + "%</div>";
                        }
                    } ) ) ) );
        plotOptions.setLegendOptions( new LegendOptions().setShow( false ) );

        // create series
        SeriesHandler series1 = model.addSeries( "Series 1" );
        series1.add( new PieDataPoint( 148 ) );

        SeriesHandler series2 = model.addSeries( "Series 2" );
        series2.add( new PieDataPoint( 221 ) );

        SeriesHandler series3 = model.addSeries( "Series 3" );
        series3.add( new PieDataPoint( 25 ) );

        SeriesHandler series4 = model.addSeries( "Series 4" );
        series4.add( new PieDataPoint( 35 ) );

        SeriesHandler series5 = model.addSeries( "Series 5" );
        series5.add( new PieDataPoint( 102 ) );

        // create the plot
        plot = new SimplePlot( model, plotOptions );

        return binder.createAndBindUi( this );
    }
}
