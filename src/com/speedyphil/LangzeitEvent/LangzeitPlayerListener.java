package com.speedyphil.LangzeitEvent;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.speedyphil.Core.CreeperCantineShared;

public class LangzeitPlayerListener implements Listener {

	LangzeitManager lm = CreeperCantineShared.getLangzeitManager();

	@EventHandler
	public void onDeath(PlayerDeathEvent e){
		
		// Ob Spieler in LangzeitEvent Welt gestorben ist

		Player p = (Player) e.getEntity();
		
		String world = p.getWorld().getName();
		
		if( world.equals( lm.getWorldName() ) ){
		
			lm.blacklistPlayer( p.getUniqueId().toString() );
			
			p.sendMessage( ChatColor.GOLD + "[LangzeitEvent] " + ChatColor.GRAY + " Du bist gestorben! Leider bist du nun ausgeschieden." );
			
		}
		
	}
	
}
