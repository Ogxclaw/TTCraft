package com.kirik.ttcraft.events.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

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

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerTakeFallDamage(EntityDamageEvent e) {

		if(!(e.getEntity() instanceof Player)) return;

		Player player = (Player)e.getEntity();
		if(e.getCause() == DamageCause.FALL && !e.getEntity().isDead()) {

			skillManager.addXP(player, "acrobatics", 10/*  * e.getDamage() */);

			// eventually you'll need to make skills matter, reduce dmg taken!
			// if(e.setDamage(e.getDamage() * (0.25 * )))

			// String cause = "Cause: " + e.getCause().toString();
			String xp = "Acrobatics XP: " + skillManager.getXP(player, "acrobatics");
			String level = "Acrobatics Level: " + skillManager.getLevel(player, "acrobatics");

			playerManager.sendMessage(player, xp);
			playerManager.sendMessage(player, level);
		}else if(e.getCause() == DamageCause.ENTITY_ATTACK) {

			// debug, need player to wear armor to increase skill
			playerManager.sendMessage(player, player.getInventory().getHelmet().getItemMeta().getDisplayName());
		}
	}

}
