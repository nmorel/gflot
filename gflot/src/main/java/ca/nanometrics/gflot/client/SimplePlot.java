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
import java.util.List;

import ca.nanometrics.gflot.client.event.LoadImagesCallback;
import ca.nanometrics.gflot.client.event.PlotClickListener;
import ca.nanometrics.gflot.client.event.PlotHoverListener;
import ca.nanometrics.gflot.client.event.PlotPanListener;
import ca.nanometrics.gflot.client.event.PlotPosition;
import ca.nanometrics.gflot.client.event.PlotSelectedListener;
import ca.nanometrics.gflot.client.event.PlotSelectingListener;
import ca.nanometrics.gflot.client.event.PlotUnselectedListener;
import ca.nanometrics.gflot.client.event.PlotZoomListener;
import ca.nanometrics.gflot.client.jsni.Plot;
import ca.nanometrics.gflot.client.options.PlotOptions;
import ca.nanometrics.gflot.client.options.Range;
import ca.nanometrics.gflot.client.resources.FlotJavaScriptLoader;
import ca.nanometrics.gflot.client.resources.FlotJavaScriptLoader.FlotJavaScriptCallback;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author AlexanderDeleon
 */
public class SimplePlot
    extends Widget
    implements PlotWidget
{

    private static final int DEFAULT_WIDTH = 600;

    private static final int DEFAULT_HEIGHT = 300;

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

    public SimplePlot( PlotModel model )
    {
        this( model, null );
    }

    public SimplePlot( PlotOptions options )
    {
        this( new PlotModel(), options );
    }

    public SimplePlot( PlotModel model, PlotOptions options )
    {
        this( PlotFactory.createUniquePlotContainer(), model, options );
    }

    public SimplePlot( Element plotContainer, PlotModel model, PlotOptions options )
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
    public void setWidth( int width )
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
    public void setHeight( int height )
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
    public void setLinearSelection( double x1, double x2 )
    {
        setSelection( new PlotSelectionArea().setX( new Range( x1, x2 ) ) );
    }

    @Override
    public void setRectangularSelection( double x1, double y1, double x2, double y2 )
    {
        setSelection( new PlotSelectionArea().setX( new Range( x1, x2 ) ).setY( new Range( y1, y2 ) ) );
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
            onLoadOperations.add( new Command() {
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
            onLoadOperations.add( new Command() {
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
            onLoadOperations.add( new Command() {
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
            return plot.getSelection( getElement() );
        }
        return null;
    }

    @Override
    public void setSelection( PlotSelectionArea area )
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
            onLoadOperations.add( new Command() {
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
    public void clearSelection( boolean preventEvent )
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
            onLoadOperations.add( new Command() {
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
            onLoadOperations.add( new Command() {
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
    public Widget getWidget()
    {
        return this;
    }

    @Override
    public void redraw()
    {
        assertLoaded();
        plot.setData( model.getSeries() );
        plot.setupGrid();
        plot.draw();
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

    public PlotOptions getPlotOptions()
    {
        return options;
    }

    public void setLoadDataImages( boolean loadDataImages )
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
            onLoadOperations.add( new Command() {
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
            onLoadOperations.add( new Command() {
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
        zoom( new Zoom() );
    }

    public void zoom( Zoom zoom )
    {
        assertLoaded();
        plot.zoom( zoom );
    }

    public void zoomOut()
    {
        zoomOut( new Zoom() );
    }

    public void zoomOut( Zoom zoom )
    {
        assertLoaded();
        plot.zoomOut( zoom );
    }

    public void pan()
    {
        pan( new Pan() );
    }

    public void pan( Pan pan )
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
            FlotJavaScriptLoader.get().loadRequiredFlotLibrary( new FlotJavaScriptCallback() {
                @Override
                public void onSuccess()
                {
                    if ( loadDataImages )
                    {
                        Plot.loadDataImages( model.getSeries(), options, new LoadImagesCallback() {
                            @Override
                            public void onImagesLoaded( JavaScriptObject data, JavaScriptObject options )
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
                public void onError( Throwable caught )
                {
                    throw new RuntimeException( "Error while loading flot library", caught );
                }
            } );
        }
    }

    private void onPlotCreated()
    {
        // Issue : 2
        assert plot != null : "A javascript error occurred while creating plot.";

        loaded = true;

        // retrieving the calculated options
        options = plot.getPlotOptions();

        for ( Command cmd : onLoadOperations )
        {
            cmd.execute();
        }
        onLoadOperations.clear();
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
    public void saveAsImage( int width, int height )
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
    public Image getImage( int width, int height )
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
    public void setCrosshair( PlotPosition pos )
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
    public void lockCrosshair( PlotPosition pos )
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

}
