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

/**
 * @author AlexanderDeleon
 */
public class PlotOptions
    extends JSONObjectWrapper
{

    private DefaultSeriesOptions defaultSeriesOptions;

    /**
     * Set the legend options
     */
    public PlotOptions setLegendOptions( LegendOptions legendOptions )
    {
        put( "legend", legendOptions );
        return this;
    }

    public PlotOptions setXAxisOptions( AbstractAxisOptions<?> xAxisOptions )
    {
        put( "xaxis", xAxisOptions );
        return this;
    }

    public PlotOptions setYAxisOptions( AbstractAxisOptions<?> yAxisOptions )
    {
        put( "yaxis", yAxisOptions );
        return this;
    }

    /**
     * Set default Line series options that will be used unless options are set directly to the series
     */
    public PlotOptions setDefaultLineSeriesOptions( AbstractSeriesOptions<?> lineSeriesOptions )
    {
        getDefaultSeriesOptions().setDefaultLineSeriesOptions( lineSeriesOptions );
        return this;
    }
    /**
     * Set default Bar series options that will be used unless options are set directly to the series
     */
    public PlotOptions setDefaultBarsSeriesOptions( AbstractSeriesOptions<?> barSeriesOptions )
    {
        getDefaultSeriesOptions().setDefaultBarsSeriesOptions( barSeriesOptions );
        return this;
    }
    /**
     * Set default Points series options that will be used unless options are set directly to the series
     */
    public PlotOptions setDefaultPointsOptions( AbstractSeriesOptions<?> pointsSeriesOptions )
    {
        getDefaultSeriesOptions().setDefaultPointsOptions( pointsSeriesOptions );
        return this;
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
    public PlotOptions setDefaultColors( String[] colors )
    {
        assert null != colors && colors.length > 0 : "colors can't be null or empty";

        put( "colors", JSONHelper.wrapArray( colors ) );
        return this;
    }

    /**
     * Set the default size of shadows in pixels. Set it to 0 to remove shadows.
     */
    public PlotOptions setDefaultShadowSize( double shadow )
    {
        getDefaultSeriesOptions().setDefaultShadowSize( shadow );
        return this;
    }

    public PlotOptions setSelectionOptions( SelectionOptions selectionOptions )
    {
        put( "selection", selectionOptions );
        return this;
    }

    public PlotOptions setGridOptions( GridOptions gridOptions )
    {
        put( "grid", gridOptions );
        return this;
    }

    private DefaultSeriesOptions getDefaultSeriesOptions()
    {
        if ( defaultSeriesOptions == null )
        {
            defaultSeriesOptions = new DefaultSeriesOptions();
            put( "series", defaultSeriesOptions );
        }
        return defaultSeriesOptions;
    }

    private static class DefaultSeriesOptions
        extends JSONObjectWrapper
    {
        public void setDefaultLineSeriesOptions( AbstractSeriesOptions<?> lineSeriesOptions )
        {
            put( "lines", lineSeriesOptions );
        }

        public void setDefaultBarsSeriesOptions( AbstractSeriesOptions<?> barSeriesOptions )
        {
            put( "bars", barSeriesOptions );
        }

        public void setDefaultPointsOptions( AbstractSeriesOptions<?> pointsSeriesOptions )
        {
            put( "points", pointsSeriesOptions );
        }

        public void setDefaultShadowSize( double shadow )
        {
            put( "shadowSize", new Double( shadow ) );
        }
    }
}
