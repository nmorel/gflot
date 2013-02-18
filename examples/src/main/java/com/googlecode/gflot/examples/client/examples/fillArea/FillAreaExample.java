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

package com.googlecode.gflot.examples.client.examples.fillArea;


import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gflot.client.*;
import com.googlecode.gflot.client.options.*;
import com.googlecode.gflot.examples.client.examples.DefaultActivity;
import com.googlecode.gflot.examples.client.resources.Resources;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesData;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesRaw;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesSource;

/**
 * @author Nicolas Morel
 */
@GFlotExamplesRaw( FillAreaPlace.UI_RAW_SOURCE_FILENAME )
public class FillAreaExample
        extends DefaultActivity
{

    private static Binder binder = GWT.create(Binder.class);

    interface Binder
            extends UiBinder<Widget, FillAreaExample>
    {
    }

    /**
     * Plot
     */
    @GFlotExamplesData
    @UiField( provided = true )
    SimplePlot plot;

    public FillAreaExample( Resources resources )
    {
        super(resources);
    }

    /**
     * Create plot
     */
    @Override
    @GFlotExamplesSource
    public Widget createPlot()
    {
        PlotModel model = new PlotModel();

        PlotOptions plotOptions = PlotOptions.create();
        plotOptions.setLegendOptions(LegendOptions.create().setShow(false));
        plotOptions.addYAxisOptions(AxisOptions.create().setShow(true));
        plotOptions.addXAxisOptions(AxisOptions.create().setShow(true));
        plotOptions.setZoomOptions(ZoomOptions.create().setInteractive(true));
        plotOptions.setPanOptions(PanOptions.create().setInteractive(true));

        JsArray<LevelInfo> levels = JavaScriptObject.createArray().cast();
        levels.push(LevelInfo.of(LevelInfo.LevelRepresentation.ASYMMETRIC).setColor("red"));
        levels.push(LevelInfo.of(LevelInfo.LevelRepresentation.SYMMETRIC).setColor("green"));

        // create a series
        final SeriesHandler vancouverSeries = model.addSeries(Series.of("Vancouver").setFillArea(levels).setLineSeriesOptions(LineSeriesOptions.create().setShow(true).setSteps(true)));

        // add data
        for ( int i = 0; i < 10; i++ )
        {
            double x = i + 10.0;
            double y = i * 0.5;
            vancouverSeries.add(MultiLevelDataPoint.of(x, y, y - 10.0, y + 15.0, 20));
        }

        // create the plot
        plot = new SimplePlot(model, plotOptions);

        return binder.createAndBindUi(this);
    }
}
