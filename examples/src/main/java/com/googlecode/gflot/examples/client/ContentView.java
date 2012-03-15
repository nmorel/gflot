/*
 * Copyright 2010 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.googlecode.gflot.examples.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * A view of a {@link ContentActivity}.
 */
public class ContentView
    extends ResizeComposite
{

    interface ContentWidgetViewUiBinder
        extends UiBinder<Widget, ContentView>
    {
    }

    private static ContentWidgetViewUiBinder uiBinder = GWT.create( ContentWidgetViewUiBinder.class );

    @UiField( provided = true )
    SimplePanel examplePanel;

    private final boolean hasMargins;

    private ContentActivity presenter;

    public ContentView( boolean hasMargins, boolean scrollable )
    {
        this.hasMargins = hasMargins;
        examplePanel = scrollable ? new ScrollPanel() : new SimpleLayoutPanel();
        examplePanel.setSize( "100%", "100%" );
        initWidget( uiBinder.createAndBindUi( this ) );
    }

    public void setExample( Widget widget )
    {
        examplePanel.setWidget( widget );
        if ( hasMargins )
        {
            widget.getElement().getStyle().setMarginLeft( 10.0, Unit.PX );
            widget.getElement().getStyle().setMarginRight( 10.0, Unit.PX );
        }
    }

    @UiHandler( "tabExample" )
    void onClickExample( ClickEvent e )
    {
        presenter.showExample();
    }

    @UiHandler( "tabSource" )
    void onClickSource( ClickEvent e )
    {
        presenter.showSourceFile();
    }

    @UiField
    ListBox tabSourceList;

    @UiField
    Anchor tabSource;


    @UiHandler( "tabSourceList" )
    void onChangeSourceList( ChangeEvent e )
    {
        presenter.showSourceFile();
    }

    public void setPresenter( ContentActivity presenter )
    {
        this.presenter = presenter;
    }

    public ListBox getTabSourceList()
    {
        return tabSourceList;
    }

    public Anchor getTabSource()
    {
        return tabSource;
    }
}
