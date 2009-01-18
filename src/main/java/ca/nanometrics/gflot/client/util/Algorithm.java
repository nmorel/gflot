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
package ca.nanometrics.gflot.client.util;

import ca.nanometrics.gflot.client.SeriesData;

/**
 * @author AlexanderDeleon
 */
public class Algorithm {

	public static int xBinarySearch(SeriesData s, double xValue) {
		return xBinarySearch(s, 0, s.size(), xValue);
	}

	public static int xBinarySearch(SeriesData s, int fromIndex, int toIndex, double xValue) {
		if (fromIndex > toIndex) {
			return -1;
		}
		int lMid = fromIndex + (int) Math.floor((toIndex - fromIndex) / 2);
		int rMid = lMid + 1;

		if (xValue < s.getX(lMid)) {
			return xBinarySearch(s, fromIndex, lMid - 1, xValue);
		}
		if (xValue == s.getX(lMid)) {
			return lMid;
		}
		if (rMid < s.size()) {
			if (xValue > s.getX(rMid)) {
				return xBinarySearch(s, rMid, toIndex, xValue);
			}
			double rVal = s.getX(rMid);
			double lVal = s.getX(lMid);
			return Math.abs(rVal - xValue) > Math.abs(lVal - xValue) ? lMid : rMid;
		} else {
			return -1;
		}
	}
}
