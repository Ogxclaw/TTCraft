package com.kirik.ttcraft.commands;
 
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.kirik.ttcraft.commands.ICommand.*;

import com.kirik.ttcraft.main.util.TTCraftCommandException;

@Name("help")
@Help("Lists available commands")
@Usage("/help")
@Level(0)
public class HelpCommand extends ICommand {

    @Override
    public boolean run(CommandSender sender, Command command, String s, String[] args) throws TTCraftCommandException {
        
        int level = 999;

        if(sender instanceof Player) {
            level = playerManager.getLevel((Player)sender);
        }

        if(args.length == 0) {
            // List<Class<? extends ICommand>> commands = Utils.getSubClasses(ICommand.class);
            StringBuilder cmds = new StringBuilder();
            cmds.append("Available commands: "); 
            // FIXME: horrible way of going about this, pls fix later (sadly requires a bit of rewriting command system)
            //TODO: update
            if(level >= 0)
                cmds.append("help, home, sethome, setnick, spawn");
            if(level >= 1)
                cmds.append(", tp");
            if(level >= 2)
                cmds.append(", ban, banish, kick, setlevel, setspawn, smite, summon, test");
            playerManager.sendMessage(sender, cmds.toString());
        }
        return true;
    }
}
