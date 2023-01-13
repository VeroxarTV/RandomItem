package de.veroxar.randomitem.countdown;

import de.veroxar.randomitem.RandomItem;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class ItemCountdown extends Countdown {
    @Override
    public void onStart() {

    }

    @Override
    public void onEnd() {

        if (getRemainingSeconds() == 0) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                givePlayerItem(player);
            }
        }
            if (!isRunning())
                startCountdown(RandomItem.getTime());
    }

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendActionBar(Component.text(ChatColor.GOLD.toString() + getRemainingSeconds()));
        }
    }

    public void givePlayerItem(Player player) {
        ItemStack item = new ItemStack(RandomItem.getItems().get(
                RandomItem.getRandom().nextInt(RandomItem.getItems().size())));
        HashMap<Integer, ItemStack> failed = player.getInventory().addItem(item);
        if (!failed.isEmpty()) {
            player.getWorld().dropItem(player.getLocation(), item);
            failed.clear();
        }
        if (!player.getInventory().contains(item)) {
            givePlayerItem(player);
        } else {
            Bukkit.broadcast(Component.text(ChatColor.GREEN + player.getName() +
                    "§7 hat folgendes Item erhalten: §6" + item.getType().name()));
        }
    }
}
