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
package ca.nanometrics.gflot.client.options;

import ca.nanometrics.gflot.client.Tick;
import ca.nanometrics.gflot.client.util.JSONHelper;
import ca.nanometrics.gflot.client.util.JSONObjectWrapper;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * @author Alexander De Leon
 */
public class AxisOptions extends JSONObjectWrapper {
	private TickFormatter m_tickFormatter;

	public AxisOptions setMinimum(double min) {
		put("min", new Double(min));
		return this;
	}

	public AxisOptions setMaximum(double max) {
		put("max", new Double(max));
		return this;
	}

	public AxisOptions setAutoscaleMargin(double margin) {
		put("autoscaleMargin", new Double(margin));
		return this;
	}

	public AxisOptions setTicks(int ticks) {
		put("ticks", new Double(ticks));
		return this;
	}

	public AxisOptions setTicks(Tick[] ticks) {
		put("ticks", JSONHelper.wrapArray(ticks));
		return this;
	}

	public AxisOptions setTickFormatter(TickFormatter tickFormatter) {
		setTickFormatterNative(getWrappedObj().getJavaScriptObject(),
				tickFormatter);
		return this;
	}

	static native void setTickFormatterNative(JavaScriptObject axisOptions,
			TickFormatter tickFormatter)/*-{
							axisOptions.tickFormatter = function(val, axis)
							{
								var jsonAxisObject = @com.google.gwt.json.client.JSONObject::new(Lcom/google/gwt/core/client/JavaScriptObject;)(axis);
								var javaAxisObject = @ca.nanometrics.gflot.client.Axis::new(Lcom/google/gwt/json/client/JSONObject;)(jsonAxisObject);
								return tickFormatter.@ca.nanometrics.gflot.client.options.TickFormatter::formatTickValue(DLca/nanometrics/gflot/client/Axis;)(val, javaAxisObject);
							};
					}-*/;
}
