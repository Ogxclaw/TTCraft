package com.kirik.ttcraft.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.kirik.ttcraft.commands.ICommand.Level;
import com.kirik.ttcraft.commands.ICommand.Name;
import com.kirik.ttcraft.main.util.TTCraftCommandException;

@Name("test")
@Level(2)
public class TestCommand extends ICommand {

	@Override
	public boolean run(CommandSender sender, Command command, String s, String[] args) throws TTCraftCommandException {

		// plugin.getServer().dispatchCommand(sender, "tellraw test");

		return false;
	}
}

/*
 * scoreboard.setName("team_test1");
 * scoreboard.setMode((byte)0);
 * scoreboard.setDisplayName(WrappedChatComponent.fromText(args[0]));
 * scoreboard.setColor(ChatColor.RED);
 * scoreboard.setEntityCount(1);
 * List<String> players = new ArrayList<String>();
 * players.add(player.getName());
 * scoreboard.setPlayers(players);
 * scoreboard.setPrefix(WrappedChatComponent.fromText(args[1]));
 * scoreboard.sendPacket(player);
 * 
 * try {
 * plugin.getProtocolManager().sendServerPacket(player, stand);
 * }catch(InvocationTargetException e) {
 * e.printStackTrace();
 * }
 */