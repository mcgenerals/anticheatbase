package com.baguars.anticheatbase.commands;

import com.baguars.anticheatbase.AntiCheatBase;
import com.baguars.anticheatbase.managers.CustomPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VerboseStatusCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if( sender instanceof Player && sender.hasPermission("anticheatbase.admin" ) ){
            AntiCheatBase instance = AntiCheatBase.getInstance();
            CustomPlayer cp = instance.getPlayerManager().getPlayer( ((Player) sender).getPlayer() );
            cp.setVerboseStatus(!cp.getVerboseStatus());
            sender.sendMessage( instance.getPrefix() + "§a Verbose is " + ( cp.getVerboseStatus() ? "ON" : "§cOFF" ));
        }
        return true;
    }
}
