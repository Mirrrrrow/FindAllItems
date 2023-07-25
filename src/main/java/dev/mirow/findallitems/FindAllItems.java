package dev.mirow.findallitems;

import dev.mirow.findallitems.challenge.RandomItem;
import dev.mirow.findallitems.commands.SkipItem;
import dev.mirow.findallitems.listener.JoinListener;
import dev.mirow.findallitems.listener.QuitListener;
import dev.mirow.findallitems.utils.BossbarUtils;
import lombok.Getter;
import org.bukkit.boss.BossBar;
import org.bukkit.plugin.java.JavaPlugin;

public final class FindAllItems extends JavaPlugin {

    @Getter
    public FindAllItems instance;

    @Getter
    public BossbarUtils bossBarUtils;

    @Getter
    public RandomItem randomItem;

    public static final String PREFIX = "§8| §f§lFindAllItems §8» §7";
    @Override
    public void onEnable() {
        instance = this;
        instance.saveResource("config.yml", false);
        instance.getConfig().options().copyDefaults(true);
        instance.saveConfig();

        randomItem = new RandomItem(this);

        randomItem.checkForItem();

        bossBarUtils = new BossbarUtils(this);

        bossBarUtils.createBossBar();



        new JoinListener(this);
        new QuitListener(this);

        new SkipItem(this);

    }

    @Override
    public void onDisable() {
        instance.saveConfig();
    }
}
