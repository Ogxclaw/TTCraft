package com.kirik.ttcraft.events;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class AFKListener implements Listener {

    private static final HashMap<Player, Long> lastMovement = new HashMap<Player, Long>();
    private static final long MOVEMENT_THRESHOLD =  60000;

    public static void playerJoined(Player player) {
        lastMovement.put(player, System.currentTimeMillis());
    }

    public static void playerQuit(Player player) {
        lastMovement.remove(player);
    }

    public static void playerMoved(Player player) {
        lastMovement.put(player, System.currentTimeMillis());
    }

    public static boolean isAFK(Player player) {
        long timeElapsed = System.currentTimeMillis() - lastMovement.get(player);

        if(timeElapsed >= MOVEMENT_THRESHOLD) { // 1 minute
            return true;
        }
        return false;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        playerJoined(e.getPlayer());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        playerQuit(e.getPlayer());
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        if(!e.getPlayer().isInWater()) {
            
        }
    }
    
}
