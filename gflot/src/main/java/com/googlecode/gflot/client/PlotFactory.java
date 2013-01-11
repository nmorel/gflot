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


import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.googlecode.gflot.client.options.PlotOptions;

public final class PlotFactory
{
    public static SimplePlot createPlot()
    {
        return createPlot( createUniquePlotContainer(), new PlotModel(), null );
    }

    public static SimplePlot createPlot( PlotModel model )
    {
        return createPlot( createUniquePlotContainer(), model, null );
    }

    public static SimplePlot createPlot( PlotOptions options )
    {
        return createPlot( createUniquePlotContainer(), new PlotModel(), options );
    }

    public static SimplePlot createPlot( String idPlotContainer )
    {
        return createPlot( getPlotContainer( idPlotContainer ), new PlotModel(), null );
    }

    public static SimplePlot createPlot( Element plotContainer )
    {
        return createPlot( plotContainer, new PlotModel(), null );
    }

    public static SimplePlot createPlot( PlotModel model, PlotOptions options )
    {
        return createPlot( createUniquePlotContainer(), model, options );
    }

    public static SimplePlot createPlot( String idPlotContainer, PlotModel model )
    {
        return createPlot( getPlotContainer( idPlotContainer ), model, null );
    }

    public static SimplePlot createPlot( Element plotContainer, PlotModel model )
    {
        return createPlot( plotContainer, model, null );
    }

    public static SimplePlot createPlot( String idPlotContainer, PlotOptions options )
    {
        return createPlot( getPlotContainer( idPlotContainer ), new PlotModel(), options );
    }

    public static SimplePlot createPlot( Element plotContainer, PlotOptions options )
    {
        return createPlot( plotContainer, new PlotModel(), options );
    }

    public static SimplePlot createPlot( String idPlotContainer, PlotModel model, PlotOptions options )
    {
        return createPlot( getPlotContainer( idPlotContainer ), model, options );
    }

    public static SimplePlot createPlot( Element plotContainer, PlotModel model, PlotOptions options )
    {
        return new SimplePlot( plotContainer, model, options );
    }

    public static SimplePlot recreatePlot( SimplePlot plot, PlotModel model, PlotOptions options )
    {
        plot.removeFromParent();
        return new SimplePlot( plot.getElement(), model, options );
    }

    public static PlotWithOverview recreatePlotWithOverview( PlotWithOverview plot, PlotWithOverviewModel model, PlotOptions windowPlotOptions, PlotOptions overviewPlotOptions )
    {
        plot.removeFromParent();
        return new PlotWithOverview( model, windowPlotOptions, overviewPlotOptions, plot.getWindowPlot().getElement(), plot.getOverviewPlot().getElement() );
    }

    static Element createUniquePlotContainer()
    {
        Element element = DOM.createElement( "div" );
        DOM.setElementProperty( element, "id", Document.get().createUniqueId() );
        return element;
    }

    static Element getPlotContainer( String id )
    {
        com.google.gwt.dom.client.Element docElement = Document.get().getElementById( id );
        Element element = null;
        if ( null == docElement )
        {
            element = DOM.createDiv();
            element.setId( id );
        }
        else
        {
            element = docElement.cast();
        }
        return element;
    }
}
