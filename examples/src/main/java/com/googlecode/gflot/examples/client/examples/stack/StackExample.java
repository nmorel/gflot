package com.googlecode.gflot.examples.client.examples.stack;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gflot.client.DataPoint;
import com.googlecode.gflot.client.PlotModel;
import com.googlecode.gflot.client.Series;
import com.googlecode.gflot.client.SeriesHandler;
import com.googlecode.gflot.client.SimplePlot;
import com.googlecode.gflot.client.options.BarSeriesOptions;
import com.googlecode.gflot.client.options.GlobalSeriesOptions;
import com.googlecode.gflot.client.options.LegendOptions;
import com.googlecode.gflot.client.options.LineSeriesOptions;
import com.googlecode.gflot.client.options.PlotOptions;
import com.googlecode.gflot.examples.client.examples.DefaultActivity;
import com.googlecode.gflot.examples.client.resources.Resources;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesRaw;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesSource;

/**
 * @author Nicolas Morel
 */
@GFlotExamplesRaw( StackPlace.UI_RAW_SOURCE_FILENAME )
public class StackExample
    extends DefaultActivity
{

    private static Binder binder = GWT.create( Binder.class );

    interface Binder
        extends UiBinder<Widget, StackExample>
    {
    }

    @UiField
    RadioButton bars;

    @UiField
    RadioButton lines;

    @UiField
    RadioButton linesStep;

    @UiField( provided = true )
    SimplePlot plot;

    public StackExample( Resources resources )
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
        PlotOptions plotOptions = PlotOptions.create();
        plotOptions.setGlobalSeriesOptions( GlobalSeriesOptions.create()
            .setLineSeriesOptions( LineSeriesOptions.create().setShow( false ).setFill( true ) )
            .setBarsSeriesOptions( BarSeriesOptions.create().setShow( true ).setBarWidth( 0.6 ) ).setStack( true ) );
        plotOptions.setLegendOptions( LegendOptions.create().setShow( false ) );

        // create series
        SeriesHandler series1 = model.addSeries( Series.of( "Series1" ) );
        SeriesHandler series2 = model.addSeries( Series.of( "Series2" ) );
        SeriesHandler series3 = model.addSeries( Series.of( "Series3" ) );

        // add data
        for ( int i = 0; i < 10; i++ )
        {
            series1.add( DataPoint.of( i, Random.nextInt( 30 ) ) );
            series2.add( DataPoint.of( i, Random.nextInt( 30 ) ) );
            series3.add( DataPoint.of( i, Random.nextInt( 30 ) ) );
        }

        // create the plot
        plot = new SimplePlot( model, plotOptions );

        return binder.createAndBindUi( this );
    }

    /**
     * When the stacking value changes
     */
    @UiHandler( "stacking" )
    @GFlotExamplesSource
    void onValueChangeStacking( ValueChangeEvent<Boolean> event )
    {
        if ( event.getValue() )
        {
            plot.getOptions().getGlobalSeriesOptions().setStack( true );
        }
        else
        {
            plot.getOptions().getGlobalSeriesOptions().setStack( null );
        }
        plot.redraw();
    }

    /**
     * When the type of graph changes
     */
    @UiHandler( { "bars", "lines", "linesStep" } )
    @GFlotExamplesSource
    void onValueChangeGraphType( ValueChangeEvent<Boolean> event )
    {
        GlobalSeriesOptions options = plot.getOptions().getGlobalSeriesOptions();
        options.getLineSeriesOptions().setShow( lines.getValue() || linesStep.getValue() )
            .setSteps( linesStep.getValue() );
        options.getBarSeriesOptions().setShow( bars.getValue() );

        plot.redraw();
    }
}
