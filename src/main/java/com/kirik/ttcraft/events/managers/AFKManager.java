package com.kirik.ttcraft.events.managers;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.kirik.ttcraft.main.TTCraft;

public class AFKManager {
    
    private final TTCraft instance;

    private static final HashMap<Player, Long> lastMovement = new HashMap<Player, Long>();
    private static final long MOVEMENT_THRESHOLD = 600000; // 10 minutes(?)

    public AFKManager(TTCraft instance) {
        this.instance = instance;
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
            if(isAFK(entry.getKey())){
                Bukkit.getScheduler().runTask(instance, new Runnable() {
                    public void run() {
                        entry.getKey().kickPlayer("AFK");
                    }
                });
            }
            // TODO: definitely need verbose mode
            // instance.sendConsoleMsg("Checking " + entry.getKey().getName() + ": " + (System.currentTimeMillis() - lastMovement.get(entry.getKey())));
        }
    }
    
}
