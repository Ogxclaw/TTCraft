package com.kirik.ttcraft.commands;

import com.kirik.ttcraft.commands.ICommand.*;
import com.kirik.ttcraft.main.util.TTCraftCommandException;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

@Name("home")
@Level(0)
public class HomeCommand extends ICommand {

	@Override
	public boolean asPlayer(Player player, Command command, String s, String[] args) throws TTCraftCommandException {
		playerManager.resetLastLocation(player);

		Location home = playerManager.getHome(player);
		player.teleport(home, TeleportCause.COMMAND);

		playerManager.sendMessage(player, "Teleported home");
		return true;
	}
}