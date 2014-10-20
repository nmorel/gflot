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

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gflot.client.event.LoadImagesCallback;
import com.googlecode.gflot.client.event.PlotClickListener;
import com.googlecode.gflot.client.event.PlotHoverListener;
import com.googlecode.gflot.client.event.PlotLoadEvent;
import com.googlecode.gflot.client.event.PlotLoadEvent.Handler;
import com.googlecode.gflot.client.event.PlotPanListener;
import com.googlecode.gflot.client.event.PlotPosition;
import com.googlecode.gflot.client.event.PlotSelectedListener;
import com.googlecode.gflot.client.event.PlotSelectingListener;
import com.googlecode.gflot.client.event.PlotUnselectedListener;
import com.googlecode.gflot.client.event.PlotZoomListener;
import com.googlecode.gflot.client.jsni.Plot;
import com.googlecode.gflot.client.options.PlotOptions;
import com.googlecode.gflot.client.options.Range;
import com.googlecode.gflot.client.resources.FlotJavaScriptLoader;
import com.googlecode.gflot.client.resources.FlotJavaScriptLoader.FlotJavaScriptCallback;

/**
 * @author AlexanderDeleon
 */
public class SimplePlot
extends Widget
implements PlotWidget
{

	public static final int DEFAULT_WIDTH = 600;

	public static final int DEFAULT_HEIGHT = 300;

	private final PlotModel model;

	private int width;

	private int height;

	private Plot plot;

	private boolean loaded;

	private boolean loadDataImages;

	private PlotOptions options;

	private final List<Command> onLoadOperations;

	public SimplePlot()
	{
		this( new PlotModel() );
	}

	public SimplePlot( final PlotModel model )
	{
		this( model, null );
	}

	public SimplePlot( final PlotOptions options )
	{
		this( new PlotModel(), options );
	}

	public SimplePlot( final PlotModel model, final PlotOptions options )
	{
		this( PlotFactory.createUniquePlotContainer(), model, options );
	}

	public SimplePlot( final Element plotContainer, final PlotModel model, final PlotOptions options )
	{
		this.model = model;
		onLoadOperations = new ArrayList<Command>();
		setElement( plotContainer );
		setWidth( DEFAULT_WIDTH );
		setHeight( DEFAULT_HEIGHT );
		this.options = options;
	}

	@Override
	public int getWidth()
	{
		return width;
	}

	@Override
	public void setWidth( final int width )
	{
		this.width = width;
		DOM.setStyleAttribute( getElement(), "width", width + "px" );
	}

	@Override
	public int getHeight()
	{
		return height;
	}

	@Override
	public void setHeight( final int height )
	{
		this.height = height;
		DOM.setStyleAttribute( getElement(), "height", height + "px" );
	}

	public void setupGrid()
	{
		assertLoaded();
		plot.setupGrid();
	}

	public void draw()
	{
		assertLoaded();
		plot.draw();
	}

	@Override
	public void setLinearSelection( final double x1, final double x2 )
	{
		setSelection( PlotSelectionArea.create().setX( Range.of( x1, x2 ) ) );
	}

	@Override
	public void setRectangularSelection( final double x1, final double y1, final double x2, final double y2 )
	{
		setSelection( PlotSelectionArea.create().setX( Range.of( x1, x2 ) ).setY( Range.of( y1, y2 ) ) );
	}

	@Override
	public void addSelectedListener( final PlotSelectedListener listener )
	{
		if ( loaded )
		{
			plot.addPlotSelectedListener( getElement(), listener );
		}
		else
		{
			onLoadOperations.add( new Command()
			{
				@Override
				public void execute()
				{
					plot.addPlotSelectedListener( getElement(), listener );
				}
			} );
		}
	}

	@Override
	public void addSelectingListener( final PlotSelectingListener listener )
	{
		if ( loaded )
		{
			plot.addPlotSelectingListener( getElement(), listener );
		}
		else
		{
			onLoadOperations.add( new Command()
			{
				@Override
				public void execute()
				{
					plot.addPlotSelectingListener( getElement(), listener );
				}
			} );
		}
	}

	@Override
	public void addUnselectedListener( final PlotUnselectedListener listener )
	{
		if ( loaded )
		{
			plot.addPlotUnselectedListener( getElement(), listener );
		}
		else
		{
			onLoadOperations.add( new Command()
			{
				@Override
				public void execute()
				{
					plot.addPlotUnselectedListener( getElement(), listener );
				}
			} );
		}
	}

	@Override
	public PlotSelectionArea getSelection()
	{
		if ( loaded )
		{
			return plot.getSelection();
		}
		return null;
	}

	@Override
	public void setSelection( final PlotSelectionArea area )
	{
		setSelection( area, false );
	}

	@Override
	public void setSelection( final PlotSelectionArea area, final boolean preventEvent )
	{
		if ( loaded )
		{
			plot.setSelection( area, preventEvent );
		}
		else
		{
			onLoadOperations.add( new Command()
			{
				@Override
				public void execute()
				{
					plot.setSelection( area, preventEvent );
				}
			} );
		}
	}

	@Override
	public void clearSelection()
	{
		clearSelection( false );
	}

	@Override
	public void clearSelection( final boolean preventEvent )
	{
		assertLoaded();
		plot.clearSelection( preventEvent );
	}

	@Override
	public void addHoverListener( final PlotHoverListener listener, final boolean onlyOnDatapoint )
	{
		if ( loaded )
		{
			plot.addPlotHoverListener( getElement(), listener, onlyOnDatapoint );
		}
		else
		{
			onLoadOperations.add( new Command()
			{
				@Override
				public void execute()
				{
					plot.addPlotHoverListener( getElement(), listener, onlyOnDatapoint );
				}
			} );
		}

	}

	@Override
	public void addClickListener( final PlotClickListener listener, final boolean onlyOnDatapoint )
	{
		if ( loaded )
		{
			plot.addPlotClickListener( getElement(), listener, onlyOnDatapoint );
		}
		else
		{
			onLoadOperations.add( new Command()
			{
				@Override
				public void execute()
				{
					plot.addPlotClickListener( getElement(), listener, onlyOnDatapoint );
				}
			} );
		}

	}

	@Override
	public PlotModel getModel()
	{
		return model;
	}

	@Override
	public void redraw()
	{
		if ( loaded )
		{
			loaded = false;
			if ( isAttached() )
			{
				createPlot();
			}
		}
	}

	public int getOffsetLeft()
	{
		return plot.getPlotOffsetLeft();
	}

	public int getOffsetRight()
	{
		return plot.getPlotOffsetRight();
	}

	public int getOffsetTop()
	{
		return plot.getPlotOffsetTop();
	}

	public int getOffsetBottom()
	{
		return plot.getPlotOffsetBottom();
	}

	/**
	 * @return the options provided by user
	 */
	 public PlotOptions getOptions()
	{
		return options;
	}

	/**
	 * @deprecated since 3.2.0 this method always returns the options created by user and not the internal plot options used by flot. Use
	 * {@link SimplePlot#getOptions()} to get user's option and {@link SimplePlot#getPlot()}.getOptions() to get internal flot options.
	 */
	 @Deprecated
	 public PlotOptions getPlotOptions()
	 {
		 return getOptions();
	 }

	 public void setLoadDataImages( final boolean loadDataImages )
	 {
		 this.loadDataImages = loadDataImages;
	 }

	 public void addPanListener( final PlotPanListener listener )
	 {
		 if ( loaded )
		 {
			 plot.addPlotPanListener( getElement(), listener );
		 }
		 else
		 {
			 onLoadOperations.add( new Command()
			 {
				 @Override
				 public void execute()
				 {
					 plot.addPlotPanListener( getElement(), listener );
				 }
			 } );
		 }
	 }

	 public void addZoomListener( final PlotZoomListener listener )
	 {
		 if ( loaded )
		 {
			 plot.addPlotZoomListener( getElement(), listener );
		 }
		 else
		 {
			 onLoadOperations.add( new Command()
			 {
				 @Override
				 public void execute()
				 {
					 plot.addPlotZoomListener( getElement(), listener );
				 }
			 } );
		 }
	 }

	 public void zoom()
	 {
		 zoom( Zoom.create() );
	 }

	 public void zoom( final Zoom zoom )
	 {
		 assertLoaded();
		 plot.zoom( zoom );
	 }

	 public void zoomOut()
	 {
		 zoomOut( Zoom.create() );
	 }

	 public void zoomOut( final Zoom zoom )
	 {
		 assertLoaded();
		 plot.zoomOut( zoom );
	 }

	 public void pan()
	 {
		 pan( Pan.create() );
	 }

	 public void pan( final Pan pan )
	 {
		 assertLoaded();
		 plot.pan( pan );
	 }

	 /* ------------------ Widget API -- */
	 @Override
	 protected void onLoad()
	 {
		 super.onLoad();
		 if ( !loaded )
		 {
			 createPlot();
		 }
	 }

	 private void createPlot()
	 {
		 FlotJavaScriptLoader.get().loadRequiredFlotLibrary( new FlotJavaScriptCallback()
		 {
			 @Override
			 public void onSuccess()
			 {
				 if ( loadDataImages )
				 {
					 Plot.loadDataImages( model.getSeries(), options, new LoadImagesCallback()
					 {
						 @Override
						 public void onImagesLoaded( final JsArray<Series> data, final PlotOptions options )
						 {
							 plot = Plot.create( getElement(), data, options );
							 onPlotCreated();
						 }
					 } );
				 }
				 else
				 {
					 plot = Plot.create( getElement(), model.getSeries(), options );
					 onPlotCreated();
				 }
			 }

			 @Override
			 public void onError( final Throwable caught )
			 {
				 throw new RuntimeException( "Error while loading flot library", caught );
			 }
		 } );
	 }

	 private void onPlotCreated()
	 {
		 // Issue : 2
		 assert plot != null : "A javascript error occurred while creating plot.";

	 loaded = true;

	 for ( final Command cmd : onLoadOperations )
	 {
		 cmd.execute();
	 }
	 onLoadOperations.clear();

	 PlotLoadEvent.fire( this );
	 }

	 public boolean isExportAsImageEnabled()
	 {
		 return FlotJavaScriptLoader.get().getCanvas2ImageLoader().isPluginEnabled();
	 }

	 /**
	  * Prompt the user to save the plot as an image
	  */
	 public void saveAsImage()
	 {
		 if ( isExportAsImageEnabled() )
		 {
			 plot.saveAsImage();
		 }
	 }

	 /**
	  * Prompt the user to save the plot as an image. The image is scaled at the given dimensions.
	  *
	  * @param width
	  * @param height
	  */
	 public void saveAsImage( final int width, final int height )
	 {
		 if ( isExportAsImageEnabled() )
		 {
			 plot.saveAsImage( width, height );
		 }
	 }

	 /**
	  * @return an image of the plot
	  */
	 public Image getImage()
	 {
		 if ( isExportAsImageEnabled() )
		 {
			 return plot.getImage();
		 }
		 return null;
	 }

	 /**
	  * @param width
	  * @param height
	  * @return an image of the plot scaled at the given dimensions
	  */
	 public Image getImage( final int width, final int height )
	 {
		 if ( isExportAsImageEnabled() )
		 {
			 return plot.getImage( width, height );
		 }
		 return null;
	 }

	 public Axes getAxes()
	 {
		 assertLoaded();
		 return plot.getAxes();
	 }

	 /**
	  * Set the position of the crosshair. Note that this is cleared if the user moves the mouse.
	  *
	  * @param pos Position of the crosshair
	  */
	 public void setCrosshair( final PlotPosition pos )
	 {
		 plot.setCrosshair( pos );
	 }

	 /**
	  * Clear the crosshair.
	  */
	 public void clearCrosshair()
	 {
		 plot.clearCrosshair();
	 }

	 /**
	  * Cause the crosshair to lock to the current location, no longer updating if the user moves the mouse.
	  */
	 public void lockCrosshair()
	 {
		 plot.lockCrosshair();
	 }

	 /**
	  * Cause the crosshair to lock to the current location, no longer updating if the user moves the mouse.
	  *
	  * @param pos position to lock the crosshair to
	  */
	 public void lockCrosshair( final PlotPosition pos )
	 {
		 plot.lockCrosshair( pos );
	 }

	 /**
	  * Free the crosshair to move again after locking it.
	  */
	 public void unlockCrosshair()
	 {
		 plot.unlockCrosshair();
	 }

	 /**
	  * @return true if the plot has been created
	  */
	 public boolean isPlotLoaded()
	 {
		 return loaded;
	 }

	 protected void assertLoaded()
	 {
		 if ( !loaded )
		 {
			 throw new IllegalStateException(
					 "The widget has not been loaded yet. Please call this method after adding this widget to a panel" );
		 }
	 }

	 /**
	  * @return the internal flot plot
	  */
	 @Override
	 public Plot getPlot()
	 {
		 return plot;
	 }

	 @Override
	 public HandlerRegistration addLoadHandler( final Handler handler )
	 {
		 return addHandler( handler, PlotLoadEvent.getType() );
	 }

	 /**
	  * Highlight a specific datapoint in the data series.
	  * @param series
	  * @param datapoint
	  */
	 public void highlight(final Series series, final DataPoint datapoint)
	 {
		 plot.highlight(series, datapoint);
	 }

	 /**
	  * Highlight a specific datapoint in the data series.
	  * @param seriesIndex index of the series (starting at 0)
	  * @param datapointIndex index of the datapoint in the series (starting at 0)
	  */
	 public void highlight(final int seriesIndex, final int datapointIndex)
	 {
		 plot.highlight(seriesIndex, datapointIndex);
	 }

	 /**
	  * Unhighlight a specific datapoint in the data series.
	  * @param series
	  * @param datapoint
	  */
	 public void unhighlight(final Series series, final DataPoint datapoint)
	 {
		 plot.unhighlight(series, datapoint);
	 }

	 /**
	  * Unhighlight a specific datapoint in the data series.
	  * @param seriesIndex index of the series (starting at 0)
	  * @param datapointIndex index of the datapoint in the series (starting at 0)
	  */
	 public void unhighlight(final int seriesIndex, final int datapointIndex)
	 {
		 plot.unhighlight(seriesIndex, datapointIndex);
	 }

	 /**
	  * Removes all current highlights
	  */
	 public void unhighlight()
	 {
		 plot.unhighlight();
	 }

}
