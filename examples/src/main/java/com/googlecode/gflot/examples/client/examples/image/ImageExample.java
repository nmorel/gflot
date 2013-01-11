package com.googlecode.gflot.examples.client.examples.image;


import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gflot.client.DataPoint;
import com.googlecode.gflot.client.ImageDataPoint;
import com.googlecode.gflot.client.PlotModel;
import com.googlecode.gflot.client.Series;
import com.googlecode.gflot.client.SeriesHandler;
import com.googlecode.gflot.client.SimplePlot;
import com.googlecode.gflot.client.options.AxisOptions;
import com.googlecode.gflot.client.options.ImageSeriesOptions;
import com.googlecode.gflot.client.options.PlotOptions;
import com.googlecode.gflot.examples.client.examples.DefaultActivity;
import com.googlecode.gflot.examples.client.resources.Resources;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesData;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesRaw;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesSource;

/**
 * @author Nicolas Morel
 */
@GFlotExamplesRaw( ImagePlace.UI_RAW_SOURCE_FILENAME )
public class ImageExample
    extends DefaultActivity
{

    private static Binder binder = GWT.create( Binder.class );

    interface Binder
        extends UiBinder<Widget, ImageExample>
    {
    }

    /**
     * Plot
     */
    @GFlotExamplesData
    @UiField( provided = true )
    SimplePlot plot;

    public ImageExample( Resources resources )
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

        // create a series
        SeriesHandler handlerImage =
            model.addSeries( Series.of( "Image series" ).setImageSeriesOptions(
                ImageSeriesOptions.create().setShow( true ).setAlpha( 0.5 ) ) );
        handlerImage.add( ImageDataPoint.of( "images/hs-2004-27-a-large_web.jpg", -2, -2, 2, 2 ) );

        // create a series
        SeriesHandler handlerLine = model.addSeries( Series.of( "Line series" ) );
        handlerLine.add( DataPoint.of( -8, -8 ) );
        handlerLine.add( DataPoint.of( -6, -4 ) );
        handlerLine.add( DataPoint.of( -2, -8 ) );
        handlerLine.add( DataPoint.of( 4, 0 ) );

        // create the plot
        plot = new SimplePlot( model, plotOptions );
        plot.setLoadDataImages( true );

        return binder.createAndBindUi( this );
    }
}
