package com.kirik.ttcraft.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import com.kirik.ttcraft.commands.ICommand.*;
import com.kirik.ttcraft.main.util.TTCraftCommandException;

@Name("spawn")
@Level(0)
public class SpawnCommand extends ICommand {

	@Override
	public boolean asPlayer(Player player, Command command, String s, String[] args) throws TTCraftCommandException {

		Location worldSpawn = plugin.getServer().getWorld("world").getSpawnLocation();
		Location lastLoc = player.getLocation();
		playerManager.setLastLocation(player, lastLoc);

		//TODO: Technically does nothing
		if(player.getWorld() != plugin.getServer().getWorld("world"))
			worldSpawn.setWorld(plugin.getServer().getWorld("world"));

		player.teleport(worldSpawn);

		playerManager.sendMessage(player, "Teleported to spawn");
		return true;
	}
}
