package ca.nanometrics.gflot.client.resources;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

public interface FlotJavaScriptBundle
    extends ClientBundle
{
    @Source( "jquery.min.js" )
    TextResource jquery();

    @Source( "jquery.flot.min.js" )
    TextResource flot();

    @Source( "jquery.flot.selection.min.js" )
    TextResource flotSelection();

    @Source( "jquery.flot.symbol.min.js" )
    TextResource flotSymbol();

    @Source( "jquery.flot.image.min.modified.js" )
    TextResource flotImage();

    @Source( "jquery.flot.pie.min.js" )
    TextResource flotPie();

    @Source( "jquery.flot.stack.min.js" )
    TextResource flotStack();

    @Source( "excanvas.min.js" )
    TextResource excanvas();
}
