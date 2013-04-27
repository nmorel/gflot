/*
 * Copyright (c) 2013 Nicolas Morel
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

package com.googlecode.gflot.examples.client.examples.categories;


import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gflot.client.DataPoint;
import com.googlecode.gflot.client.PlotModel;
import com.googlecode.gflot.client.SeriesHandler;
import com.googlecode.gflot.client.SimplePlot;
import com.googlecode.gflot.client.options.BarSeriesOptions;
import com.googlecode.gflot.client.options.CategoriesAxisOptions;
import com.googlecode.gflot.client.options.GlobalSeriesOptions;
import com.googlecode.gflot.client.options.PlotOptions;
import com.googlecode.gflot.examples.client.examples.DefaultActivity;
import com.googlecode.gflot.examples.client.resources.Resources;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesData;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesRaw;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesSource;

/**
 * @author Nicolas Morel
 */
@GFlotExamplesRaw(CategoriesPlace.UI_RAW_SOURCE_FILENAME)
public class CategoriesExample
        extends DefaultActivity {

    private static Binder binder = GWT.create(Binder.class);

    interface Binder
            extends UiBinder<Widget, CategoriesExample> {
    }

    /**
     * Plot
     */
    @GFlotExamplesData
    @UiField(provided = true)
    SimplePlot plot;

    public CategoriesExample(Resources resources) {
        super(resources);
    }

    /**
     * Create plot
     */
    @GFlotExamplesSource
    public Widget createPlot() {
        PlotModel model = new PlotModel();
        PlotOptions plotOptions = PlotOptions.create();
        plotOptions.setGlobalSeriesOptions(GlobalSeriesOptions.create().setBarsSeriesOptions(BarSeriesOptions.create().setShow(true).setBarWidth(0.6).setAlignment(BarSeriesOptions.BarAlignment.CENTER))).addXAxisOptions(CategoriesAxisOptions.create().setTickLength(0));

        SeriesHandler series = model.addSeries();
        series.add(DataPoint.of("January", 10));
        series.add(DataPoint.of("February", 8));
        series.add(DataPoint.of("March", 4));
        series.add(DataPoint.of("April", 13));
        series.add(DataPoint.of("May", 17));
        series.add(DataPoint.of("June", 9));

        // create the plot
        plot = new SimplePlot(model, plotOptions);

        return binder.createAndBindUi(this);
    }
}
