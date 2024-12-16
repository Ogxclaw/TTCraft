package com.kirik.ttcraft.commands;

import com.kirik.ttcraft.commands.ICommand.*;
import com.kirik.ttcraft.main.util.PlayerNotFoundException;
import com.kirik.ttcraft.main.util.TTCraftCommandException;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Name("kick")
@Level(2)
public class KickCommand extends ICommand {

	@Override
	public boolean run(CommandSender sender, Command command, String s, String[] args) throws TTCraftCommandException {

		if (args.length > 0) {

			String nickname = "CONSOLE";
			StringBuilder reason = new StringBuilder();

			Player target = plugin.getServer().getPlayer(args[0]);
			if (target == null) {
				playerManager.sendException(sender, new PlayerNotFoundException());
				return true;
			}

			if (sender instanceof Player) {
				nickname = playerManager.getNickname((Player) sender);

				if (!checkPermissions((Player) sender, target, true))
					return true;
			}

			for (String w : args)
				reason.append(w + " ");
			reason = new StringBuilder(reason.substring(target.getName().length() + 1, reason.length() - 1));

			String kickReason;
			if (args.length == 1)
				kickReason = "Kicked by " + nickname;
			else
				kickReason = "Kicked by " + nickname + " (" + reason.toString() + ")";

			target.kickPlayer(kickReason);
			plugin.sendServerMessage(nickname + " kicked " + playerManager.getNickname(target) + " ("
					+ reason.toString() + ")");
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
