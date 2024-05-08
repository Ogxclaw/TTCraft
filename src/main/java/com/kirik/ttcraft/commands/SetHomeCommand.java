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

@Name("sethome")
@Help("Sets the home of the player")
@Usage("/sethome")
@Level(0)
public class SetHomeCommand extends ICommand {

    @Override
    public boolean onCommandPlayer(Player player, Command command, String s, String[] args) throws TTCraftCommandException {
        
        PlayerConfiguration _playerConfig = new PlayerConfiguration(player.getUniqueId());
        FileConfiguration _player = _playerConfig.getPlayerConfig();
        
        _player.set("home", (Location)player.getLocation());
        _playerConfig.savePlayerConfig();

        plugin.sendPlayerMessage(player, "Home set!");
        return true;
    }
}