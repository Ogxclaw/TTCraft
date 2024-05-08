package com.kirik.ttcraft.commands;

import com.kirik.ttcraft.commands.ICommand.*;
import com.kirik.ttcraft.main.util.TTCraftCommandException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

@Name("kickall")
@Help("Kicks all players on the server")
@Usage("/kickall <reason>")
@Level(2)
public class KickAllCommand extends ICommand {

    @Override
    public boolean onCommandAll(CommandSender sender, Command command, String s, String[] args) throws TTCraftCommandException {
        // TTPlayer player_ = plugin.onlinePlayers.get(player.getUniqueId());
        String reason = args[0];
        
        return true;
    }
}