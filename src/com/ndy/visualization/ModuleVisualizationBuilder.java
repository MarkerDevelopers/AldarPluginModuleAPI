package com.ndy.visualization;

import com.ndy.module.PluginModule;
import com.ndy.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ModuleVisualizationBuilder {

    private Inventory inventory;

    public ModuleVisualizationBuilder(String title, int size) {
        this.inventory = Bukkit.createInventory(null, size, title);
    }

    public ModuleVisualizationBuilder setItem(PluginModule module) {
        if(module.getVisualizationData().getSlot() > 53) return this;

        ItemStack item = new ItemBuilder(module.getVisualizationData().getItem())
                .setDisplayName("§f" + module.getPlugin().getName())
                .setData(module.getVisualizationData().getDurability())
                .build();

        this.inventory.setItem(module.getVisualizationData().getSlot(), item);
        return this;
    }

    public Inventory build() { return inventory; }

}
