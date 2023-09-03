package com.q13x.nloginjoinmessages;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class JoinMessageCache<T> {
    private final HashMap<Player, T> cache;

    public JoinMessageCache() {
        this.cache = new HashMap<>();
    }

    @Nullable
    public T get(Player player) {
        return cache.get(player);
    }

    @Nullable
    public T set(Player player, T value) {
        return cache.put(player, value);
    }

    @Nullable
    public T remove(Player player) {
        return cache.remove(player);
    }
}
