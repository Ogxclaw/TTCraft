package com.kirik.ttcraft.events.managers;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.kirik.ttcraft.main.TTCraft;

public class AFKManager {
    
    private final TTCraft plugin;

    private static final HashMap<Player, Long> lastMovement = new HashMap<Player, Long>();
    private static final long MOVEMENT_THRESHOLD = 600000; // 10 minutes(?)

    public AFKManager(TTCraft plugin) {
        this.plugin = plugin;
    }

    public static void playerJoined(Player player) {
        lastMovement.put(player, System.currentTimeMillis());
    }

    public static void playerQuit(Player player) {
        lastMovement.remove(player);
    }

    public static void playerMove(Player player) {
        // long lastMovementTime = lastMovement.get(e.getPlayer());
        
        if(!player.isInWater() && !player.isSwimming()) { // TODO: afk pools, this is a bad solution
            lastMovement.put(player, System.currentTimeMillis());
        }
    }

    public static boolean isAFK(Player player) {

        if(lastMovement.containsKey(player)) {
            long timeElapsed = System.currentTimeMillis() - lastMovement.get(player);

            if(timeElapsed >= MOVEMENT_THRESHOLD) { 
                return true;
            }
        }else{
            lastMovement.put(player, System.currentTimeMillis());
        }
        return false;
    }

    public void checkPlayers() {
        for(Map.Entry<Player, Long> entry : lastMovement.entrySet()) {
            if(plugin.playerManager.getLevel(entry.getKey()) >= 2)
                continue;
            if(isAFK(entry.getKey())){
                Bukkit.getScheduler().runTask(plugin, new Runnable() {
                    public void run() {
                        entry.getKey().kickPlayer("[TT] Kicked by CONSOLE. Reason: AFK");
                        plugin.sendServerMessage("CONSOLE kicked " + plugin.playerManager.getNickname(entry.getKey()) + " (AFK)");
                    }
                });
            }
        }
    }
    
}
