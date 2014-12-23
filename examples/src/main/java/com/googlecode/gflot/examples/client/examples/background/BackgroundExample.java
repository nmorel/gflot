package com.googlecode.gflot.examples.client.examples.background;


import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gflot.client.DataPoint;
import com.googlecode.gflot.client.PlotModel;
import com.googlecode.gflot.client.SeriesHandler;
import com.googlecode.gflot.client.SimplePlot;
import com.googlecode.gflot.client.options.AxisOptions;
import com.googlecode.gflot.client.options.BackgroundOptions;
import com.googlecode.gflot.client.options.GridOptions;
import com.googlecode.gflot.client.options.PlotOptions;
import com.googlecode.gflot.examples.client.examples.DefaultActivity;
import com.googlecode.gflot.examples.client.resources.Resources;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesData;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesRaw;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesSource;

/**
 * @author Igor Baldachini
 */
@GFlotExamplesRaw( BackgroundPlace.UI_RAW_SOURCE_FILENAME )
public class BackgroundExample
    extends DefaultActivity
{

    private static Binder binder = GWT.create( Binder.class );

    interface Binder
        extends UiBinder<Widget, BackgroundExample>
    {
    }

    /**
     * Plot
     */
    @GFlotExamplesData
    @UiField( provided = true )
    SimplePlot plot;

    public BackgroundExample( Resources resources )
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
        final PlotOptions plotOptions = PlotOptions.create();

        // add tick formatter to the options
        plotOptions.addXAxisOptions( AxisOptions.create().setMinimum( -8 ).setMaximum( 4 ) );
        plotOptions.addYAxisOptions( AxisOptions.create().setMinimum( -8 ).setMaximum( 4 ) );
        plotOptions.setGridOptions(GridOptions.create().setBackgroundOptions(
        		BackgroundOptions.create().setImageUrl("images/hs-2004-27-a-large_web.jpg").setAlpha(0.2)));

        // create a series
        SeriesHandler handlerLine = model.addSeries( );
        handlerLine.add( DataPoint.of( -8, -8 ) );
        handlerLine.add( DataPoint.of( -6, -4 ) );
        handlerLine.add( DataPoint.of( -2, -8 ) );
        handlerLine.add( DataPoint.of( 4, 0 ) );

        // create the plot
        plot = new SimplePlot( model, plotOptions );

        return binder.createAndBindUi( this );
    }
}
