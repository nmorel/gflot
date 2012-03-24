package ca.nanometrics.gflot.client.resources;

public abstract class DefaultLoader
    implements PluginLoader
{
    @Override
    public boolean isPluginEnabled()
    {
        return true;
    }
}
