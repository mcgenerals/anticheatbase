package com.baguars.anticheatbase.managers;

import com.baguars.anticheatbase.AntiCheatBase;
import com.baguars.anticheatbase.check.Check;
import org.bukkit.plugin.PluginManager;

import java.util.ArrayList;

public class CheckManager {

    ArrayList<Check> checks;
    AntiCheatBase instance;
    PluginManager pm;

    public CheckManager(){
        checks = new ArrayList<>();
        instance = AntiCheatBase.getInstance();
        pm = instance.getServer().getPluginManager();
    }

    public void addCheck(Check check){
        checks.add( check );
    }

    public void setupChecks(){
        for( Check c : checks ){
            pm.registerEvents( c , instance );
        }
    }

}
