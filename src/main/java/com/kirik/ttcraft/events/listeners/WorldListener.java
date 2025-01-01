package com.kirik.ttcraft.events.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

import com.kirik.ttcraft.events.managers.WorldManager;
import com.kirik.ttcraft.main.TTCraft;

public class WorldListener implements Listener {

	private final TTCraft plugin;
	private final WorldManager worldManager;

	public WorldListener(TTCraft instance, WorldManager worldManager) {
		this.plugin = instance;
		this.worldManager = worldManager;
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerSleep(PlayerBedEnterEvent e) {
		
		/* for (Player player : plugin.getServer().getOnlinePlayers()) {
			plugin.getServer().getWorld("world").time
		} */
	}

}
