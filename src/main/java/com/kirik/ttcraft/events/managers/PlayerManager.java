package com.kirik.ttcraft.events.managers;

import java.util.List;

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

	public int getLevel(Player player) {
		int playerLevel = new PlayerConfiguration(player.getUniqueId()).getPlayerConfig().getInt("level");
		return playerLevel; // will auto-return 0 if "null"
	}

	public String getNickname(Player player) {
		String playerNickname = new PlayerConfiguration(player.getUniqueId()).getPlayerConfig().getString("nickname");
		if (playerNickname == null) {
			return player.getName();
		}
		return playerNickname;
	}

	public Location getHome(Player player) {
		Location home = new PlayerConfiguration(player.getUniqueId()).getPlayerConfig().getLocation("home");
		if (home == null) {
			home = plugin.getServer().getWorld("world").getSpawnLocation();
		}
		return home;
	}

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
		ItemStack[] inv = (ItemStack[]) new PlayerConfiguration(player.getUniqueId()).getPlayerConfig()
				.get(worldName + ".echest");
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

	public void setHome(Player player, Location home) {
		PlayerConfiguration playerConfigFile = new PlayerConfiguration(player.getUniqueId());
		FileConfiguration playerConfig = playerConfigFile.getPlayerConfig();
		playerConfig.set("home", home);
		playerConfigFile.savePlayerConfig();
	}

	public void setLastLocation(Player player, Location lastLoc) {
		PlayerConfiguration playerConfigFile = new PlayerConfiguration(player.getUniqueId());
		FileConfiguration playerConfig = playerConfigFile.getPlayerConfig();
		playerConfig.set("lastLoc", lastLoc);
		playerConfigFile.savePlayerConfig();
	}

	public void setLastWorldLoc(Player player, String worldName, Location lastLoc) {
		PlayerConfiguration playerConfigFile = new PlayerConfiguration(player.getUniqueId());
		FileConfiguration playerConfig = playerConfigFile.getPlayerConfig();
		playerConfig.set(worldName, lastLoc);
		playerConfigFile.savePlayerConfig();
	}

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

	public void sendException(Player player, TTCraftCommandException exception) {
		player.sendMessage("\u00a7" + exception.getColor() + "[TT] \u00a7f" + exception.getMessage());
	}

	public void sendException(CommandSender sender, TTCraftCommandException exception) {
		sender.sendMessage("\u00a7" + exception.getColor() + "[TT] \u00a7f" + exception.getMessage());
	}

	public void savePlayerConfig(Player player) {
		PlayerConfiguration playerConfigFile = new PlayerConfiguration(player.getUniqueId());
		playerConfigFile.savePlayerConfig();
	}

	public String getWorld(Player player) {
		return player.getWorld().getName();
	}
}
