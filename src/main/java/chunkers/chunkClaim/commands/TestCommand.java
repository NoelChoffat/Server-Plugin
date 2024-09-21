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

public class TestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        // Überprüfen, ob der Sender ein Spieler ist
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            // Den aktuellen Chunk des Spielers erhalten
            Chunk chunk = player.getLocation().getChunk();

            // Dateipfad für das YAML-File (kann angepasst werden)
            File file = new File(Bukkit.getServer().getPluginManager().getPlugin("chunkClaim").getDataFolder(), "chunkClaims.yml");

            // YAML-Konfiguration laden oder erstellen, falls sie nicht existiert
            FileConfiguration config = YamlConfiguration.loadConfiguration(file);

            // Den Spielernamen und die Chunk-Daten speichern (z.B. X und Z Koordinaten)
            String playerName = player.getName();
            String chunkKey = "claims." + playerName;
            config.set(chunkKey + ".world", chunk.getWorld().getName());
            config.set(chunkKey + ".x", chunk.getX());
            config.set(chunkKey + ".z", chunk.getZ());

            // Speichern der Konfiguration in die Datei
            try {
                config.save(file);
                player.sendMessage("Der aktuelle Chunk wurde erfolgreich in der YAML-Datei gespeichert.");
            } catch (IOException e) {
                player.sendMessage("Fehler beim Speichern der Datei.");
                e.printStackTrace();
            }

            return true;
        } else {
            // Falls der Befehl nicht von einem Spieler ausgeführt wird
            commandSender.sendMessage("Nur Spieler können diesen Befehl ausführen.");
            return false;
        }
    }
}
