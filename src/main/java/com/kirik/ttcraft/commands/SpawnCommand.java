package com.kirik.ttcraft.commands;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import com.kirik.ttcraft.commands.ICommand.Level;
import com.kirik.ttcraft.commands.ICommand.Name;
import com.kirik.ttcraft.main.util.TTCraftCommandException;

@Name("spawn")
@Level(0)
public class SpawnCommand extends ICommand {

	@Override
	public boolean asPlayer(Player player, Command command, String s, String[] args) throws TTCraftCommandException {

		// Location worldSpawn = plugin.getServer().getWorld("world").getSpawnLocation();
		playerManager.resetLastLocation(player);

		// Technically does nothing
		/* if(player.getWorld() != plugin.getServer().getWorld("world"))
			worldSpawn.setWorld(plugin.getServer().getWorld("world")); */

		player.teleport(worldManager.getServerSpawn());

		playerManager.sendMessage(player, "Teleported to spawn");
		return true;
	}
}
