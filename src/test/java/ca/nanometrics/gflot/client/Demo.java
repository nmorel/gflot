package ca.nanometrics.gflot.client;

import ca.nanometrics.gflot.client.example.GFlotExample;
import ca.nanometrics.gflot.client.example.SimplePlotExample;
import ca.nanometrics.gflot.client.example.TickFormatterExample;

import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;

public class Demo {

	private final TabPanel m_tabs = new TabPanel();

	private void addExamples() {
		addExample(new SimplePlotExample());
		addExample(new TickFormatterExample());
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
