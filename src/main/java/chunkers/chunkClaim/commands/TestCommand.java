package chunkers.chunkClaim.commands;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            Chunk chunk = player.getLocation().getChunk();

            File file = new File(Bukkit.getServer().getPluginManager().getPlugin("chunkClaim").getDataFolder(), "chunkClaims.yml");
            FileConfiguration config = YamlConfiguration.loadConfiguration(file);

            String playerName = player.getName();
            String chunkKey = "claims." + playerName;

            // Lade die Liste der Chunks oder erstelle eine neue, wenn sie nicht existiert
            List<Map<String, Object>> chunks = (List<Map<String, Object>>) config.getList(chunkKey);
            if (chunks == null) {
                chunks = new ArrayList<>();
            }

            // Füge den neuen Chunk hinzu
            Map<String, Object> newChunk = new HashMap<>();
            newChunk.put("world", chunk.getWorld().getName());
            newChunk.put("x", chunk.getX());
            newChunk.put("z", chunk.getZ());
            chunks.add(newChunk);
            config.set(chunkKey, chunks);

            // Speichern der Konfiguration in die Datei
            try {
                config.save(file);
                player.sendMessage("Der Chunk wurde erfolgreich gespeichert.");
            } catch (IOException e) {
                player.sendMessage("Fehler beim Speichern der Datei.");
                e.printStackTrace();
            }

            return true;
        } else {
            commandSender.sendMessage("Nur Spieler können diesen Befehl ausführen.");
            return false;
        }
    }
}