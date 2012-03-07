package ca.nanometrics.gflot.client.resources;

import ca.nanometrics.gflot.client.util.JavaScriptInjector;

import com.google.gwt.core.client.GWT;

public class FlotJavaScriptLoader
{
    public interface FlotJavaScriptCallback
    {
        void onError(Throwable caught);

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

        private FlotJavaScriptBundle bundle;

        private FlotJavaScriptBundle getBundle()
        {
            if ( null == bundle )
            {
                bundle = GWT.create( FlotJavaScriptBundle.class );
            }
            return bundle;
        }

        public void loadRequiredFlotLibrary( final FlotJavaScriptCallback callback )
        {
            if ( !loaded )
            {
                load( getBundle() );
                loaded = true;
            }
            callback.onSuccess();
        }

        protected void load( FlotJavaScriptBundle bundle )
        {
            JavaScriptInjector.inject( bundle.jquery().getText() );
            JavaScriptInjector.inject( bundle.flot().getText() );
            JavaScriptInjector.inject( bundle.flotSelection().getText() );
            JavaScriptInjector.inject( bundle.flotSymbol().getText() );
            JavaScriptInjector.inject( bundle.flotImage().getText() );
            JavaScriptInjector.inject( bundle.flotPie().getText() );
            JavaScriptInjector.inject( bundle.flotStack().getText() );
        }
    }

    public static class SynchronousIEUnder9Impl
        extends SynchronousImpl
    {
        @Override
        protected void load( FlotJavaScriptBundle bundle )
        {
            super.load( bundle );
            JavaScriptInjector.inject( bundle.excanvas().getText() );
        }
    }

    public static class NoInjectionImpl
        implements Impl
    {
        public void loadRequiredFlotLibrary( final FlotJavaScriptCallback callback )
        {
            callback.onSuccess();
        }
    }
}
