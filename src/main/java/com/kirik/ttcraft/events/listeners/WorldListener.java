package com.kirik.ttcraft.events.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.kirik.ttcraft.events.managers.PlayerManager;
import com.kirik.ttcraft.events.managers.WorldManager;
import com.kirik.ttcraft.main.TTCraft;

public class WorldListener implements Listener {

	private final TTCraft plugin;
	private final PlayerManager playerManager;
	private final WorldManager worldManager;

	public WorldListener(TTCraft instance, PlayerManager playerManager, WorldManager worldManager) {
		this.plugin = instance;
		this.worldManager = worldManager;
		this.playerManager = playerManager;
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerSleep(PlayerBedEnterEvent e) {
		
		/* for (Player player : plugin.getServer().getOnlinePlayers()) {
			plugin.getServer().getWorld("world").time
		} */
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerInteract(PlayerInteractEvent e) {
		if(plugin.playerManager.getLevel(e.getPlayer()) == 0) {
			// playerManager.sendMessage(e.getPlayer(), "Please use /auth to play. Check GroupMe for instructions!");
			e.setCancelled(true);
		}
	}

}
