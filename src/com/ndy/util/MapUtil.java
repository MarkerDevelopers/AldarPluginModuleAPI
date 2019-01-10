package com.ndy.util;

import java.util.HashMap;
import java.util.Map;

public class MapUtil<T, U> {

    private Map<T, U> map = new HashMap<>();

    public MapUtil putData(T t, U u) {
        this.map.put(t, u);
        return this;
    }

    public Map<T, U> getMap() { return map; }

}
