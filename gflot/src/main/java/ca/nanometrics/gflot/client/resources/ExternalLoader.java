package ca.nanometrics.gflot.client.resources;

public class ExternalLoader
    implements PluginLoader
{
    @Override
    public void load()
    {
    }

    @Override
    public boolean isPluginEnabled()
    {
        // the plugin is loaded externally
        return true;
    }
}
