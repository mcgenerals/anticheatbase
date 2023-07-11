# AntiCheatBase
This is Base for anti-cheat development for 1.8.9 minecraft.
# Why our AntiCheatBase?
Packet Based. Asynchronous. Simple and more.
# How to Setup.
Just clone this repository.
Recommended IDEA is IntelliJ IDEA.
# How to create checks?
1. Create New Class in "checks"
2. Add "extends Check" ( please do like ExampleFly.java. )
3. Add this code (  please do like ExampleFly.java )
```java
@EventHandler
public void onMove(CustomMoveEvent e){
  //your checks here
}
```
4. How to flag player?
It's easy!
```java
flag(e.getPlayer(),"Informatnion");
```
5. Register check! Go AntiCheatBase.java and add this code in "onEable"
```
checkManager.addCheck( new ExampleFly("CheckName","CheckType") );
```
Please add it above this code.
```
checkManager.setupChecks(); 
```
6. Done! Build it and try :D

# Dependencies
ProtocolLib | https://www.spigotmc.org/resources/protocollib.1997/


# Thanks! 
Coboid.java and CustomLocation.java from https://github.com/DerRedstoner/CheatGuard
