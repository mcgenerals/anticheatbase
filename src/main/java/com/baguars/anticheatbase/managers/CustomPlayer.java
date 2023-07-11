package com.baguars.anticheatbase.managers;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class CustomPlayer {

    Player player;
    boolean verbose = false;
    public int damageTick = 0;

    Location lastLocation;

    public int totalVL;
    public HashMap<String,Integer> checksVL = new HashMap<>();

    public int getTotalVL(){
        return totalVL;
    }

    public int getCheckVL(String check){
        if( checksVL.get( check ) == null ){
            checksVL.put( check , 0 );
            return 0;
        }
        return checksVL.get( check );
    }

    @Deprecated
    public int onFailed(String check){
        totalVL++;
        int newVL = getCheckVL( check ) + 1;
        checksVL.put( check , newVL );
        return newVL;
    }

    public int serverAirTick,clientAirTick = 0;
    public double lastDeltaX,lastDeltaY,lastDeltaZ;

    public double lastDeltaYaw,lastDeltaPitch;

    public int getDamageTick(){
        return damageTick;
    }

    public Location getLastLocation(){
        return lastLocation;
    }

    public void updateLastLocation(Location newLoc){
        this.lastLocation = newLoc;
    }

    public CustomPlayer(Player p){
        this.player = p;
    }

    public Player getPlayer(){
        return player;
    }

    public boolean getVerboseStatus(){
        return verbose;
    }

    public void setVerboseStatus(boolean newbool){
        this.verbose = newbool;
    }

}
