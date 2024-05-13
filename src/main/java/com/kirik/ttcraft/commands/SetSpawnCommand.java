package com.kirik.ttcraft.commands;

import com.kirik.ttcraft.commands.ICommand.*;
import com.kirik.ttcraft.main.util.TTCraftCommandException;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

@Name("setspawn")
@Level(2)
public class SetSpawnCommand extends ICommand {

	@Override
	public boolean asPlayer(Player player, Command command, String s, String[] args) throws TTCraftCommandException {

		if (!checkPermissions(player))
			return true;

		Location newSpawn = player.getLocation();
		player.getWorld().setSpawnLocation(newSpawn); // redunant, but better than it bugging out
		plugin.getConfig().set(player.getWorld().getName(), newSpawn);
		plugin.saveConfig();

		playerManager.sendMessage(player, "Spawn set");
		return true;
	}
}