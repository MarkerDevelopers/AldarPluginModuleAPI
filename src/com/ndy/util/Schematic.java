package com.ndy.util;

import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.schematic.MCEditSchematicFormat;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Schematic {

    private List<File> schematicFiles = new ArrayList<>();
    private static Schematic instance;

    public static Schematic getSchematic() {
        if (Schematic.instance == null) {
            Schematic.instance = new Schematic();
        }
        return Schematic.instance;
    }

    public boolean registerSchematicFile(File file) {
        if (file.exists()) {
            this.schematicFiles.add(file);
            return true;
        }
        return false;
    }

    public CuboidClipboard loadSchematic(Location center) {
        int index = (int) (Math.random() * this.schematicFiles.size());
        WorldEditPlugin worldEditPlugin = (WorldEditPlugin) Bukkit.getPluginManager().getPlugin("WorldEdit");
        EditSession session = worldEditPlugin.getWorldEdit().getEditSessionFactory().getEditSession(new BukkitWorld(center.getWorld()), 999999999);
        try {
            CuboidClipboard clipboard = MCEditSchematicFormat.getFormat(this.schematicFiles.get(index))
                    .load(this.schematicFiles.get(index));
            clipboard.paste(session, new Vector(center.getX(), center.getY(), center.getZ()), true);
            return clipboard;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void loadSchematic(File file, Location location) {

    }

}
