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
		player.teleport(home);

		playerManager.sendMessage(player, "Teleported home");
		return true;
	}
}