package ca.nanometrics.gflot.client.resources;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

public interface FlotJavaScriptBundle
    extends ClientBundle
{
    @Source( "jquery-1.7.1.min.js" )
    TextResource jquery();

    @Source( "jquery.flot-0.7.min.js" )
    TextResource flot();

    @Source( "jquery.flot-0.7.selection.min.js" )
    TextResource flotSelection();

    @Source( "jquery.flot-0.7.symbol.min.js" )
    TextResource flotSymbol();

    @Source( "jquery.flot-0.7.image-modified.min.js" )
    TextResource flotImage();

    @Source( "jquery.flot-0.7.pie-modified.min.js" )
    TextResource flotPie();

    @Source( "jquery.flot-0.7.stack.min.js" )
    TextResource flotStack();

    @Source( "excanvas.min.js" )
    TextResource excanvas();
}
