package com.kirik.ttcraft.commands;

import com.kirik.ttcraft.commands.ICommand.*;
import com.kirik.ttcraft.main.util.PlayerNotFoundException;
import com.kirik.ttcraft.main.util.TTCraftCommandException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Name("setnick")
@Level(0)
public class SetNickCommand extends ICommand {

	@Override
	public boolean run(CommandSender sender, Command command, String s, String[] args) throws TTCraftCommandException {

		String nickname = "CONSOLE";
		String targetNickname;

		if (sender instanceof Player) {
			nickname = playerManager.getNickname((Player) sender);
		}

		if (args.length > 1) {

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
				plugin.sendServerMessage(nickname + " \u00a7freset nickname of " + target.getName());
			else
				plugin.sendServerMessage(
						nickname + " \u00a7fset nickname of " + target.getName() + "\u00a7f to " + targetNickname);
		} else if (args.length > 0 && (sender instanceof Player)) {

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
}
