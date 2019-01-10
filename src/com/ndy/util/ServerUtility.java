package com.ndy.util;

import com.ndy.ModulePlugin;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ServerUtility {

    public static void sendFireWorks(Location location, int power, int sendAmount) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(ModulePlugin.instance, () -> {
            for(int i = 0; i < sendAmount; i++) {
                Firework firework = (Firework) location.getWorld().spawn(location, Firework.class);
                FireworkMeta meta = firework.getFireworkMeta();
                FireworkEffect.Type[] types = FireworkEffect.Type.values();
                Color[] color = new Color[] {Color.AQUA, Color.BLACK, Color.GREEN, Color.RED, Color.ORANGE, Color.PURPLE};
                meta.addEffect(FireworkEffect.builder().flicker(false).trail(true).with(types[(int) (Math.random() * types.length)])
                        .withColor(color[(int) (Math.random() * color.length)]).withFade(color[(int) (Math.random() * color.length)])
                        .build());
                meta.setPower(power);
                firework.setFireworkMeta(meta);
            }
        }, 1L);
    }

    /***
     * @return getUUIDs
     */
    public static List<String> getAllPlayers() {
        List<String> players = new ArrayList<>();
        String systemDir = System.getProperty("user.dir");
        File playerDirectory = new File(systemDir + "\\world\\playerdata");

        if(playerDirectory.exists()) {
            File[] playerFiles = playerDirectory.listFiles();
            for(File file : playerFiles)
                players.add(file.getName().split("[.]")[0]);
        }
        return players;
    }

    public static boolean range(Location location1, Location location2, int range) {
        for(int y = location1.getBlockY()-range; y < location1.getBlockY()+range; y++){
            for(int x = location1.getBlockX()-range; x < location1.getBlockX()+range; x++) {
                for(int z = location1.getBlockZ()-range; z < location1.getBlockZ()+range; z++) {
                    Location location = new Location(location1.getWorld(), x, y, z);
                    if(location.equals(location2)) return true;
                }
            }
        }
        return false;
    }

    public static String getFormatNumber(String format, float target) {
        DecimalFormat decimalFormat = new DecimalFormat(format);
        return decimalFormat.format(target);
    }

    /**
     * @usage 센터를 기점으로 dist(거리) + range(범위) 안에 원 중 하나의 로케이션을 가져온다.
     * */
    public static Location getLocation(Location center, int dist, int range, boolean hollow) {
        List<Location> locations = new ArrayList<>();

        int r = dist + range;
        int bx = center.getBlockX();
        int bz = center.getBlockZ();

        for(int x = bx - r; x <= bx + r; x++) {
            for(int z = bz - r; z <= bz + r; z++) {
                double distance = ((bx-x) * (bx-x) + ((bz-z) * (bz-z)));

                if(!hollow){
                    locations.add(new Location(center.getWorld(), x, center.getBlockY(), z));
                    continue;
                }
                if(distance < r* r && !(hollow && distance < ((r - 1) * (r - 1)))) {
                    Location l = new Location(center.getWorld(), x, center.getBlockY(), z);
                    locations.add(l);
                }
            }
        }
        return locations.get((int) (Math.random() * locations.size()));
    }

    /**
     * @usage 센터를 기점으로 range안에 원 형태의 범위 위치를 가져옴
     * */
    public static List<Location> getLocations(Location center, int dist, int range, boolean hollow) {
        List<Location> locations = new ArrayList<>();

        int r = dist + range;
        int bx = center.getBlockX();
        int bz = center.getBlockZ();

        for(int x = bx - r; x <= bx + r; x++) {
            for(int z = bz - r; z <= bz + r; z++) {
                double distance = ((bx-x) * (bx-x) + ((bz-z) * (bz-z)));

                if(!hollow){
                    locations.add(new Location(center.getWorld(), x, center.getBlockY(), z));
                    continue;
                }
                if(distance < r* r && !(hollow && distance < ((r - 1) * (r - 1)))) {
                    Location l = new Location(center.getWorld(), x, center.getBlockY(), z);
                    locations.add(l);
                }
            }
        }
        return locations;
    }

    public static boolean withInArea(Location targetLoc, Location center, int range) {
        int x = center.getBlockX(), z = center.getBlockZ();
        int subX = x-range, subZ = z-range;
        int addX = x+range, addZ = z+range;
        int locX = targetLoc.getBlockX(), locZ = targetLoc.getBlockZ();

        return (subX <= locX && addX >= locX) && (subZ <= locZ && addZ >= locZ);
    }


}
