package com.kirik.ttcraft.commands;

import com.kirik.ttcraft.commands.ICommand.*;
import com.kirik.ttcraft.main.util.PlayerNotFoundException;
import com.kirik.ttcraft.main.util.TTCraftCommandException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Name("who")
@Level(0)
public class WhoCommand extends ICommand {

	@Override
	public boolean run(CommandSender sender, Command command, String s, String[] args) throws TTCraftCommandException {

		/*
		 * int level = 999;
		 * 
		 * if(sender instanceof Player) {
		 * level = playerManager.getLevel((Player)sender);
		 * }
		 */

		if (args.length == 0) {
			StringBuilder players = new StringBuilder();
			for (Player player : plugin.getServer().getOnlinePlayers()) {
				players.append(playerManager.getNickname(player) + ", ");
			}
			players = new StringBuilder(players.substring(0, players.length() - 2));
			playerManager.sendMessage(sender, "Online players: " + players.toString());
			return true;
		} else {
			Player target = plugin.getServer().getPlayer(args[0]);
			StringBuilder info = new StringBuilder("Player info: ");
			if (target == null) {
				// check if using nickname
				for (Player playerCheck : plugin.getServer().getOnlinePlayers()) {
					String nickname = playerManager.getNickname(playerCheck);
					if (nickname.contains("§")) {
						nickname = nickname.substring(nickname.indexOf("§") + 2, nickname.length());
						plugin.sendConsoleMsg(nickname);
					}
					if (nickname.equalsIgnoreCase(args[0])) {
						target = playerCheck;
					}
				}

				// recheck if target is null
				if (target == null) {
					plugin.sendConsoleMsg("target was null");
					playerManager.sendException(sender, new PlayerNotFoundException());
					return false;
				}
			}

			info.append("\u00a7fUsername: " + target.getName());
			info.append("\u00a7fNickname: " + playerManager.getNickname(target));

			/*
			 * if(level < playerManager.getLevel(target)) {
			 * playerManager.sendException(plugin.getServer().getConsoleSender(), new
			 * PermissionDeniedException("Command /" + this.getName() + " failed by " +
			 * ((Player)sender).getName() + ": Permission denied on target " +
			 * target.getName()));
			 * playerManager.sendException(sender, new PermissionDeniedException());
			 * return false;
			 * }
			 */

		}
		return true;
	}
}