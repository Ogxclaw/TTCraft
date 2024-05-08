package com.kirik.ttcraft.commands;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import com.kirik.ttcraft.commands.ICommand.*;

import com.kirik.ttcraft.main.util.TTCraftCommandException;

@Name("help")
@Help("Lists available commands")
@Usage("/help")
@Level(0)
public class HelpCommand extends ICommand {

    @Override
    public boolean onCommandPlayer(Player player, Command command, String s, String[] args) throws TTCraftCommandException {
        plugin.sendPlayerMessage(player, listPlayerCommands(player));
        
        return true;
    }

    private String listPlayerCommands(Player player) {
        StringBuilder commands = new StringBuilder();
        commands.append("Available commands: ");
         
        return "";
    }
}
