package com.ndy.module;

import com.ndy.module.type.ModuleLoadResult;

public class PluginModuleLoader {

    public static ModuleLoadResult load(PluginModule module) {
        try {
            module.setLoaded(true);
            return module.getInitializer().initialize();
        }catch (Exception e) {
            e.printStackTrace();
            return ModuleLoadResult.Exception;
        }
    }

}
