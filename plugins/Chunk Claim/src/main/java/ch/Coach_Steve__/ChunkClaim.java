package ch.Coach_Steve__;

import ch.Coach_Steve__.commands.TestCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class ChunkClaim extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("test").setExecutor(new TestCommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
