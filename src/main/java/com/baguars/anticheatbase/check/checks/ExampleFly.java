package com.baguars.anticheatbase.check.checks;

import com.baguars.anticheatbase.check.Check;
import com.baguars.anticheatbase.events.CustomMoveEvent;
import org.bukkit.event.EventHandler;

public class ExampleFly extends Check {
    public ExampleFly(String name, String type) {
        super(name, type);
    }
    /*
        This check is easly to false. 
        This is just "example".
    */

    @EventHandler
    public void onMove(CustomMoveEvent e){ 
        if( e.getServerAirTick() < 12 )return; 
        // you can change it from 12 to 7. but I will suggest 12 because it may false when jummping
        if( e.getTo().getY() >= e.getFrom().getY() ){ 
            // if they are NOT going down. they are cheating.
            flag( e.getPlayer() , "airtick=" + e.getServerAirTick() );
        }
    }
}
