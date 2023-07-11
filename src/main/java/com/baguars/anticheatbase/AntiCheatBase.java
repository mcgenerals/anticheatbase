package com.baguars.anticheatbase;

import com.baguars.anticheatbase.check.checks.ExampleFly;
import com.baguars.anticheatbase.commands.VerboseStatusCommand;
import com.baguars.anticheatbase.events.CustomMoveEvent;
import com.baguars.anticheatbase.managers.CheckManager;
import com.baguars.anticheatbase.managers.PlayerManager;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public final class AntiCheatBase extends JavaPlugin implements Listener {


    public String prefix = "";
    public static AntiCheatBase instance;
    public PlayerManager playerManager;
    public CheckManager checkManager;

    public Executor packetThread;

    @Override
    public void onEnable() {
        instance = this;
        prefix = "§8[§cAntiCheat§8]§r";
        getServer().getPluginManager().registerEvents(this,this);
        packetThread = Executors.newSingleThreadExecutor();
        playerManager = new PlayerManager();
        checkManager = new CheckManager();
        checkManager.addCheck( new ExampleFly("Fly","Example") ); // how to add checks.
        checkManager.setupChecks();

        for( Player p : Bukkit.getOnlinePlayers() ){
            playerManager.registerPlayer( p );
        }


        // Register Command
        getCommand("verbose").setExecutor( new VerboseStatusCommand() ); // I registered /verbose command

        // Protocollib Setup
        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(  this,
                ListenerPriority.NORMAL,
                PacketType.Play.Client.POSITION,
                PacketType.Play.Client.POSITION_LOOK) {
            @Override
            public void onPacketReceiving(PacketEvent event) {
                packetThread.execute(() ->{
                    if (event.getPacketType() == PacketType.Play.Client.POSITION || event.getPacketType() == PacketType.Play.Client.POSITION_LOOK) {
                        Player player = event.getPlayer();
                        double x = event.getPacket().getDoubles().read(0);
                        double y = event.getPacket().getDoubles().read(1);
                        double z = event.getPacket().getDoubles().read(2);
                        float yaw = event.getPacket().getFloat().read(0);
                        float pitch = event.getPacket().getFloat().read(1);
                        CustomMoveEvent customEvent = new CustomMoveEvent(player,new Location(player.getWorld(),x,y,z,yaw,pitch));
                        getServer().getPluginManager().callEvent( customEvent );
                    }
                });
            }

            @Override
            public void onPacketSending(PacketEvent event) {
                // No action needed in this example
            }
        });
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        getPlayerManager().registerPlayer( e.getPlayer() );
    }

    @EventHandler
    public void onJoin(PlayerQuitEvent e){
        getPlayerManager().unregisterPlayer( e.getPlayer() );
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e){
        if( e.getEntity() instanceof  Player ){
            playerManager.getPlayer( ((Player) e.getEntity()).getPlayer() ).damageTick = 0;
        }
    }

    public static AntiCheatBase getInstance(){
        return instance;
    }

    public PlayerManager getPlayerManager(){
        return playerManager;
    }

    public CheckManager getCheckManager(){
        return checkManager;
    }

    public String getPrefix(){
        return prefix;
    }

}
