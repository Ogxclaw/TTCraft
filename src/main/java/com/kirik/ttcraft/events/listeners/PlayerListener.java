package com.kirik.ttcraft.events.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.kirik.ttcraft.events.managers.PlayerManager;
import com.kirik.ttcraft.main.TTCraft;

public class PlayerListener implements Listener {

	private final TTCraft plugin;
	private final PlayerManager playerManager;

	public PlayerListener(TTCraft plugin, PlayerManager manager) {
		this.plugin = plugin;
		playerManager = manager;
	}

	@EventHandler()
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();

		if(!player.hasPlayedBefore()) { // make sure no first time spawn shenanigans
			Location worldSpawn = player.getWorld().getSpawnLocation();
			player.teleport(worldSpawn);
		}

		String nickname = playerManager.getNickname(player);
		/* int level = playerManager.getLevel(player);
		if (level >= 2) {
			player.p
		} */

		e.setJoinMessage("\u00a72[+] \u00a77" + nickname + " \u00a7ejoined!");
		player.setPlayerListName(nickname);
		player.setDisplayName(nickname);

		String[] motd = plugin.getMOTD().replace("$", "\u00a7").split("(nl)");
		for (String motdPiece : motd)
			playerManager.sendMessage(player, motdPiece);
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		Player player = e.getPlayer();
		playerManager.savePlayerConfig(player);

		String nickname = playerManager.getNickname(player);

		e.setQuitMessage("\u00a74[-] \u00a77" + nickname + " \u00a7edisconnected!");
	}

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		String nickname = playerManager.getNickname(e.getPlayer());
		String colorMessage = e.getMessage().replace("$", "\u00a7");
		e.setFormat("\u00a77" + nickname + "\u00a7f: " + colorMessage);
	}

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		/* if (e.getEntity().getRespawnLocation().getWorld().getName().startsWith("world")) {
			e.getEntity().setGameMode(GameMode.SURVIVAL);
		} */
	}

	@EventHandler
	public void onPlayerChangeWorld(PlayerChangedWorldEvent e) {
		if (e.getPlayer().getWorld().getName().contains("end")) {

			Location targetLastLoc = e.getPlayer().getLocation();
			playerManager.setLastLocation(e.getPlayer(), targetLastLoc);

			e.getPlayer().teleport(plugin.getConfig().getLocation("world"));
			plugin.sendServerMessage("\u00a7fCONSOLE banished " + playerManager.getNickname(e.getPlayer()));
		}
	}

	// This works!! (Skill system)
	/* @EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerBreakBlock(BlockBreakEvent e) {
		if(e.getBlock().getType().name().equalsIgnoreCase("Stone")) {
			e.getPlayer().sendMessage("Break");
		}
	} */
}
