package com.kirik.ttcraft.commands;

import com.kirik.ttcraft.commands.ICommand.*;
import com.kirik.ttcraft.main.util.PlayerNotFoundException;
import com.kirik.ttcraft.main.util.TTCraftCommandException;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

@Name("summon")
@Level(2)
public class SummonCommand extends ICommand {

	@Override
	public boolean asPlayer(Player player, Command command, String s, String[] args) throws TTCraftCommandException {

		Player target = plugin.getServer().getPlayer(args[0]);
		if (target == null) {
			playerManager.sendException(player, new PlayerNotFoundException());
			return true;
		}

		if (!checkPermissions(player, target, false))
			return true;

		Location targetLastLoc = target.getLocation();
		playerManager.setLastLocation(target, targetLastLoc);

		target.teleport(player);
		plugin.sendServerMessage(
				playerManager.getNickname(player) + " \u00a7fsummoned " + playerManager.getNickname(target));
		return true;
	}
}