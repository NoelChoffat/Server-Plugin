package chunkers.chunkClaim;

import chunkers.chunkClaim.commands.TestCommand;
import org.bukkit.plugin.java.JavaPlugin;


public final class ChunkClaim extends JavaPlugin {

    @Override
    public void onEnable() {
        if (getCommand("test") != null) {
            getCommand("test").setExecutor(new TestCommand());
        } else {
            getLogger().severe("Command 'test' could not be found in plugin.yml");
        }
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
