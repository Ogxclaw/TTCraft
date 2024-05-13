package com.kirik.ttcraft.main;

import com.kirik.ttcraft.commands.ICommand;
import com.kirik.ttcraft.events.listeners.AFKListener;
import com.kirik.ttcraft.events.listeners.PlayerListener;
import com.kirik.ttcraft.events.managers.AFKManager;
import com.kirik.ttcraft.events.managers.InventoryManager;
import com.kirik.ttcraft.events.managers.PlayerManager;
import com.kirik.ttcraft.events.managers.WorldManager;
import com.kirik.ttcraft.events.tasks.AFKTask;

import org.bukkit.Bukkit;
import org.bukkit.World.Environment;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

/**
 * TTCraft is a custom plugin for basic
 * commands for the TT Minecraft server
 * 
 * @author Kazzius
 */

public class TTCraft extends JavaPlugin {

	public static TTCraft instance;
	public AFKManager afkManager;
	public PlayerManager playerManager;
	public WorldManager worldManager;
	public InventoryManager inventoryManager;

	// Dependencies
	/* private ProtocolManager protocolManager; */
	/*
	 * private HologramManager hologramManager; // Took code from
	 * https://github.com/sainttx/Holograms and updated to 1.18.1
	 * private HologramEntityController hologramController;
	 * private Runnable updateTask = new HologramUpdateTask(this);
	 */

	/*
	 * private NameTagManager nameTagManager;
	 * private Runnable updateTask = new NameTagUpdateTask(this);
	 */

	public TTCraft() {
		instance = this;
	}

	@Override
	public void onEnable() {
		/* protocolManager = ProtocolLibrary.getProtocolManager(); */
		/* hologramManager = HologramLibrary.getHologramManager(); */
		// sendConsoleMsg("Dependencies Loaded.");

		saveResource("config.yml", false);
		saveDefaultConfig();

		if (getMOTD() == null) {
			setDefaultMOTD();
			sendConsoleMsg("MOTD was NULL, loading default!");
		}
		sendConsoleMsg("Config Defaults Loaded.");

		if (this.getServer().getWorld("creative") == null) {
			this.sendConsoleMsg("[INFO]: Creative world does not exist, creating...");
			// this.getServer().createWorld(new WorldCreator("creative"), );
			this.getServer()
					.createWorld(new WorldCreator("creative").type(WorldType.FLAT).environment(Environment.NORMAL));
		}

		playerManager = new PlayerManager(this);
		getServer().getPluginManager().registerEvents(new PlayerListener(this, playerManager), this);

		worldManager = new WorldManager(this);
		inventoryManager = new InventoryManager(this);

		afkManager = new AFKManager(this);
		getServer().getPluginManager().registerEvents(new AFKListener(this, this.afkManager), this);
		Bukkit.getScheduler().runTaskTimerAsynchronously(this, new AFKTask(this, this.afkManager), 0L, 1200L); // ping
																												// every
																												// 1
																												// minute
		sendConsoleMsg("Listeners and Managers Loaded");

		ICommand.registerCommands();
		// commandSystem = new CommandSystem(this);
		// commandSystem.registerCommands();
		StateContainer.loadAll();
		sendConsoleMsg("Commands Loaded.");

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

	/*
	 * public ProtocolManager getProtocolManager() {
	 * return protocolManager;
	 * }
	 * 
	 * public NameTagManager getNameTagManager() {
	 * return nameTagManager;
	 * }
	 */

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