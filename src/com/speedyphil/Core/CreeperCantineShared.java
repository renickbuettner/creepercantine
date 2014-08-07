package com.speedyphil.Core;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.speedyphil.LangzeitEvent.LangzeitCommandListener;
import com.speedyphil.LangzeitEvent.LangzeitConfiguration;
import com.speedyphil.LangzeitEvent.LangzeitManager;
import com.speedyphil.LangzeitEvent.LangzeitPlayerListener;


public class CreeperCantineShared extends JavaPlugin
{
	private static LangzeitCommandListener lzCL = new LangzeitCommandListener();
	private static LangzeitPlayerListener lzPL = new LangzeitPlayerListener();
	private static LangzeitConfiguration lzCF = new LangzeitConfiguration();
	private static LangzeitManager lzMG = new LangzeitManager();

	private static String pluginname = "LangzeitEvent";
	
	
	private static Plugin plugin;
	private PluginManager pm;
	private static FileConfiguration config;
	
	public void onEnable()
	{

		// Alles was beim Start getan werden muss hier rein c:
		this.pm = Bukkit.getPluginManager();
		this.plugin = this;
		
		loadConfig();
		
		lzCF.createConfig();
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
	

	
	// LŠd die Config
	public void loadConfig(){
		FileConfiguration cfg = this.getConfig();
		cfg.options().copyDefaults(true);
		this.saveConfig();
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

