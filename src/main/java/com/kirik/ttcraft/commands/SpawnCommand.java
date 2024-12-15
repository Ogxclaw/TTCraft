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

		playerManager.setLastLocation(player, lastLoc);
		player.teleport(worldSpawn);

		playerManager.sendMessage(player, "Teleported to spawn");
		return true;
	}
}
