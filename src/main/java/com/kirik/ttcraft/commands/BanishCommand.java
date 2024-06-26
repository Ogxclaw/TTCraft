package com.kirik.ttcraft.commands;

import com.kirik.ttcraft.commands.ICommand.*;
import com.kirik.ttcraft.main.util.PlayerNotFoundException;
import com.kirik.ttcraft.main.util.TTCraftCommandException;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Name("banish")
@Level(2)
public class BanishCommand extends ICommand {

	@Override
	public boolean run(CommandSender sender, Command command, String s, String[] args) throws TTCraftCommandException {
		
		if (args.length > 0) {

			String nickname = "CONSOLE";

			Player target = plugin.getServer().getPlayer(args[0]);
			if (target == null) {
				playerManager.sendException(sender, new PlayerNotFoundException());
				return true;
			}

			if (sender instanceof Player) {
				nickname = playerManager.getNickname((Player) sender);

				if (!checkPermissions((Player) sender, target, false))
					return true;
			}

			Location targetLastLoc = target.getLocation();
			playerManager.setLastLocation(target, targetLastLoc);

			target.teleport(plugin.getConfig().getLocation("world"));
			plugin.sendServerMessage(nickname + " \u00a7fbanished " + playerManager.getNickname(target));
			return true;
		} else
			return false;
	}
}