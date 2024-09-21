package chunkers.chunkClaim;

import chunkers.chunkClaim.commands.TestCommand;
import chunkers.chunkClaim.commands.ReadChunk;
import org.bukkit.plugin.java.JavaPlugin;


public final class ChunkClaim extends JavaPlugin {

    @Override
    public void onEnable() {
        // chunk speichern
        if (getCommand("claim") != null) {
            getCommand("claim").setExecutor(new TestCommand());
        } else {
            getLogger().severe("Command 'claim' could not be found in plugin.yml");
        }

        // Die config-datei lesen
        if (getCommand("who") != null) {
            getCommand("who").setExecutor(new ReadChunk());
        } else {
            getLogger().severe("Command 'who' could not be found in plugin.yml");
        }
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
