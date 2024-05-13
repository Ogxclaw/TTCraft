package com.kirik.ttcraft.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.kirik.ttcraft.commands.ICommand.Level;
import com.kirik.ttcraft.commands.ICommand.Name;
import com.kirik.ttcraft.main.util.TTCraftCommandException;

@Name("goto")
@Level(2)
public class GoToCommand extends ICommand {

	@Override
	public boolean asPlayer(Player player, Command command, String s, String[] args) throws TTCraftCommandException {

		if (args.length > 0) {

			if (!checkPermissions(player))
				return true;

			Location worldLoc;
			ItemStack[] worldInv;
			ItemStack[] worldEChest;

			if (args[0].equalsIgnoreCase("creative")) {

				playerManager.setLastWorldLoc(player, "world", player.getLocation());
				// plugin.inventoryManager.setInventory(player, "world");
				playerManager.setWorldInventory(player, "world");

				worldLoc = playerManager.getLastWorldLocation(player, "creative");
				worldInv = playerManager.getWorldInventory(player, "creative");
				// worldEChest = playerManager.getWorldEnderChest(player, "creative");

				playerManager.sendMessage(player, "Teleported to \u00a79Creative");

			} else if (args[0].equalsIgnoreCase("survival")) {

				playerManager.setLastWorldLoc(player, "creative", player.getLocation());
				// plugin.inventoryManager.setInventory(player, "world");
				playerManager.setWorldInventory(player, "creative");

				worldLoc = playerManager.getLastWorldLocation(player, "world");
				worldInv = playerManager.getWorldInventory(player, "world");
				// worldEChest = playerManager.getWorldEnderChest(player, "world");

				playerManager.sendMessage(player, "Teleported to \u00a7cSurvival");

			} else {
				playerManager.sendMessage(player,
						"Please specify world [\u00a7csurvival\u00a7f|\u00a79creative\u00a7f]");
				return true;
			}

			player.teleport(worldLoc);

			player.getInventory().setContents(worldInv);

			// player.getInventory().setArmorContents(plugin.inventoryManager.getInventory(player,
			// "world").get("armor"));
			return true;
		} else
			return false;
	}
}