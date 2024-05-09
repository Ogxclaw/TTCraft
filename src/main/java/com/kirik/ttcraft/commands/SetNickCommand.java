package com.kirik.ttcraft.commands;

import com.kirik.ttcraft.commands.ICommand.*;
import com.kirik.ttcraft.main.util.TTCraftCommandException;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

@Name("setnick")
@Help("Sets your nickname")
@Usage("/setnick <nickname|reset> (color codes start with $)")
@Level(0)
public class SetNickCommand extends ICommand {

    @Override
    public boolean onCommandPlayer(Player player, Command command, String s, String[] args) throws TTCraftCommandException {
        String newNickname = args[0];
        newNickname = newNickname.replace("$", "\u00a7");
        if(newNickname.equalsIgnoreCase("reset")) {
            newNickname = "";
        }
        playerManager.setNickname(player, newNickname);
        if(newNickname == "") {
            playerManager.sendMessage(player, "Nickname reset!");
        }else{
            playerManager.sendMessage(player, "Set nickname to " + newNickname);
        }
        return true;
    }
}
