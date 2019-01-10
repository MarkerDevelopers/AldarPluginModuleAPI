package com.ndy.module.impl;

import com.ndy.module.type.ModuleLoadResult;

public interface IModuleInitializer {
    public ModuleLoadResult initialize() throws Exception;
    public void dispose();
}
