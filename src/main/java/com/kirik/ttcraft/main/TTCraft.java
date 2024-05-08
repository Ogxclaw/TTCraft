package com.kirik.ttcraft.main;

import com.kirik.ttcraft.commands.ICommand;
import com.kirik.ttcraft.events.listeners.AFKListener;
import com.kirik.ttcraft.events.listeners.PlayerListener;
import com.kirik.ttcraft.events.managers.AFKManager;
import com.kirik.ttcraft.events.managers.PlayerManager;
import com.kirik.ttcraft.events.tasks.AFKTask;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

/**
 * TTCraft is a custom plugin for basic
 * commands for the TT Minecraft server
 * @author Kazzius
 */

public class TTCraft extends JavaPlugin {

    public static TTCraft instance;
    private AFKManager afkManager;
    private PlayerManager playerManager;

    // Dependencies
    /*private ProtocolManager protocolManager;*/
    /*private HologramManager hologramManager; // Took code from https://github.com/sainttx/Holograms and updated to 1.18.1
    private HologramEntityController hologramController;
    private Runnable updateTask = new HologramUpdateTask(this);*/

    /*private NameTagManager nameTagManager;
    private Runnable updateTask = new NameTagUpdateTask(this);*/

    // public HashMap<UUID, TTPlayer> onlinePlayers = new HashMap<>();

    public TTCraft() {
        instance = this;
    }

    @Override
    public void onEnable(){
        /*protocolManager = ProtocolLibrary.getProtocolManager();*/
        /*hologramManager = HologramLibrary.getHologramManager();*/
        // sendConsoleMsg("Dependencies Loaded.");

        saveResource("config.yml", false);
        saveDefaultConfig();

        if(getMOTD() == null) {
            setDefaultMOTD();
            sendConsoleMsg("MOTD was NULL, loading default!");
        }

        /*if(getWorldSpawn() == null) {
            setDefaultWorldSpawn();
            sendConsoleMsg("WARNING: No spawn location found, setting to default...");
        }else{
            getServer().getWorld("world").setSpawnLocation(getWorldSpawn());
            saveConfig();
        }
        if(getMOTD() == null) {
            setDefaultMOTD();
            sendConsoleMsg("WARNING: No MOTD, assigning default...");
        }*/
        sendConsoleMsg("Config Defaults Loaded.");

        playerManager = new PlayerManager(this);
        afkManager = new AFKManager(this);
        getServer().getPluginManager().registerEvents(new AFKListener(this, this.afkManager), this);
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, new AFKTask(this, this.afkManager), 0L, 1200L); // 1 minute (?)
        sendConsoleMsg("AFKManager Loaded");

        ICommand.registerCommands();
        StateContainer.loadAll();
        sendConsoleMsg("Commands Loaded.");

        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);

        sendConsoleMsg("Listeners Loaded.");

        sendConsoleMsg("Plugin enabled");
    }

    @Override
    public void onDisable() {
        /*log("Plugin disabled");*/
        sendConsoleMsg("Plugin disabled");
        saveConfig();
        /*saveDefaultConfig();*/ // dont think this is needed
    }

    public String getMOTD() {
        return getConfig().getString("MOTD");
    }

    public void setDefaultMOTD() {
        getConfig().set("MOTD", "Welcome back to $5123$fSMP!");
        saveConfig();
    }

    /*public ProtocolManager getProtocolManager() {
        return protocolManager;
    }

    public NameTagManager getNameTagManager() {
        return nameTagManager;
    }*/

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public void log(String msg) {
        log(Level.INFO, msg);
    }

    public void log(Level level, String msg) {
        getLogger().log(level, msg);
    }

    public void sendServerMessage(String msg) {
        msg = "\u00a75[TT]\u00a7f " + msg;
        this.getServer().broadcastMessage(msg);
    }

    public void sendConsoleMsg(String msg) {
        msg = "[TT] " + msg;
        log(msg);
    }

    public void sendConsoleError(String msg){
        msg = "[ERR][TT]: " + msg;
        log(msg);
    }

    public void sendPlayerMessage(Player target, String msg) {
        target.sendMessage("\u00a75[TT] \u00a7f" + msg);
    }
}