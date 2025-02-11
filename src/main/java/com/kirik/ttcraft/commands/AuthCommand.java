package com.kirik.ttcraft.commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import com.kirik.ttcraft.commands.ICommand.Level;
import com.kirik.ttcraft.commands.ICommand.Name;
import com.kirik.ttcraft.main.util.TTCraftCommandException;

@Name("auth")
@Level(0)
public class AuthCommand extends ICommand {

	@Override
	public boolean asPlayer(Player player, Command command, String s, String[] args) throws TTCraftCommandException {

		// plugin.getServer().dispatchCommand(sender, "tellraw test");

		if(args.length > 0) {
			String authPass = args[0];
			File file = new File(plugin.getDataFolder(), "auth.txt"); // stored in txt file so u cant see it ;)

			try {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String line;
				while((line = reader.readLine()) != null) {
					if(authPass.equalsIgnoreCase(line)) {
						// authentication success!
						playerManager.setLevel(player, 1);
						player.setGameMode(GameMode.SURVIVAL);
						playerManager.sendMessage(player, "Authentication Success! You can now build and destroy blocks");

						reader.close();
						return true;
					}
				}

				playerManager.sendMessage(player, "Authentication Failed!");
				reader.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
			return true;
		}else{
			return false;
		}
	}

}
