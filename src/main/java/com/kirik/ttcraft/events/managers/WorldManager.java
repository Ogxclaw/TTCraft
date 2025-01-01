package com.kirik.ttcraft.events.managers;

import org.bukkit.Location;
import org.bukkit.World;

import com.kirik.ttcraft.main.TTCraft;

public class WorldManager {

	private final TTCraft plugin;

	public WorldManager(TTCraft instance) {
		plugin = instance;
	}

	public World getOverworld() {
		return plugin.getServer().getWorld("world");
	}

	public World getNether() {
		return plugin.getServer().getWorld("world_nether");
	}

	public World getEnd() {
		return plugin.getServer().getWorld("world_end");
	}

	public Location getServerSpawn() {
		return plugin.getConfig().getLocation("world");
	}



}
