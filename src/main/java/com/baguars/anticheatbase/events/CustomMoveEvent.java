package com.baguars.anticheatbase.events;

import com.baguars.anticheatbase.AntiCheatBase;
import com.baguars.anticheatbase.managers.Cuboid;
import com.baguars.anticheatbase.managers.CustomLocation;
import com.baguars.anticheatbase.managers.CustomPlayer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;


public class CustomMoveEvent extends Event {
    private static final HandlerList handlers = new HandlerList();


    Player player;
    CustomPlayer cp;
    double deltaX,deltaY,deltaZ;
    double deltaYaw,deltaPitch,lastDeltaYaw,lastDeltaPitch;
    double deltaXZ,lastDeltaXZ;
    double lastDeltaX,lastDeltaY,lastDeltaZ;
    Location to,from;
    boolean serverGround,clientGround;

    public CustomMoveEvent(Player p, Location newLoc) {
        cp = AntiCheatBase.getInstance().getPlayerManager().getPlayer( p );
        this.player = p;
        this.to = newLoc;
        this.from = cp.getLastLocation();
        if( from == null ){
            cp.updateLastLocation(newLoc);
            return;
        }

        deltaX = Math.abs( to.getX() - from.getX() );
        deltaY = Math.abs( to.getY() - from.getY() );
        deltaZ = Math.abs( to.getZ() - from.getZ() );
        lastDeltaX = cp.lastDeltaX;
        lastDeltaY = cp.lastDeltaY;
        lastDeltaZ = cp.lastDeltaZ;
        lastDeltaYaw = cp.lastDeltaYaw;
        lastDeltaPitch = cp.lastDeltaPitch;

        deltaYaw = Math.abs( to.getYaw() - from.getYaw() );
        deltaPitch = Math.abs( to.getPitch() - from.getPitch() );

        deltaXZ = Math.hypot(deltaX,deltaZ);
        lastDeltaXZ = Math.hypot(lastDeltaX,lastDeltaZ);

        final Cuboid nearBox = new Cuboid(new CustomLocation(to.getX(),to.getY(),to.getZ(),to.getYaw(),to.getPitch())).expand(0.5, 0.55, 0.5);
        if(nearBox.checkBlocks(player.getWorld(), material -> material == Material.AIR)){
            serverGround = false;
            cp.serverAirTick++;
        }else {
            serverGround = true;
            cp.serverAirTick = 0;
        }

        if(player.isOnGround()){
            clientGround = true;
            cp.clientAirTick = 0;
        }else {
            clientGround = false;
            cp.clientAirTick++;
        }

        cp.updateLastLocation( newLoc );
        cp.lastDeltaX = deltaX;
        cp.lastDeltaY = deltaY;
        cp.lastDeltaZ = deltaZ;
        cp.lastDeltaYaw = deltaYaw;
        cp.lastDeltaPitch = deltaPitch;
    }

    public Player getPlayer(){
        return player;
    }

    public CustomPlayer getCustomPlayer(){
        return cp;
    }

    public Location getTo(){
        return to;
    }

    public Location getFrom(){
        return from;
    }

    public boolean isServerGround(){
        return serverGround;
    }

    public boolean isClientGround(){
        return clientGround;
    }

    public int getServerAirTick(){
        return cp.serverAirTick;
    }

    public int getClientAirTick(){
        return cp.clientAirTick;
    }

    public double getDeltaX() {
        return deltaX;
    }

    public double getDeltaY() {
        return deltaY;
    }

    public double getDeltaZ() {
        return deltaZ;
    }

    public double getLastDeltaX() {
        return lastDeltaX;
    }

    public double getLastDeltaY() {
        return lastDeltaY;
    }

    public double getLastDeltaZ() {
        return lastDeltaZ;
    }

    public double getDeltaXZ(){
        return deltaXZ;
    }

    public double getLastDeltaXZ(){
        return lastDeltaXZ;
    }

    public double getDeltaYaw(){
        return deltaYaw;
    }

    public double getLastDeltaYaw(){
        return lastDeltaYaw;
    }

    public double getDeltaPitch(){
        return deltaPitch;
    }

    public double getLastDeltaPitch(){
        return lastDeltaPitch;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
