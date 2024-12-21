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

	public void setSkillXP(Player player, String skillName, int xp) {
		PlayerConfiguration playerConfigFile = new PlayerConfiguration(player.getUniqueId());
		FileConfiguration playerConfig = playerConfigFile.getPlayerConfig();
		playerConfig.set("skills." + skillName, xp);
		playerConfigFile.savePlayerConfig();
	}

	public void addSkillXP(Player player, String skillName, int xpToAdd) {
		int xp = getSkillXP(player, skillName);
		setSkillXP(player, skillName, xp + xpToAdd);
	}

	public int getSkillXP(Player player, String skillName) {
		int skillXP = new PlayerConfiguration(player.getUniqueId()).getPlayerConfig().getInt("skills." + skillName);
		return skillXP;
	}

}
