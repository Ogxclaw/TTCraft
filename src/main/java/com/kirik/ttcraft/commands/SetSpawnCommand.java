package com.kirik.ttcraft.commands;

import com.kirik.ttcraft.commands.ICommand.Name;
import com.kirik.ttcraft.commands.ICommand.Help;
import com.kirik.ttcraft.commands.ICommand.Usage;
import com.kirik.ttcraft.commands.ICommand.Level;
import com.kirik.ttcraft.main.util.TTCraftCommandException;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

@Name("setspawn")
@Help("Sets world's spawnpoint")
@Usage("/setspawn")
@Level(2)
public class SetSpawnCommand extends ICommand {

    @Override
    public boolean onCommandPlayer(Player player, Command command, String s, String[] args) throws TTCraftCommandException {
        
        Location newSpawn = player.getLocation();
        player.getWorld().setSpawnLocation(newSpawn); // redunant, but better than it bugging out
        plugin.getConfig().set(player.getWorld().getName(), newSpawn);
        plugin.saveConfig();

        plugin.sendPlayerMessage(player, "Spawn set.");
        return true;
    }
}