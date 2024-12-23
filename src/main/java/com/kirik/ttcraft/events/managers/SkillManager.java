package com.kirik.ttcraft.events.managers;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import com.kirik.ttcraft.main.PlayerConfiguration;
import com.kirik.ttcraft.main.TTCraft;

public class SkillManager {

	private final TTCraft plugin;

	public SkillManager(TTCraft instance) {
		this.plugin = instance;
	}

	public void setXP(Player player, String skillName, double xp) {
		PlayerConfiguration playerConfigFile = new PlayerConfiguration(player.getUniqueId());
		FileConfiguration playerConfig = playerConfigFile.getPlayerConfig();
		playerConfig.set("skills." + skillName, xp);
		playerConfigFile.savePlayerConfig();
	}

	public void addXP(Player player, String skillName, double xpToAdd) {
		double xp = getXP(player, skillName);
		setXP(player, skillName, xp + xpToAdd);
	}

	public double getXP(Player player, String skillName) {
		double skillXP = new PlayerConfiguration(player.getUniqueId()).getPlayerConfig().getDouble("skills." + skillName);
		return skillXP;
	}

	// TODO: Make stardew valley type progression
	// Levels 1 -> 10, small bonus each lvl, big bonus on 5 & 10
	public int getLevel(Player player, String skillName) {
		double skillXP = getXP(player, skillName);
		int skillLevel = (int)(skillXP / 10);
		return skillLevel;
	}

}
