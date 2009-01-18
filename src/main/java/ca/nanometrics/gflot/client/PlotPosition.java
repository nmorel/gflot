/**
 * Copyright (c) 2008 Nanometrics Inc. All Rights Reserved.
 */
package ca.nanometrics.gflot.client;

import ca.nanometrics.gflot.client.util.JSONObjectWrapper;

import com.google.gwt.json.client.JSONObject;

/**
 * A position in a plot. If the plot has dual axis x2 and/or y2 will be not
 * null.
 * 
 * @author Alexander De Leon
 */
public class PlotPosition extends JSONObjectWrapper {
	private static final String X1 = "x1";
	private static final String Y1 = "y1";
	private static final String X2 = "x2";
	private static final String Y2 = "y2";

	protected PlotPosition() {
		super();
	}

	protected PlotPosition(JSONObject obj) {
		super(obj);
	}

	public PlotPosition(double x1, double y1) {
		this(new Double(x1), new Double(y1), null, null);
	}

	public PlotPosition(Double x1, Double y1, Double x2, Double y2) {
		this();
		if (x1 != null) {
			put(X1, x1);
		}
		if (y1 != null) {
			put(Y1, y1);
		}
		if (x2 != null) {
			put(X2, x2);
		}
		if (y2 != null) {
			put(Y2, y2);
		}
	}

	public Double getX1() {
		return getDouble(X1);
	}

	public Double getY1() {
		return getDouble(Y1);
	}

	public Double getX2() {
		return getDouble(X2);
	}

	public Double getY2() {
		return getDouble(Y2);
	}

}
