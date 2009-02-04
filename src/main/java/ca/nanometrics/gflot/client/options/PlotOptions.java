/*
 * Copyright (c) 2008 Nanometrics Inc. 
 *
 *	Permission is hereby granted, free of charge, to any person obtaining a copy
 *	of this software and associated documentation files (the "Software"), to deal
 *	in the Software without restriction, including without limitation the rights
 *	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *	copies of the Software, and to permit persons to whom the Software is
 *	furnished to do so, subject to the following conditions:
 *
 *	The above copyright notice and this permission notice shall be included in
 *	all copies or substantial portions of the Software.
 *
 *	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *	THE SOFTWARE.
 */
package ca.nanometrics.gflot.client.options;

import ca.nanometrics.gflot.client.util.JSONHelper;
import ca.nanometrics.gflot.client.util.JSONObjectWrapper;

/**
 * @author AlexanderDeleon
 */
public class PlotOptions extends JSONObjectWrapper {

	public PlotOptions setLegendOptions(LegendOptions legendOptions) {
		put("legend", legendOptions);
		return this;
	}

	public PlotOptions setXAxisOptions(AxisOptions xAxisOptions) {
		put("xaxis", xAxisOptions);
		return this;
	}

	public PlotOptions setYAxisOptions(AxisOptions yAxisOptions) {
		put("yaxis", yAxisOptions);
		return this;
	}

	public PlotOptions setDefaultLineSeriesOptions(
			SeriesOptions lineSeriesOptions) {
		put("lines", lineSeriesOptions);
		return this;
	}

	public PlotOptions setDefaultBarsSeriesOptions(
			SeriesOptions barSeriesOptions) {
		put("bars", barSeriesOptions);
		return this;
	}

	public PlotOptions setDefaultPointsOptions(SeriesOptions pointsSeriesOptions) {
		put("points", pointsSeriesOptions);
		return this;
	}

	public PlotOptions setDefaultColors(String[] cssColors) {

		put("colors", JSONHelper.wrapArray(cssColors));
		return this;
	}

	public PlotOptions setDefaultShadowSize(double shadow) {
		put("shadowSize", new Double(shadow));
		return this;
	}

	public PlotOptions setSelectionOptions(SelectionOptions selectionOptions) {
		put("selection", selectionOptions);
		return this;
	}

	public PlotOptions setGridOptions(GridOptions gridOptions) {
		put("grid", gridOptions);
		return this;
	}

}
