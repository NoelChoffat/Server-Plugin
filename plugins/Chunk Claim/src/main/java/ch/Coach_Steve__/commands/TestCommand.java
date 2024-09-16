package ch.Coach_Steve__.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        String message = "Hallo Welt";

        commandSender.sendMessage(message);
        return false;
    }
}
