package com.kirik.ttcraft.commands;

import com.kirik.ttcraft.commands.ICommand.*;
import com.kirik.ttcraft.main.PlayerConfiguration;
import com.kirik.ttcraft.main.util.TTCraftCommandException;
import org.bukkit.command.Command;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

@Name("setnick")
@Help("Sets your nickname")
@Usage("/setnick <nickname|reset> (color codes start with $)")
@Level(0)
public class SetNickCommand extends ICommand {

    @Override
    public boolean onCommandPlayer(Player player, Command command, String s, String[] args) throws TTCraftCommandException {
        PlayerConfiguration _playerConfig = new PlayerConfiguration(player.getUniqueId());
        FileConfiguration _player = _playerConfig.getPlayerConfig();
        String newNickname = args[0];
        if(newNickname.equalsIgnoreCase("reset")) {
            newNickname = "";
        }
        newNickname = newNickname.replace("$", "\u00a7");
        // player_.setNickname(newNickname);
        // plugin.getConfig().set(player.getUniqueId().toString() + ".nickname", newNickname);
        _player.set("nickname", newNickname);
        player.setPlayerListName(newNickname);
        player.setDisplayName(newNickname);
        // plugin.saveConfig();
        _playerConfig.savePlayerConfig();
        if(newNickname == "") {
            plugin.sendPlayerMessage(player, "Nickname reset!");
        }else{
            plugin.sendPlayerMessage(player, "Set nickname to " + newNickname);
        }
        return true;
    }
}
