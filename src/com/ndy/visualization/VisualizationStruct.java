package com.ndy.visualization;

import org.bukkit.Material;

public class VisualizationStruct {

    private int slot;
    private Material item;
    private short durability;

    public VisualizationStruct(int slot, Material item, short durability) {
        this.slot = slot;
        this.item = item;
        this.durability = durability;
    }

    public void setItem(Material item) { this.item = item; }
    public void setSlot(int slot) { this.slot = slot; }
    public void setDurability(short durability) { this.durability = durability; }

    public int getSlot() { return slot; }
    public Material getItem() { return item; }
    public short getDurability() { return durability; }
}
