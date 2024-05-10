package com.kirik.ttcraft.commands;

import com.kirik.ttcraft.commands.ICommand.Name;
import com.kirik.ttcraft.commands.ICommand.Help;
import com.kirik.ttcraft.commands.ICommand.Usage;
import com.kirik.ttcraft.commands.ICommand.Level;
import com.kirik.ttcraft.main.util.PermissionDeniedException;
import com.kirik.ttcraft.main.util.PlayerNotFoundException;
import com.kirik.ttcraft.main.util.TTCraftCommandException;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

@Name("summon")
@Help("Teleport another player to you")
@Usage("/summon <target>")
@Level(2)
public class SummonCommand extends ICommand {

    @Override
    public boolean onCommandPlayer(Player player, Command command, String s, String[] args) throws TTCraftCommandException {

        Player target = plugin.getServer().getPlayer(args[0]);
        if(target == null){
            playerManager.sendException(player, new PlayerNotFoundException());
            return false;
        }

        if(!playerHasPermission(player, target, false)) {
            playerManager.sendException(plugin.getServer().getConsoleSender(), new PermissionDeniedException("Command /" + this.getName() + " failed by " + player.getName() + ": Permission denied on target " + target.getName()));
            playerManager.sendException(player, new PermissionDeniedException());
            return false;
        }

        target.teleport(player);
        plugin.sendServerMessage(playerManager.getNickname(player) + " \u00a7fsummoned " + playerManager.getNickname(target));
        return true;
    }
}