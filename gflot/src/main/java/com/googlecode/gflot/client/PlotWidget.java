/*
 * Copyright (c) 2012 Nicolas Morel
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */
package com.googlecode.gflot.client;

import com.google.gwt.user.client.ui.IsWidget;
import com.googlecode.gflot.client.event.PlotClickListener;
import com.googlecode.gflot.client.event.PlotHoverListener;
import com.googlecode.gflot.client.event.PlotLoadEvent.HasPlotLoadHandlers;
import com.googlecode.gflot.client.event.PlotSelectedListener;
import com.googlecode.gflot.client.event.PlotSelectingListener;
import com.googlecode.gflot.client.event.PlotUnselectedListener;
import com.googlecode.gflot.client.jsni.Plot;

/**
 * @author Alexander De Leon
 */
public interface PlotWidget
    extends HasPlotLoadHandlers, IsWidget
{

    /**
     * @return the width of the plot in pixel
     */
    int getWidth();

    /**
     * Set the width of the plot in pixel
     *
     * @param width the width
     */
    void setWidth( int width );

    /**
     * @return the height of the plot in pixel
     */
    int getHeight();

    /**
     * Set the height of the plot in pixel
     *
     * @param height the height
     */
    void setHeight( int height );

    void setLinearSelection( double x1, double x2 );

    void setRectangularSelection( double x1, double y1, double x2, double y2 );

    /**
     * Add a selected listener to the plot
     *
     * @param listener listener to add to the plot
     */
    void addSelectedListener( PlotSelectedListener listener );

    /**
     * Add a selecting listener to the plot
     *
     * @param listener listener to add to the plot
     */
    void addSelectingListener( PlotSelectingListener listener );

    /**
     * Add a unselected listener to the plot
     *
     * @param listener listener to add to the plot
     */
    void addUnselectedListener( PlotUnselectedListener listener );

    /**
     * @return the current selection in the plot. If there's currently no selection, the function returns null.
     */
    PlotSelectionArea getSelection();

    /**
     * Set the selection rectangle. "plotselected" event is fired.
     *
     * @param area area to select
     */
    void setSelection( PlotSelectionArea area );

    /**
     * Set the selection rectangle.
     *
     * @param area area to select
     * @param preventEvent true to avoid getting a "plotselected" event
     */
    void setSelection( PlotSelectionArea area, boolean preventEvent );

    /**
     * Clear the selection rectangle. "plotunselected" event is fired.
     */
    void clearSelection();

    /**
     * Clear the selection rectangle. Pass in true to avoid getting a "plotunselected" event.
     *
     * @param preventEvent true if you want to prevent the "plotunselected" event
     */
    void clearSelection( boolean preventEvent );

    /**
     * Add a hover listener to the plot
     *
     * @param listener listener to add to the plot
     * @param onlyOnDatapoint true if the event should fire only when hovering a datapoint
     */
    void addHoverListener( PlotHoverListener listener, boolean onlyOnDatapoint );

    /**
     * Add a click listener to the plot
     *
     * @param listener listener to add to the plot
     * @param onlyOnDatapoint true if the event should fire only when hovering a datapoint
     */
    void addClickListener( PlotClickListener listener, boolean onlyOnDatapoint );

    /**
     * @return the model
     */
    PlotModel getModel();

    /**
     * Redraw the plot
     */
    void redraw();

    /**
     * @return the internal flot plot
     */
    Plot getPlot();
}
