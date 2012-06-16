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

        PluginLoader getFlotNavigateLoader();

        PluginLoader getFlotCrosshairLoader();

        PluginLoader getFlotMultipleBarsLoader();
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

        private PluginLoader flotNavigateLoader;

        private PluginLoader flotCrosshairLoader;

        private PluginLoader flotMultipleBarsLoader;

        @Override
        public void loadRequiredFlotLibrary( final FlotJavaScriptCallback callback )
        {
            if ( !loaded )
            {
                load();
                loaded = true;
            }
            callback.onSuccess();
        }

        @Override
        public PluginLoader getJqueryLoader()
        {
            if ( null == jqueryLoader )
            {
                jqueryLoader = GWT.create( JQueryLoader.class );
            }
            return jqueryLoader;
        }

        @Override
        public PluginLoader getFlotLoader()
        {
            if ( null == flotLoader )
            {
                flotLoader = GWT.create( FlotLoader.class );
            }
            return flotLoader;
        }

        @Override
        public PluginLoader getFlotSelectionLoader()
        {
            if ( null == flotSelectionLoader )
            {
                flotSelectionLoader = GWT.create( FlotSelectionLoader.class );
            }
            return flotSelectionLoader;
        }

        @Override
        public PluginLoader getFlotSymbolLoader()
        {
            if ( null == flotSymbolLoader )
            {
                flotSymbolLoader = GWT.create( FlotSymbolLoader.class );
            }
            return flotSymbolLoader;
        }

        @Override
        public PluginLoader getFlotImageLoader()
        {
            if ( null == flotImageLoader )
            {
                flotImageLoader = GWT.create( FlotImageLoader.class );
            }
            return flotImageLoader;
        }

        @Override
        public PluginLoader getFlotPieLoader()
        {
            if ( null == flotPieLoader )
            {
                flotPieLoader = GWT.create( FlotPieLoader.class );
            }
            return flotPieLoader;
        }

        @Override
        public PluginLoader getFlotStackLoader()
        {
            if ( null == flotStackLoader )
            {
                flotStackLoader = GWT.create( FlotStackLoader.class );
            }
            return flotStackLoader;
        }

        @Override
        public PluginLoader getFlotTextLoader()
        {
            if ( null == flotTextLoader )
            {
                flotTextLoader = GWT.create( FlotTextLoader.class );
            }
            return flotTextLoader;
        }

        @Override
        public PluginLoader getExcanvasLoader()
        {
            if ( null == excanvasLoader )
            {
                excanvasLoader = GWT.create( ExplorerCanvasLoader.class );
            }
            return excanvasLoader;
        }

        @Override
        public PluginLoader getFlotResizeLoader()
        {
            if ( null == flotResizeLoader )
            {
                flotResizeLoader = GWT.create( FlotResizeLoader.class );
            }
            return flotResizeLoader;
        }

        @Override
        public PluginLoader getCanvas2ImageLoader()
        {
            if ( null == canvas2ImageLoader )
            {
                canvas2ImageLoader = GWT.create( Canvas2ImageLoader.class );
            }
            return canvas2ImageLoader;
        }

        @Override
        public PluginLoader getFlotAxisLabelsLoader()
        {
            if ( null == flotAxisLabelsLoader )
            {
                flotAxisLabelsLoader = GWT.create( FlotAxisLabelsLoader.class );
            }
            return flotAxisLabelsLoader;
        }

        @Override
        public PluginLoader getFlotThresholdLoader()
        {
            if ( null == flotThresholdLoader )
            {
                flotThresholdLoader = GWT.create( FlotThresholdLoader.class );
            }
            return flotThresholdLoader;
        }

        @Override
        public PluginLoader getFlotNavigateLoader()
        {
            if ( null == flotNavigateLoader )
            {
                flotNavigateLoader = GWT.create( FlotNavigateLoader.class );
            }
            return flotNavigateLoader;
        }

        @Override
        public PluginLoader getFlotCrosshairLoader()
        {
            if ( null == flotCrosshairLoader )
            {
                flotCrosshairLoader = GWT.create( FlotCrosshairLoader.class );
            }
            return flotCrosshairLoader;
        }

        @Override
        public PluginLoader getFlotMultipleBarsLoader()
        {
            if ( null == flotMultipleBarsLoader )
            {
                flotMultipleBarsLoader = GWT.create( FlotMultipleBarsLoader.class );
            }
            return flotMultipleBarsLoader;
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
            getFlotNavigateLoader().load();
            getFlotCrosshairLoader().load();
            getFlotMultipleBarsLoader().load();
        }
    }
}
