package com.kirik.ttcraft.events.listeners;

import com.kirik.ttcraft.events.managers.PlayerManager;
import com.kirik.ttcraft.main.TTCraft;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

public class PlayerListener implements Listener {

    private final TTCraft plugin;
    private final PlayerManager playerManager;

    public PlayerListener(TTCraft plugin, PlayerManager manager) {
        this.plugin = plugin;
        playerManager = manager;
    }

    @EventHandler()
    public void onPlayerJoin(PlayerJoinEvent e) {
        String nickname = playerManager.getNickname(e.getPlayer());
        if(nickname == "" || nickname == null) {
            nickname = e.getPlayer().getName();
        }

        e.setJoinMessage("\u00a72[+] \u00a77" + nickname + " \u00a7ejoined!");
        e.getPlayer().setPlayerListName(nickname);
        e.getPlayer().setDisplayName(nickname);

        String[] motd = plugin.getMOTD().replace("$", "\u00a7").split("(nl)");
        for(String motdPiece : motd)
           playerManager.sendMessage(e.getPlayer(), motdPiece);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        playerManager.savePlayerConfig(e.getPlayer());

        e.setQuitMessage("\u00a74[-] \u00a77" + playerManager.getNickname(e.getPlayer()) + " \u00a7edisconnected!");
    }

    // Don't think I need this anymore since kick/ban commands will send server messages
    /*@EventHandler
    public void onPlayerKick(PlayerKickEvent e) {
        FileConfiguration _player = new PlayerConfiguration(e.getPlayer().getUniqueId()).getPlayerConfig();

        String nickname = _player.getString("nickname");
        if(nickname == "" || nickname == null) {
            nickname = e.getPlayer().getName();
        }

        e.setLeaveMessage("\u00a74[-] \u00a77" + nickname + " \u00a7ekicked! (" + e.getReason() + ")");
    }*/

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        String nickname = playerManager.getNickname(e.getPlayer());

        String colorMessage = e.getMessage().replace("$", "\u00a7");

        e.setFormat("\u00a77" + nickname + "\u00a7f: " + colorMessage);
    }
}
