package com.q13x.nloginjoinmessages;

import com.nickuc.login.api.event.bukkit.auth.LoginEvent;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class EventListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        NLoginJoinMessages.JOIN_MESSAGE_CACHE.set(event.getPlayer(), event.getJoinMessage() == null ? ChatColor.YELLOW + event.getPlayer().getDisplayName() + " joined the game" : event.getJoinMessage());
        event.setJoinMessage(null);
    }

    @EventHandler
    public void onSuccessfulLogin(LoginEvent event) {
        event.getPlayer().getServer().broadcastMessage(NLoginJoinMessages.JOIN_MESSAGE_CACHE.get(event.getPlayer()));
        NLoginJoinMessages.JOIN_MESSAGE_CACHE.remove(event.getPlayer());
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        NLoginJoinMessages.JOIN_MESSAGE_CACHE.remove(event.getPlayer());
    }
}
