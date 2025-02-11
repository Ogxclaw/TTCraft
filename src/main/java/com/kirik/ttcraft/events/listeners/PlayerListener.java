package com.kirik.ttcraft.events.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

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

			player.setGameMode(GameMode.ADVENTURE);
			playerManager.setLevel(player, 0);

			e.setJoinMessage("\u00a72[+] \u00a7f" + player.getName() + " \u00a7ejoined for the first time!");
		}else{
			player.setPlayerListName(nickname);
			player.setDisplayName(nickname);

			e.setJoinMessage("\u00a72[+] " + nickname + " \u00a7ejoined!");
		}

		String[] motd = plugin.getMOTD().replace("$", "\u00a7").split("(nl)");
		for (String motdPiece : motd)
			playerManager.sendMessage(player, motdPiece);

		if(playerManager.getLevel(player) == 0) {
			player.setGameMode(GameMode.ADVENTURE);
			playerManager.sendMessage(player, "Please use /auth [password] to interact!");
		}
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

		e.setFormat("\u00a77" + nickname + ": \u00a7f" + colorMessage);
	}

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		Player player = (Player)e.getEntity();
		playerManager.resetLastLocation(player);
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerMove(PlayerMoveEvent e) {
		Player player = e.getPlayer();

		if(playerManager.isAFK(player)) { // if player is moving while afk, return them
			plugin.sendServerMessage(playerManager.getNickname(player) + " returned from being AFK");
			playerManager.setAFK(player, false);
			player.setPlayerListName(playerManager.getNickname(player));
		}
	}

	/* @EventHandler
	public void onPlayerChangeWorld(PlayerChangedWorldEvent e) {
		Player player = e.getPlayer();

		// if player enters the end, banish them and give them 6 shulker shells
		if (player.getWorld().getName().contains("end")) {

			player.teleport(worldManager.getServerSpawn());
			plugin.sendServerMessage("\u00a7fCONSOLE banished " + playerManager.getNickname(player));

			// if player has not gone through portal before, they get shells
			if(!playerManager.hasVisitedEnd(player)) {
				player.getInventory().addItem(new ItemStack(Material.SHULKER_SHELL, 6));
				playerManager.setVisitedEnd(player, true);
			}
		}
	} */
}
