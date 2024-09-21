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

public class ReadChunk implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        // Überprüfen, ob der Sender ein Spieler ist
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            // Den aktuellen Chunk des Spielers erhalten
            Chunk chunk = player.getLocation().getChunk();

            // Dateipfad für das YAML-File (muss dasselbe sein wie beim Speichern)
            File file = new File(Bukkit.getServer().getPluginManager().getPlugin("chunkClaim").getDataFolder(), "chunkClaims.yml");

            // Überprüfen, ob die Datei existiert
            if (!file.exists()) {
                player.sendMessage("Die chunkClaims.yml Datei existiert nicht.");
                return false;
            }

            // YAML-Konfiguration laden
            FileConfiguration config = YamlConfiguration.loadConfiguration(file);

            // Den aktuellen Chunk durchsuchen, um zu sehen, ob er einem Spieler gehört
            String worldName = chunk.getWorld().getName();
            int chunkX = chunk.getX();
            int chunkZ = chunk.getZ();
            boolean found = false;

            // Durch die Konfiguration iterieren, um den Chunk zu finden
            for (String playerName : config.getConfigurationSection("claims").getKeys(false)) {
                String path = "claims." + playerName;

                String savedWorld = config.getString(path + ".world");
                int savedX = config.getInt(path + ".x");
                int savedZ = config.getInt(path + ".z");

                // Überprüfen, ob Welt, X und Z übereinstimmen
                if (savedWorld.equals(worldName) && savedX == chunkX && savedZ == chunkZ) {
                    player.sendMessage("Dieser Chunk gehört " + playerName);

                    //prüfen, ob es der eigene chunk ist
                    // -------- TODO ------------------
                    // anhand von Besitz gewisse aktionen verweigern.
                    // -----------------------------------
                    if (player.getName().equals(playerName)) {
                        player.sendMessage("Dieser Chunk gehört dir!");
                    } else {
                        player.sendMessage("Dieser Chunk gehört *nicht* dir!");
                    }

                    found = true;
                    break;
                }
            }

            // Falls der Chunk keinem Spieler gehört
            if (!found) {
                player.sendMessage("Dieser Chunk gehört niemandem. Benutze /claim, um ihn für dich zu claimen.");
            }

            return true;
        } else {
            // Falls der Befehl nicht von einem Spieler ausgeführt wird
            commandSender.sendMessage("Nur Spieler können diesen Befehl ausführen.");
            return false;
        }
    }
}
