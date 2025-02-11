package com.kirik.ttcraft.main;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.kirik.ttcraft.commands.ICommand;
import com.kirik.ttcraft.events.listeners.AFKListener;
import com.kirik.ttcraft.events.listeners.PlayerListener;
import com.kirik.ttcraft.events.listeners.WorldListener;
import com.kirik.ttcraft.events.managers.AFKManager;
import com.kirik.ttcraft.events.managers.PlayerManager;
import com.kirik.ttcraft.events.managers.SkillManager;
import com.kirik.ttcraft.events.managers.WorldManager;
import com.kirik.ttcraft.events.tasks.AFKTask;

/**
 * TTCraft is a custom plugin 
 * for the TT Minecraft server
 * 
 * @author Kazzius
 */

public class TTCraft extends JavaPlugin {

	public static TTCraft instance;

	public AFKManager afkManager;
	public PlayerManager playerManager;
	public SkillManager skillManager;

	public WorldManager worldManager;

	public TTCraft() {
		instance = this;
	}

	@Override
	public void onEnable() {

		// load server config
		saveResource("config.yml", false);
		saveDefaultConfig();

		// load MOTD (idk if this even works)
		if (getMOTD() == null) {
			setDefaultMOTD();
			sendConsoleMsg("MOTD was NULL, loading default!");
		}
		sendConsoleMsg("Config Defaults Loaded");

		// load worldManager (WIP)
		worldManager = new WorldManager(this);
		getServer().getPluginManager().registerEvents(new WorldListener(this, playerManager, worldManager), this);

		// load playerManager & listener
		playerManager = new PlayerManager(this);
		getServer().getPluginManager().registerEvents(new PlayerListener(this, playerManager, worldManager), this);
		
		// load skillManager & listener (WIP, disabled)
		/* skillManager = new SkillManager(this);
		getServer().getPluginManager().registerEvents(new SkillListener(this, playerManager, skillManager), this); */

		// load AFK tracker, run async
		afkManager = new AFKManager(this, playerManager);
		getServer().getPluginManager().registerEvents(new AFKListener(this, this.afkManager), this);
		Bukkit.getScheduler().runTaskTimerAsynchronously(this, new AFKTask(this, this.afkManager), 0L, 1200L); // ping every 1 minute
		sendConsoleMsg("Listeners and Managers Loaded");

		// load command system
		ICommand.registerCommands();
		StateContainer.loadAll();

		sendConsoleMsg("Commands Loaded");
		sendConsoleMsg("Plugin enabled");
	}

	@Override
	public void onDisable() {
		sendConsoleMsg("Plugin disabled");
		saveConfig();
	}

	public String getMOTD() {
		return getConfig().getString("MOTD");
	}

	public void setDefaultMOTD() {
		getConfig().set("MOTD", "Welcome back to $5123$fSMP!");
		saveConfig();
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
		// msg = "[TT] " + msg;
		log(msg);
	}

	public void sendConsoleError(String msg) {
		msg = "[ERR]: " + msg;
		log(msg);
	}
}