package com.kirik.ttcraft.events.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import com.kirik.ttcraft.events.managers.PlayerManager;
import com.kirik.ttcraft.events.managers.WorldManager;
import com.kirik.ttcraft.main.TTCraft;

/**
 * EventListener for Player Events
 * 
 */
public class PlayerListener implements Listener {

	private final TTCraft plugin;
	private final PlayerManager playerManager;
	private final WorldManager worldManager;

	public PlayerListener(TTCraft plugin, PlayerManager playerManager, WorldManager worldManager) {
		this.plugin = plugin;
		this.playerManager = playerManager;
		this.worldManager = worldManager;
	}

	@EventHandler()
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		String nickname = playerManager.getNickname(player);

		if(!player.hasPlayedBefore()) { // make sure no first time spawn shenanigans + unique welcome msg
			// Location worldSpawn = player.getWorld().getSpawnLocation();
			player.teleport(worldManager.getServerSpawn());

			e.setJoinMessage("\u00a72[+] \u00a77" + player.getName() + " \u00a7ejoined for the first time!");
		}else{
			player.setPlayerListName(nickname);
			player.setDisplayName(nickname);

			e.setJoinMessage("\u00a72[+] \u00a77" + nickname + " \u00a7ejoined!");
		}

		String[] motd = plugin.getMOTD().replace("$", "\u00a7").split("(nl)");
		for (String motdPiece : motd)
			playerManager.sendMessage(player, motdPiece);
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		Player player = e.getPlayer();
		String nickname = playerManager.getNickname(player);

		playerManager.savePlayerConfig(player);

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
		Player player = (Player)e.getEntity();
		playerManager.resetLastLocation(player);
	}

	@EventHandler
	public void onPlayerChangeWorld(PlayerChangedWorldEvent e) {
		//TODO: bans the end, remove BEFORE ender dragon fight (1/5)
		Player player = e.getPlayer();

		// if player enters the end, banish them and give them 6 shulker shells
		if (player.getWorld() == worldManager.getEnd()) {

			player.teleport(worldManager.getServerSpawn());
			plugin.sendServerMessage("\u00a7fCONSOLE banished " + playerManager.getNickname(player));

			// if player has not gone through portal before, they get shells
			if(!playerManager.hasVisitedEnd(player)) {
				player.getInventory().addItem(new ItemStack(Material.SHULKER_SHELL, 6));
				playerManager.setVisitedEnd(player, true);
			}
		}
	}
}
