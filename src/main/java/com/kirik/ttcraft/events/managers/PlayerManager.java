package com.kirik.ttcraft.events.managers;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.kirik.ttcraft.main.PlayerConfiguration;
import com.kirik.ttcraft.main.TTCraft;
import com.kirik.ttcraft.main.util.TTCraftCommandException;

public class PlayerManager {

	private final TTCraft plugin;

	public PlayerManager(TTCraft instance) {
		this.plugin = instance;
	}

	/**
	 * getLevel - get level from player file
	 * @param player - UUID for player config file
	 * @return playerLevel - level of the player from config file (default 0) (mod 1) (admin 2)
	 */
	public int getLevel(Player player) {
		int playerLevel = new PlayerConfiguration(player.getUniqueId()).getPlayerConfig().getInt("level");
		return playerLevel; // will auto-return 0 if "null"
	}

	/**
	 * getNickname - get nickname of player from player file
	 * @param player - UUID for player config file
	 * @return playerNickname - +leading white color code for following text
	 */
	public String getNickname(Player player) {
		String playerNickname = new PlayerConfiguration(player.getUniqueId()).getPlayerConfig().getString("nickname");
		if (playerNickname == null || playerNickname.equals("")) {
			playerNickname = getLevelColor(player) + player.getName();
		}
		return playerNickname + "\u00a7f";
	}

	/**
	 * getHome - get location of player home
	 * @param player - UUID for player config file
	 * @return home - Location
	 */
	public Location getHome(Player player) {
		Location home = new PlayerConfiguration(player.getUniqueId()).getPlayerConfig().getLocation("home");
		if (home == null) {
			home = plugin.getServer().getWorld("world").getSpawnLocation();
		}
		return home;
	}

	/**
	 * getLastLocation - get location player was at BEFORE using a teleport command
	 * @param player
	 * @return lastLocation - Location
	 */
	public Location getLastLocation(Player player) {
		Location lastLoc = new PlayerConfiguration(player.getUniqueId()).getPlayerConfig().getLocation("lastLoc");
		if (lastLoc == null) {
			lastLoc = plugin.getConfig().getLocation("world");
			lastLoc.setWorld(plugin.getServer().getWorld("world"));
		}
		return lastLoc;
	}

	public Location getLastWorldLocation(Player player, String worldName) {
		Location lastLoc = new PlayerConfiguration(player.getUniqueId()).getPlayerConfig().getLocation(worldName);
		if (lastLoc == null) {
			lastLoc = plugin.getServer().getWorld(worldName).getSpawnLocation();
		}
		return lastLoc;
	}

	public String getLevelColor(Player player) {
		int level = getLevel(player);
		switch(level) {
			case 0:
				return "\u00a70";
			case 1:
				return "\u00a77";
			case 2:
				return "\u00a7b";
			case 3:
				return "\u00a75";
			default:
				return "\u00a7f";
		}
	}

	public boolean hasVisitedEnd(Player player) {
		boolean visitedEnd = new PlayerConfiguration(player.getUniqueId()).getPlayerConfig().getBoolean("visitedEnd");
		return visitedEnd;
	}

	public boolean isAFK(Player player) {
		boolean afkFlag = new PlayerConfiguration(player.getUniqueId()).getPlayerConfig().getBoolean("isAFK");
		return afkFlag;
	}

	public ItemStack[] getWorldInventory(Player player, String worldName) {
		ItemStack[] inv = new PlayerConfiguration(player.getUniqueId()).getPlayerConfig()
				.getList(worldName + ".inventory").toArray(new ItemStack[0]);

		if (inv == null) {
			setWorldInventory(player, worldName);
			inv = getWorldInventory(player, worldName);
		}
		return inv;
	}

	public ItemStack[] getWorldEnderChest(Player player, String worldName) {
		ItemStack[] inv = new PlayerConfiguration(player.getUniqueId()).getPlayerConfig()
				.getList(worldName + ".echest").toArray(new ItemStack[0]);

		if (inv == null) {
			setWorldInventory(player, worldName);
			inv = getWorldInventory(player, worldName);
		}
		return inv;
	}

	public void setLevel(Player player, int level) {
		PlayerConfiguration playerConfigFile = new PlayerConfiguration(player.getUniqueId());
		FileConfiguration playerConfig = playerConfigFile.getPlayerConfig();
		playerConfig.set("level", level);
		playerConfigFile.savePlayerConfig();
	}

	public void setNickname(Player player, String nickname) {
		PlayerConfiguration playerConfigFile = new PlayerConfiguration(player.getUniqueId());
		FileConfiguration playerConfig = playerConfigFile.getPlayerConfig();
		playerConfig.set("nickname", nickname);
		player.setPlayerListName(nickname);
		player.setDisplayName(nickname);
		playerConfigFile.savePlayerConfig();
	}

	public void resetHome(Player player) {
		setHome(player, player.getLocation());
	}

	private void setHome(Player player, Location home) {
		PlayerConfiguration playerConfigFile = new PlayerConfiguration(player.getUniqueId());
		FileConfiguration playerConfig = playerConfigFile.getPlayerConfig();
		playerConfig.set("home", home);
		playerConfigFile.savePlayerConfig();
	}

	public void resetLastLocation(Player player) {
		setLastLocation(player, player.getLocation());
	}

	private void setLastLocation(Player player, Location lastLoc) {
		PlayerConfiguration playerConfigFile = new PlayerConfiguration(player.getUniqueId());
		FileConfiguration playerConfig = playerConfigFile.getPlayerConfig();
		playerConfig.set("lastLoc", lastLoc);
		playerConfigFile.savePlayerConfig();
	}

	public void setVisitedEnd(Player player, boolean flag) {
		PlayerConfiguration playerConfigFile = new PlayerConfiguration(player.getUniqueId());
		FileConfiguration playerConfig = playerConfigFile.getPlayerConfig();
		playerConfig.set("visitedEnd", flag);
		playerConfigFile.savePlayerConfig();
	}

	public void setAFK(Player player, boolean flag) {
		PlayerConfiguration pcf = new PlayerConfiguration(player.getUniqueId());
		FileConfiguration cf = pcf.getPlayerConfig();
		cf.set("isAFK", flag);
		pcf.savePlayerConfig();
	}

	/* private void setLastWorldLoc(Player player, String worldName, Location lastLoc) {
		PlayerConfiguration playerConfigFile = new PlayerConfiguration(player.getUniqueId());
		FileConfiguration playerConfig = playerConfigFile.getPlayerConfig();
		playerConfig.set(worldName, lastLoc);
		playerConfigFile.savePlayerConfig();
	} */

	public void setWorldInventory(Player player, String worldName) {
		PlayerConfiguration playerConfigFile = new PlayerConfiguration(player.getUniqueId());
		FileConfiguration playerConfig = playerConfigFile.getPlayerConfig();
		playerConfig.set(worldName + ".inventory", player.getInventory().getContents());
		playerConfig.set(worldName + ".echest", player.getEnderChest().getContents());
		playerConfigFile.savePlayerConfig();
	}

	public void sendMessage(Player player, String msg) {
		player.sendMessage("\u00a75[TT] \u00a7f" + msg);
	}

	public void sendMessage(CommandSender sender, String msg) {
		sender.sendMessage("\u00a75[TT] \u00a7f" + msg);
	}

	public void sendException(Player player, TTCraftCommandException e) {
		player.sendMessage("\u00a7" + e.getColor() + "[TT] \u00a7f" + e.getMessage());
	}

	public void sendException(CommandSender sender, TTCraftCommandException e) {
		sender.sendMessage("\u00a7" + e.getColor() + "[TT] \u00a7f" + e.getMessage());
	}

	public void savePlayerConfig(Player player) {
		PlayerConfiguration playerConfigFile = new PlayerConfiguration(player.getUniqueId());
		playerConfigFile.savePlayerConfig();
	}

	public String getWorld(Player player) {
		return player.getWorld().getName();
	}
}
