package ca.nanometrics.gflot.sample.client.stack;

import ca.nanometrics.gflot.client.DataPoint;
import ca.nanometrics.gflot.client.PlotModel;
import ca.nanometrics.gflot.client.SeriesHandler;
import ca.nanometrics.gflot.client.SimplePlot;
import ca.nanometrics.gflot.client.options.BarSeriesOptions;
import ca.nanometrics.gflot.client.options.GlobalSeriesOptions;
import ca.nanometrics.gflot.client.options.LegendOptions;
import ca.nanometrics.gflot.client.options.LineSeriesOptions;
import ca.nanometrics.gflot.client.options.PlotOptions;
import ca.nanometrics.gflot.sample.client.GFlotExample;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Alexander De Leon
 */
public class StackExample
    implements GFlotExample
{
    private static Binder binder = GWT.create( Binder.class );

    interface Binder
        extends UiBinder<Widget, StackExample>
    {
    }

    @UiField
    SimplePanel placeholder;

    @UiField
    RadioButton bars;

    @UiField
    RadioButton lines;

    @UiField
    RadioButton linesStep;

    private SimplePlot plot;

    public String getName()
    {
        return "Stack";
    }

    public Widget createExample()
    {
        PlotModel model = new PlotModel();
        PlotOptions plotOptions = new PlotOptions();
        plotOptions.setGlobalSeriesOptions( new GlobalSeriesOptions().setLineSeriesOptions( new LineSeriesOptions().setShow( false ).setFill( true ) )
            .setBarsSeriesOptions( new BarSeriesOptions().setShow( true ).setBarWidth( 0.6 ) ).setStack( true ) );
        plotOptions.setLegendOptions( new LegendOptions().setShow( false ) );

        // create series
        SeriesHandler series1 = model.addSeries( "Series1" );
        SeriesHandler series2 = model.addSeries( "Series2" );
        SeriesHandler series3 = model.addSeries( "Series3" );

        // add data
        for ( int i = 0; i < 10; i++ )
        {
            series1.add( new DataPoint( i, Random.nextInt( 30 ) ) );
            series2.add( new DataPoint( i, Random.nextInt( 30 ) ) );
            series3.add( new DataPoint( i, Random.nextInt( 30 ) ) );
        }

        // create the plot
        plot = new SimplePlot( model, plotOptions );
        plot.setSize( "600px", "300px" );

        Widget w = binder.createAndBindUi( this );
        placeholder.setWidget( plot );
        return w;
    }

    @UiHandler( "stacking" )
    void onValueChangeStacking( ValueChangeEvent<Boolean> event )
    {
        if ( event.getValue() )
        {
            plot.getPlotOptions().getGlobalSeriesOptions().setStack( true );
        }
        else
        {
            plot.getPlotOptions().getGlobalSeriesOptions().setStack( null );
        }
        plot.redraw();
    }

    @UiHandler( { "bars", "lines", "linesStep" } )
    void onValueChangeGraphType( ValueChangeEvent<Boolean> event )
    {
        GlobalSeriesOptions options = plot.getPlotOptions().getGlobalSeriesOptions();
        options.getLineSeriesOptions().setShow( lines.getValue() || linesStep.getValue() ).setSteps( linesStep.getValue() );
        options.getBarSeriesOptions().setShow( bars.getValue() );

        plot.redraw();
    }
}
