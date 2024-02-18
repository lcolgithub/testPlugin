package org.lcolgithub.testplugin;

import org.bukkit.plugin.java.JavaPlugin;
import org.lcolgithub.testplugin.events.blockbreakevents.BlockBreakListener;

public final class TestPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
