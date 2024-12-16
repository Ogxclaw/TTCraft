package com.kirik.ttcraft.commands;

import com.kirik.ttcraft.commands.ICommand.*;
import com.kirik.ttcraft.main.util.PlayerNotFoundException;
import com.kirik.ttcraft.main.util.TTCraftCommandException;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Name("summon")
@Level(2)
public class SummonCommand extends ICommand {

	@Override
	public boolean asPlayer(Player player, Command command, String s, String[] args) throws TTCraftCommandException {

		Player target = plugin.getServer().getPlayer(args[0]);
		if (target == null) {
			playerManager.sendException(player, new PlayerNotFoundException());
			return true;
		}

		if (!checkPermissions(player, target, false))
			return true;

		Location targetLastLoc = target.getLocation();
		playerManager.setLastLocation(target, targetLastLoc);

		target.teleport(player);
		plugin.sendServerMessage(
				playerManager.getNickname(player) + " summoned " + playerManager.getNickname(target));
		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
		List<String> list = new ArrayList<>();
		for(Player p : plugin.getServer().getOnlinePlayers()) {
			list.add(p.getName());
		}
		return list;
	}
}