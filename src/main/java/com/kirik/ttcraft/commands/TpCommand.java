package com.kirik.ttcraft.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.kirik.ttcraft.commands.ICommand.Level;
import com.kirik.ttcraft.commands.ICommand.Name;
import com.kirik.ttcraft.main.util.PlayerNotFoundException;
import com.kirik.ttcraft.main.util.TTCraftCommandException;

@Name("tp")
@Level(2)
public class TpCommand extends ICommand {

	@Override
	public boolean asPlayer(Player player, Command command, String s, String[] args) throws TTCraftCommandException {

		if (args.length > 0) {
			Player target = plugin.getServer().getPlayer(args[0]);
			if (target == null) {
				playerManager.sendException(player, new PlayerNotFoundException());
				return true;
			}

			if (!checkPermissions(player, target, true))
				return true;

			playerManager.resetLastLocation(player);

			player.teleport(target);
			plugin.sendServerMessage(
					playerManager.getNickname(player) + " \u00a7fteleported to " + playerManager.getNickname(target));
			return true;
		} else
			return false;
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