package de.veroxar.randomitem.listeners;

import de.veroxar.randomitem.RandomItem;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectionListener implements Listener {

    @EventHandler
    public void onJoin (PlayerJoinEvent event) {
        event.joinMessage(Component.text("§a+ §7" + event.getPlayer().getName()));
        if (Bukkit.getOnlinePlayers().size() == 1) {
            RandomItem.getItemCountdown().startCountdown(RandomItem.getTime());
        }
    }

    @EventHandler
    public void onQuit (PlayerQuitEvent event) {
        event.quitMessage(Component.text("§c- §7" + event.getPlayer().getName()));
        if (Bukkit.getOnlinePlayers().size() < 1 && RandomItem.getItemCountdown().isRunning())
            RandomItem.getItemCountdown().cancelCountdown();
    }
}
