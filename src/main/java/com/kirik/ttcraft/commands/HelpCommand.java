package com.kirik.ttcraft.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.kirik.ttcraft.commands.ICommand.*;
import com.kirik.ttcraft.main.util.TTCraftCommandException;

@Name("help")
@Level(0)
public class HelpCommand extends ICommand {

	@Override
	public boolean run(CommandSender sender, Command command, String s, String[] args) throws TTCraftCommandException {

		if (args.length > 0) {

			String helpString = args[0];
			if (!plugin.getCommand(helpString).isRegistered()) {
				playerManager.sendException(sender, new TTCraftCommandException("Command not found!"));
				return true;
			}

			playerManager.sendMessage(sender, "-- Command Help -- " + helpString + " --");
			playerManager.sendMessage(sender, "Info: " + plugin.getCommand(helpString).getDescription());
			playerManager.sendMessage(sender, "Usage: " + plugin.getCommand(helpString).getUsage());
			return true;
		} else {

			int level = 999;
			if (sender instanceof Player)
				level = playerManager.getLevel((Player) sender);

			playerManager.sendMessage(sender, "Available commands: ");

			// TODO: update
			if (level >= 0) {
				/* playerManager.sendMessage(sender,
						"Note: You can use \u00a7d/help [command] \u00a7fto see description and usage of command"); */
				playerManager.sendMessage(sender, "\u00a7d/help \u00a7f[\u00a7dcommand\u00a7f] - details on any command");
				playerManager.sendMessage(sender, "\u00a7d/home \u00a7f- teleport player to their home");
				playerManager.sendMessage(sender, "\u00a7d/sethome \u00a7f- set player home");
				playerManager.sendMessage(sender, "\u00a7d/setnick \u00a7f- set your nickname");
				playerManager.sendMessage(sender, "\u00a7d/spawn \u00a7f- teleport player to server spawn");
			}
			if(level >= 1) {
				playerManager.sendMessage(sender, "\u00a7d################ MOD COMMANDS ################");
				playerManager.sendMessage(sender, "\u00a7d/time \u00a7f-set server time");
				playerManager.sendMessage(sender, "\u00a7d/tpa \u00a7f- send teleport request to target");
				playerManager.sendMessage(sender, "\u00a7d/tpaccept \u00a7f-accept teleport request from player");
				playerManager.sendMessage(sender, "\u00a7d/tpdeny \u00a7f-deny teleport request from player");
				playerManager.sendMessage(sender, "\u00a7d/weather \u00a7f-set server weather");
			}
			if (level >= 2) {
				playerManager.sendMessage(sender, "\u00a7d################ ADMIN COMMANDS ################");
				playerManager.sendMessage(sender, "\u00a7d/back \u00a7f- teleport player to previous location");
				playerManager.sendMessage(sender, "\u00a7d/banish \u00a7f- send target player to spawn");
				playerManager.sendMessage(sender, "\u00a7d/invsee \u00a7f- see target player's inventory");
				playerManager.sendMessage(sender, "\u00a7d/kick \u00a7f- disconnect target player");
				playerManager.sendMessage(sender, "\u00a7d/say \u00a7f- broadcast message from console");
				playerManager.sendMessage(sender, "\u00a7d/setlevel \u00a7f- set target permission level");
				playerManager.sendMessage(sender, "\u00a7d/setspawn \u00a7f- set world spawn location");
				playerManager.sendMessage(sender, "\u00a7d/smite \u00a7f- smite target player (cosmetic)");
				playerManager.sendMessage(sender, "\u00a7d/summon \u00a7f- teleport target to player");
				playerManager.sendMessage(sender, "\u00a7d/test \u00a7f- who knows really");
				playerManager.sendMessage(sender, "\u00a7d/tp \u00a7f- teleport to target player");
			}
			return true;
		}
	}
}
