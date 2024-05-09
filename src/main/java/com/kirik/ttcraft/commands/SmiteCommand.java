package com.kirik.ttcraft.commands;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import com.kirik.ttcraft.commands.ICommand.Help;
import com.kirik.ttcraft.commands.ICommand.Level;
import com.kirik.ttcraft.commands.ICommand.Name;
import com.kirik.ttcraft.commands.ICommand.Usage;
import com.kirik.ttcraft.main.util.TTCraftCommandException;

@Name("smite")
@Help("Smite a player (visually)")
@Usage("/smite <player>")
@Level(2)
public class SmiteCommand extends ICommand {

    @Override
    public boolean onCommandPlayer(Player player, Command command, String s, String[] args) throws TTCraftCommandException {
        Player target = plugin.getServer().getPlayer(args[0]);

        target.getWorld().strikeLightningEffect(target.getLocation());
        plugin.sendServerMessage(playerManager.getNickname(player) + " smited " + playerManager.getNickname(target));
        
        return true;
    }
}
