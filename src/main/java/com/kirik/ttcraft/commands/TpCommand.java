package com.kirik.ttcraft.commands;

import com.kirik.ttcraft.commands.ICommand.*;
import com.kirik.ttcraft.main.util.PlayerNotFoundException;
import com.kirik.ttcraft.main.util.TTCraftCommandException;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

@Name("tp")
@Level(2)
public class TpCommand extends ICommand {

	@Override
	public boolean asPlayer(Player player, Command command, String s, String[] args) throws TTCraftCommandException {

		if (args.length > 0) {
			Player target = plugin.getServer().getPlayer(args[0]);
			if (target == null) {
				playerManager.sendException(player, new PlayerNotFoundException());
				return true;
			}

			if (!checkPermissions(player, target, true)) {
				playerManager.sendMessage(player, "Please use /tpa [player]");
				return true;
			}

			Location lastLoc = player.getLocation();
			playerManager.setLastLocation(player, lastLoc);

			player.teleport(target);
			plugin.sendServerMessage(
					playerManager.getNickname(player) + " \u00a7fteleported to " + playerManager.getNickname(target));
			return true;
		} else
			return false;
	}
}