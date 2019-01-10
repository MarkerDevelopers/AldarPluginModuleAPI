package com.ndy.module;

import com.ndy.ModulePlugin;
import com.ndy.module.impl.IModuleInitializer;
import com.ndy.module.type.ModuleLoadResult;
import com.ndy.visualization.ModuleVisualization;
import com.ndy.visualization.ModuleVisualizationBuilder;
import com.ndy.visualization.VisualizationStruct;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.libs.jline.internal.Log;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PluginModuleManager {

    private static PluginModuleManager manager;
    private Set<PluginModule> modules = new HashSet<>();

    private PluginModuleManager() {}

    public static PluginModuleManager getManager() {
        if(manager == null) manager = new PluginModuleManager();
        return manager;
    }

    public boolean registerModule(Plugin plugin, IModuleInitializer initializer, VisualizationStruct... structs) {
        PluginModule module = new PluginModule(plugin, initializer,
                structs.length > 0 ? structs[0] : new VisualizationStruct(1000, Material.PAPER, (short) 0));
        modules.add(module);
        return true;
    }

    public void unloadModule(String pluginName) {
        PluginModule module = getModule(pluginName);
        module.getInitializer().dispose();

        Bukkit.getPluginManager().disablePlugin(module.getPlugin());
    }

    public PluginModule getModule(String pluginName) {
        List<PluginModule> m = modules.stream().filter(i -> i.getPlugin().getName().equals(pluginName))
                .collect(Collectors.toList());

        return m.size() == 0 ? null : m.get(0);
    }

    public void loadModules() {
        this.modules.stream().forEach(i -> {
            ModuleLoadResult loadResult = PluginModuleLoader.load(i);

            System.out.println(loadResult);
            if(loadResult == ModuleLoadResult.Failed || loadResult == ModuleLoadResult.Exception) {
                Bukkit.getConsoleSender().sendMessage("§c" + i.getPlugin().getName() + " Module Load Failed!");
            }else{
                ModuleVisualization.getVisualization().getBuilder().setItem(i);
                Bukkit.getConsoleSender().sendMessage("§a" + i.getPlugin().getName() + " Module Loaded!");
            }
        });
    }

    public Set<PluginModule> getUnloadedModules() {
        Set<PluginModule> modules = new HashSet<>();
        modules.addAll(this.modules.stream().filter(i -> !i.isLoaded()).collect(Collectors.toList()));

        return modules;
    }

    /**
     * @description PluginModule 인스턴스가 없을경우 임의로 모듈 폴더를 반환합니다.
     * */
    public File getModuleFolder(String pluginName) { return new File(ModulePlugin.instance.getDataFolder(), pluginName); }
    public Set<PluginModule> getModules() { return modules; }
}
