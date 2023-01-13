package de.veroxar.randomitem;

import de.veroxar.randomitem.countdown.ItemCountdown;
import de.veroxar.randomitem.listeners.ConnectionListener;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public final class RandomItem extends JavaPlugin {

    private static JavaPlugin instance;
    private static Long time;
    private static List<Material> items = new ArrayList<>();
    private static Random random = new Random();
    private static ItemCountdown itemCountdown = new ItemCountdown();

    @Override
    public void onEnable() {
        instance = this;
        this.saveDefaultConfig();
        items.addAll(Arrays.asList(Material.values()));
        time = this.getConfig().getLong("time");
        Bukkit.getServer().getPluginManager().registerEvents(new ConnectionListener(), this);
        Bukkit.getWorlds().forEach(world -> world.setTime(0));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static JavaPlugin getInstance() {
        return instance;
    }

    public static Long getTime() {
        return time;
    }

    public static List<Material> getItems() {
        return items;
    }

    public static Random getRandom() {
        return random;
    }

    public static ItemCountdown getItemCountdown() {
        return itemCountdown;
    }
}
