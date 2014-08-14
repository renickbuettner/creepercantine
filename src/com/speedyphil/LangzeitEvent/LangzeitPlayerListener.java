package com.speedyphil.LangzeitEvent;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import com.speedyphil.Core.CreeperCantineShared;

public class LangzeitPlayerListener implements Listener {

	

	@EventHandler
	public void onDeath(PlayerDeathEvent e){
		LangzeitManager lm = CreeperCantineShared.getLangzeitManager();
		// Ob Spieler in LangzeitEvent Welt gestorben ist

		Player p = (Player) e.getEntity();
		
		String world = p.getWorld().getName();
		
		if( world.equals( lm.getWorldName() ) ){
		
			lm.blacklistPlayer( p.getUniqueId().toString() );
			
			p.sendMessage( ChatColor.GOLD + "[LangzeitEvent] " + ChatColor.GRAY + " Du bist gestorben! Leider bist du nun ausgeschieden." );
			
		}
		
	}
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event)
	{
		LangzeitManager lm = CreeperCantineShared.getLangzeitManager();
		if(event.getPlayer().getLocation().getWorld() == lm.getWorld())
		{
			event.setRespawnLocation(lm.getSpawn());
		}
	}
	
	@EventHandler
	public void onPlayerEat(PlayerItemConsumeEvent event)
	{
		LangzeitManager lm = CreeperCantineShared.getLangzeitManager();
		if(event.getPlayer().getLocation().getWorld() == lm.getWorld())
		{
			if(lm.isConsumeBlacklisted(event.getItem().getType().name()))
			{
				event.setCancelled(true);
			}
		}
	}
}
