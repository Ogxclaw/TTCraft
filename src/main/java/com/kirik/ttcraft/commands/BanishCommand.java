package com.kirik.ttcraft.commands;

import com.kirik.ttcraft.commands.ICommand.Name;
import com.kirik.ttcraft.commands.ICommand.Help;
import com.kirik.ttcraft.commands.ICommand.Usage;
import com.kirik.ttcraft.commands.ICommand.Level;
import com.kirik.ttcraft.main.util.PermissionDeniedException;
import com.kirik.ttcraft.main.util.PlayerNotFoundException;
import com.kirik.ttcraft.main.util.TTCraftCommandException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Name("banish")
@Help("Teleport another player to spawn")
@Usage("/banish <target>")
@Level(2)
public class BanishCommand extends ICommand {

    @Override
    public boolean run(CommandSender sender, Command command, String s, String[] args) throws TTCraftCommandException {
        
        String nickname = "CONSOLE";

        // target checking
        Player target = plugin.getServer().getPlayer(args[0]);
        if(target == null){
            playerManager.sendException(sender, new PlayerNotFoundException());
            return false;
        }

        if(sender instanceof Player) {
            
            nickname = playerManager.getNickname((Player)sender);

            // permission checking (PvT) (checks both)
            if(!playerHasPermission((Player)sender, target, false)) {
                playerManager.sendException(plugin.getServer().getConsoleSender(), new PermissionDeniedException("Command /" + this.getName() + " failed by " + ((Player)sender).getName() + ": Permission denied on target " + target.getName()));
                playerManager.sendException(sender, new PermissionDeniedException());
                return false;
            }
        }

        target.teleport(plugin.getConfig().getLocation("world"));
        plugin.sendServerMessage(nickname + " \u00a7fbanished " + playerManager.getNickname(target));
        return true;
    }
}