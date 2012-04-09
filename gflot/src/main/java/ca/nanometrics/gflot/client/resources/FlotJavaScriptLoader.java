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

        PluginLoader getJqueryLoader();

        PluginLoader getFlotLoader();

        PluginLoader getFlotSelectionLoader();

        PluginLoader getFlotSymbolLoader();

        PluginLoader getFlotImageLoader();

        PluginLoader getFlotPieLoader();

        PluginLoader getFlotStackLoader();

        PluginLoader getFlotTextLoader();

        PluginLoader getExcanvasLoader();

        PluginLoader getFlotResizeLoader();

        PluginLoader getCanvas2ImageLoader();

        PluginLoader getFlotAxisLabelsLoader();

        PluginLoader getFlotThresholdLoader();
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

        private PluginLoader canvas2ImageLoader;

        private PluginLoader flotAxisLabelsLoader;

        private PluginLoader flotThresholdLoader;

        public void loadRequiredFlotLibrary( final FlotJavaScriptCallback callback )
        {
            if ( !loaded )
            {
                load();
                loaded = true;
            }
            callback.onSuccess();
        }

        public PluginLoader getJqueryLoader()
        {
            if ( null == jqueryLoader )
            {
                jqueryLoader = GWT.create( JQueryLoader.class );
            }
            return jqueryLoader;
        }

        public PluginLoader getFlotLoader()
        {
            if ( null == flotLoader )
            {
                flotLoader = GWT.create( FlotLoader.class );
            }
            return flotLoader;
        }

        public PluginLoader getFlotSelectionLoader()
        {
            if ( null == flotSelectionLoader )
            {
                flotSelectionLoader = GWT.create( FlotSelectionLoader.class );
            }
            return flotSelectionLoader;
        }

        public PluginLoader getFlotSymbolLoader()
        {
            if ( null == flotSymbolLoader )
            {
                flotSymbolLoader = GWT.create( FlotSymbolLoader.class );
            }
            return flotSymbolLoader;
        }

        public PluginLoader getFlotImageLoader()
        {
            if ( null == flotImageLoader )
            {
                flotImageLoader = GWT.create( FlotImageLoader.class );
            }
            return flotImageLoader;
        }

        public PluginLoader getFlotPieLoader()
        {
            if ( null == flotPieLoader )
            {
                flotPieLoader = GWT.create( FlotPieLoader.class );
            }
            return flotPieLoader;
        }

        public PluginLoader getFlotStackLoader()
        {
            if ( null == flotStackLoader )
            {
                flotStackLoader = GWT.create( FlotStackLoader.class );
            }
            return flotStackLoader;
        }

        public PluginLoader getFlotTextLoader()
        {
            if ( null == flotTextLoader )
            {
                flotTextLoader = GWT.create( FlotTextLoader.class );
            }
            return flotTextLoader;
        }

        public PluginLoader getExcanvasLoader()
        {
            if ( null == excanvasLoader )
            {
                excanvasLoader = GWT.create( ExplorerCanvasLoader.class );
            }
            return excanvasLoader;
        }

        public PluginLoader getFlotResizeLoader()
        {
            if ( null == flotResizeLoader )
            {
                flotResizeLoader = GWT.create( FlotResizeLoader.class );
            }
            return flotResizeLoader;
        }

        public PluginLoader getCanvas2ImageLoader()
        {
            if ( null == canvas2ImageLoader )
            {
                canvas2ImageLoader = GWT.create( Canvas2ImageLoader.class );
            }
            return canvas2ImageLoader;
        }

        public PluginLoader getFlotAxisLabelsLoader()
        {
            if ( null == flotAxisLabelsLoader )
            {
                flotAxisLabelsLoader = GWT.create( FlotAxisLabelsLoader.class );
            }
            return flotAxisLabelsLoader;
        }

        public PluginLoader getFlotThresholdLoader()
        {
            if ( null == flotThresholdLoader )
            {
                flotThresholdLoader = GWT.create( FlotThresholdLoader.class );
            }
            return flotThresholdLoader;
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
            getCanvas2ImageLoader().load();
            getFlotAxisLabelsLoader().load();
            getFlotThresholdLoader().load();
        }
    }
}
