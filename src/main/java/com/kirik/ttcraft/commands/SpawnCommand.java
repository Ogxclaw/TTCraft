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
		Location lastLoc = player.getLocation();

		if (player.getWorld().getName().startsWith("world"))
			plugin.getConfig().getLocation("world");
		else if (player.getWorld().getName().startsWith("creative"))
			plugin.getServer().getWorld("creative").getSpawnLocation();

		playerManager.setLastLocation(player, lastLoc);
		player.teleport(worldSpawn);

		playerManager.sendMessage(player, "Teleported to spawn");
		return true;
	}
}
