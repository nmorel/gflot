package com.googlecode.gflot.examples.client.resources;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

public interface Resources
    extends ClientBundle
{
    @Source( "gflot.css" )
    Style style();

    public interface Style
        extends CssResource
    {
        String headerContainer();

        String menuScrollContainer();

        String menuContainer();

        String menuLink();

        String menuLinkSelected();

        String sourceLink();

        String sourceLinkSelected();

        String mainScrollContainer();
    }
}
