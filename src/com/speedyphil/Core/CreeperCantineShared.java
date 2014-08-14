package com.speedyphil.Core;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.speedyphil.LangzeitEvent.LangzeitCommandListener;
import com.speedyphil.LangzeitEvent.LangzeitConfiguration;
import com.speedyphil.LangzeitEvent.LangzeitManager;
import com.speedyphil.LangzeitEvent.LangzeitPlayerListener;
import com.speedyphil.WebSigns.ForumSigns;
import com.speedyphil.WebSigns.WebSigns;


public class CreeperCantineShared extends JavaPlugin
{
	private static LangzeitCommandListener lzCL = new LangzeitCommandListener();
	private static LangzeitPlayerListener lzPL = new LangzeitPlayerListener();
	private static LangzeitConfiguration lzCF = new LangzeitConfiguration();
	private static LangzeitManager lzMG;

	private static String pluginname = "LangzeitEvent";
	
	
	private static Plugin plugin;
	private PluginManager pm;
	private static FileConfiguration config;
	
	public void onEnable()
	{

		// Alles was beim Start getan werden muss hier rein c:
		this.pm = Bukkit.getPluginManager();
		this.plugin = this;
		System.out.println("######################################################################################################################");
		enablePex();
		enableWorldEdit();
		loadConfig();
		
		ForumSigns fs = new ForumSigns();
		WebSigns ws = new WebSigns();
		
		pm.registerEvents(fs, this);
		pm.registerEvents(ws, this);
		
		lzCF.createConfig();
		lzMG = new LangzeitManager();
		if(lzMG.isEnabled())
		{
			lzMG.loadBlacklist();
			pm.registerEvents(lzPL, this);
		}
		this.getCommand("le").setExecutor(lzCL);

	}
	
	public void onDisable()
	{
		lzMG.saveBlacklist();
	}
	

	
	private void enablePex()
	{
		Plugin pl = Bukkit.getPluginManager().getPlugin("PermissionsEx");
		if(pl != null && !pl.isEnabled())
		{
			System.out.println("[CreeperCantineShared] PermissionsEx wird gestartet!");
			Bukkit.getPluginManager().enablePlugin(pl);
		}
	}
	
	private void enableWorldEdit()
	{
		Plugin pl = Bukkit.getPluginManager().getPlugin("WorldEdit");
		if(pl != null && !pl.isEnabled())
		{
			System.out.println("[CreeperCantineShared] WorldEdit wird gestartet!");
			Bukkit.getPluginManager().enablePlugin(pl);
		}
		pl = Bukkit.getPluginManager().getPlugin("WorldGuard");
		if(pl != null && !pl.isEnabled())
		{
			System.out.println("[CreeperCantineShared] WorldEdit wird gestartet!");
			Bukkit.getPluginManager().enablePlugin(pl);
		}
	}
	
	public static WorldGuardPlugin getWorldGuard() {
		Plugin plugin = Bukkit.getPluginManager().getPlugin("WorldGuard");
		return (WorldGuardPlugin)plugin;
	}
	
	public static boolean isTeam(String player) {
		if(player.equals("CONSOLE"))
			return true;
		PermissionUser pu = PermissionsEx.getUser(player);
		if(pu.getGroups()[0].getName().equals("Owner") || pu.getGroups()[0].getName().equals("Admin") || pu.getGroups()[0].getName().equals("Developer") || pu.getGroups()[0].getName().equals("Moderator") || pu.getGroups()[0].getName().equals("Supporter") || pu.getGroups()[0].getName().equals("Architekt"))
			return true;
		return false;
	}
	
	public static boolean hasPermission(CommandSender sender, String permission)
	{
		if(sender.isOp())
			return true;
		if(sender instanceof Player)
		{
			Player p = (Player) sender;
			if(p.hasPermission(permission))
				return true;
		}
		return false;
	}
	
	public static boolean hasPermission(String uuid, String permission)
	{
		OfflinePlayer sender = (OfflinePlayer) Bukkit.getOfflinePlayer(UUID.fromString(uuid));
		if(sender.isOp())
			return true;
		if(sender instanceof Player)
		{
			Player p = (Player) sender;
			if(p.hasPermission(permission))
				return true;
		}
		return false;
	}
	
	// LŠd die Config
	public void loadConfig(){
		FileConfiguration cfg = this.getConfig();
		cfg.options().copyDefaults(true);
		this.saveConfig();
	}
	
	// Setzt Einstellung
	
	public boolean setConfig(String path, String value){
		FileConfiguration cfg = this.getConfig();
		cfg.options().copyDefaults(true);
		cfg.set(path, value);
		this.saveConfig();
		return true;
	}
	
	// LŠd Einstellung aus Config 
	public static String configString(String value){
		FileConfiguration config = plugin.getConfig();
		return config.getString(value);
	}
	
	// Schreibt etwas in den Log
	public static boolean writeToLog(String s){
		System.out.println( "["+ pluginname + "] " +s );
		return true;
	}
	
	public static Plugin getPlugin() {
		return plugin;
	}
	
	public static LangzeitConfiguration getLangzeitConfiguration() {
		return lzCF;
	}
	
	public static LangzeitManager getLangzeitManager() {
		return lzMG;
	}
}

