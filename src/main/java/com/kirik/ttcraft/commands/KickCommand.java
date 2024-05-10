package com.kirik.ttcraft.commands;

import com.kirik.ttcraft.commands.ICommand.*;
import com.kirik.ttcraft.main.util.PermissionDeniedException;
import com.kirik.ttcraft.main.util.PlayerNotFoundException;
import com.kirik.ttcraft.main.util.TTCraftCommandException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Name("kick")
@Help("Kick player on the server")
@Usage("/kick <player> [reason]")
@Level(2)
public class KickCommand extends ICommand {

    public boolean run(CommandSender sender, Command command, String s, String[] args) throws TTCraftCommandException {

        String nickname = "CONSOLE";
        StringBuilder reason = new StringBuilder();

        Player target = plugin.getServer().getPlayer(args[0]);
        if(target == null){
            playerManager.sendException(sender, new PlayerNotFoundException());
            return false;
        }
        
        if(sender instanceof Player) {
            nickname = playerManager.getNickname((Player)sender);

            if(!playerHasPermission((Player)sender, target, true)) {
                playerManager.sendException(plugin.getServer().getConsoleSender(), new PermissionDeniedException("Command /" + this.getName() + " failed by " + ((Player)sender).getName() + ": Permission denied on target " + target.getName()));
                playerManager.sendException(sender, new PermissionDeniedException());
                return false;
            }

        }

        for(String w : args)
            reason.append(w + " ");
        reason = new StringBuilder(reason.substring(target.getName().length()+1, reason.length()-1)); // remove username arg & trailing " "

        String kickReason;
        if(args.length == 0)
            kickReason = "Kicked by " + nickname;
        else
            kickReason = "Kicked by " + nickname + " \u00a7f(" + reason.toString() + ")";

        target.kickPlayer(kickReason);
        plugin.sendServerMessage(nickname + " kicked " + playerManager.getNickname(target) + " \u00a7f(" + reason.toString() + ")");
        return true;
    }
}
