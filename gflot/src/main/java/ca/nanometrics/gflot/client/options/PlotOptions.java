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

import ca.nanometrics.gflot.client.util.JSONHelper;
import ca.nanometrics.gflot.client.util.JSONObjectWrapper;

import com.google.gwt.json.client.JSONObject;

/**
 * @author AlexanderDeleon
 */
public class PlotOptions
    extends JSONObjectWrapper
{
    private static final String COLORS_KEY = "colors";

    private static final String LEGEND_KEY = "legend";

    private static final String X_AXES_KEY = "xaxes";

    private static final String Y_AXES_KEY = "yaxes";

    private static final String SERIES_KEY = "series";

    private static final String GRID_KEY = "grid";

    private static final String SELECTION_KEY = "selection";

    private static final String ZOOM_KEY = "zoom";

    private static final String PAN_KEY = "pan";

    private static final String CROSSHAIR_KEY = "crosshair";

    private LegendOptions legendOptions;

    private AxesOptions xAxesOptions;

    private AxesOptions yAxesOptions;

    private SelectionOptions selectionOptions;

    private GridOptions gridOptions;

    private GlobalSeriesOptions globalSeriesOptions;

    private ZoomOptions zoom;

    private PanOptions pan;

    private CrosshairOptions crosshair;

    public PlotOptions()
    {
        super();
    }

    public PlotOptions( JSONObject jsonObj )
    {
        super( jsonObj );
        globalSeriesOptions = new GlobalSeriesOptions( getObject( SERIES_KEY ) );
        selectionOptions = new SelectionOptions( getObject( SELECTION_KEY ) );
        gridOptions = new GridOptions( getObject( GRID_KEY ) );
        legendOptions = new LegendOptions( getObject( LEGEND_KEY ) );

        zoom = new ZoomOptions( getObject( ZOOM_KEY ) );
        pan = new PanOptions( getObject( PAN_KEY ) );
        crosshair = new CrosshairOptions( getObject( CROSSHAIR_KEY ) );

        xAxesOptions = new AxesOptions( getArray( X_AXES_KEY ) );
        yAxesOptions = new AxesOptions( getArray( Y_AXES_KEY ) );
    }

    /**
     * Set a default color theme to get colors for the data series from. You can specify as many colors as you like,
     * like this:
     * <p>
     * colors: ["#d18b2c", "#dba255", "#919733"]
     * </p>
     * If there are more data series than colors, Flot will try to generate extra colors by lightening and darkening
     * colors in the theme.
     */
    public PlotOptions setDefaultColorTheme( String[] colors )
    {
        assert null != colors && colors.length > 0 : "colors can't be null or empty";

        put( COLORS_KEY, JSONHelper.wrapArray( colors ) );
        return this;
    }

    /**
     * @return the default color theme to get colors for the data series from
     */
    public String[] getDefaultColorTheme()
    {
        return getStringArray( COLORS_KEY );
    }

    /**
     * Set the legend options
     */
    public PlotOptions setLegendOptions( LegendOptions legendOptions )
    {
        this.legendOptions = legendOptions;
        put( LEGEND_KEY, legendOptions );
        return this;
    }

    /**
     * @return the legend options
     */
    public LegendOptions getLegendOptions()
    {
        return legendOptions;
    }

    /**
     * Add options for a x axis
     */
    public PlotOptions addXAxisOptions( AbstractAxisOptions<?> xAxisOptions )
    {
        if ( null == xAxesOptions )
        {
            setXAxesOptions( new AxesOptions() );
        }
        xAxesOptions.addAxisOptions( xAxisOptions );

        return this;
    }

    /**
     * Return first x axis options
     */
    public AbstractAxisOptions<?> getXAxisOptions()
    {
        return getXAxisOptions( 1 );
    }

    /**
     * Return x axis options at given index, starting at 1
     */
    public AbstractAxisOptions<?> getXAxisOptions( int xAxisNumber )
    {
        assert xAxisNumber > 0 : "xAxisNumber starts at 1";
        if ( null == xAxesOptions )
        {
            return null;
        }
        return xAxesOptions.getAxisOptions( xAxisNumber );
    }

    /**
     * Set the options for x axes
     */
    public PlotOptions setXAxesOptions( AxesOptions xAxesOptions )
    {
        this.xAxesOptions = xAxesOptions;

        put( X_AXES_KEY, xAxesOptions );
        return this;
    }

    /**
     * @return the options for x axes
     */
    public AxesOptions getXAxesOptions()
    {
        return xAxesOptions;
    }

    /**
     * Add y axis options
     */
    public PlotOptions addYAxisOptions( AbstractAxisOptions<?> yAxisOptions )
    {
        if ( null == yAxesOptions )
        {
            setYAxesOptions( new AxesOptions() );
        }
        yAxesOptions.addAxisOptions( yAxisOptions );

        return this;
    }

    /**
     * Return first y axis options
     */
    public AbstractAxisOptions<?> getYAxisOptions()
    {
        return getYAxisOptions( 1 );
    }

    /**
     * Return y axis options at given index, starting at 1
     */
    public AbstractAxisOptions<?> getYAxisOptions( int yAxisNumber )
    {
        assert yAxisNumber > 0 : "yAxisNumber starts at 1";
        if ( null == yAxesOptions )
        {
            return null;
        }
        return yAxesOptions.getAxisOptions( yAxisNumber );
    }

    /**
     * Set the options for y axes
     */
    public PlotOptions setYAxesOptions( AxesOptions yAxesOptions )
    {
        this.yAxesOptions = yAxesOptions;

        put( Y_AXES_KEY, yAxesOptions );
        return this;
    }

    /**
     * @return the options for y axes
     */
    public AxesOptions getYAxesOptions()
    {
        return yAxesOptions;
    }

    /**
     * Set the selection options
     */
    public PlotOptions setSelectionOptions( SelectionOptions selectionOptions )
    {
        this.selectionOptions = selectionOptions;
        put( SELECTION_KEY, selectionOptions );
        return this;
    }

    /**
     * @return the selection options
     */
    public SelectionOptions getSelectionOptions()
    {
        return selectionOptions;
    }

    /**
     * Set the grid options
     */
    public PlotOptions setGridOptions( GridOptions gridOptions )
    {
        this.gridOptions = gridOptions;
        put( GRID_KEY, gridOptions );
        return this;
    }

    /**
     * @return the grid options
     */
    public GridOptions getGridOptions()
    {
        return gridOptions;
    }

    /**
     * Set the global series options
     */
    public PlotOptions setGlobalSeriesOptions( GlobalSeriesOptions globalSeriesOptions )
    {
        this.globalSeriesOptions = globalSeriesOptions;
        put( SERIES_KEY, globalSeriesOptions );
        return this;
    }

    /**
     * @return the global series options
     */
    public GlobalSeriesOptions getGlobalSeriesOptions()
    {
        return globalSeriesOptions;
    }

    /**
     * Set the zoom options
     */
    public PlotOptions setZoomOptions( ZoomOptions zoom )
    {
        this.zoom = zoom;
        put( ZOOM_KEY, zoom );
        return this;
    }

    /**
     * @return the zoom options
     */
    public ZoomOptions getZoomOptions()
    {
        return zoom;
    }

    /**
     * Set the pan options
     */
    public PlotOptions setPanOptions( PanOptions pan )
    {
        this.pan = pan;
        put( PAN_KEY, pan );
        return this;
    }

    /**
     * @return the pan options
     */
    public PanOptions getPanOptions()
    {
        return pan;
    }

    /**
     * Set the crosshair options
     */
    public PlotOptions setCrosshairOptions( CrosshairOptions crosshair )
    {
        this.crosshair = crosshair;
        put( CROSSHAIR_KEY, crosshair );
        return this;
    }

    /**
     * @return the crosshair options
     */
    public CrosshairOptions getCrosshairOptions()
    {
        return crosshair;
    }

}
