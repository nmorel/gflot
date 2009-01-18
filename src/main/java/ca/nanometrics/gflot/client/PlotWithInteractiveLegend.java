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

import java.util.HashMap;
import java.util.Map;

import ca.nanometrics.gflot.client.PlotModel.PlotModelListener;
import ca.nanometrics.gflot.client.event.PlotClickListener;
import ca.nanometrics.gflot.client.event.PlotHoverListener;
import ca.nanometrics.gflot.client.event.SelectionListener;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Alexander De Leon
 */
public class PlotWithInteractiveLegend extends Composite implements PlotWidget, PlotModelListener {

	private final PlotWidget m_plot;
	private Panel m_legendPanel;
	private final Map<SeriesHandler, LegendItem> m_legend;

	public PlotWithInteractiveLegend(PlotWidget plot) {
		m_legend = new HashMap<SeriesHandler, LegendItem>();
		m_plot = plot;
		initWidget(createUi());
		buildLegendFromModel(m_plot.getModel());
	}

	private void buildLegendFromModel(PlotModel model) {
		for (SeriesHandler seriesHandler : model.getHandlers()) {
			addSeriesToLegend(seriesHandler.getSeries().getLabel(), seriesHandler.getSeries().getColor(), seriesHandler);
		}
		model.addListener(this);
	}

	public void addClickListener(PlotClickListener listener, boolean onlyOnDatapoint) {
		m_plot.addClickListener(listener, onlyOnDatapoint);
	}

	public void addHoverListener(PlotHoverListener listener, boolean onlyOnDatapoint) {
		m_plot.addHoverListener(listener, onlyOnDatapoint);
	}

	public void addSelectionListener(SelectionListener listener) {
		m_plot.addSelectionListener(listener);
	}

	public int getHeight() {
		return m_plot.getHeight();
	}

	public int getWidth() {
		return m_plot.getWidth();
	}

	public void setHeight(int height) {
		m_plot.setHeight(height);
	}

	public void setLinearSelection(double x1, double x2) {
		m_plot.setLinearSelection(x1, x2);
	}

	public void setRectangularSelection(double x1, double y1, double x2, double y2) {
		m_plot.setRectangularSelection(x1, y1, x2, y2);
	}

	public void setWidth(int width) {
		m_plot.setWidth(width);
	}

	public void redraw() {
		m_plot.redraw();
	}

	public Widget getWidget() {
		return this;
	}

	public PlotModel getModel() {
		return m_plot.getModel();
	}

	public void addLegendWidget(SeriesHandler handler, Widget widget) {
		m_legend.get(handler).addWidget(widget);
	}

	public void onAddSeries(PlotModel model, String label, String color, SeriesHandler handler) {
		addSeriesToLegend(label, color, handler);

	}

	public void onRemoveSeries(PlotModel model, SeriesHandler handler) {
		m_legendPanel.remove(m_legend.get(handler));
	}

	/* --------------------- helper methods -- */
	private void addSeriesToLegend(String label, String color, SeriesHandler handler) {
		LegendItem item = new LegendItem(color, handler, label);
		m_legend.put(handler, item);
		m_legendPanel.add(item);
	}

	private Widget createUi() {
		VerticalPanel panel = new VerticalPanel();
		Widget plotWidget = m_plot.getWidget();

		m_legendPanel = new HorizontalPanel();

		panel.add(m_legendPanel);
		panel.add(plotWidget);

		return panel;
	}

	/**
	 * Class for the legend item widget
	 */
	private class LegendItem extends Composite {

		private static final String COLOR_BAND_HEIGHT = "3px";

		final HorizontalPanel m_labelsPanel;

		public LegendItem(String color, final SeriesHandler handler, String label) {
			FlexTable table = new FlexTable();

			HTML colorBand = new HTML("<div style=\"width: 100%; height: " + COLOR_BAND_HEIGHT + "; background-color: "
					+ color + ";\"></div>");
			table.setWidget(0, 0, colorBand);
			table.getFlexCellFormatter().setColSpan(0, 0, 2);

			final CheckBox checkBox = new CheckBox();
			checkBox.setChecked(true);
			checkBox.addClickListener(new ClickListener() {
				public void onClick(Widget sender) {
					handler.setVisible(checkBox.isChecked());
					m_plot.redraw();
				}
			});
			table.setWidget(1, 0, checkBox);
			table.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_LEFT);

			m_labelsPanel = new HorizontalPanel();
			m_labelsPanel.add(new Label(label));
			table.setWidget(1, 1, m_labelsPanel);
			table.getCellFormatter().setHorizontalAlignment(1, 1, HasHorizontalAlignment.ALIGN_CENTER);

			initWidget(table);

		}

		public void addWidget(Widget widget) {
			m_labelsPanel.add(widget);
		}

	}

}
