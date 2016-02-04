package com.github.tckz916.jumppad;

import com.github.tckz916.jumppad.listener.PlayerListener;
import lombok.Getter;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by tckz916 on 2016/02/05
 */
public class JumpPad extends JavaPlugin {

    @Getter
    private static JumpPad instance;

    private PluginManager pluginManager = this.getServer().getPluginManager();

    @Override
    public void onEnable() {
        super.onEnable();
        pluginManager.registerEvents(new PlayerListener(), this);

        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
