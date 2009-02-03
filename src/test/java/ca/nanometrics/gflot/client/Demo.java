package ca.nanometrics.gflot.client;

import ca.nanometrics.gflot.client.event.PlotHoverListener;
import ca.nanometrics.gflot.client.jsni.Plot;
import ca.nanometrics.gflot.client.options.AxisOptions;
import ca.nanometrics.gflot.client.options.BarSeriesOptions;
import ca.nanometrics.gflot.client.options.PlotOptions;
import ca.nanometrics.gflot.client.options.BarSeriesOptions.BarAlignment;

import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class Demo
{

  private static int incrementingX = 0;
  private Timer timer;

  /**
   * @see com.google.gwt.core.client.EntryPoint#onModuleLoad()
   */
  public void onModuleLoad()
  {
    // testMultipleSeries();
    // testBarChart();
    RootPanel.get().add(new Button("Test", new ClickListener()
    {
      public void onClick(Widget sender)
      {
        testBarChart();
      }
    }));

  }

  private void testDecimation()
  {
    PlotWithOverviewModel model = new PlotWithOverviewModel(PlotModelStrategy.downSamplingStrategy(200));
    final PlotWithOverview plot = new PlotWithOverview(model);

    final SeriesHandler handler = model.addSeries("test");
    final Timer timer = new Timer()
    {
      @Override
      public void run()
      {
        int x = incrementingX++;
        handler.add(new DataPoint(x, Random.nextDouble()));
        plot.redraw();
      }
    };
    RootPanel.get().add(plot);
    RootPanel.get().add(new Button("Start", new ClickListener()
    {
      public void onClick(Widget sender)
      {
        timer.scheduleRepeating(100);
      }
    }));
    RootPanel.get().add(new Button("Stop", new ClickListener()
    {
      public void onClick(Widget sender)
      {
        timer.cancel();
      }
    }));
    RootPanel.get().add(new Button("Debug", new ClickListener()
    {
      public void onClick(Widget sender)
      {
        Window.alert(handler.getSeries().getData().toString());
      }
    }));

  }

  private void testBarChart()
  {
    PlotModel model = new PlotModel();
    PlotOptions plotOptions = new PlotOptions();
    AxisOptions xAxisOptions = new AxisOptions();
    xAxisOptions.setMinimum(0).setMaximum(4).setTicks(
        new Tick[] { new Tick(0, ""), new Tick(1, "All Bands"), new Tick(2, ""), new Tick(3, "Time Series Bands"),
            new Tick(4, "") });
    plotOptions.setXAxisOptions(xAxisOptions);
    BarSeriesOptions barSeriesOptions = new BarSeriesOptions();
    barSeriesOptions.setShow(true);
    barSeriesOptions.setLineWidth(0);
    barSeriesOptions.setBarWidth(1);
    barSeriesOptions.setAlignment(BarAlignment.CENTER);
    plotOptions.setDefaultBarsSeriesOptions(barSeriesOptions);
    SimplePlot plot = new SimplePlot(model, plotOptions);

    SeriesHandler series1 = model.addSeries("test series 1", "blue");
    SeriesHandler series2 = model.addSeries("test series 2", "red");
    series1.add(new DataPoint(1, 96));
    series2.add(new DataPoint(3, 73));
    RootPanel.get().add(plot);
  }

  private void testMultipleSeries()
  {
    final PlotWithOverviewModel model = new PlotWithOverviewModel(PlotModelStrategy.downSamplingStrategy(10, 60));
    final PlotWithInteractiveLegend plot =
        new PlotWithInteractiveLegend(new PlotWithOverview(model, new PlotOptions()));
    plot.addHoverListener(new PlotHoverListener()
    {
      public void onPlotHover(Plot plot, PlotPosition position, PlotItem item)
      {
        Window.alert("" + item);

      }
    }, false);

    final SeriesHandler series1 = model.addSeries("test series 1", "blue");
    final SeriesHandler series2 = model.addSeries("test series 2", "red");

    RootPanel.get().add(plot);
    RootPanel.get().add(new Button("start", new ClickListener()
    {
      public void onClick(Widget arg0)
      {
        timer.scheduleRepeating(1000);
      }
    }));
    RootPanel.get().add(new Button("stop", new ClickListener()
    {
      public void onClick(Widget arg0)
      {
        timer.cancel();
      }
    }));
    timer = new Timer()
    {
      @Override
      public void run()
      {
        int x = incrementingX++;
        series1.add(new DataPoint(x, Random.nextDouble()));
        series2.add(new DataPoint(x, Random.nextDouble()));
        plot.redraw();
        schedule(1000);
      }
    };
    RootPanel.get().add(new Button("debug", new ClickListener()
    {
      public void onClick(Widget sender)
      {
        for (Series series : model.getOverviewSeries())
        {
          Window.alert(series.getData().toString());
        }
      }
    }));
  }

}
