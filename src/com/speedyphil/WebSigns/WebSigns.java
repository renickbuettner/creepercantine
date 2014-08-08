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

public class WebSigns  implements Listener {
	
	private ChatColor pc = ChatColor.GREEN; // Prefixfarbe
	private ChatColor tc = ChatColor.DARK_GREEN; // Textfarbe
	private String prefix = pc + "[WebSigns] " + tc; // Prefix
	
	private String options[] = {"webseite", "forum", "regeln"};
	
	@EventHandler
	
	public void ForumSignPlace(SignChangeEvent e){
	
		Player p = e.getPlayer();
			
		if ( e.getLine(0).equalsIgnoreCase("[Web]") || e.getLine(0).equalsIgnoreCase("[web]") ) {
			
			if( e.getLine(1) != null ){
				
				// Zeile 1 verändern bzw. Farbig machen
				
				e.setLine(0, pc + "[Link]" );
				
				// Zeile 2 korrigieren
				
				String z2 = e.getLine(2);
				int checked = 0;
				
				for(String o: options){
					
					if(o.equals(z2)){
						
						e.setLine(2, o);
						checked = 1;
					}
					
				}
				
				
				if(checked == 1){
				
					// Sende Message an Spieler
					p.sendMessage( prefix + "Du hast erfolgreich ein Beitrags-Schild erstellt." );
				
					// Nachricht in den Log schreiben
				
					CreeperCantineShared.writeToLog( prefix + p.getName() + " hat ein WebSigns-Schild erstellt.");
				
				}
				else
				{
					// Schild droppen (Weißt nicht ob es passiert, die Option hört sich aber stark danach an xd)
					e.setCancelled(true);
				}
				
			}
			else
			{
				
				// Sende Fehlernachricht an Spieler
			
				p.sendMessage( prefix + ChatColor.RED + "Diese Information ist nicht verfügbar." );
				
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
				
				if( s.getLine(0).equals( ChatColor.DARK_AQUA + "[Link]" ) ){
					
					// Get Topic ID from Sign
					
					String info = s.getLine(1);
					
					
					
					
					
					
					
					
					
					
					
				}
				
			}
			
		}
		
	}

}

