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

@Name("setlevel")
@Level(3)
public class SetLevelCommand extends ICommand {

	@Override
	public boolean run(CommandSender sender, Command command, String s, String[] args) throws TTCraftCommandException {

		if (args.length > 1) {
			String nickname = "CONSOLE";

			Player target = plugin.getServer().getPlayer(args[0]);
			if (target == null) {
				playerManager.sendException(sender, new PlayerNotFoundException());
				return true;
			}

			int level = Integer.parseInt(args[1]);

			if (sender instanceof Player) {
				nickname = playerManager.getNickname((Player) sender);

				if (!checkPermissions((Player) sender, target, false))
					return true;

				if (!checkPermissions((Player) sender, level))
					return true;
			}

			plugin.sendServerMessage(nickname + " set level of " + playerManager.getNickname(target) + " to: " + level);
			playerManager.setLevel(target, level);
			return true;
		} else
			return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
		List<String> list = new ArrayList<>();
		if(args.length == 1) {
			for (Player p : plugin.getServer().getOnlinePlayers()) {
				list.add(p.getName());
			}
		}else if(args.length == 2) {
			list.add("<number>");
		}
		return list;
	}
}
