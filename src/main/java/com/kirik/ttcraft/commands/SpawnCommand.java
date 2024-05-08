package com.kirik.ttcraft.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import com.kirik.ttcraft.commands.ICommand.Help;
import com.kirik.ttcraft.commands.ICommand.Level;
import com.kirik.ttcraft.commands.ICommand.Name;
import com.kirik.ttcraft.commands.ICommand.Usage;
import com.kirik.ttcraft.main.util.TTCraftCommandException;

@Name("spawn")
@Help("Sets world's spawnpoint")
@Usage("/setspawn")
@Level(0)
public class SpawnCommand extends ICommand {

    @Override
    public boolean onCommandPlayer(Player player, Command command, String s, String[] args) throws TTCraftCommandException {
        
        Location worldSpawn = plugin.getConfig().getLocation(player.getWorld().getName());
        if(worldSpawn == null) {
            worldSpawn = player.getWorld().getSpawnLocation();
        }
        player.teleport(worldSpawn);

        plugin.sendPlayerMessage(player, "Spawn set.");
        return true;
    }
}
