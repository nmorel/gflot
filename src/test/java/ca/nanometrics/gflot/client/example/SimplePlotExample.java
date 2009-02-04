/**
 * Copyright (c) 2009 Nanometrics Inc. All Rights Reserved.
 */
package ca.nanometrics.gflot.client.example;

import ca.nanometrics.gflot.client.DataPoint;
import ca.nanometrics.gflot.client.PlotModel;
import ca.nanometrics.gflot.client.SeriesHandler;
import ca.nanometrics.gflot.client.SimplePlot;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Alexander De Leon
 * 
 */
public class SimplePlotExample implements GFlotExample {

	public String getName() {
		return "SimplePlot Example";
	}

	public Widget createExample() {
		PlotModel model = new PlotModel();

		SeriesHandler handler = model.addSeries(
				"Ottawa's Month Temperatures (Daily Average in °C)", "blue");
		handler.add(new DataPoint(1, -10.5));
		handler.add(new DataPoint(2, -8.6));
		handler.add(new DataPoint(3, -2.4));
		handler.add(new DataPoint(4, 6));
		handler.add(new DataPoint(5, 13.6));
		handler.add(new DataPoint(6, 18.4));
		handler.add(new DataPoint(7, 21));
		handler.add(new DataPoint(8, 19.7));
		handler.add(new DataPoint(9, 14.7));
		handler.add(new DataPoint(10, 8.2));
		handler.add(new DataPoint(11, 1.5));
		handler.add(new DataPoint(12, -6.6));

		SimplePlot plot = new SimplePlot(model);

		FlowPanel panel = new FlowPanel();
		panel.add(plot);
		return panel;
	}

}
