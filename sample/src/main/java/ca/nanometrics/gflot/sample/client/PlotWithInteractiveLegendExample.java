package ca.nanometrics.gflot.sample.client;

import ca.nanometrics.gflot.client.Axis;
import ca.nanometrics.gflot.client.DataPoint;
import ca.nanometrics.gflot.client.PlotModel;
import ca.nanometrics.gflot.client.PlotModelStrategy;
import ca.nanometrics.gflot.client.PlotWithInteractiveLegend;
import ca.nanometrics.gflot.client.SeriesHandler;
import ca.nanometrics.gflot.client.SimplePlot;
import ca.nanometrics.gflot.client.options.AxisOptions;
import ca.nanometrics.gflot.client.options.LegendOptions;
import ca.nanometrics.gflot.client.options.LineSeriesOptions;
import ca.nanometrics.gflot.client.options.PlotOptions;
import ca.nanometrics.gflot.client.options.PointsSeriesOptions;
import ca.nanometrics.gflot.client.options.TickFormatter;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Alexander De Leon
 *
 */
public class PlotWithInteractiveLegendExample implements GFlotExample {

	private static final String[] MONTH_NAMES = { "jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct",
			"nov", "dec" };

	public String getName() {
		return "PlotWithInteractiveLegend";
	}

	public Widget createExample() {

		PlotModel model = new PlotModel(PlotModelStrategy.defaultStrategy());
		PlotOptions plotOptions = new PlotOptions();
		plotOptions.setDefaultLineSeriesOptions(new LineSeriesOptions().setLineWidth(1).setShow(true));
		plotOptions.setDefaultPointsOptions(new PointsSeriesOptions().setRadius(2).setShow(true));
		plotOptions.setDefaultShadowSize(0);
		plotOptions.setLegendOptions(new LegendOptions().setShow(false));

		// add tick formatter to the options
		plotOptions.setXAxisOptions(new AxisOptions().setTicks(12).setTickFormatter(new TickFormatter() {
			public String formatTickValue(double tickValue, Axis axis) {
				int value = (int) tickValue;
				if (value > 0 && value <= MONTH_NAMES.length) {
					return MONTH_NAMES[(int) (tickValue - 1)];
				}else{
					return "";
				}
			}
		}));

		// create a series
		// Note: you need to specified the colors in other for the legend to
		// work properly
		SeriesHandler ottawaSeries = model.addSeries("Ottawa", "#edc240");
		SeriesHandler vancouverSeries = model.addSeries("Vancouver", "#afd8f8");

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

		vancouverSeries.add(new DataPoint(1, 4.8));
		vancouverSeries.add(new DataPoint(2, 5.9));
		vancouverSeries.add(new DataPoint(3, 7.6));
		vancouverSeries.add(new DataPoint(4, 10));
		vancouverSeries.add(new DataPoint(5, 13.2));
		vancouverSeries.add(new DataPoint(6, 15.9));
		vancouverSeries.add(new DataPoint(7, 18.1));
		vancouverSeries.add(new DataPoint(8, 18.3));
		vancouverSeries.add(new DataPoint(9, 15.4));
		vancouverSeries.add(new DataPoint(10, 11.1));
		vancouverSeries.add(new DataPoint(11, 7.1));
		vancouverSeries.add(new DataPoint(12, 4.8));

		// create the plot
		SimplePlot plot = new SimplePlot(model, plotOptions);

		// put it on a panel
		FlowPanel panel = new FlowPanel();
		panel.add(new HTML(
				"<div style=\"font-weight: bold; align: center; margin: 0px 0px 5px 5px;\">Month Temperatures (Daily Average in &deg;C)</div>"));
		panel.add(new PlotWithInteractiveLegend(plot));

		return panel;
	}

}
