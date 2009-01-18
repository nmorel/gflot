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

import ca.nanometrics.gflot.client.util.JSONObjectWrapper;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;

/**
 * @author AlexanderDeleon
 */
public class LegendOptions extends JSONObjectWrapper {

	public static final String NORTH_EAST = "ne";
	public static final String NORTH_WEST = "nw";
	public static final String SOUTH_EAST = "se";
	public static final String SOUTH_WEST = "sw";

	public LegendOptions setShow(boolean show) {
		put("show", show);
		return this;
	}

	public LegendOptions setLabelBoxBorderColor(String cssColor) {
		put("labelBoxBorderColor", cssColor);
		return this;
	}

	public LegendOptions setNumOfColumns(int cols) {
		put("noColumns", new Integer(cols));
		return this;
	}

	public LegendOptions setPosition(String position) {
		put("position", position);
		return this;
	}

	public LegendOptions setMargin(double margin) {
		put("margin", new Double(margin));
		return this;
	}

	public LegendOptions setBackgroundColor(String cssColor) {
		put("backgroudColor", cssColor);
		return this;
	}

	public LegendOptions setBackgroundOpacity(double opacity) {
		put("backgroundOpacity", new Double(opacity));
		return this;
	}

	public LegendOptions setContainer(Element container) {
		put("container", DOM.getElementProperty(container, "id"));
		return this;
	}
}
