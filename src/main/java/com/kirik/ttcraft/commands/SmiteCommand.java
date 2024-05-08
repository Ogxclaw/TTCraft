package com.kirik.ttcraft.commands;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import com.kirik.ttcraft.commands.ICommand.Help;
import com.kirik.ttcraft.commands.ICommand.Level;
import com.kirik.ttcraft.commands.ICommand.Name;
import com.kirik.ttcraft.commands.ICommand.Usage;
import com.kirik.ttcraft.main.PlayerConfiguration;
import com.kirik.ttcraft.main.util.TTCraftCommandException;

@Name("smite")
@Help("Smite a player (visually)")
@Usage("/smite <player>")
@Level(2)
public class SmiteCommand extends ICommand {

    @Override
    public boolean onCommandPlayer(Player player, Command command, String s, String[] args) throws TTCraftCommandException {
        
        int playerLevel = new PlayerConfiguration(player.getUniqueId()).getPlayerConfig().getInt("level");

        Player target = plugin.getServer().getPlayer(args[0]);

        if(playerLevel >= this.getRequiredLevel()) { //TODO fine for now but need better permission checking in the future
            target.getWorld().strikeLightningEffect(target.getLocation());
            plugin.sendPlayerMessage(player, "You have smited " + target.getName());
        }else{
            plugin.sendPlayerMessage(player, "Permission denied!");
        }
        
        return true;
    }
}
