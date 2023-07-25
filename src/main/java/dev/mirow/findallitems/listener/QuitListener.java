package dev.mirow.findallitems.listener;

import dev.mirow.findallitems.FindAllItems;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {

    private final FindAllItems instance;

    public QuitListener(FindAllItems instance) {
        this.instance = instance;
        Bukkit.getPluginManager().registerEvents(this, instance);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        event.setQuitMessage(FindAllItems.PREFIX + "§7Der Spieler §f" + player.getName() + " §7hat den Server verlassen.");

    }
}
