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

		Location worldSpawn = player.getWorld().getSpawnLocation();
		worldSpawn.setWorld(plugin.getServer().getWorld("world"));
		Location lastLoc = player.getLocation();

		/*
		 * if (player.getWorld().getName().startsWith("world"))
		 * plugin.getConfig().getLocation("world");
		 * else if (player.getWorld().getName().startsWith("creative"))
		 * plugin.getServer().getWorld("creative").getSpawnLocation();
		 */

		playerManager.setLastLocation(player, lastLoc);

		playerManager.sendMessage(player, "Please wait 3 seconds for teleportation");
		player.teleport(worldSpawn);

		playerManager.sendMessage(player, "Teleported to spawn");
		return true;
	}
}
