package com.kirik.ttcraft.commands;

import com.kirik.ttcraft.commands.ICommand.*;
import com.kirik.ttcraft.main.util.TTCraftCommandException;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

@Name("home")
@Level(0)
public class HomeCommand extends ICommand {

	@Override
	public boolean asPlayer(Player player, Command command, String s, String[] args) throws TTCraftCommandException {

		Location home = playerManager.getHome(player);
		Location lastLoc = player.getLocation();

		playerManager.setLastLocation(player, lastLoc);

		playerManager.sendMessage(player, "Please wait 3 seconds for teleportation");
		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

			public void run() {
				player.teleport(home);
			}

		}, 60L);

		playerManager.sendMessage(player, "Teleported home");
		return true;
	}
}