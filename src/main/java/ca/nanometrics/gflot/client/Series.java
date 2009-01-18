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
package ca.nanometrics.gflot.client;

import ca.nanometrics.gflot.client.options.SeriesOptions;
import ca.nanometrics.gflot.client.util.JSONObjectWrapper;

import com.google.gwt.json.client.JSONObject;

/**
 * @author AlexanderDeleon
 */
public class Series extends JSONObjectWrapper {

	public Series() {
		super();
	}

	protected Series(JSONObject obj) {
		super(obj);
	}

	public void setData(SeriesData data) {
		put("data", data);
	}

	public SeriesData getData() {
		return new SeriesData(getArray("data"));
	}

	public void setColor(String cssColor) {
		put("color", cssColor);
	}

	public String getColor() {
		return getString("color");
	}

	public void setLabel(String label) {
		put("label", label);
	}

	public String getLabel() {
		return getString("label");
	}

	public void setShadowSize(int sizeInPx) {
		put("shadowSize", new Integer(sizeInPx));
	}

	public Integer getShadowSize() {
		return getInteger("shadowSize");
	}

	public void setSeriesOptions(SeriesType type, SeriesOptions options) {
		put(type.toString(), options);
	}

}
