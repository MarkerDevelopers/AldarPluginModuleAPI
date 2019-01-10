package com.ndy.module;

import com.ndy.ModulePlugin;
import com.ndy.module.impl.IModuleInitializer;
import com.ndy.visualization.VisualizationStruct;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class PluginModule {

    private VisualizationStruct struct;

    private IModuleInitializer initializer;
    private Plugin plugin;
    private boolean loaded = false;

    public PluginModule(Plugin plugin, IModuleInitializer initializer, VisualizationStruct struct) {
        this.plugin = plugin;
        this.initializer = initializer;
        this.struct = struct;
    }

    public void setLoaded(boolean loaded) { this.loaded = loaded; }
    public Plugin getPlugin() { return plugin; }
    public IModuleInitializer getInitializer() { return initializer; }
    public VisualizationStruct getVisualizationData() { return struct; }
    public File getModuleFolder() { return new File(ModulePlugin.instance.getDataFolder(), this.getPlugin().getName()); }
    public boolean isLoaded() { return loaded; }
}
