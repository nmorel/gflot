/*
 * Copyright (c) 2012 Nicolas Morel
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

package com.googlecode.gflot.examples.client.examples.errorbars.bar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gflot.client.DataPoint;
import com.googlecode.gflot.client.PlotModel;
import com.googlecode.gflot.client.Series;
import com.googlecode.gflot.client.SeriesHandler;
import com.googlecode.gflot.client.SimplePlot;
import com.googlecode.gflot.client.options.AxisOptions;
import com.googlecode.gflot.client.options.BarSeriesOptions;
import com.googlecode.gflot.client.options.GlobalSeriesOptions;
import com.googlecode.gflot.client.options.LegendOptions;
import com.googlecode.gflot.client.options.LineSeriesOptions;
import com.googlecode.gflot.client.options.PanOptions;
import com.googlecode.gflot.client.options.PlotOptions;
import com.googlecode.gflot.client.options.PointsSeriesOptions;
import com.googlecode.gflot.client.options.ZoomOptions;
import com.googlecode.gflot.client.options.errorbars.DrawCap;
import com.googlecode.gflot.client.options.errorbars.ErrorBarsOptions;
import com.googlecode.gflot.examples.client.examples.DefaultActivity;
import com.googlecode.gflot.examples.client.resources.Resources;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesData;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesRaw;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesSource;

/**
 * @author Nicolas Morel
 */
@GFlotExamplesRaw( ErrorBarsPlace.UI_RAW_SOURCE_FILENAME )
public class ErrorBarsExample extends DefaultActivity
{

    interface Binder extends UiBinder<Widget, ErrorBarsExample>
    {}

    private static Binder binder = GWT.create( Binder.class );
    /**
     * Plot
     */
    @GFlotExamplesData
    @UiField( provided = true )
    SimplePlot plot;

    public ErrorBarsExample( Resources resources )
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
        PlotModel model = new PlotModel();
        PlotOptions plotOptions = PlotOptions.create()
            .setLegendOptions( LegendOptions.create()
                .setPosition( LegendOptions.LegendPosition.SOUTH_WEST )
                .setShow( true ) )
            .setGlobalSeriesOptions( GlobalSeriesOptions.create()
                .setLineSeriesOptions( LineSeriesOptions.create().setShow( false ) ) )
            .addXAxisOptions( AxisOptions.create()
                .setMinimum( 0.6 )
                .setMaximum( 3.1 ) )
            .addYAxisOptions( AxisOptions.create().setMinimum( 0 ).setMaximum( 3.5 ) )
            .setZoomOptions( ZoomOptions.create().setInteractive( true ) )
            .setPanOptions( PanOptions.create().setInteractive( true ) );

        // data1
        SeriesHandler series1 = model.addSeries( Series.of( "data1" )
            .setColor( "blue" )
            .setPointsOptions( PointsSeriesOptions.create()
                .setShow( true )
                .setRadius( 5 )
                .setFillColor( "blue" )
                .setErrorBars( PointsSeriesOptions.ErrorBarsMode.XY )
                .setXErrOptions( ErrorBarsOptions.create()
                    .setShow( true )
                    .setAsymmetric( true )
                    .setUpperCap( ErrorBarsOptions.CAP )
                    .setLowerCap( ErrorBarsOptions.CAP ) )
                .setYErrOptions( ErrorBarsOptions.create()
                    .setShow( true )
                    .setColor( "red" )
                    .setUpperCap( ErrorBarsOptions.CAP ) ) ) );
        series1.add( DataPoint.of( 1, 1, .5, .1, .3 ) );
        series1.add( DataPoint.of( 2, 2, .3, .5, .2 ) );
        series1.add( DataPoint.of( 3, 3, .9, .5, .2 ) );
        series1.add( DataPoint.of( 1.5, -.05, .5, .1, .3 ) );
        series1.add( DataPoint.of( 3.15, 1., .5, .1, .3 ) );
        series1.add( DataPoint.of( 2.5, -1., .5, .1, .3 ) );

        // data2
        SeriesHandler series2 = model.addSeries( Series.of( "data2" )
            .setColor( "red" )
            .setPointsOptions( PointsSeriesOptions.create()
                .setShow( true )
                .setRadius( 5 )
                .setErrorBars( PointsSeriesOptions.ErrorBarsMode.Y )
                .setYErrOptions( ErrorBarsOptions.create()
                    .setShow( true )
                    .setAsymmetric( true )
                    .setUpperCap( DrawCap.drawArrow() )
                    .setLowerCap( DrawCap.drawSemiCircle() ) ) ) );
        series2.add( DataPoint.of( .7, 3, .2, .4 ) );
        series2.add( DataPoint.of( 1.5, 2.2, .3, .4 ) );
        series2.add( DataPoint.of( 2.3, 1, .5, .2 ) );

        // data3
        SeriesHandler series3 = model.addSeries( Series.of( "data3" )
            .setColor( "green" )
            .setLineSeriesOptions( LineSeriesOptions.create().setShow( true ) )
            .setPointsOptions( PointsSeriesOptions.create()
                .setRadius( 0 )
                .setErrorBars( PointsSeriesOptions.ErrorBarsMode.Y )
                .setYErrOptions( ErrorBarsOptions.create()
                    .setShow( true )
                    .setUpperCap( ErrorBarsOptions.CAP )
                    .setLowerCap( ErrorBarsOptions.CAP )
                    .setRadius( 5 ) ) ) );
        series3.add( DataPoint.of( 1, 2, .4 ) );
        series3.add( DataPoint.of( 2, 0.5, .3 ) );
        series3.add( DataPoint.of( 2.7, 2, .5 ) );

        // data4
        SeriesHandler series4 = model.addSeries( Series.of( "data4" )
            .setColor( "orange" )
            .setBarsSeriesOptions( BarSeriesOptions.create()
                .setShow( true )
                .setAlignment( BarSeriesOptions.BarAlignment.CENTER )
                .setBarWidth( 0.25 ) ) );
        series4.add( DataPoint.of( 1.3, 1 ) );
        series4.add( DataPoint.of( 1.75, 2.5 ) );
        series4.add( DataPoint.of( 2.5, 0.5 ) );

        // data4 error
        SeriesHandler series4Errors = model.addSeries( Series.create()
            .setColor( "orange" )
            .setPointsOptions( PointsSeriesOptions.create()
                .setRadius( 0 )
                .setErrorBars( PointsSeriesOptions.ErrorBarsMode.Y )
                .setYErrOptions( ErrorBarsOptions.create()
                    .setShow( true )
                    .setUpperCap( ErrorBarsOptions.CAP )
                    .setLowerCap( ErrorBarsOptions.CAP )
                    .setRadius( 5 ) ) ) );
        series4Errors.add( DataPoint.of( 1.3, 1, 0.1 ) );
        series4Errors.add( DataPoint.of( 1.75, 2.5, 0.4 ) );
        series4Errors.add( DataPoint.of( 2.5, 0.5, 0.2 ) );


        // create the plot
        plot = new SimplePlot( model, plotOptions );

        return binder.createAndBindUi( this );
    }

}
