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
package ca.nanometrics.gflot.client.jsni;

import ca.nanometrics.gflot.client.Series;
import ca.nanometrics.gflot.client.event.PlotClickListener;
import ca.nanometrics.gflot.client.event.PlotHoverListener;
import ca.nanometrics.gflot.client.event.SelectionListener;
import ca.nanometrics.gflot.client.options.PlotOptions;
import ca.nanometrics.gflot.client.util.JSONHelper;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Element;

/**
 * @author AlexanderDeleon
 */
public class Plot extends JavaScriptObject {

	protected Plot() {
		// empty
	}

	public static Plot create(Element container, Series[] series) {
		Plot plot = PlotImpl.create(container, JSONHelper.getJSONArray(
				JSONHelper.wrapArray(series)).getJavaScriptObject());
		return plot;
	}

	public static Plot create(Element container, Series[] series,
			PlotOptions options) {
		return PlotImpl.create(container, JSONHelper.getJSONArray(
				JSONHelper.wrapArray(series)).getJavaScriptObject(), JSONHelper
				.getJSONObject(options).getJavaScriptObject());

	}

	public final void setData(Series[] series) {
		PlotImpl.setData(this, JSONHelper.getJSONArray(
				JSONHelper.wrapArray(series)).getJavaScriptObject());
	}

	public final void setData(Series series) {
		setData(new Series[] { series });
	}

	public final void draw() {
		PlotImpl.draw(this);
	}

	public final void setupGrid() {
		PlotImpl.setupGrid(this);
	}

	public final void setLinearSelection(double x1, double x2) {
		PlotImpl.setLinearSelection(this, x1, x2);
	}

	public final void setRectangularSelection(double x1, double y1, double x2,
			double y2) {
		PlotImpl.setRectangularSelection(this, x1, y1, x2, y2);
	}

	public final void addSelectionListener(Element container,
			SelectionListener listener) {
		PlotImpl.addSelectionListener(container, listener);
	}

	public final void addPlotHoverListener(Element container,
			PlotHoverListener listener, boolean onlyOnDatapoint) {
		PlotImpl.addPlotHoverListener(container, listener, onlyOnDatapoint,
				this);
	}

	public final void addPlotClickListener(Element container,
			PlotClickListener listener, boolean onlyOnDatapoint) {
		PlotImpl.addPlotClickListener(container, listener, onlyOnDatapoint,
				this);
	}

	public final int getPlotOffsetLeft() {
		return PlotImpl.getPlotOffsetLeft(this);
	}

	public final int getPlotOffsetRight() {
		return PlotImpl.getPlotOffsetRight(this);
	}

	public final int getPlotOffsetTop() {
		return PlotImpl.getPlotOffsetTop(this);
	}

	public final int getPlotOffsetBottom() {
		return PlotImpl.getPlotOffsetBottom(this);
	}
}
