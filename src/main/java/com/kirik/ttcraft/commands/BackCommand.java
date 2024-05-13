package com.kirik.ttcraft.commands;

import com.kirik.ttcraft.commands.ICommand.*;
import com.kirik.ttcraft.main.util.TTCraftCommandException;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

@Name("back")
@Level(2)
public class BackCommand extends ICommand {

	@Override
	public boolean asPlayer(Player player, Command command, String s, String[] args) throws TTCraftCommandException {

		if (!checkPermissions(player))
			return true;

		Location lastLoc = playerManager.getLastLocation(player);

		player.teleport(lastLoc);
		playerManager.sendMessage(player, "Sent back to last location");
		return true;
	}
}