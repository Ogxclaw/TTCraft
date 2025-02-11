package com.kirik.ttcraft.commands;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import com.kirik.ttcraft.commands.ICommand.Level;
import com.kirik.ttcraft.commands.ICommand.Name;
import com.kirik.ttcraft.main.util.TTCraftCommandException;

@Name("invsee")
@Level(3)
public class InvSeeCommand extends ICommand {

	@Override
	public boolean asPlayer(Player player, Command command, String s, String[] args) throws TTCraftCommandException {

		if (!checkPermissions(player))
			return true;

		Player target = plugin.getServer().getPlayer(args[0]);
		player.openInventory(target.getInventory());
		return true;
	}
}
