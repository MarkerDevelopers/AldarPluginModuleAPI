package com.ndy;

import com.ndy.module.PluginModule;
import com.ndy.module.PluginModuleManager;
import com.ndy.scheduler.ModuleLoadScheduler;
import com.ndy.scheduler.ModuleSchedulerManager;
import com.ndy.scheduler.impl.ModuleScheduler;
import com.ndy.util.gui.GUISession;
import org.bukkit.plugin.java.JavaPlugin;

public class ModulePlugin extends JavaPlugin  {

    public static ModulePlugin instance;

    @Override
    public void onEnable() {
        instance = this;

        if(!getDataFolder().exists()) getDataFolder().mkdir();

        ModuleSchedulerManager.getManager().addSyncRepeatingTask(new ModuleLoadScheduler("moduleLoadScheduler"), 40);
    }

    @Override
    public void onDisable() {
        for(PluginModule module : PluginModuleManager.getManager().getModules()) {
            module.getInitializer().dispose();
        }
    }
}
