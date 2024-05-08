package com.kirik.ttcraft.events;

import com.kirik.ttcraft.main.TTCraft;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import java.util.UUID;

public class PlayerListener implements Listener {

    private final TTCraft plugin;

    public PlayerListener(TTCraft plug) {
        plugin = plug;
    }

    @EventHandler()
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        UUID playerUUID = player.getUniqueId();

        // Loads defaults for additional attributes
        // TTPlayer ttplayer = new TTPlayer(playerUUID);

       /* if (playerUUID.equals(UUID.fromString("3f050fe1-a454-4a30-8c8d-4acc691f2b2d")) || // kirik__
                playerUUID.equals(UUID.fromString("dd871f27-e02d-46b2-b505-3b931aac6daa"))) { // circusfreak83
            try {
                plugin.getServer().getWorld(player.getWorld().getName()).strikeLightningEffect(player.getLocation());
            } catch (NullPointerException ex) {
                plugin.sendConsoleError("Oopsies, can't strike lightning... " + ex);
            }
        }*/

        // plugin.onlinePlayers.put(playerUUID, ttplayer);

        String nickname = plugin.getConfig().getString(playerUUID + ".nickname");
        if(nickname == "" || nickname == null) {
            nickname = player.getName();
        }

        e.setJoinMessage("\u00a72[+] \u00a77" + nickname + " \u00a7ejoined!");
        player.setPlayerListName(nickname);
        player.setDisplayName(nickname);

        String[] motd = plugin.getMOTD().replace("$", "\u00a7").split("(nl)");
        for(String motdPiece : motd)
           plugin.sendPlayerMessage(player, motdPiece);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        UUID playerUUID = player.getUniqueId();

        // TTPlayer ttplayer = plugin.onlinePlayers.get(playerUUID);
        /*try {
            ttplayer.playerConfig.save(ttplayer.getPlayerFile());
            plugin.sendConsoleMsg("Saved player file " + playerUUID);
        } catch (IOException | NullPointerException ex) {
            plugin.sendConsoleError("Failed to save player file " + playerUUID + ". Reason: " + ex);
        }*/

        // plugin.onlinePlayers.remove(playerUUID);

        String nickname = plugin.getConfig().getString(playerUUID + ".nickname");
        if(nickname == "" || nickname == null) {
            nickname = player.getName();
        }

        e.setQuitMessage("\u00a74[-] \u00a77" + nickname + " \u00a7edisconnected!");

        /*ttplayer.getPlayerTag().destroy();*/
    }

    @EventHandler
    public void onPlayerKick(PlayerKickEvent e) {
        Player player = e.getPlayer();
        UUID playerUUID = player.getUniqueId();

        // TTPlayer ttplayer = plugin.onlinePlayers.get(playerUUID);
        /*try {
            ttplayer.playerConfig.save(ttplayer.getPlayerFile());
            plugin.sendConsoleMsg("Saved player file " + playerUUID);
        } catch (IOException | NullPointerException ex) {
            plugin.sendConsoleError("Failed to save player file " + playerUUID + ". Reason: " + ex);
        }

        plugin.onlinePlayers.remove(playerUUID);*/

        String nickname = plugin.getConfig().getString(playerUUID + ".nickname");
        if(nickname == "" || nickname == null) {
            nickname = player.getName();
        }

        e.setLeaveMessage("\u00a74[-] \u00a77" + nickname + " \u00a7ekicked! (" + e.getReason() + ")");
        /*ttplayer.getPlayerTag().destroy();*/
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        UUID playerUUID = player.getUniqueId();

        // TTPlayer ttplayer = plugin.onlinePlayers.get(playerUUID);

        String nickname = plugin.getConfig().getString(playerUUID + ".nickname");
        if(nickname == "" || nickname == null) {
            nickname = player.getName();
        }

        String colorMessage = e.getMessage().replace("$", "\u00a7");

        e.setFormat("\u00a77" + nickname + "\u00a7f: " + colorMessage);
    }

    /*@EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerMove(PlayerMoveEvent e) {
        TTPlayer ttplayer = plugin.onlinePlayers.get(e.getPlayer().getUniqueId());

        ttplayer.getPlayerTag().update();
    }*/
}
