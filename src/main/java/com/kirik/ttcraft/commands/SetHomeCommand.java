package com.kirik.ttcraft.commands;

import com.kirik.ttcraft.commands.ICommand.Name;
import com.kirik.ttcraft.commands.ICommand.Level;
import com.kirik.ttcraft.main.util.TTCraftCommandException;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

@Name("sethome")
@Level(0)
public class SetHomeCommand extends ICommand {

	@Override
	public boolean asPlayer(Player player, Command command, String s, String[] args) throws TTCraftCommandException {
		
		playerManager.setHome(player, player.getLocation());
		playerManager.sendMessage(player, "Home set");
		return true;
	}
}