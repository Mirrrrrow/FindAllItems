package dev.mirow.findallitems.utils;

import dev.mirow.findallitems.FindAllItems;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;

public class BossbarUtils {
    private BossBar bossBar;

    private final FindAllItems instance;

    public BossbarUtils(FindAllItems instance) {
        this.instance = instance;
    }

    public void addPlayer(Player player) {
        this.bossBar.addPlayer(player);
    }

    public void createBossBar() {
        Material currentMaterial = instance.getRandomItem().material;
        String itemName = instance.getRandomItem().getItemName(currentMaterial);
        String itemPos = formatInteger(instance.getRandomItem().currentItemInt);

        this.bossBar = Bukkit.createBossBar("§7Item §8» §f " + itemName + " #" + itemPos, BarColor.WHITE, BarStyle.SOLID);
        this.bossBar.setVisible(true);
    }

    public void updateBossbar() {
        Material currentMaterial = instance.getRandomItem().material;
        String itemName = instance.getRandomItem().getItemName(currentMaterial);
        String itemPos = formatInteger(instance.getRandomItem().currentItemInt);

        this.bossBar.setTitle("§7Item §8» §f " + itemName + " #" + itemPos);
    }

    public String formatInteger(int number) {
        return (new DecimalFormat("0000")).format(number);
    }
}
