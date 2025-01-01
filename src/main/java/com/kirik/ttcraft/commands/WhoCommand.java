package com.kirik.ttcraft.commands;

import com.kirik.ttcraft.commands.ICommand.*;
import com.kirik.ttcraft.main.util.PlayerNotFoundException;
import com.kirik.ttcraft.main.util.TTCraftCommandException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Name("who")
@Level(2)
public class WhoCommand extends ICommand {

	@Override
	public boolean run(CommandSender sender, Command command, String s, String[] args) throws TTCraftCommandException {
		// TODO: could be better, doesn't identify nicknames only usernames 
		if (args.length > 0) {

			Player target = plugin.getServer().getPlayer(args[0]);
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
					return true;
				}
			}

			playerManager.sendMessage(sender, "Player info:");
			playerManager.sendMessage(sender, "\u00a7fUsername: " + target.getName());
			playerManager.sendMessage(sender, "\u00a7fNickname: " + playerManager.getNickname(target));
			return true;
		} else {
			StringBuilder players = new StringBuilder();
			for (Player player : plugin.getServer().getOnlinePlayers()) {
				players.append(playerManager.getNickname(player) + ", ");
			}
			players = new StringBuilder(players.substring(0, players.length() - 2)); // remove trailing comma
			playerManager.sendMessage(sender, "Online players: " + players.toString());
			return true;
		}
	}
}