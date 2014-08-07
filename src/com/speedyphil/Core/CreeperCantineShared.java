package com.speedyphil.Core;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.speedyphil.LangzeitEvent.LangzeitCommandListener;
import com.speedyphil.LangzeitEvent.LangzeitConfiguration;
import com.speedyphil.LangzeitEvent.LangzeitPlayerListener;


public class CreeperCantineShared extends JavaPlugin
{
	private static LangzeitCommandListener lzCL = new LangzeitCommandListener();
	private static LangzeitPlayerListener lzPL = new LangzeitPlayerListener();
	private static LangzeitConfiguration lzCF = new LangzeitConfiguration();

	public static String pluginname = "LangzeitEvent";	
	
	private static Plugin plugin;
	private PluginManager pm;
	
	public void onEnable()
	{
		
			// Alles was beim Start getan werden muss hier rein c:
			this.pm = Bukkit.getPluginManager();
			this.plugin = this;
		
		
	}
	
	public void onDisable()
	{

	}
	
	public static LangzeitConfiguration getLangzeitConfiguration() {
		return lzCF;
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
	
	
	// Ist Enabled
	public static boolean Enabled(){
		FileConfiguration config = plugin.getConfig();
		String result = config.getString("enable-langzeitevent");
		if(result == "true") return true; else return false;	
	}
}

