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

import ca.nanometrics.gflot.client.util.JSONArrayWrapper;

import com.google.gwt.json.client.JSONArray;

/**
 * @author AlexanderDeleon
 */
public class DataPoint extends JSONArrayWrapper {

	public DataPoint() {
		// empty
	}

	public DataPoint(JSONArray array) {
		super(array);
	}

	public DataPoint(double x, double y) {
		setX(x);
		setY(y);
	}

	public void setX(double x) {
		set(0, new Double(x));
	}

	public void setY(double y) {
		set(1, new Double(y));
	}

	public double getX() {
		return get(0).isNumber().doubleValue();
	}

	public double getY() {
		return get(1).isNumber().doubleValue();
	}

}
