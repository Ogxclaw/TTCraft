package com.kirik.ttcraft.commands;

import com.kirik.ttcraft.commands.ICommand.Name;
import com.kirik.ttcraft.commands.ICommand.Help;
import com.kirik.ttcraft.commands.ICommand.Usage;
import com.kirik.ttcraft.commands.ICommand.Level;
import com.kirik.ttcraft.main.util.PermissionDeniedException;
import com.kirik.ttcraft.main.util.TTCraftCommandException;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

@Name("banish")
@Help("Teleport another player to spawn")
@Usage("/banish <target>")
@Level(2)
public class BanishCommand extends ICommand {

    @Override
    public boolean onCommandPlayer(Player player, Command command, String s, String[] args) throws TTCraftCommandException {
        Player target = plugin.getServer().getPlayer(args[0]); // TODO: error checking?

        if(playerManager.getLevel(player) >= playerManager.getLevel(target)) {
            target.teleport(plugin.getConfig().getLocation("world"));
            plugin.sendServerMessage(playerManager.getNickname(player) + " \u00a7fbanished " + playerManager.getNickname(target));
        }else{
            throw new PermissionDeniedException();
        }
        return true;
    }
}