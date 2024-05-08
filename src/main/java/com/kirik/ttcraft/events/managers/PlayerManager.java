package com.kirik.ttcraft.events.managers;

import org.bukkit.entity.Player;

import com.kirik.ttcraft.main.PlayerConfiguration;
import com.kirik.ttcraft.main.TTCraft;

public class PlayerManager {

    private final TTCraft instance;

    public PlayerManager(TTCraft instance) {
        this.instance = instance;
    }

    public int getPlayerLevel(Player player) {
        int playerLevel = new PlayerConfiguration(player.getUniqueId()).getPlayerConfig().getInt("level");
        return playerLevel;
    }
    
}
