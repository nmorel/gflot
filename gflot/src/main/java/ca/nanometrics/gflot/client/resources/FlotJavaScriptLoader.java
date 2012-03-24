package ca.nanometrics.gflot.client.resources;

import com.google.gwt.core.client.GWT;

public class FlotJavaScriptLoader
{
    public interface FlotJavaScriptCallback
    {
        void onError( Throwable caught );

        void onSuccess();
    }

    private static Impl impl;

    public static Impl get()
    {
        if ( null == impl )
        {
            impl = GWT.create( Impl.class );
        }
        return impl;
    }

    public static interface Impl
    {
        void loadRequiredFlotLibrary( final FlotJavaScriptCallback callback );
    }

    public static class SynchronousImpl
        implements Impl
    {
        private boolean loaded = false;

        private PluginLoader jqueryLoader;

        private PluginLoader flotLoader;

        private PluginLoader flotSelectionLoader;

        private PluginLoader flotSymbolLoader;

        private PluginLoader flotImageLoader;

        private PluginLoader flotPieLoader;

        private PluginLoader flotStackLoader;

        private PluginLoader flotTextLoader;

        private PluginLoader flotResizeLoader;

        private PluginLoader excanvasLoader;

        public void loadRequiredFlotLibrary( final FlotJavaScriptCallback callback )
        {
            if ( !loaded )
            {
                load();
                loaded = true;
            }
            callback.onSuccess();
        }

        private PluginLoader getJqueryLoader()
        {
            if ( null == jqueryLoader )
            {
                jqueryLoader = GWT.create( JQueryLoader.class );
            }
            return jqueryLoader;
        }

        private PluginLoader getFlotLoader()
        {
            if ( null == flotLoader )
            {
                flotLoader = GWT.create( FlotLoader.class );
            }
            return flotLoader;
        }

        private PluginLoader getFlotSelectionLoader()
        {
            if ( null == flotSelectionLoader )
            {
                flotSelectionLoader = GWT.create( FlotSelectionLoader.class );
            }
            return flotSelectionLoader;
        }

        private PluginLoader getFlotSymbolLoader()
        {
            if ( null == flotSymbolLoader )
            {
                flotSymbolLoader = GWT.create( FlotSymbolLoader.class );
            }
            return flotSymbolLoader;
        }

        private PluginLoader getFlotImageLoader()
        {
            if ( null == flotImageLoader )
            {
                flotImageLoader = GWT.create( FlotImageLoader.class );
            }
            return flotImageLoader;
        }

        private PluginLoader getFlotPieLoader()
        {
            if ( null == flotPieLoader )
            {
                flotPieLoader = GWT.create( FlotPieLoader.class );
            }
            return flotPieLoader;
        }

        private PluginLoader getFlotStackLoader()
        {
            if ( null == flotStackLoader )
            {
                flotStackLoader = GWT.create( FlotStackLoader.class );
            }
            return flotStackLoader;
        }

        private PluginLoader getFlotTextLoader()
        {
            if ( null == flotTextLoader )
            {
                flotTextLoader = GWT.create( FlotTextLoader.class );
            }
            return flotTextLoader;
        }

        private PluginLoader getExcanvasLoader()
        {
            if ( null == excanvasLoader )
            {
                excanvasLoader = GWT.create( ExplorerCanvasLoader.class );
            }
            return excanvasLoader;
        }

        private PluginLoader getFlotResizeLoader()
        {
            if ( null == flotResizeLoader )
            {
                flotResizeLoader = GWT.create( FlotResizeLoader.class );
            }
            return flotResizeLoader;
        }

        private void load()
        {
            getJqueryLoader().load();
            getFlotLoader().load();
            getFlotSelectionLoader().load();
            getFlotSymbolLoader().load();
            getFlotImageLoader().load();
            getFlotPieLoader().load();
            getFlotStackLoader().load();
            getFlotTextLoader().load();
            getFlotResizeLoader().load();
            getExcanvasLoader().load();
        }
    }
}
