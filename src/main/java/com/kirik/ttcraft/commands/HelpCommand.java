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
						"Note: You can use \u00a7d/help [command] \u00a7fto see description and usage of command");
				playerManager.sendMessage(sender, "\u00a7d/help \u00a7f- this command");
				playerManager.sendMessage(sender, "\u00a7d/home \u00a7f- teleport player to their home");
				playerManager.sendMessage(sender, "\u00a7d/sethome \u00a7f- set player home");
				playerManager.sendMessage(sender, "\u00a7d/setnick \u00a7f- set your nickname");
				playerManager.sendMessage(sender, "\u00a7d/spawn \u00a7f- teleport player to server spawn");
			}
			if (level >= 2)
				playerManager.sendMessage(sender,", back, ban, banish, kick, say, setlevel, setspawn, smite, summon, test, tp");
			return true;
		}
	}
}
