package com.kirik.ttcraft.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.kirik.ttcraft.commands.ICommand.*;
import com.kirik.ttcraft.main.util.PlayerNotFoundException;
import com.kirik.ttcraft.main.util.TTCraftCommandException;

@Name("smite")
@Level(2)
public class SmiteCommand extends ICommand {

	@Override
	public boolean run(CommandSender sender, Command command, String s, String[] args) throws TTCraftCommandException {

		// FIXME: index 0 out of bounds for length 0

		if (args.length > 0) {
			String nickname = "CONSOLE";

			Player target = plugin.getServer().getPlayer(args[0]);
			if (target == null) {
				playerManager.sendException(sender, new PlayerNotFoundException());
				return true;
			}

			if (sender instanceof Player) {
				nickname = playerManager.getNickname((Player) sender);

				if (!checkPermissions((Player) sender, target, true))
					return false;
			}

			target.getWorld().strikeLightningEffect(target.getLocation());
			plugin.sendServerMessage(nickname + " \u00a7fsmited " + playerManager.getNickname(target));

			return true;
		} else
			return false;
	}
}
