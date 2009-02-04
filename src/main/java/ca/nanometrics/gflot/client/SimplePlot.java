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
package ca.nanometrics.gflot.client;

import java.util.ArrayList;
import java.util.List;

import ca.nanometrics.gflot.client.event.PlotClickListener;
import ca.nanometrics.gflot.client.event.PlotHoverListener;
import ca.nanometrics.gflot.client.event.SelectionListener;
import ca.nanometrics.gflot.client.jsni.Plot;
import ca.nanometrics.gflot.client.options.PlotOptions;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author AlexanderDeleon
 */
public class SimplePlot extends Widget implements PlotWidget {

	private static final int DEFAULT_WIDTH = 600;
	private static final int DEFAULT_HEIGHT = 300;
	private static int s_counter = 1;

	private final PlotModel model;
	private int width;
	private int height;
	private Plot plot;
	private boolean loaded;
	private PlotOptions options;
	private final List<Command> onLoadOperations;

	public SimplePlot(PlotModel model) {
		this.model = model;
		onLoadOperations = new ArrayList<Command>();
		setElement(createPlotContainer());
		setWidth(DEFAULT_WIDTH);
		setHeight(DEFAULT_HEIGHT);
	}

	public SimplePlot(PlotModel model, PlotOptions options) {
		this(model);
		this.options = options;
	}

	public SimplePlot() {

		this(new PlotModel());
	}

	public SimplePlot(PlotOptions options) {
		this(new PlotModel(), options);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
		DOM.setStyleAttribute(getElement(), "width", width + "px");
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
		DOM.setStyleAttribute(getElement(), "height", height + "px");
	}

	public void setupGrid() {
		assertLoaded();
		plot.setupGrid();
	}

	public void draw() {
		assertLoaded();
		plot.draw();
	}

	public void setLinearSelection(double x1, double x2) {
		assertLoaded();
		plot.setLinearSelection(x1, x2);
	}

	public void setRectangularSelection(double x1, double y1, double x2,
			double y2) {
		assertLoaded();
		plot.setRectangularSelection(x1, y1, x2, y2);
	}

	public void addSelectionListener(final SelectionListener listener) {
		if (loaded) {
			plot.addSelectionListener(getElement(), listener);
		} else {
			onLoadOperations.add(new Command() {
				public void execute() {
					plot.addSelectionListener(getElement(), listener);
				}
			});
		}
	}

	public void addHoverListener(final PlotHoverListener listener,
			final boolean onlyOnDatapoint) {
		if (loaded) {
			plot.addPlotHoverListener(getElement(), listener, onlyOnDatapoint);
		} else {
			onLoadOperations.add(new Command() {
				public void execute() {
					plot.addPlotHoverListener(getElement(), listener,
							onlyOnDatapoint);
				}
			});
		}

	}

	public void addClickListener(final PlotClickListener listener,
			final boolean onlyOnDatapoint) {
		if (loaded) {
			plot.addPlotClickListener(getElement(), listener, onlyOnDatapoint);
		} else {
			onLoadOperations.add(new Command() {
				public void execute() {
					plot.addPlotClickListener(getElement(), listener,
							onlyOnDatapoint);
				}
			});
		}

	}

	public PlotModel getModel() {
		return model;
	}

	public Widget getWidget() {
		return this;
	}

	public void redraw() {
		plot.setData(model.getSeries());
		plot.setupGrid();
		plot.draw();
	}

	/* ------------------ Widget API -- */
	protected void onLoad() {
		super.onLoad();
		if (!loaded) {
			if (options == null) {
				plot = Plot.create(getElement(), model.getSeries());
			} else {
				plot = Plot.create(getElement(), model.getSeries(), options);
			}
			for (Command cmd : onLoadOperations) {
				cmd.execute();
			}
			onLoadOperations.clear();
			loaded = true;
		}
		redraw();
	}

	/* ------------------ Helper methods -- */
	protected Element createPlotContainer() {
		Element element = DOM.createElement("div");
		DOM.setElementProperty(element, "id", "gflot_" + s_counter++);
		return element;
	}

	protected void assertLoaded() {
		if (!loaded) {
			throw new IllegalStateException(
					"The widget has not been loaded yet. Please call this method after adding this widget to a panel");
		}
	}

}
