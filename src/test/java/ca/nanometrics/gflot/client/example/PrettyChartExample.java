/**
 * Copyright (c) 2009 Nanometrics Inc. All Rights Reserved.
 */
package ca.nanometrics.gflot.client.example;

import ca.nanometrics.gflot.client.Axis;
import ca.nanometrics.gflot.client.DataPoint;
import ca.nanometrics.gflot.client.PlotModelStrategy;
import ca.nanometrics.gflot.client.PlotWithOverviewModel;
import ca.nanometrics.gflot.client.SeriesHandler;
import ca.nanometrics.gflot.client.SimplePlot;
import ca.nanometrics.gflot.client.options.AxisOptions;
import ca.nanometrics.gflot.client.options.LineSeriesOptions;
import ca.nanometrics.gflot.client.options.PlotOptions;
import ca.nanometrics.gflot.client.options.PointsSeriesOptions;
import ca.nanometrics.gflot.client.options.TickFormatter;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Alexander De Leon
 * 
 */
public class PrettyChartExample implements GFlotExample {

	private static final String[] MONTH_NAMES = { "jan", "feb", "mar", "apr",
			"may", "jun", "jul", "aug", "sep", "oct", "nov", "dec" };

	public String getName() {
		return "Pretty Chart Example";
	}

	public Widget createExample() {

		PlotWithOverviewModel model = new PlotWithOverviewModel(
				PlotModelStrategy.defaultStrategy());
		PlotOptions plotOptions = new PlotOptions();
		plotOptions.setDefaultLineSeriesOptions(new LineSeriesOptions()
				.setLineWidth(1).setShow(true));
		plotOptions.setDefaultPointsOptions(new PointsSeriesOptions()
				.setRadius(2).setShow(true));
		plotOptions.setDefaultShadowSize(0);

		// add tick formatter to the options
		plotOptions.setXAxisOptions(new AxisOptions().setTicks(12)
				.setTickFormatter(new TickFormatter() {
					public String formatTickValue(double tickValue, Axis axis) {
						return MONTH_NAMES[(int) (tickValue - 1)];
					}
				}));

		// create a series
		SeriesHandler ottawaSeries = model.addSeries("Ottawa");
		SeriesHandler vancouberSeries = model.addSeries("Vacouber");

		// add data
		ottawaSeries.add(new DataPoint(1, -10.5));
		ottawaSeries.add(new DataPoint(2, -8.6));
		ottawaSeries.add(new DataPoint(3, -2.4));
		ottawaSeries.add(new DataPoint(4, 6));
		ottawaSeries.add(new DataPoint(5, 13.6));
		ottawaSeries.add(new DataPoint(6, 18.4));
		ottawaSeries.add(new DataPoint(7, 21));
		ottawaSeries.add(new DataPoint(8, 19.7));
		ottawaSeries.add(new DataPoint(9, 14.7));
		ottawaSeries.add(new DataPoint(10, 8.2));
		ottawaSeries.add(new DataPoint(11, 1.5));
		ottawaSeries.add(new DataPoint(12, -6.6));

		// create the plot
		SimplePlot plot = new SimplePlot(model, plotOptions);

		// put it on a panel
		FlowPanel panel = new FlowPanel();
		panel.add(plot);

		return panel;
	}

}
