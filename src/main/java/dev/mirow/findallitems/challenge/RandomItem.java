package dev.mirow.findallitems.challenge;

import dev.mirow.findallitems.FindAllItems;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class RandomItem {

    public ArrayList<String> remainingItems = new ArrayList<>();

    public Material material;

    public int currentItemInt;

    private final FindAllItems instance;

    public RandomItem(FindAllItems instance) {
        this.instance = instance;



        if (instance.getConfig().get("materials") == null || instance.getConfig().getList("materials") == null || instance.getConfig().getList("materials").size() == 0) {
            for (Material material : Material.values()) {
                if (!material.isAir()) {
                    this.remainingItems.add(material.name());
                }
            }

            Collections.shuffle(remainingItems);

            instance.getConfig().set("materials", this.remainingItems);

        } else {
            instance.getConfig().getList("materials").forEach(material -> {
                this.remainingItems.add(String.valueOf(material));
            });
        }


        this.currentItemInt = instance.getConfig().getInt("currentitem");
        this.material = Material.matchMaterial(this.remainingItems.get(this.currentItemInt - 1));
    }

    public void getItem() {
        final String itemName = getItemName(material);
        this.currentItemInt++;

        instance.getConfig().set("currentitem", this.currentItemInt);

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            onlinePlayer.playSound(onlinePlayer.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1.0f, 1.0f);
            onlinePlayer.sendMessage(FindAllItems.PREFIX + "§7Das folgende Item wurde registriert: §f" + itemName + " §7es fehlen noch §f" + (this.remainingItems.size() - this.currentItemInt) + " §7Items.");
        }

        generateRandomItem();

        instance.getBossBarUtils().updateBossbar();
    }

    public void generateRandomItem() {
        if (this.currentItemInt == this.remainingItems.size()) {
            for (Player onlinePlayer: Bukkit.getOnlinePlayers()) {
                onlinePlayer.playSound(onlinePlayer.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
                onlinePlayer.sendMessage(FindAllItems.PREFIX + "Alle Items wurden gefunden, ihr habt die Challenge erfolgreich geschafft.");
                onlinePlayer.setGameMode(GameMode.SPECTATOR);
            }
            return;
        }

        Material newMaterial = Material.matchMaterial(this.remainingItems.get(this.currentItemInt - 1));
        Bukkit.getConsoleSender().sendMessage("New Item: " + this.currentItemInt);
        this.material = newMaterial;
    }

    public String getItemName(Material material) {
        String capitalizedName = material.name().substring(0, 1).toUpperCase() + material.name().substring(1).toLowerCase();
        return capitalizedName.replace("_", " ");
    }

    public void checkForItem() {
        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)instance, () -> {
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                if (onlinePlayer.getInventory().contains(this.material))
                    getItem();
            }
            checkForItem();
        }, 20L);
    }
}
