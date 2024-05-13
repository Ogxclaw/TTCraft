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

			StringBuilder cmds = new StringBuilder();
			cmds.append("Note: You can use /help [command] to see description and usage of command");
			cmds.append("Available commands: ");

			// FIXME: horrible way of going about this, pls fix later (sadly requires a bit
			// of rewriting command system)
			// TODO: update
			if (level >= 0)
				cmds.append("help, home, sethome, setnick, spawn");
			if (level >= 1)
				cmds.append(", tp");
			if (level >= 2)
				cmds.append(", back, ban, banish, kick, say, setlevel, setspawn, smite, summon, test");
			
			playerManager.sendMessage(sender, cmds.toString());
			return true;
		}
	}
}
