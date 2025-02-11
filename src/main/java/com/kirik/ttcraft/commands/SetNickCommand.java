package com.kirik.ttcraft.commands;

import com.kirik.ttcraft.commands.ICommand.*;
import com.kirik.ttcraft.main.util.PlayerNotFoundException;
import com.kirik.ttcraft.main.util.TTCraftCommandException;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Name("setnick")
@Level(1)
public class SetNickCommand extends ICommand {

	@Override
	public boolean run(CommandSender sender, Command command, String s, String[] args) throws TTCraftCommandException {

		String nickname = "CONSOLE";
		String targetNickname;

		if (sender instanceof Player) {
			nickname = playerManager.getNickname((Player) sender);
		}

		if (args.length > 1) { // targeting another player

			Player target = plugin.getServer().getPlayer(args[0]);
			if (target == null) {
				playerManager.sendException(sender, new PlayerNotFoundException());
				return true;
			}

			if (sender instanceof Player) {
				if (!checkPermissions((Player) sender, target, false))
					return true;
			}

			targetNickname = args[1];
			targetNickname = targetNickname.replace("$", "\u00a7");
			if (targetNickname.equalsIgnoreCase("reset"))
				targetNickname = "";
			playerManager.setNickname(target, targetNickname);
			if (targetNickname == "")
				plugin.sendServerMessage(nickname + " reset nickname of " + target.getName());
			else
				plugin.sendServerMessage(nickname + " set nickname of " + target.getName() + " to " + targetNickname);
			return true;
		} else if (args.length > 0 && (sender instanceof Player)) { // targeting yourself

			targetNickname = args[0];
			targetNickname = targetNickname.replace("$", "\u00a7");
			if (targetNickname.equalsIgnoreCase("reset")) {
				targetNickname = "";
			}
			playerManager.setNickname((Player) sender, targetNickname);
			if (targetNickname == "") {
				playerManager.sendMessage((Player) sender, "Nickname reset!");
			} else {
				playerManager.sendMessage((Player) sender, "Set nickname to " + targetNickname);
			}
			return true;
		}
		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
		List<String> list = new ArrayList<>();
		if(args.length == 1) {
			list.add("[nickname]");
			list.add("reset");
			if(playerManager.getLevel((Player) sender) >= 1) {
				for(Player p : plugin.getServer().getOnlinePlayers()) {
					list.add(p.getName());
				}
			}
		}else if (args.length == 2 && playerManager.getLevel((Player)sender) >= 1) {
			list.add("[nickname]");
			list.add("reset");
		}
		return list;
	}
}
