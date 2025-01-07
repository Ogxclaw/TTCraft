package com.kirik.ttcraft.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.kirik.ttcraft.commands.ICommand.Level;
import com.kirik.ttcraft.commands.ICommand.Name;
import com.kirik.ttcraft.main.util.TTCraftCommandException;

@Name("afk")
@Level(0)
public class AfkCommand extends ICommand {

	@Override
	public boolean run(CommandSender sender, Command command, String s, String[] args) throws TTCraftCommandException {

		Player player = (Player)sender;
		playerManager.setAFK(player, !playerManager.isAFK(player));

		if(playerManager.isAFK(player)) { // if player is now afk
			plugin.sendServerMessage(playerManager.getNickname(player) + " \u00a77went AFK");
			player.setPlayerListName("\u00a77[AFK] " + playerManager.getNickname(player));
		}else{
			plugin.sendServerMessage(playerManager.getNickname(player) + " returned from being AFK");
			player.setPlayerListName(playerManager.getNickname(player));
		}
		
		return true;
	}
}