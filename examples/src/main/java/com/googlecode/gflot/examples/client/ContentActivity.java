/*
 * Copyright 2008 Google Inc.
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.i18n.client.HasDirection.Direction;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gflot.examples.client.resources.Resources;

/**
 * <p>
 * A widget used to show GWT examples in the ContentPanel.
 * </p>
 * <p>
 * This {@link Widget} uses a lazy initialization mechanism so that the content is not rendered until the onInitialize
 * method is called, which happens the first time the {@link Widget} is added to the page. The data in the source and
 * css tabs are loaded using an RPC call to the server.
 * </p>
 */
public abstract class ContentActivity
    extends SimpleLayoutPanel
    implements HasValueChangeHandlers<String>, Activity
{
    /**
     * Generic callback used for asynchronously loaded data.
     *
     * @param <T> the data type
     */
    public static interface Callback<T>
    {
        void onError();

        void onSuccess( T value );
    }

    /**
     * Get the simple filename of a class.
     *
     * @param c the class
     */
    protected static String getSimpleName( Class<?> c )
    {
        String name = c.getName();
        return name.substring( name.lastIndexOf( "." ) + 1 );
    }

    private Resources resources;

    /**
     * A description of the example.
     */
    private final SafeHtml description;

    /**
     * The name of the example.
     */
    private final String name;

    /**
     * A mapping of filenames to their raw source code. The map is populated as source is loaded.
     */
    private final Map<String, String> rawSource = new HashMap<String, String>();

    /**
     * A list of filenames of the raw source code included with this example.
     */
    private final List<String> rawSourceFilenames = new ArrayList<String>();

    /**
     * The source code associated with this widget.
     */
    private String sourceCode;

    /**
     * The view that holds the name, description, and example.
     */
    private ContentView view;

    /**
     * Whether the demo widget has been initialized.
     */
    private boolean widgetInitialized;

    /**
     * Whether the demo widget is (asynchronously) initializing.
     */
    private boolean widgetInitializing;

    /**
     * Construct a {@link ContentActivity}.
     *
     * @param name the text name of the example
     * @param description a text description of the example
     * @param hasStyle true if the example has associated styles
     * @param rawSourceFiles the list of raw source files to include
     */
    public ContentActivity( String name, String description, Resources resources, String... rawSourceFiles )
    {
        this( name, SafeHtmlUtils.fromString( description ), resources, rawSourceFiles );
    }

    /**
     * Construct a {@link ContentActivity}.
     *
     * @param name the text name of the example
     * @param description a safe html description of the example
     * @param hasStyle true if the example has associated styles
     * @param rawSourceFiles the list of raw source files to include
     */
    public ContentActivity( String name, SafeHtml description, Resources resources, String... rawSourceFiles )
    {
        this.resources = resources;
        this.name = name;
        this.description = description;
        if ( rawSourceFiles != null )
        {
            for ( String rawSourceFile : rawSourceFiles )
            {
                rawSourceFilenames.add( rawSourceFile );
            }
        }
        loadingHtml = "loading";
        contentSource.getElement().getStyle().setBackgroundColor("#eee");
        contentSource.getElement().getStyle().setMargin(10.0, Unit.PX);
        contentSource.getElement().getStyle().setProperty(
            "border", "1px solid #c3c3c3");
        contentSource.getElement().getStyle().setProperty("padding", "10px 2px");
    }

    public HandlerRegistration addValueChangeHandler( ValueChangeHandler<String> handler )
    {
        return addHandler( handler, ValueChangeEvent.getType() );
    }

    /**
     * Get the description of this example.
     *
     * @return a description for this example
     */
    public final SafeHtml getDescription()
    {
        return description;
    }

    /**
     * Get the name of this example to use as a title.
     *
     * @return a name for this example
     */
    public final String getName()
    {
        return name;
    }

    /**
     * Get the source code for a raw file.
     *
     * @param filename the filename to load
     * @param callback the callback to call when loaded
     */
    public void getRawSource( final String filename, final Callback<String> callback )
    {
        if ( rawSource.containsKey( filename ) )
        {
            callback.onSuccess( rawSource.get( filename ) );
        }
        else
        {
            RequestCallback rc = new RequestCallback()
            {
                public void onError( Request request, Throwable exception )
                {
                    callback.onError();
                }

                public void onResponseReceived( Request request, Response response )
                {
                    String text = response.getText();
                    rawSource.put( filename, text );
                    callback.onSuccess( text );
                }
            };

            String className = this.getClass().getName();
            className = className.substring( className.lastIndexOf( "." ) + 1 );
            sendSourceRequest( rc, ExamplesConstants.DST_SOURCE_RAW + filename + ".html" );
        }
    }

    /**
     * Get the filenames of the raw source files.
     *
     * @return the raw source files.
     */
    public List<String> getRawSourceFilenames()
    {
        return Collections.unmodifiableList( rawSourceFilenames );
    }

    /**
     * Request the source code associated with the widget.
     *
     * @param callback the callback used when the source become available
     */
    public void getSource( final Callback<String> callback )
    {
        if ( sourceCode != null )
        {
            callback.onSuccess( sourceCode );
        }
        else
        {
            RequestCallback rc = new RequestCallback()
            {
                public void onError( Request request, Throwable exception )
                {
                    callback.onError();
                }

                public void onResponseReceived( Request request, Response response )
                {
                    sourceCode = response.getText();
                    callback.onSuccess( sourceCode );
                }
            };

            String className = this.getClass().getName();
            className = className.substring( className.lastIndexOf( "." ) + 1 );
            sendSourceRequest( rc, ExamplesConstants.DST_SOURCE_EXAMPLE + className + ".html" );
        }
    }

    /**
     * Check if the widget should have margins.
     *
     * @return true to use margins, false to flush against edges
     */
    public boolean hasMargins()
    {
        return true;
    }

    /**
     * Check if the widget should be wrapped in a scrollable div.
     *
     * @return true to use add scrollbars, false not to
     */
    public boolean hasScrollableContent()
    {
        return true;
    }

    /**
     * When the widget is first initialized, this method is called. If it returns a Widget, the widget will be added as
     * the first tab. Return null to disable the first tab.
     *
     * @return the widget to add to the first tab
     */
    public abstract Widget onInitialize();

    /**
     * Called when initialization has completed and the widget has been added to the page.
     */
    public void onInitializeComplete()
    {
    }

    protected abstract void asyncOnInitialize( final AsyncCallback<Widget> callback );

    /**
     * Fire a {@link ValueChangeEvent} indicating that the user wishes to see the specified source file.
     *
     * @param filename the filename that the user wishes to see
     */
    protected void fireRawSourceRequest( String filename )
    {
        if ( !rawSourceFilenames.contains( filename ) )
        {
            throw new IllegalArgumentException( "Filename is not registered with this example: " + filename );
        }
        ValueChangeEvent.fire( this, filename );
    }

    /**
     * Ensure that the demo widget has been initialized. Note that initialization can fail if there is a network
     * failure.
     */
    private void ensureWidgetInitialized()
    {
        if ( widgetInitializing || widgetInitialized )
        {
            return;
        }

        widgetInitializing = true;

        asyncOnInitialize( new AsyncCallback<Widget>()
        {
            public void onFailure( Throwable reason )
            {
                widgetInitializing = false;
                Window.alert( "Failed to download code for this widget (" + reason + ")" );
            }

            public void onSuccess( Widget result )
            {
                widgetInitializing = false;
                widgetInitialized = true;

                example = result;
                if ( example != null )
                {
                    view.setExample( example );
                }
                onInitializeComplete();
            }
        } );
    }

    private Widget example;

    /**
     * Send a request for source code.
     *
     * @param callback the {@link RequestCallback} to send
     * @param url the URL to target
     */
    private void sendSourceRequest( RequestCallback callback, String url )
    {
        RequestBuilder builder = new RequestBuilder( RequestBuilder.GET, GWT.getModuleBaseURL() + url );
        builder.setCallback( callback );
        try
        {
            builder.send();
        }
        catch ( RequestException e )
        {
            callback.onError( null, e );
        }
    }

    @Override
    public String mayStop()
    {
        return null;
    }

    @Override
    public void onCancel()
    {
    }

    @Override
    public void onStop()
    {
    }

    @Override
    public void start( AcceptsOneWidget panel, EventBus eventBus )
    {
        if ( view == null )
        {
            view = new ContentView( hasMargins(), hasScrollableContent() );
            view.setPresenter( this );
            setWidget( view );
        }
        panel.setWidget( this );
        ensureWidgetInitialized();



       view.getTabSourceList().addItem("Example");
        List<String> rawFilenames = getRawSourceFilenames();
        if (rawFilenames.size() > 0) {
          String text = view.getTabSource().getText();
          if (!text.endsWith(":")) {
              view.getTabSource().setText(text + ":");
          }
          view.getTabSourceList().setVisible(true);
          for (String filename : rawFilenames) {
              view.getTabSourceList().addItem(filename);
          }
          view.getTabSourceList().setSelectedIndex(0);
        } else {
          String text = view.getTabSource().getText();
          if (text.endsWith(":")) {
              view.getTabSource().setText(text.substring(0, text.length() - 1));
          }
          view.getTabSourceList().setVisible(false);
        }
    }

    @UiFactory
    public Resources getResources()
    {
        return resources;
    }

    public void showExample()
    {
        view.setExample( example );
    }

    public void showSourceFile()
    {
        contentSource.setHTML(loadingHtml, Direction.LTR);
        view.setExample(new ScrollPanel(contentSource));
        if (!view.getTabSourceList().isVisible() || view.getTabSourceList().getSelectedIndex() == 0) {
          // If the source list isn't visible or the first item is selected, load
          // the source for the example.
          getSource(new CustomCallback());
        } else {
          // Load a raw file.
          String filename = view.getTabSourceList().getItemText(
              view.getTabSourceList().getSelectedIndex());
          getRawSource(filename, new CustomCallback());
        }
    }

    /**
     * The handler used to handle user requests to view raw source.
     */
    private HandlerRegistration contentSourceHandler;

    /**
     * The widget that holds CSS or source code for an example.
     */
    private HTML contentSource = new HTML();

    /**
     * The html used to show a loading icon.
     */
    private final String loadingHtml;

    /**
     * The unique ID assigned to the next callback.
     */
    private static int nextCallbackId = 0;
    /**
     * The callback used when retrieving source code.
     */
    private class CustomCallback implements ContentActivity.Callback<String> {

      private int id;

      public CustomCallback() {
        id = ++nextCallbackId;
      }

      public void onError() {
        if (id == nextCallbackId) {
          contentSource.setHTML("Cannot find resource", Direction.LTR);
        }
      }

      public void onSuccess(String value) {
        if (id == nextCallbackId) {
          contentSource.setHTML(value, Direction.LTR);
        }
      }
    }
}
