package ca.nanometrics.gflot.client.options;

import ca.nanometrics.gflot.client.util.JSONObjectWrapper;

/**
 * 
 * @author Mohamed M. El-Kalioby
 * @since Septmeber 21, 2009
 */
public class Marking extends JSONObjectWrapper {

	public void setY(Range Y) {
		put("yaxis", Y);
	}

	public void setX(Range X) {
		put("xaxis", X);
	}

	public void setColor(String cssColor) {
		put("color", cssColor);
	}

}
