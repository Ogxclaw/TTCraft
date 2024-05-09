package com.kirik.ttcraft.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.kirik.ttcraft.commands.ICommand.Help;
import com.kirik.ttcraft.commands.ICommand.Level;
import com.kirik.ttcraft.commands.ICommand.Name;
import com.kirik.ttcraft.commands.ICommand.Usage;
import com.kirik.ttcraft.main.util.PermissionDeniedException;
import com.kirik.ttcraft.main.util.TTCraftCommandException;

@Name("setlevel")
@Help("Test command for new command system")
@Usage("/setlevel <user> <level>")
@Level(2)
public class SetLevelCommand extends ICommand {

    @Override
    public boolean onCommandAll(CommandSender sender, Command command, String s, String[] args) throws TTCraftCommandException {
        
        if(!(sender instanceof Player)) {
            Player target = plugin.getServer().getPlayer(args[0]);
            int level = Integer.parseInt(args[1]);

            playerManager.setLevel(target, level);
            playerManager.sendMessage(target, "CONSOLE has set your level to: " + level);

            return true;
        }

        Player player = (Player)sender;
        Player target = plugin.getServer().getPlayer(args[0]);

        if(playerManager.getLevel(player) > playerManager.getLevel(target)) {
            int level = Integer.parseInt(args[1]);

            playerManager.setLevel(target, level);
            playerManager.sendMessage(player, "You have set level of " + playerManager.getNickname(target) + " \u00a7fto " + level);
        }else throw new PermissionDeniedException();
        
        return true;
    }
}
