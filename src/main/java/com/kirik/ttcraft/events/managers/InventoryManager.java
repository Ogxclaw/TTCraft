package com.kirik.ttcraft.events.managers;

import java.util.HashMap;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.kirik.ttcraft.main.PlayerConfiguration;
import com.kirik.ttcraft.main.TTCraft;

public class InventoryManager {

	private final TTCraft plugin;

	public InventoryManager(TTCraft plugin) {
		this.plugin = plugin;
	}

	public HashMap<String, ItemStack[]> getInventory(Player player, String world) {
		FileConfiguration playerConfig = new PlayerConfiguration(player.getUniqueId()).getPlayerConfig();
		HashMap<String, ItemStack[]> inv = new HashMap<String, ItemStack[]>();
		ItemStack[] armor = (ItemStack[]) playerConfig.get(world + ".inventory.armor");
		inv.put("armor", armor);
		return inv;
	}

	public void setInventory(Player player, String world) {

		PlayerConfiguration playerConfigFile = new PlayerConfiguration(player.getUniqueId());
		FileConfiguration playerConfig = playerConfigFile.getPlayerConfig();

		ItemStack[] armor = player.getInventory().getArmorContents();

		playerConfig.set(world + ".inventory.armor", armor);

		playerConfigFile.savePlayerConfig();

	}

}
