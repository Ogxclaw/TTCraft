package com.kirik.ttcraft.commands;

import com.kirik.ttcraft.commands.ICommand.*;
import com.kirik.ttcraft.main.util.TTCraftCommandException;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Name("weather")
@Level(1)
public class WeatherCommand extends ICommand {

	@Override
	public boolean run(CommandSender sender, Command command, String s, String[] args) throws TTCraftCommandException {

		String nickname = "CONSOLE\u00a7f";

		if (sender instanceof Player) {
			if (!checkPermissions((Player) sender))
				return true;
			nickname = playerManager.getNickname((Player) sender);
		}
		if (args.length > 0) {
			if (args[0].equalsIgnoreCase("clear")) {
				plugin.getServer().getWorld("world").setStorm(false);
				plugin.sendServerMessage(nickname + " cleared weather");
			} else if (args[0].equalsIgnoreCase("rain")) {
				plugin.getServer().getWorld("world").setStorm(true);
				plugin.getServer().getWorld("world").setWeatherDuration(24000);
				plugin.sendServerMessage(nickname + " set weather to raining");
			} else if (args[0].equalsIgnoreCase("thunderstorm")) {
				plugin.getServer().getWorld("world").setStorm(true);
				plugin.getServer().getWorld("world").setWeatherDuration(24000);
				plugin.getServer().getWorld("world").setThundering(true);
				plugin.getServer().getWorld("world").setThunderDuration(24000);
				plugin.sendServerMessage(nickname + " set weather to thunderstorm");
			}else{
				playerManager.sendMessage(sender, "Usage: " + plugin.getCommand("weather").getUsage()); 
			}
			return true;
		}else{
			playerManager.sendMessage(sender, "Usage: " + plugin.getCommand("weather").getUsage());
		}

		return false;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
		List<String> list = new ArrayList<>();
		list.add("clear");
		list.add("rain");
		list.add("thunderstorm");
		return list;
	}
}
