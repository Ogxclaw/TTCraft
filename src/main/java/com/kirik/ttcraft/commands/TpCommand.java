package com.kirik.ttcraft.commands;

import com.kirik.ttcraft.commands.ICommand.Name;
import com.kirik.ttcraft.commands.ICommand.Help;
import com.kirik.ttcraft.commands.ICommand.Usage;
import com.kirik.ttcraft.commands.ICommand.Level;
import com.kirik.ttcraft.main.util.PermissionDeniedException;
import com.kirik.ttcraft.main.util.TTCraftCommandException;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

@Name("tp")
@Help("Teleport to another player")
@Usage("/tp <target>")
@Level(1)
public class TpCommand extends ICommand {

    @Override
    public boolean onCommandPlayer(Player player, Command command, String s, String[] args) throws TTCraftCommandException {

        Player target = plugin.getServer().getPlayer(args[0]);

        if(playerManager.getLevel(player) >= playerManager.getLevel(target)) {
            player.teleport(target);
            plugin.sendServerMessage(playerManager.getNickname(player) + " teleported to " + playerManager.getNickname(target));
        }else{
            throw new PermissionDeniedException();
        }
        return true;
    }
}