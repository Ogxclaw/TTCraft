package com.kirik.ttcraft.events.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import com.kirik.ttcraft.events.managers.PlayerManager;
import com.kirik.ttcraft.events.managers.SkillManager;
import com.kirik.ttcraft.main.TTCraft;

public class SkillListener implements Listener {

	private final TTCraft plugin;
	private final SkillManager skillManager;
	private final PlayerManager playerManager;

	public SkillListener(TTCraft instance, PlayerManager playerManager, SkillManager skillManager) {
		this.plugin = instance;
		this.playerManager = playerManager;
		this.skillManager = skillManager;
	}

	@EventHandler
	public void onPlayerTakeFallDamage(EntityDamageEvent e) {
		if(e.getEntity() instanceof Player) {
			Player player = (Player)e.getEntity();
			String xp = "XP: " + skillManager.getSkillXP(player, "Acrobatics");
			playerManager.sendMessage(player, xp);
		}
	}

}
