package com.ndy.scheduler.impl;

import com.ndy.scheduler.ModuleSchedulerManager;
import org.bukkit.Bukkit;

public abstract class ModuleScheduler implements Runnable{

    private String scheduleName;
    private int taskId = -1;

    public ModuleScheduler(String scheduleName) {
        this.scheduleName = scheduleName;
    }

    public String getScheduleName() { return scheduleName; }
    public int getTaskId() { return taskId; }

    public void cancelTask() {
        Bukkit.getScheduler().cancelTask(this.taskId);
        ModuleSchedulerManager.getManager().remove(this);
    }
}
