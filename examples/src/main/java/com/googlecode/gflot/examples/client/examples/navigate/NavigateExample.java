package com.googlecode.gflot.examples.client.examples.navigate;

import ca.nanometrics.gflot.client.Axes;
import ca.nanometrics.gflot.client.DataPoint;
import ca.nanometrics.gflot.client.Pan;
import ca.nanometrics.gflot.client.PlotModel;
import ca.nanometrics.gflot.client.SeriesHandler;
import ca.nanometrics.gflot.client.SimplePlot;
import ca.nanometrics.gflot.client.event.PlotPanListener;
import ca.nanometrics.gflot.client.event.PlotZoomListener;
import ca.nanometrics.gflot.client.options.AxisOptions;
import ca.nanometrics.gflot.client.options.GlobalSeriesOptions;
import ca.nanometrics.gflot.client.options.LegendOptions;
import ca.nanometrics.gflot.client.options.LineSeriesOptions;
import ca.nanometrics.gflot.client.options.PanOptions;
import ca.nanometrics.gflot.client.options.PlotOptions;
import ca.nanometrics.gflot.client.options.ZoomOptions;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gflot.examples.client.examples.DefaultActivity;
import com.googlecode.gflot.examples.client.resources.Resources;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesData;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesRaw;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesSource;

/**
 * @author Nicolas Morel
 */
@GFlotExamplesRaw( NavigatePlace.UI_RAW_SOURCE_FILENAME )
public class NavigateExample
    extends DefaultActivity
{

    private static Binder binder = GWT.create( Binder.class );

    interface Binder
        extends UiBinder<Widget, NavigateExample>
    {
    }

    private static final String NO_ZOOM_MESSAGE = "No current zoom or pan";

    /**
     * Plot
     */
    @GFlotExamplesData
    @UiField( provided = true )
    SimplePlot plot;

    /**
     * Message showing panning and zooming information
     */
    @GFlotExamplesData
    @UiField
    Label message;

    public NavigateExample( Resources resources )
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
            new LineSeriesOptions().setShow( true ).setFill( true ) ).setShadowSize( 0 ) );
        plotOptions.setLegendOptions( new LegendOptions().setShow( false ) );

        plotOptions.addXAxisOptions( new AxisOptions().setZoomRange( 0.1d, 10d ).setPanRange(
            new Double[] { -10d, 10d } ) );
        plotOptions.addYAxisOptions( new AxisOptions().setZoomRange( 0.1d, 10d )
            .setPanRange( new Double[] { -10d, 10d } ).setLabelWidth( 20 ) );
        plotOptions.setZoomOptions( new ZoomOptions().setInteractive( true ) ).setPanOptions(
            new PanOptions().setInteractive( true ) );

        // create series
        SeriesHandler series1 = model.addSeries( "Series1" );

        // add data
        for ( double t = 0; t <= 2 * Math.PI; t += 0.01 )
        {
            series1.add( DataPoint.of( sumfCos( t, 10 ), sumfSin( t, 10 ) ) );
        }

        // create the plot
        plot = new SimplePlot( model, plotOptions );

        final NumberFormat format = NumberFormat.getDecimalFormat();

        plot.addZoomListener( new PlotZoomListener() {
            @Override
            public void onPlotZoom( Axes axes )
            {
                message.setText( "Zooming to x=[min:\"" + format.format( axes.getX().getMinimumValue() ) + "\", max:\""
                    + format.format( axes.getX().getMaximumValue() ) + "\"], y=[min:\""
                    + format.format( axes.getY().getMinimumValue() ) + "\", max:\""
                    + format.format( axes.getY().getMaximumValue() ) + "\"]" );
            }
        } );

        plot.addPanListener( new PlotPanListener() {
            @Override
            public void onPlotPan( Axes axes )
            {
                message.setText( "Panning to x=[min:\"" + format.format( axes.getX().getMinimumValue() ) + "\", max:\""
                    + format.format( axes.getX().getMaximumValue() ) + "\"], y=[min:\""
                    + format.format( axes.getY().getMinimumValue() ) + "\", max:\""
                    + format.format( axes.getY().getMaximumValue() ) + "\"]" );
            }
        } );

        Widget widget = binder.createAndBindUi( this );
        message.setText( NO_ZOOM_MESSAGE );
        return widget;
    }

    /**
     *
     */
    @GFlotExamplesSource
    private double sumfCos( double t, double m )
    {
        double res = 0;
        for ( double i = 1; i < m; ++i )
        {
            res += Math.cos( i * i * t ) / ( i * i );
        }
        return res;
    }

    /**
     *
     */
    @GFlotExamplesSource
    private double sumfSin( double t, double m )
    {
        double res = 0;
        for ( double i = 1; i < m; ++i )
        {
            res += Math.sin( i * i * t ) / ( i * i );
        }
        return res;
    }

    /**
     * On click left arrow
     */
    @GFlotExamplesSource
    @UiHandler( "left" )
    void onClickLeft( ClickEvent event )
    {
        plot.pan( new Pan().setLeft( -100 ) );
    }

    /**
     * On click right arrow
     */
    @GFlotExamplesSource
    @UiHandler( "right" )
    void onClickRight( ClickEvent event )
    {
        plot.pan( new Pan().setLeft( 100 ) );
    }

    /**
     * On click up arrow
     */
    @GFlotExamplesSource
    @UiHandler( "up" )
    void onClickUp( ClickEvent event )
    {
        plot.pan( new Pan().setTop( -100 ) );
    }

    /**
     * On click down arrow
     */
    @GFlotExamplesSource
    @UiHandler( "down" )
    void onClickDown( ClickEvent event )
    {
        plot.pan( new Pan().setTop( 100 ) );
    }

    /**
     * On click zoom out
     */
    @GFlotExamplesSource
    @UiHandler( "zoomOut" )
    void onClickZoomOut( ClickEvent event )
    {
        plot.zoomOut();
    }

    /**
     * On click reset
     */
    @GFlotExamplesSource
    @UiHandler( "reset" )
    void onClickReset( ClickEvent event )
    {
        message.setText( NO_ZOOM_MESSAGE );
        plot.getPlotOptions().getXAxisOptions().clearMinimum().clearMaximum();
        plot.getPlotOptions().getYAxisOptions().clearMinimum().clearMaximum();
        plot.redraw();
    }
}
