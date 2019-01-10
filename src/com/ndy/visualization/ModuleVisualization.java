package com.ndy.visualization;

public class ModuleVisualization {

    private static ModuleVisualization instance;
    private ModuleVisualizationBuilder builder;

    private ModuleVisualization() {}

    public static ModuleVisualization getVisualization() {
        if(instance == null) instance = new ModuleVisualization();
        return instance;
    }

    public ModuleVisualizationBuilder getBuilder() {
        if(builder == null) builder = new ModuleVisualizationBuilder("모듈", 54);
        return builder;
    }

}
