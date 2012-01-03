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

import ca.nanometrics.gflot.client.util.JSONObjectWrapper;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;

/**
 * @author Alexander De Leon
 */
public class PlotItem extends JSONObjectWrapper {

	private static final String DATAPOINT = "datapoint";
	private static final String DATA_INDEX = "dataindex";
	private static final String SERIES = "series";
	private static final String SERIES_INDEX = "seriesIndex";
	private static final String PAGE_X = "pageX";
	private static final String PAGE_Y = "pageY";

	protected PlotItem(JSONObject obj) {
		super(obj);
	}

	public DataPoint getDataPoint() {
		JSONArray array = getArray(DATAPOINT);
		if (array == null) {
			return null;
		}
		return new DataPoint(array);
	}

	public Integer getDataIndex() {
		return getInteger(DATA_INDEX);
	}

	public Series getSeries() {
		JSONObject obj = getObject(SERIES);
		if (obj == null) {
			return null;
		}
		return new Series(obj);
	}

	public Integer getSeriesIndex() {
		return getInteger(SERIES_INDEX);
	}

	public Integer getPageX() {
		return getInteger(PAGE_X);
	}

	public Integer getPageY() {
		return getInteger(PAGE_Y);
	}
}
