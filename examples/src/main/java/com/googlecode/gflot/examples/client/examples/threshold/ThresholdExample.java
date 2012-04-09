package com.googlecode.gflot.examples.client.examples.threshold;

import ca.nanometrics.gflot.client.DataPoint;
import ca.nanometrics.gflot.client.PlotModel;
import ca.nanometrics.gflot.client.SeriesHandler;
import ca.nanometrics.gflot.client.SimplePlot;
import ca.nanometrics.gflot.client.options.CommonSeriesOptions.Threshold;
import ca.nanometrics.gflot.client.options.GlobalSeriesOptions;
import ca.nanometrics.gflot.client.options.LegendOptions;
import ca.nanometrics.gflot.client.options.LineSeriesOptions;
import ca.nanometrics.gflot.client.options.PlotOptions;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gflot.examples.client.examples.DefaultActivity;
import com.googlecode.gflot.examples.client.resources.Resources;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesRaw;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesSource;

/**
 * @author Nicolas Morel
 */
@GFlotExamplesRaw( ThresholdPlace.UI_RAW_SOURCE_FILENAME )
public class ThresholdExample
    extends DefaultActivity
{

    private static Binder binder = GWT.create( Binder.class );

    interface Binder
        extends UiBinder<Widget, ThresholdExample>
    {
    }

    @UiField
    RadioButton positive;

    @UiField
    RadioButton zero;

    @UiField
    RadioButton negative;

    @UiField( provided = true )
    SimplePlot plot;

    public ThresholdExample( Resources resources )
    {
        super( resources );
    }

    /**
     * Create plot
     */
    @GFlotExamplesSource
    protected Widget createPlot()
    {
        PlotModel model = new PlotModel();
        PlotOptions plotOptions = new PlotOptions();
        plotOptions.setGlobalSeriesOptions( new GlobalSeriesOptions().setLineSeriesOptions(
            new LineSeriesOptions().setShow( true ).setSteps( true ) ).setThreshold(
            new Threshold().setBelow( 0 ).setColor( "red" ) ) );
        plotOptions.setLegendOptions( new LegendOptions().setShow( false ) );

        // create series
        SeriesHandler series1 = model.addSeries( "Random series 1", "green" );

        // add data
        for ( int i = 0; i < 10; i++ )
        {
            series1.add( new DataPoint( i, Random.nextInt( 30 ) - 10 ) );
        }

        // create the plot
        plot = new SimplePlot( model, plotOptions );

        return binder.createAndBindUi( this );
    }

    /**
     * When the below changes
     */
    @UiHandler( { "positive", "zero", "negative" } )
    @GFlotExamplesSource
    void onValueChangeGraphType( ValueChangeEvent<Boolean> event )
    {
        GlobalSeriesOptions options = plot.getPlotOptions().getGlobalSeriesOptions();
        if ( positive.getValue() )
        {
            options.getThreshold().setBelow( 5 );
        }
        else if ( zero.getValue() )
        {
            options.getThreshold().setBelow( 0 );
        }
        else
        {
            options.getThreshold().setBelow( -2.5 );
        }

        plot.redraw();
    }
}
