package com.kirik.ttcraft.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.kirik.ttcraft.commands.ICommand.Help;
import com.kirik.ttcraft.commands.ICommand.Level;
import com.kirik.ttcraft.commands.ICommand.Name;
import com.kirik.ttcraft.commands.ICommand.Usage;
import com.kirik.ttcraft.main.util.PermissionDeniedException;
import com.kirik.ttcraft.main.util.PlayerNotFoundException;
import com.kirik.ttcraft.main.util.TTCraftCommandException;

@Name("setlevel")
@Help("Test command for new command system")
@Usage("/setlevel <user> <level>")
@Level(2)
public class SetLevelCommand extends ICommand {

    @Override
    public boolean run(CommandSender sender, Command command, String s, String[] args) throws TTCraftCommandException {

        String nickname = "CONSOLE";

        Player target = plugin.getServer().getPlayer(args[0]);
        if(target == null){
            playerManager.sendException(sender, new PlayerNotFoundException());
            return false;
        }

        int level = Integer.parseInt(args[1]);
        
        if(sender instanceof Player) {
            nickname = playerManager.getNickname((Player)sender);

            if(!playerHasPermission((Player)sender, target, false)) {
                playerManager.sendException(plugin.getServer().getConsoleSender(), new PermissionDeniedException("Command /" + this.getName() + " failed by " + ((Player)sender).getName() + ": Permission denied on target " + target.getName()));
                playerManager.sendException(sender, new PermissionDeniedException());
                return false;
            }

            if(playerManager.getLevel((Player)sender) <= level) {
                playerManager.sendException(plugin.getServer().getConsoleSender(), new PermissionDeniedException("Command /" + this.getName() + " failed by " + ((Player)sender).getName() + ": Level exceeds player level"));
                playerManager.sendException(sender, new PermissionDeniedException());
                return false;
            }
        } 

        playerManager.sendMessage(target, nickname + " has set your level to: " + level);
        playerManager.setLevel(target, level);
        playerManager.sendMessage(sender, "You have set level of " + playerManager.getNickname(target) + " \u00a7fto " + level);
        
        return true;
    }
}
