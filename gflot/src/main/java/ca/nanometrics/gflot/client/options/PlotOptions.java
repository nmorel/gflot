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

import ca.nanometrics.gflot.client.jsni.JsonObject;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;

/**
 * @author AlexanderDeleon
 */
public class PlotOptions
    extends JsonObject
{
    /**
     * Creates a {@link PlotOptions}
     */
    public static final PlotOptions create()
    {
        return JavaScriptObject.createObject().cast();
    }

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
    private static final String MULTIPLE_BARS_KEY = "multiplebars";

    protected PlotOptions()
    {
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
    public final PlotOptions setDefaultColorTheme( JsArrayString colors )
    {
        assert null != colors && colors.length() > 0 : "colors can't be null or empty";

        put( COLORS_KEY, colors );
        return this;
    }

    /**
     * @return the default color theme to get colors for the data series from
     */
    public final JsArrayString getDefaultColorTheme()
    {
        return getStringArray( COLORS_KEY );
    }

    /**
     * Clear the default color theme
     */
    public final PlotOptions clearDefaultColorTheme()
    {
        clear( COLORS_KEY );
        return this;
    }

    /**
     * Set the legend options
     */
    public final PlotOptions setLegendOptions( LegendOptions legendOptions )
    {
        put( LEGEND_KEY, legendOptions );
        return this;
    }

    /**
     * @return the legend options
     */
    public final LegendOptions getLegendOptions()
    {
        return getJsObject( LEGEND_KEY );
    }

    /**
     * Add options for a x axis
     */
    public final PlotOptions addXAxisOptions( AbstractAxisOptions<?> xAxisOptions )
    {
        AxesOptions xAxesOptions = getXAxesOptions();
        if ( null == xAxesOptions )
        {
            xAxesOptions = AxesOptions.create();
            setXAxesOptions( xAxesOptions );
        }
        xAxesOptions.addAxisOptions( xAxisOptions );
        return this;
    }

    /**
     * Return first x axis options
     */
    public final AbstractAxisOptions<?> getXAxisOptions()
    {
        return getXAxisOptions( 1 );
    }

    /**
     * Return x axis options at given index, starting at 1
     */
    public final AbstractAxisOptions<?> getXAxisOptions( int xAxisNumber )
    {
        assert xAxisNumber > 0 : "xAxisNumber starts at 1";
        AxesOptions xAxesOptions = getXAxesOptions();
        if ( null == xAxesOptions )
        {
            return null;
        }
        return xAxesOptions.getAxisOptions( xAxisNumber );
    }

    /**
     * Set the options for x axes
     */
    public final PlotOptions setXAxesOptions( AxesOptions xAxesOptions )
    {
        put( X_AXES_KEY, xAxesOptions );
        return this;
    }

    /**
     * @return the options for x axes
     */
    public final AxesOptions getXAxesOptions()
    {
        return getJsObject( X_AXES_KEY );
    }

    /**
     * Add y axis options
     */
    public final PlotOptions addYAxisOptions( AbstractAxisOptions<?> yAxisOptions )
    {
        AxesOptions yAxesOptions = getYAxesOptions();
        if ( null == yAxesOptions )
        {
            yAxesOptions = AxesOptions.create();
            setYAxesOptions( yAxesOptions );
        }
        yAxesOptions.addAxisOptions( yAxisOptions );
        return this;
    }

    /**
     * Return first y axis options
     */
    public final AbstractAxisOptions<?> getYAxisOptions()
    {
        return getYAxisOptions( 1 );
    }

    /**
     * Return y axis options at given index, starting at 1
     */
    public final AbstractAxisOptions<?> getYAxisOptions( int yAxisNumber )
    {
        assert yAxisNumber > 0 : "yAxisNumber starts at 1";
        AxesOptions yAxesOptions = getYAxesOptions();
        if ( null == yAxesOptions )
        {
            return null;
        }
        return yAxesOptions.getAxisOptions( yAxisNumber );
    }

    /**
     * Set the options for y axes
     */
    public final PlotOptions setYAxesOptions( AxesOptions yAxesOptions )
    {
        put( Y_AXES_KEY, yAxesOptions );
        return this;
    }

    /**
     * @return the options for y axes
     */
    public final AxesOptions getYAxesOptions()
    {
        return getJsObject( Y_AXES_KEY );
    }

    /**
     * Set the selection options
     */
    public final PlotOptions setSelectionOptions( SelectionOptions selectionOptions )
    {
        put( SELECTION_KEY, selectionOptions );
        return this;
    }

    /**
     * @return the selection options
     */
    public final SelectionOptions getSelectionOptions()
    {
        return getJsObject( SELECTION_KEY );
    }

    /**
     * Set the grid options
     */
    public final PlotOptions setGridOptions( GridOptions gridOptions )
    {
        put( GRID_KEY, gridOptions );
        return this;
    }

    /**
     * @return the grid options
     */
    public final GridOptions getGridOptions()
    {
        return getJsObject( GRID_KEY );
    }

    /**
     * Set the global series options
     */
    public final PlotOptions setGlobalSeriesOptions( GlobalSeriesOptions globalSeriesOptions )
    {
        put( SERIES_KEY, globalSeriesOptions );
        return this;
    }

    /**
     * @return the global series options
     */
    public final GlobalSeriesOptions getGlobalSeriesOptions()
    {
        return getJsObject( SERIES_KEY );
    }

    /**
     * Set the zoom options
     */
    public final PlotOptions setZoomOptions( ZoomOptions zoom )
    {
        put( ZOOM_KEY, zoom );
        return this;
    }

    /**
     * @return the zoom options
     */
    public final ZoomOptions getZoomOptions()
    {
        return getJsObject( ZOOM_KEY );
    }

    /**
     * Set the pan options
     */
    public final PlotOptions setPanOptions( PanOptions pan )
    {
        put( PAN_KEY, pan );
        return this;
    }

    /**
     * @return the pan options
     */
    public final PanOptions getPanOptions()
    {
        return getJsObject( PAN_KEY );
    }

    /**
     * Set the crosshair options
     */
    public final PlotOptions setCrosshairOptions( CrosshairOptions crosshair )
    {
        put( CROSSHAIR_KEY, crosshair );
        return this;
    }

    /**
     * @return the crosshair options
     */
    public final CrosshairOptions getCrosshairOptions()
    {
        return getJsObject( CROSSHAIR_KEY );
    }

    /**
     * Enable the multiple bars plugin
     */
    public final PlotOptions setMultipleBars( boolean multiplebars )
    {
        put( MULTIPLE_BARS_KEY, multiplebars );
        return this;
    }

    /**
     * @return true if the multiple bars plugin is enabled
     */
    public final Boolean getMultipleBars()
    {
        return getBoolean( MULTIPLE_BARS_KEY );
    }

}
