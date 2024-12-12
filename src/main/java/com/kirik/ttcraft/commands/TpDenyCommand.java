package com.kirik.ttcraft.commands;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import com.kirik.ttcraft.commands.ICommand.Level;
import com.kirik.ttcraft.commands.ICommand.Name;
import com.kirik.ttcraft.events.managers.RequestManager;
import com.kirik.ttcraft.main.util.PermissionDeniedException;
import com.kirik.ttcraft.main.util.TTCraftCommandException;

@Name("tpdeny")
@Level(1)
public class TpDenyCommand extends ICommand {

	@Override
	public boolean asPlayer(Player player, Command command, String s, String[] args) throws TTCraftCommandException {

		Player target = plugin.getServer().getPlayer(args[0]);
		/*
		 * if (target == null) {
		 * playerManager.sendException(player, new PlayerNotFoundException());
		 * return true;
		 * }
		 */

		RequestManager req = RequestManager.getRequest(player, target);
		if (req == null) {
			throw new PermissionDeniedException("No pending request found");
		} else {
			playerManager.sendMessage(player, "Request declined");
			req.decline();
		}
		return true;
	}
}