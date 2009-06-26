/**
 * Copyright (c) 2009 Dumontierlab
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package ca.nanometrics.gflot.client.example;

import ca.nanometrics.gflot.client.DataPoint;
import ca.nanometrics.gflot.client.PlotModelStrategy;
import ca.nanometrics.gflot.client.PlotWidget;
import ca.nanometrics.gflot.client.PlotWithOverview;
import ca.nanometrics.gflot.client.PlotWithOverviewModel;
import ca.nanometrics.gflot.client.SeriesHandler;
import ca.nanometrics.gflot.client.options.LineSeriesOptions;
import ca.nanometrics.gflot.client.options.PlotOptions;
import ca.nanometrics.gflot.client.options.PointsSeriesOptions;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Alexander De Leon
 */
public class DecimationExample implements GFlotExample {

	private double previous = 0;
	private int timeCounter = 0;

	public Widget createExample() {
		PlotWithOverviewModel model = new PlotWithOverviewModel(PlotModelStrategy.downSamplingStrategy(20));
		PlotOptions plotOptions = new PlotOptions();
		plotOptions.setDefaultLineSeriesOptions(new LineSeriesOptions().setLineWidth(1).setShow(true));
		plotOptions.setDefaultPointsOptions(new PointsSeriesOptions().setRadius(2).setShow(true));
		plotOptions.setDefaultShadowSize(0);

		final SeriesHandler series = model.addSeries("Random Series", "#003366");

		// create the plot
		final PlotWithOverview plot = new PlotWithOverview(model, plotOptions);

		// pull the "fake" RPC service for new data
		final Timer updater = new Timer() {
			@Override
			public void run() {
				update(series, plot);
			}
		};

		// put it on a panel
		FlowPanel panel = new FlowPanel();
		panel.add(plot);
		HorizontalPanel buttonsPanel = new HorizontalPanel();
		buttonsPanel.setSpacing(5);
		buttonsPanel.add(new Button("Start", new ClickListener() {
			public void onClick(Widget sender) {
				updater.scheduleRepeating(1000);
			}
		}));
		buttonsPanel.add(new Button("Stop", new ClickListener() {
			public void onClick(Widget sender) {
				updater.cancel();
			}
		}));
		panel.add(buttonsPanel);
		return panel;
	}

	public String getName() {
		return "Decimation";
	}

	private void update(final SeriesHandler series, final PlotWidget plot) {
		FakeRpcServiceAsync service = getRpcService();
		service.getNewData(new AsyncCallback<DataPoint[]>() {
			public void onFailure(Throwable caught) {
				GWT.log("Something went wrong", caught);
			}

			public void onSuccess(DataPoint[] result) {
				for (DataPoint dataPoint : result) {
					series.add(dataPoint);
				}
				plot.redraw();
			}
		});

	}

	private FakeRpcServiceAsync getRpcService() {
		return new FakeRpcServiceAsync() {

			public void getNewData(final AsyncCallback<DataPoint[]> callback) {
				double up = Random.nextDouble();
				double down = Random.nextDouble();
				callback.onSuccess(new DataPoint[] { new DataPoint(timeCounter++, previous - down),
						new DataPoint(timeCounter++, previous + up) });
				previous = previous + up;
			}
		};
	}

	/** The Async interface of the service */
	interface FakeRpcServiceAsync {

		void getNewData(AsyncCallback<DataPoint[]> callback);

	}
}
