package com.kirik.ttcraft.events.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.kirik.ttcraft.events.managers.AFKManager;
import com.kirik.ttcraft.main.TTCraft;

public class AFKListener implements Listener {

    private final TTCraft plugin;
    private final AFKManager afkManager;
    

    public AFKListener(TTCraft plugin, AFKManager manager) {
        this.plugin = plugin;
        afkManager = manager;
    }

    //FIXME: 
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        afkManager.playerJoined(e.getPlayer());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        afkManager.playerQuit(e.getPlayer());
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        afkManager.playerMove(e.getPlayer());
    }
    
}
