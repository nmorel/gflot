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
package com.googlecode.gflot.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

/**
 * Represents a plot load event. It is fired after the flot library is loaded and the plot is created. 
 */
public class PlotLoadEvent extends
    GwtEvent<PlotLoadEvent.Handler> {

  /**
   * Handler interface for {@link PlotLoadEvent} events.
   */
  public static interface Handler extends EventHandler {

    /**
     * Called when a {@link PlotLoadEvent} is fired.
     *
     * @param event the {@link PlotLoadEvent} that was fired
     */
    void onLoad(PlotLoadEvent event);
  }

  /**
   * Interface specifying that a class can add
   * {@code PlotLoadEvent.Handler}s.
   */
  public interface HasPlotLoadHandlers extends HasHandlers {
    /**
     * Adds a {@link PlotLoadEvent} handler.
     * 
     * @param handler the handler
     * @return {@link HandlerRegistration} used to remove this handler
     */
    HandlerRegistration addLoadHandler(Handler handler);
  }

  /**
   * Handler type.
   */
  private static Type<PlotLoadEvent.Handler> TYPE;

  /**
   * Fires a load event on all registered handlers in the handler
   * manager. If no such handlers exist, this method will do nothing.
   *
   * @param source the source of the handlers
   */
  public static void fire(HasPlotLoadHandlers source) {
    if (TYPE != null) {
      PlotLoadEvent event = new PlotLoadEvent();
      source.fireEvent(event);
    }
  }

  /**
   * Gets the type associated with this event.
   *
   * @return returns the handler type
   */
  public static Type<PlotLoadEvent.Handler> getType() {
    if (TYPE == null) {
      TYPE = new Type<PlotLoadEvent.Handler>();
    }
    return TYPE;
  }

  /**
   * Creates a plot load event.
   */
  PlotLoadEvent() {
  }

  @Override
  public final Type<PlotLoadEvent.Handler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(PlotLoadEvent.Handler handler) {
    handler.onLoad(this);
  }
}
