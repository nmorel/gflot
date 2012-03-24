package ca.nanometrics.gflot.client.resources;

public class NullLoader
    implements PluginLoader
{
    @Override
    public void load()
    {
    }

    @Override
    public boolean isPluginEnabled()
    {
        // plugin is disabled
        return false;
    }
}
