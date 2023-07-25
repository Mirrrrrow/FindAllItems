package dev.mirow.findallitems.listener;

import dev.mirow.findallitems.FindAllItems;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    private final FindAllItems instance;

    public JoinListener(FindAllItems instance) {
        this.instance = instance;
        Bukkit.getPluginManager().registerEvents(this, instance);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage(FindAllItems.PREFIX + "§7Der Spieler §f" + player.getName() + " §7hat den Server betreten.");

        instance.getBossBarUtils().addPlayer(player);

    }
}
