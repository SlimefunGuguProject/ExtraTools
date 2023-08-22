package me.sfiguz7.extratools;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import me.sfiguz7.extratools.implementation.machines.CobblestoneGenerator;
import me.sfiguz7.extratools.implementation.machines.ConcreteFactory;
import me.sfiguz7.extratools.implementation.machines.ElectricComposter;
import me.sfiguz7.extratools.implementation.machines.GoldTransmuter;
import me.sfiguz7.extratools.implementation.machines.Pulverizer;
import me.sfiguz7.extratools.implementation.machines.Vaporizer;
import me.sfiguz7.extratools.implementation.tools.Hammer;
import me.sfiguz7.extratools.lists.ETItems;
import net.guizhanss.guizhanlibplugin.updater.GuizhanUpdater;
import org.bstats.bukkit.Metrics;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Level;

public class ExtraTools extends JavaPlugin implements SlimefunAddon {

    public static ExtraTools instance;
    private int researchId = 4100;

    @Override
    public void onEnable() {

        instance = this;

        if (!getServer().getPluginManager().isPluginEnabled("GuizhanLibPlugin")) {
            getLogger().log(Level.SEVERE, "本插件需要 鬼斩前置库插件(GuizhanLibPlugin) 才能运行!");
            getLogger().log(Level.SEVERE, "从此处下载: https://50L.cc/gzlib");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        if (!new File(getDataFolder(), "config.yml").exists()) {
            saveDefaultConfig();
        }

        if (getConfig().getBoolean("options.auto-update") && getDescription().getVersion().startsWith("Build")) {
            GuizhanUpdater.start(this, getFile(), "SlimefunGuguProject", "ExtraTools", "master");
        }

        int bStatsId = 6945;
        new Metrics(this, bStatsId);

        new Hammer().register(this);
        new Research(new NamespacedKey(this, "HAMMER"),
            ++researchId, "锤子", 3)
            .addItems(ETItems.HAMMER).register();

        new GoldTransmuter().register(this);
        new Research(new NamespacedKey(this, "GOLD_TRANSMUTER"),
            ++researchId, "黄金转化器", 12)
            .addItems(ETItems.GOLD_TRANSMUTER).register();

        new ElectricComposter(ElectricComposter.Tier.ONE) {

            @Override
            public int getEnergyConsumption() {
                return 9;
            }

            @Override
            public int getSpeed() {
                return 1;
            }

        }.register(this);
        new Research(new NamespacedKey(this, "ELECTRIC_COMPOSTER"),
            ++researchId, "电动堆肥机", 18)
            .addItems(ETItems.ELECTRIC_COMPOSTER).register();

        new ElectricComposter(ElectricComposter.Tier.TWO) {

            @Override
            public int getEnergyConsumption() {
                return 25;
            }

            @Override
            public int getSpeed() {
                return 4;
            }

        }.register(this);
        new Research(new NamespacedKey(this, "ELECTRIC_COMPOSTER_2"),
            ++researchId, "电动堆肥机 II", 18)
            .addItems(ETItems.ELECTRIC_COMPOSTER_2).register();

        new CobblestoneGenerator().register(this);
        new Research(new NamespacedKey(this, "COBBLESTONE_GENERATOR"),
            ++researchId, "圆石生成器", 40)
            .addItems(ETItems.COBBLESTONE_GENERATOR).register();

        new Vaporizer().register(this);
        new Research(new NamespacedKey(this, "VAPORIZER"),
            ++researchId, "蒸馏器", 18)
            .addItems(ETItems.VAPORIZER).register();

        new ConcreteFactory().register(this);
        new Research(new NamespacedKey(this, "CONCRETE_FACTORY"),
            ++researchId, "混凝土搅拌机", 12)
            .addItems(ETItems.CONCRETE_FACTORY).register();

        new Pulverizer().register(this);
        new Research(new NamespacedKey(this, "PULVERIZER"),
            ++researchId, "方块过筛机", 18)
            .addItems(ETItems.PULVERIZER).register();

    }

    @Override
    public void onDisable() {
        // Logic for disabling the plugin...
    }

    @Override
    public String getBugTrackerURL() {
        return "https://github.com/SlimefunGuguProject/ExtraTools/issues";
    }

    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    public static ExtraTools getInstance() {
        return instance;
    }

}
