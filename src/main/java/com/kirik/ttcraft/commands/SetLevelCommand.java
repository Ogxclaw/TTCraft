package com.kirik.ttcraft.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import com.kirik.ttcraft.commands.ICommand.Help;
import com.kirik.ttcraft.commands.ICommand.Level;
import com.kirik.ttcraft.commands.ICommand.Name;
import com.kirik.ttcraft.commands.ICommand.Usage;
import com.kirik.ttcraft.main.PlayerConfiguration;
import com.kirik.ttcraft.main.util.PermissionDeniedException;
import com.kirik.ttcraft.main.util.TTCraftCommandException;

@Name("setlevel")
@Help("Test command for new command system")
@Usage("/setlevel <user> <level>")
@Level(2)
public class SetLevelCommand extends ICommand {

    @Override
    public boolean onCommandAll(CommandSender sender, Command command, String s, String[] args) throws TTCraftCommandException {
        
        if(!(sender instanceof Player)) {
            Player target = plugin.getServer().getPlayer(args[0]);
            PlayerConfiguration _targetConfig = new PlayerConfiguration(target.getUniqueId());
            FileConfiguration _target = _targetConfig.getPlayerConfig();
            
            int level = Integer.parseInt(args[1]);
            _target.set("level", level);
            _targetConfig.savePlayerConfig();
            plugin.sendPlayerMessage(target, "CONSOLE has set your level to: " + level);
            return true;
        }

        Player player = (Player)sender;
        int playerLevel = new PlayerConfiguration(player.getUniqueId()).getPlayerConfig().getInt("level");

        Player target = plugin.getServer().getPlayer(args[0]);
        PlayerConfiguration _targetConfig = new PlayerConfiguration(target.getUniqueId());
        FileConfiguration _target = _targetConfig.getPlayerConfig();
        int targetLevel = _target.getInt("level");

        if(playerLevel > targetLevel) {
            int level = Integer.parseInt(args[1]);
            _target.set("level", level);
            plugin.sendPlayerMessage(player, "You have set level of " + target.getName() + " to " + level);
            _targetConfig.savePlayerConfig();
        }else{
            throw new PermissionDeniedException();
        }
        
        return true;
    }
}
