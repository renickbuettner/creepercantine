package com.speedyphil.WebSigns;

import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.speedyphil.Core.CreeperCantineShared;

public class ForumSigns implements Listener {
	
	@EventHandler
	
	public void ForumSignPlace(SignChangeEvent e){
	
		Player p = e.getPlayer();
			
		if ( e.getLine(0).equalsIgnoreCase("[Forum]") || e.getLine(0).equalsIgnoreCase("[forum]") ) {
			
			// Wenn TopicID vorhanden
			
			if( e.getLine(1) != null ){
				
				// Zeile 1 verändern bzw. Farbig machen
				
				e.setLine(0, "§3[Forum]" );
				
				// Sende Message an Spieler
					
				p.sendMessage( ChatColor.DARK_AQUA + "[Forum] Du hast erfolgreich ein Beitrags-Schild erstellt." );
				
				// Nachricht in den Log schreiben
				
				CreeperCantineShared.writeToLog( p.getName() + " hat ein Forums-Schild mit der TopicID '" + e.getLine(1) + "' erstellt.");
				
			}
			else
			{
				
				// Sende Fehlernachricht an Spieler
			
				p.sendMessage( ChatColor.DARK_AQUA + "[Forum] " + ChatColor.RED + "Du hast die TopicID vergessen." );
				
			}
			
		}
			
	}
	
	@EventHandler
	public void ForumSignClick(PlayerInteractEvent e){
		
		Player p = e.getPlayer();
		
		if( e.getAction() == Action.RIGHT_CLICK_BLOCK ){
			
			if( e.getClickedBlock().getState() instanceof Sign ){
			
				// Schild in Variable laden
				
				Sign s = (Sign) e.getClickedBlock().getState();
				
				// Wenn Tag in erster Zeile des Schildes
				
				if( s.getLine(0).equals( ChatColor.DARK_AQUA + "[Forum]" ) || s.getLine(0).equals( ChatColor.DARK_AQUA + "[forum]" )  ){
					
					// Get Topic ID from Sign
					
					String topicid = s.getLine(1);
					
					if( topicid != null ){
						
						// Generiere Link
						
						String link = CreeperCantineShared.configString("forumurl-topic") + topicid;
						
						// Gebe Link zum Spieler aus
						
						p.sendMessage( ChatColor.DARK_AQUA + "[Forum] Hier ist der Link zum Topic: " + ChatColor.GREEN + link );

					}
					else
					{
						// Falls keine ID gesetzt ist
						p.sendMessage( ChatColor.RED + " Die TopicID ist ungültig!" );
						
					}
					
				}
				
			}
			
		}
		
	}

}

