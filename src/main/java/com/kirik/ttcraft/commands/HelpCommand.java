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
        
        // List<Class<? extends ICommand>> commands = Utils.getSubClasses(ICommand.class);
        StringBuilder cmds = new StringBuilder();
        cmds.append("Available commands: "); 
        // TODO: horrible way of going about this, pls fix later (sadly requires a bit of rewriting command system)
        if(playerManager.getPlayerLevel(player) >= 0)
            cmds.append("help, home, sethome, setnick, spawn");
        if(playerManager.getPlayerLevel(player) >= 2)
            cmds.append(", ban, kick, kickall, setspawn, smite, test");
        plugin.sendPlayerMessage(player, cmds.toString());
        return true;
    }
}
