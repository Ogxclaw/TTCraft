package com.kirik.ttcraft.commands;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import com.kirik.ttcraft.commands.ICommand.Level;
import com.kirik.ttcraft.commands.ICommand.Name;
import com.kirik.ttcraft.events.managers.RequestManager;
import com.kirik.ttcraft.events.tasks.RequestTask;
import com.kirik.ttcraft.main.util.PlayerNotFoundException;
import com.kirik.ttcraft.main.util.TTCraftCommandException;

@Name("tpa")
@Level(2)
public class TpaCommand extends ICommand {

	@Override
	public boolean asPlayer(Player player, Command command, String s, String[] args) throws TTCraftCommandException {

		if (args.length > 0) {
			Player target = plugin.getServer().getPlayer(args[0]);
			if (target == null) {
				playerManager.sendException(player, new PlayerNotFoundException());
				return true;
			}

			playerManager.sendMessage(player, "Sending teleport request to " + target.getDisplayName() + "...");
			requestTeleport(player, target,
					player.getDisplayName()
							+ " wants to teleport to you! Use \u00a75/tpaccept \u00a7for \u00a75/tpdeny");
			return true;
		} else
			return false;
	}

	protected void requestTeleport(final Player byPlayer, final Player forPlayer, final String msg) {
		new RequestManager(forPlayer, byPlayer, new RequestTask() {

			@Override
			public void accept() {
				playerManager.resetLastLocation(byPlayer);
				byPlayer.teleport(forPlayer, TeleportCause.COMMAND);
				playerManager.sendMessage(byPlayer, "Your teleportation request was accepted!");
			}

			@Override
			public void decline() {
				playerManager.sendMessage(byPlayer, "Your teleportation request was declined!");
			}
		}).add(msg);
	}
}
