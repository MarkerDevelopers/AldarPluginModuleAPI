package com.ndy.scheduler;

import com.ndy.module.PluginModuleLoader;
import com.ndy.module.PluginModuleManager;
import com.ndy.module.type.ModuleLoadResult;
import com.ndy.scheduler.impl.ModuleScheduler;
import org.bukkit.Bukkit;
import sun.security.pkcs11.Secmod;

public class ModuleLoadScheduler extends ModuleScheduler {

    public ModuleLoadScheduler(String scheduleName) {
        super(scheduleName);
    }

    @Override
    public void run() {
        PluginModuleManager.getManager().getUnloadedModules().stream()
                .forEach(i ->{
                    ModuleLoadResult result = PluginModuleLoader.load(i);

                    if(result == ModuleLoadResult.Exception || result == ModuleLoadResult.Failed) {
                        Bukkit.getConsoleSender().sendMessage("§c" + i.getPlugin().getName() + " Module Load Failed!");
                    }else {
                        Bukkit.getConsoleSender().sendMessage("§a" + i.getPlugin().getName() + " Module Loaded!");
                    }
                });
    }
}
