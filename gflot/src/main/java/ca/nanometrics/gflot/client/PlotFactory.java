package ca.nanometrics.gflot.client;

import ca.nanometrics.gflot.client.options.PlotOptions;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;

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
