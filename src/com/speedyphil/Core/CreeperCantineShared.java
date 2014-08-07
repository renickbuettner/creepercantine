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
		this.pm = Bukkit.getPluginManager();
		this.plugin = this;
		
	}
	
	public void onDisable()
	{

	}
	
	public static LangzeitConfiguration getLangzeitConfiguration() {
		return lzCF;
	}
	
	public void loadConfig(){
		FileConfiguration cfg = this.getConfig();
		cfg.options().copyDefaults(true);
		this.saveConfig();
	}
	
	public static String configstring(String value){
		FileConfiguration config = plugin.getConfig();
		return config.getString(value);
	}
	
	public static boolean WriteToLog(String s){
		System.out.println( "["+ pluginname + "] " +s );
		return true;
	}
	
}
