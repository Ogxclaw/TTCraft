package com.kirik.ttcraft.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import com.kirik.ttcraft.commands.ICommand.Level;
import com.kirik.ttcraft.commands.ICommand.Name;
import com.kirik.ttcraft.main.util.TTCraftCommandException;

@Name("back")
@Level(2)
public class BackCommand extends ICommand {

	@Override
	public boolean asPlayer(Player player, Command command, String s, String[] args) throws TTCraftCommandException {

		if (!checkPermissions(player))
			return true;

		Location lastLoc = playerManager.getLastLocation(player);
		player.teleport(lastLoc, TeleportCause.COMMAND);
		playerManager.sendMessage(player, "Sent back to last location");
		return true;
	}
}