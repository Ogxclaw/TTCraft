package com.kirik.ttcraft.commands;

import com.kirik.ttcraft.events.managers.PlayerManager;
import com.kirik.ttcraft.main.TTCraft;
import com.kirik.ttcraft.main.util.PermissionDeniedException;
import com.kirik.ttcraft.main.util.TTCraftCommandException;
import com.kirik.ttcraft.main.util.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.util.List;

public abstract class ICommand implements CommandExecutor {

	@Retention(RetentionPolicy.RUNTIME)
	public @interface Name {
		String value();
	}

	@Retention(RetentionPolicy.RUNTIME)
	public @interface Level {
		int value();
	}

	protected static TTCraft plugin = TTCraft.instance;
	protected static PlayerManager playerManager = plugin.playerManager;

	@Override
	public final boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
		try {
			return run(sender, command, s, args);
		} catch (Exception e) {
			plugin.sendConsoleError("Error: " + e.getMessage());
			return false;
		}
	}

	public boolean run(CommandSender sender, Command command, String s, String[] args) throws TTCraftCommandException {
		if (sender instanceof Player)
			return asPlayer((Player) sender, command, s, args);
		else
			return onCommandConsole(sender, command, s, args);
	}

	public boolean onCommandConsole(CommandSender sender, Command command, String s, String[] args)
			throws TTCraftCommandException {
		plugin.sendConsoleError("Sorry, this command cannot be used by the console");
		return true;
	}

	public boolean asPlayer(Player player, Command command, String s, String[] args) throws TTCraftCommandException {
		playerManager.sendMessage(player, "Sorry, this command cannot be used by a player");
		return true;
	}

	public static void registerCommands() {
		List<Class<? extends ICommand>> commands = Utils.getSubClasses(ICommand.class);
		for (Class<? extends ICommand> command : commands) {
			registerCommand(command);
		}
	}

	private static void registerCommand(Class<? extends ICommand> commandClass) {
		try {
			Constructor<? extends ICommand> ctor = commandClass.getConstructor();
			ICommand command = ctor.newInstance();
			if (!commandClass.isAnnotationPresent(Name.class))
				return;
			plugin.getCommand(commandClass.getAnnotation(Name.class).value()).setExecutor(command);
		} catch (Exception ignored) {
		}
	}

	public boolean checkPermissions(Player player) {
		final int playerLevel = playerManager.getLevel(player);
		final int requiredLevel = getRequiredLevel();

		if (playerLevel < requiredLevel) {
			playerManager.sendException(plugin.getServer().getConsoleSender(), new PermissionDeniedException(
					"Command /" + this.getName() + " failed by " + player.getName() + ": Permission denied!"));
			playerManager.sendException(player, new PermissionDeniedException());
			return false;
		}
		return true;
	}

	public boolean checkPermissions(Player player, int level) {
		final int playerLevel = playerManager.getLevel(player);

		if (playerLevel < level) {
			playerManager.sendException(plugin.getServer().getConsoleSender(), new PermissionDeniedException(
					"Command /" + this.getName() + " failed by " + player.getName() + ": Level exceeds player level"));
			playerManager.sendException(player, new PermissionDeniedException());
			return false;
		}

		return true;
	}

	public boolean checkPermissions(Player player, Player target, boolean canBeEquals) {
		final int playerLevel = playerManager.getLevel(player);
		final int targetLevel = playerManager.getLevel(target);
		final int requiredLevel = getRequiredLevel();

		if (playerLevel < requiredLevel) {
			playerManager.sendException(plugin.getServer().getConsoleSender(), new PermissionDeniedException(
					"Command /" + this.getName() + " failed by " + player.getName() + ": Permission denied!"));
			playerManager.sendException(player, new PermissionDeniedException());
			return false;
		}

		if ((playerLevel <= targetLevel) && !canBeEquals) {
			playerManager.sendException(plugin.getServer().getConsoleSender(),
					new PermissionDeniedException("Command /" + this.getName() + " failed by " + player.getName()
							+ ": Permission denied on target " + target.getName()));
			playerManager.sendException(player, new PermissionDeniedException());
			return false;
		}

		if ((playerLevel < targetLevel) && canBeEquals) {
			playerManager.sendException(plugin.getServer().getConsoleSender(),
					new PermissionDeniedException("Command /" + this.getName() + " failed by " + player.getName()
							+ ": Permission denied on target " + target.getName()));
			playerManager.sendException(player, new PermissionDeniedException());
			return false;
		}
		return true;
	}

	public String getName() {
		final Name nameAnnotation = this.getClass().getAnnotation(Name.class);
		if (nameAnnotation == null)
			return "";

		return nameAnnotation.value();
	}

	public final int getRequiredLevel() {
		final Level levelAnnotation = this.getClass().getAnnotation(Level.class);
		if (levelAnnotation == null)
			throw new UnsupportedOperationException("You need either a GetMinLevel method or an @Level annotation");
		return levelAnnotation.value();
	}

}
