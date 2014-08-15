package com.speedyphil.LangzeitEvent;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.speedyphil.Core.CreeperCantineShared;

public class LangzeitPlayerListener implements Listener {

	

	@EventHandler
	public void onDeath(PlayerDeathEvent e) //SPIELER AUF BLACKLIST SCHREIBEN WENN ER STIRBT
	{
		LangzeitManager lm = CreeperCantineShared.getLangzeitManager();

		Player p = (Player) e.getEntity();
		String world = p.getWorld().getName();
		
		if( world.equals( lm.getWorldName() ) ){
			lm.blacklistPlayer( p.getUniqueId().toString() );
			p.sendMessage( ChatColor.GOLD + "[LangzeitEvent] " + ChatColor.GRAY + " Du bist gestorben! Leider bist du nun ausgeschieden." );
		}
	}
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event) //AUSERHALB DER EVENT WELT SPAWNEN BEIM STERBEN
	{
		LangzeitManager lm = CreeperCantineShared.getLangzeitManager();
		if(lm.isInEvent(event.getPlayer()))
		{
			event.setRespawnLocation(lm.getSpawn());
		}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) //BLACKLISTETE NAHRUNGSMITTEL VERHINDERN
	{
		if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)
		{
			LangzeitManager lm = CreeperCantineShared.getLangzeitManager();
			if(lm.isInEvent(event.getPlayer()))
			{
				if(event.getItem() == null)
					return;
				if(lm.isConsumeBlacklisted(event.getItem().getType().name()))
				{
					
					event.setCancelled(true);
					event.getPlayer().sendMessage(ChatColor.RED+"Du darfst dieses Item nicht konsumieren!");
				}
			}
		}
	}
	
	@EventHandler
	public void onCommandPreProcess(PlayerCommandPreprocessEvent event) //WHITELIST FUER BEFEHLE DIE MAN WAEREND DEM EVENT VERWENDEN DARF
	{
		LangzeitManager lm = CreeperCantineShared.getLangzeitManager();
		if(lm.isInEvent(event.getPlayer()))
		{
			if(!event.getPlayer().isOp())
			{
				if(!lm.isCommandWhiteisted(event.getMessage().replace("/", "").replace(" ", "_")))
				{
					event.setCancelled(true);
					event.getPlayer().sendMessage(ChatColor.RED+"Du darfst diesen Befehl waerend dem Event nicht verwenden!");
				}
			}
		}
	}
}
