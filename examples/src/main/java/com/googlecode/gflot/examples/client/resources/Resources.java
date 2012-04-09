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

        String headerTitle();

        String headerDescription();

        String headerHomePageLink();

        String menuScrollContainer();

        String menuContainer();

        String menuCategory();

        String menuLink();

        String menuLinkSelected();

        String sourceContainer();

        String sourceLink();

        String sourceLinkSelected();

        String mainScrollContainer();

        String mainContainer();
    }
}
