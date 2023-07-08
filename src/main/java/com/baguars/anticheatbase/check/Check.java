package com.baguars.anticheatbase.check;

import com.baguars.anticheatbase.AntiCheatBase;
import com.baguars.anticheatbase.managers.CustomPlayer;
import com.baguars.anticheatbase.managers.PlayerManager;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Check implements Listener {

    String name,type;

    public Check(String name,String type){
        this.name = name;
        this.type = type;
    }

    public void flag(Player p){
        send(p,"NONE");
    }

    public void flag(Player p,String info){
        send(p,info);
    }

    private void send(Player p,String info){
        PlayerManager pm = AntiCheatBase.getInstance().getPlayerManager();
        CustomPlayer violator = pm.getPlayer( p );
        int vl = violator.onFailed( name + " (" + type + ")" );
        String message = AntiCheatBase.getInstance().getPrefix() + "§c " + p.getName() + "§7 failed §c" + name + " (" + type + ") §7[§fx" + vl + "§7]";
        TextComponent tc = new TextComponent( message );
        tc.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT , new ComponentBuilder("§7Information\n§f" + info + "\n\n§7CheckVL:§f " + vl + "\n§7Total:§f " + violator.getTotalVL() + "\n§7Click To Teleport" ).create() ) );
        tc.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND , "/tp " + p.getName() ) );
        for( Player player : Bukkit.getOnlinePlayers() ){
            if( pm.getPlayer( player ).getVerboseStatus() ){
                player.spigot().sendMessage( tc );
            }
        }
    }

}
