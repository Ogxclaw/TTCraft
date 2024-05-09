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
        
        if(args.length == 0) {
            // List<Class<? extends ICommand>> commands = Utils.getSubClasses(ICommand.class);
            StringBuilder cmds = new StringBuilder();
            cmds.append("Available commands: "); 
            // TODO: horrible way of going about this, pls fix later (sadly requires a bit of rewriting command system)
            if(playerManager.getLevel(player) >= 0)
                cmds.append("help, home, sethome, setnick, spawn");
            if(playerManager.getLevel(player) >= 1)
                cmds.append("tp");
            if(playerManager.getLevel(player) >= 2)
                cmds.append(", ban, banish, kick, kickall, setspawn, smite, summon, test");
            playerManager.sendMessage(player, cmds.toString());
        }
        return true;
    }
}
