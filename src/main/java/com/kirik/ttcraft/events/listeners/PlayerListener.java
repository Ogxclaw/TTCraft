package com.kirik.ttcraft.events.listeners;

import com.kirik.ttcraft.main.PlayerConfiguration;
import com.kirik.ttcraft.main.TTCraft;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

public class PlayerListener implements Listener {

    private final TTCraft plugin;

    public PlayerListener(TTCraft plug) {
        plugin = plug;
    }

    @EventHandler()
    public void onPlayerJoin(PlayerJoinEvent e) {
       /* if (playerUUID.equals(UUID.fromString("3f050fe1-a454-4a30-8c8d-4acc691f2b2d")) || // kirik__
                playerUUID.equals(UUID.fromString("dd871f27-e02d-46b2-b505-3b931aac6daa"))) { // circusfreak83
            try {
                plugin.getServer().getWorld(player.getWorld().getName()).strikeLightningEffect(player.getLocation());
            } catch (NullPointerException ex) {
                plugin.sendConsoleError("Oopsies, can't strike lightning... " + ex);
            }
        }*/

        FileConfiguration _player = new PlayerConfiguration(e.getPlayer().getUniqueId()).getPlayerConfig();

        String nickname = _player.getString("nickname");
        int level = _player.getInt("level");
        plugin.sendConsoleMsg(nickname + " joined with level: " + level);

        // String nickname = plugin.getConfig().getString(playerUUID + ".nickname");
        if(nickname == "" || nickname == null) {
            nickname = e.getPlayer().getName();
        }

        e.setJoinMessage("\u00a72[+] \u00a77" + nickname + " \u00a7ejoined!");
        e.getPlayer().setPlayerListName(nickname);
        e.getPlayer().setDisplayName(nickname);

        String[] motd = plugin.getMOTD().replace("$", "\u00a7").split("(nl)");
        for(String motdPiece : motd)
           plugin.sendPlayerMessage(e.getPlayer(), motdPiece);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        PlayerConfiguration _playerConfig = new PlayerConfiguration(e.getPlayer().getUniqueId());
        FileConfiguration _player = _playerConfig.getPlayerConfig();

        String nickname = _player.getString("nickname");
        if(nickname == "" || nickname == null) {
            nickname = e.getPlayer().getName();
        }

        _playerConfig.savePlayerConfig();

        e.setQuitMessage("\u00a74[-] \u00a77" + nickname + " \u00a7edisconnected!");
    }

    @EventHandler
    public void onPlayerKick(PlayerKickEvent e) {
        FileConfiguration _player = new PlayerConfiguration(e.getPlayer().getUniqueId()).getPlayerConfig();

        String nickname = _player.getString("nickname");
        if(nickname == "" || nickname == null) {
            nickname = e.getPlayer().getName();
        }

        e.setLeaveMessage("\u00a74[-] \u00a77" + nickname + " \u00a7ekicked! (" + e.getReason() + ")");
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        PlayerConfiguration _playerConfig = new PlayerConfiguration(e.getPlayer().getUniqueId());
        FileConfiguration _player = _playerConfig.getPlayerConfig();

        String nickname = _player.getString("nickname");
        if(nickname == "" || nickname == null) {
            nickname = e.getPlayer().getName();
        }

        String colorMessage = e.getMessage().replace("$", "\u00a7");

        e.setFormat("\u00a77" + nickname + "\u00a7f: " + colorMessage);
    }
}
