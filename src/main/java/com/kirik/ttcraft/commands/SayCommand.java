package com.kirik.ttcraft.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.kirik.ttcraft.commands.ICommand.*;
import com.kirik.ttcraft.main.util.TTCraftCommandException;

@Name("say")
@Level(3)
public class SayCommand extends ICommand {

	@Override
	public boolean run(CommandSender sender, Command command, String s, String[] args) throws TTCraftCommandException {

		if (args.length > 0) {

			if (sender instanceof Player)
				if (!checkPermissions((Player) sender))
					return true;

			StringBuilder message = new StringBuilder();
			for (String piece : args)
				message.append(piece + " ");
			message = new StringBuilder(message.substring(0, message.length() - 1));

			plugin.getServer().broadcastMessage("\u00a7d[CONSOLE] " + message);
			return true;
		} else
			return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
		List<String> list = new ArrayList<>();
		list.add("[message]");
		return list;
	}
}
