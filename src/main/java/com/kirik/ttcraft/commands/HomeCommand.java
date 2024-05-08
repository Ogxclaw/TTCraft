package com.kirik.ttcraft.commands;


import com.kirik.ttcraft.commands.ICommand.Name;
import com.kirik.ttcraft.commands.ICommand.Help;
import com.kirik.ttcraft.commands.ICommand.Usage;
import com.kirik.ttcraft.commands.ICommand.Level;
import com.kirik.ttcraft.main.PlayerConfiguration;
import com.kirik.ttcraft.main.util.TTCraftCommandException;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

@Name("home")
@Help("Teleports player to their home")
@Usage("/home")
@Level(0)
public class HomeCommand extends ICommand {

    @Override
    public boolean onCommandPlayer(Player player, Command command, String s, String[] args) throws TTCraftCommandException {
        
        FileConfiguration _player = new PlayerConfiguration(player.getUniqueId()).getPlayerConfig();
        
        Location home = _player.getLocation("home");
        if(home == null) {
            home = plugin.getServer().getWorld("world").getSpawnLocation();
        }
        player.teleport(home);

        plugin.sendPlayerMessage(player, "Teleported home!");
        return true;
    }
}