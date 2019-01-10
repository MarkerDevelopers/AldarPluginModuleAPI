package com.ndy.scheduler;

import com.ndy.ModulePlugin;
import com.ndy.scheduler.impl.ModuleScheduler;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ModuleSchedulerManager {

    private static ModuleSchedulerManager instance;

    private Set<ModuleScheduler> schedulers = new HashSet<>();

    private ModuleSchedulerManager() {}

    public static ModuleSchedulerManager getManager() {
        if(instance == null) instance = new ModuleSchedulerManager();
        return instance;
    }

    public void addSyncRepeatingTask(ModuleScheduler scheduler, int second) {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(ModulePlugin.instance, scheduler, 0L, second * 20L);
        this.schedulers.add(scheduler);
    }

    public void addAsyncDelayedTask(ModuleScheduler scheduler, int second) { Bukkit.getScheduler().scheduleAsyncDelayedTask(ModulePlugin.instance, scheduler, second * 20L); }
    public void remove(ModuleScheduler scheduler) { schedulers.removeIf(i -> i.equals(scheduler)); }

    public List<String> getWorkingRepeatingTasks() {
        List<String> list = new ArrayList<>();
        return list;
    }

}
