package com.googlecode.gflot.examples.client.examples.threshold;


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
import com.googlecode.gflot.client.event.PlotHoverListener;
import com.googlecode.gflot.client.event.PlotItem;
import com.googlecode.gflot.client.event.PlotPosition;
import com.googlecode.gflot.client.jsni.Plot;
import com.googlecode.gflot.client.options.*;
import com.googlecode.gflot.client.options.CommonSeriesOptions.Threshold;
import com.googlecode.gflot.examples.client.examples.DefaultActivity;
import com.googlecode.gflot.examples.client.resources.Resources;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesRaw;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesSource;

import java.util.logging.Logger;

/**
 * @author Nicolas Morel
 */
@GFlotExamplesRaw( ThresholdPlace.UI_RAW_SOURCE_FILENAME )
public class ThresholdExample
    extends DefaultActivity
{
    private static final Logger logger = Logger.getLogger(ThresholdExample.class.getName());

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
        PlotOptions plotOptions = PlotOptions.create();
        plotOptions.setGlobalSeriesOptions( GlobalSeriesOptions.create()
            .setLineSeriesOptions(LineSeriesOptions.create().setShow(true).setSteps(true))
            .setThreshold(Threshold.create().setBelow(0).setColor("red")) );
        plotOptions.setLegendOptions( LegendOptions.create().setShow( false ) );

        plotOptions.setGridOptions(GridOptions.create().setHoverable(true));

        // create series
        SeriesHandler series1 = model.addSeries( Series.of( "Random series 1", "green" ) );

        // add data
        for ( int i = 0; i < 10; i++ )
        {
            series1.add( DataPoint.of( i, Random.nextInt( 30 ) - 10 ) );
        }

        // create the plot
        plot = new SimplePlot( model, plotOptions );
        plot.addHoverListener(new PlotHoverListener() {
            @Override
            public void onPlotHover(Plot plot, PlotPosition position, PlotItem item) {
                Series series = item.getSeries().getOriginSeries();
                if (null == series) {
                    series = item.getSeries();
                }
                logger.info("Hovering series : " + series.getLabel());
            }
        }, true);

        return binder.createAndBindUi( this );
    }

    /**
     * When the below changes
     */
    @UiHandler( { "positive", "zero", "negative" } )
    @GFlotExamplesSource
    void onValueChangeGraphType( ValueChangeEvent<Boolean> event )
    {
        GlobalSeriesOptions options = plot.getOptions().getGlobalSeriesOptions();
        if ( positive.getValue() )
        {
            options.getThreshold().get( 0 ).setBelow( 5 );
        }
        else if ( zero.getValue() )
        {
            options.getThreshold().get( 0 ).setBelow( 0 );
        }
        else
        {
            options.getThreshold().get( 0 ).setBelow( -2.5 );
        }

        plot.redraw();
    }
}
