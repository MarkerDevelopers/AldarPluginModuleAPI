package com.ndy.util;

import java.util.*;
import java.util.stream.Collectors;

public class Tag<T, U> {

    /**
     * @author 남대영
     * @usage 여러 Object의 종속성을 고려한 임시 데이터 저장 API
     * */

    private static Set<Tag> tags = new HashSet<>();

    private Map<T, U> data;
    private String name;

    private Tag(String name, Map<T, U> map) {
        this.name = name;
        this.data = map;
    }

    public <K> K getObject(T key) { return data.containsKey(key) ? (K) data.get(key) : null; }
    public String getName() { return name; }
    public void addObject(T key, U o) { this.data.put(key, o); }

    public static <T, U> Tag createTag(String name, Map<T, U> map) {
        Tag<T, U> tag = new Tag<T, U>(name, map);
        tags.add(tag);

        return tag;
    }

    public static Tag getTag(String name) {
        List<Tag> list = tags.stream().filter(i -> i.name.equals(name)).collect(Collectors.toList());
        if(list.size() == 0) return null;

        return list.get(0);
    }

    public static void removeTag(String name) { tags.removeIf(i -> i.name.equals(name)); }

}
