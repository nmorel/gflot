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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Alexander De Leon
 */
public class DownsamplingSeriesData extends SeriesData {
	private final int m_capacity;
	private double m_downsamplingRate = 1;
	private final List<DataPoint> m_downSamplingBuffer;

	public DownsamplingSeriesData(int capacity) {
		m_capacity = capacity;
		m_downSamplingBuffer = new ArrayList<DataPoint>();
	}

	public void add(DataPoint dataPoint) {
		if (m_downsamplingRate == 1) {
			super.add(dataPoint);
		} else {
			m_downSamplingBuffer.add(dataPoint);
			if (m_downSamplingBuffer.size() == (int) (2 / m_downsamplingRate)) {
				DataPoint[] samples = downsampling(m_downSamplingBuffer);
				for (int i = 0; i < samples.length; i++) {
					super.add(samples[i]);
				}
				m_downSamplingBuffer.clear();
			}
		}
		if (size() > m_capacity) {
			decimate();
		}
	}

	private void decimate() {
		m_downsamplingRate /= 2;
		int bufferSize = 4;
		List<DataPoint> decimationBuffer = new ArrayList<DataPoint>(bufferSize);
		SeriesData decimatedData = new SeriesData();
		for (int i = 0; i < size(); i++) {
			decimationBuffer.add(new DataPoint(getX(i), getY(i)));
			if (bufferSize == decimationBuffer.size()) {
				downsamplingAndAdd(decimationBuffer, decimatedData);
				decimationBuffer.clear();
			}
		}
		// deal with the remainder samples in the buffer
		if (decimationBuffer.size() > 0) {
			if (decimationBuffer.size() <= 2) {
				for (Iterator i = decimationBuffer.iterator(); i.hasNext();) {
					decimatedData.add((DataPoint) i.next());
				}
			} else {
				downsamplingAndAdd(decimationBuffer, decimatedData);
			}
		}
		setData(decimatedData);
	}

	private DataPoint[] downsampling(List decimationBuffer) {
		DataPoint[] samples = new DataPoint[2];
		DataPoint min = getMin(decimationBuffer);
		DataPoint max = getMax(decimationBuffer);
		if (min.getX() < max.getX()) {
			samples[0] = min;
			samples[1] = max;
		} else {
			samples[0] = max;
			samples[1] = min;
		}
		return samples;
	}

	private void downsamplingAndAdd(List decimationBuffer,
			SeriesData decimatedData) {
		DataPoint[] samples = downsampling(decimationBuffer);
		for (int i = 0; i < samples.length; i++) {
			decimatedData.add(samples[i]);
		}
	}

	private DataPoint getMax(List decimationBuffer) {
		double max = -1 * Double.MAX_VALUE;
		DataPoint maxPoint = null;
		for (int i = 0; i < decimationBuffer.size(); i++) {
			DataPoint point = (DataPoint) decimationBuffer.get(i);
			if (max < point.getY()) {
				maxPoint = point;
				max = maxPoint.getY();
			}
		}
		return maxPoint;
	}

	private DataPoint getMin(List decimationBuffer) {
		double min = Double.MAX_VALUE;
		DataPoint minPoint = null;
		for (int i = 0; i < decimationBuffer.size(); i++) {
			DataPoint point = (DataPoint) decimationBuffer.get(i);
			if (min > point.getY()) {
				minPoint = point;
				min = minPoint.getY();
			}
		}
		return minPoint;
	}

}
