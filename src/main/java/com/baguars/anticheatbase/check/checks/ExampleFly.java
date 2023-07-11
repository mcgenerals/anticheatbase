package com.baguars.anticheatbase.check.checks;

import com.baguars.anticheatbase.check.Check;
import com.baguars.anticheatbase.events.CustomMoveEvent;
import org.bukkit.event.EventHandler;

public class ExampleFly extends Check {
    public ExampleFly(String name, String type) {
        super(name, type);
    }

    @EventHandler
    public void onMove(CustomMoveEvent e){
        if( e.getCustomPlayer().getDamageTick() < 20 )return;
        if( e.getServerAirTick() < 12 )return;
        if( e.getTo().getY() >= e.getFrom().getY() ){
            flag( e.getPlayer() , "airtick=" + e.getServerAirTick() );
        }
    }
}
