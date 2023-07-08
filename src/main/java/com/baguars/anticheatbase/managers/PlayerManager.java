package com.baguars.anticheatbase.managers;

import com.baguars.anticheatbase.AntiCheatBase;
import org.bukkit.entity.Player;

import java.util.concurrent.ConcurrentLinkedQueue;

public class PlayerManager {

    ConcurrentLinkedQueue<CustomPlayer> players = new ConcurrentLinkedQueue<>();

    public PlayerManager(){

    }

    @Deprecated
    public CustomPlayer getPlayerNullIfNotRegistered(Player p){
        for( CustomPlayer cp : players ){
            if( cp.getPlayer() == p ){
                return cp;
            }
        }
        return null;
    }

    public CustomPlayer getPlayer(Player p){
        CustomPlayer cp = getPlayerNullIfNotRegistered( p );
        if( cp != null ){
            return cp;
        }
        return registerPlayer( p );
    }

    @Deprecated
    public CustomPlayer registerPlayer(Player p){
        CustomPlayer cp = new CustomPlayer( p );
        players.add( cp );
        return cp;
    }

    @Deprecated
    public void unregisterPlayer(Player p){
        CustomPlayer cp = getPlayerNullIfNotRegistered( p );
        if( cp != null ){
            players.remove( cp );
        }
    }


}
