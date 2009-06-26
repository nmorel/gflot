package ca.nanometrics.gflot.client;

import ca.nanometrics.gflot.client.example.BarChartExample;
import ca.nanometrics.gflot.client.example.DecimationExample;
import ca.nanometrics.gflot.client.example.GFlotExample;
import ca.nanometrics.gflot.client.example.HoverExample;
import ca.nanometrics.gflot.client.example.PlotWithInteractiveLegendExample;
import ca.nanometrics.gflot.client.example.PlotWithOverviewExample;
import ca.nanometrics.gflot.client.example.SimplePlotExample;
import ca.nanometrics.gflot.client.example.SlidingWindowExample;

import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;

public class Demo {

	private final TabPanel m_tabs = new TabPanel();

	private void addExamples() {
		addExample(new SimplePlotExample());
		addExample(new BarChartExample());
		addExample(new PlotWithInteractiveLegendExample());
		addExample(new PlotWithOverviewExample());
		addExample(new HoverExample());
		addExample(new SlidingWindowExample());
		addExample(new DecimationExample());
	}

	private void addExample(GFlotExample example) {
		m_tabs.add(example.createExample(), example.getName());
	}

	public void onModuleLoad() {

		m_tabs.setWidth("100%");
		m_tabs.setHeight("100%");

		RootPanel.get().add(m_tabs);

		addExamples();
		m_tabs.selectTab(0);
	}

}
