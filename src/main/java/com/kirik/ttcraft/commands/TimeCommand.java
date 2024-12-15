package com.kirik.ttcraft.commands;

import com.kirik.ttcraft.commands.ICommand.*;
import com.kirik.ttcraft.main.util.TTCraftCommandException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Name("time")
@Level(1)
public class TimeCommand extends ICommand {

	@Override
	public boolean run(CommandSender sender, Command command, String s, String[] args) throws TTCraftCommandException {

		String nickname = "CONSOLE\u00a7f";

		if (sender instanceof Player) {
			if (!checkPermissions((Player) sender))
				return true;
			nickname = playerManager.getNickname((Player) sender);
		}
		if (args.length > 0) {
			if(args[0].equalsIgnoreCase("dawn")) {
				plugin.getServer().getWorld("world").setTime(23500);
				plugin.sendServerMessage(nickname + " set time to dawn");
			}else if (args[0].equalsIgnoreCase("morning")) {
				plugin.getServer().getWorld("world").setTime(0);
				plugin.sendServerMessage(nickname + " set time to morning");
			}else if (args[0].equalsIgnoreCase("day")) {
				plugin.getServer().getWorld("world").setTime(6000);
				plugin.sendServerMessage(nickname + " set time to day");
			}else if (args[0].equalsIgnoreCase("evening")) {
				plugin.getServer().getWorld("world").setTime(12000);
				plugin.sendServerMessage(nickname + " set time to evening");
			}else if (args[0].equalsIgnoreCase("dusk")) {
				plugin.getServer().getWorld("world").setTime(12750);
				plugin.sendServerMessage(nickname + " set time to dusk");
			}else if (args[0].equalsIgnoreCase("night")) {
				plugin.getServer().getWorld("world").setTime(18000);
				plugin.sendServerMessage(nickname + " set time to night");
			}else{
				playerManager.sendMessage(sender, "Usage: " + plugin.getCommand("time").getUsage());
			}
			return true;
		} else {
			playerManager.sendMessage(sender, "Usage: " + plugin.getCommand("time").getUsage());
		}

		return false;
	}
}
