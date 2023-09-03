package com.q13x.nloginjoinmessages;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class NLoginJoinMessages extends JavaPlugin {
    public static String AUTHOR_NAME;
    public static String VERSION;
    public static JoinMessageCache<String> JOIN_MESSAGE_CACHE;

    private final Logger logger = this.getLogger();
    private static NLoginJoinMessages instance;

    @Override
    public void onEnable() {
        InputStream is = this.getResource("metadata.properties");
        Properties p = new Properties();
        try {
            p.load(is);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        AUTHOR_NAME = p.getProperty("author");
        VERSION = p.getProperty("version");
        JOIN_MESSAGE_CACHE = new JoinMessageCache<>();

        NLoginJoinMessages.instance = this;
        try {
            Class.forName("com.nickuc.login.api.nLoginAPIHolder");
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Couldn't find nLogin v10+ in your game server instance! Have you updated your nLogin version yet? Disabling...");
            this.getPluginLoader().disablePlugin(this);
        }
        this.getServer().getPluginManager().registerEvents(new EventListener(), this);
        logger.info(String.format("Successfully loaded %s %s by %s!", this.getName(), NLoginJoinMessages.VERSION, NLoginJoinMessages.AUTHOR_NAME));
    }

    @Override
    public void onDisable() {
        logger.info("Unloading...");
        NLoginJoinMessages.instance = null;
        logger.info(String.format("Successfully unloaded %s %s by %s!", this.getName(), NLoginJoinMessages.VERSION, NLoginJoinMessages.AUTHOR_NAME));
    }

    public static NLoginJoinMessages getInstance() {
        return instance;
    }
}
