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
				playerManager.sendMessage(sender,
						"Note: You can use /help [command] to see description and usage of command");
				playerManager.sendMessage(sender, "/help - this command home, sethome, setnick, spawn");
				playerManager.sendMessage(sender, "/home - teleport player to their home");
				playerManager.sendMessage(sender, "/sethome - set player home");
				playerManager.sendMessage(sender, "/setnick - set your nickname");
				playerManager.sendMessage(sender, "/spawn - teleport player to server spawn");
			}
			if (level >= 2)
				playerManager.sendMessage(sender,", back, ban, banish, kick, say, setlevel, setspawn, smite, summon, test, tp");
			return true;
		}
	}
}
