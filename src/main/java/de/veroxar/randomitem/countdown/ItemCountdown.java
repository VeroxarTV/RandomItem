package de.veroxar.randomitem.countdown;

import de.veroxar.randomitem.RandomItem;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemCountdown extends Countdown {
    @Override
    public void onStart() {

    }

    @Override
    public void onEnd() {

        if (getRemainingSeconds() == 0) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                ItemStack item = new ItemStack(RandomItem.getItems().get(
                        RandomItem.getRandom().nextInt(RandomItem.getItems().size())));
                player.getInventory().addItem(item);
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
}
