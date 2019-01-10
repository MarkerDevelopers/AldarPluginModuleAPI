package test;

import com.ndy.module.PluginModuleManager;
import com.ndy.module.impl.IModuleInitializer;
import com.ndy.module.type.ModuleLoadResult;
import org.bukkit.plugin.java.JavaPlugin;

public class TestMain extends JavaPlugin implements IModuleInitializer {

    @Override
    public void onEnable() {
        PluginModuleManager.getManager().registerModule(this, this);
    }

    @Override
    public ModuleLoadResult initialize() {
        return ModuleLoadResult.Success;
    }

    @Override
    public void dispose() {

    }
}
