package listener;

import java.util.HashMap;
import java.util.UUID;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import data.config;
import data.designs;
import data.variables;
import de.spyrex.main.Firma;
import inventories.DesignInv;
import inventories.GehaltsInv;
import inventories.KontoInv;
import inventories.OwnerInv;
import inventories.Unsetted;

public class ChatListener implements Listener{

	HashMap<UUID,Boolean> kannwiederschreiben = new HashMap<>();
	
	@EventHandler
	public void onChat(PlayerChatEvent e) {
		try {
		if(variables.cantype.get(e.getPlayer().getUniqueId()) == "create") {
			e.setCancelled(true);
			
		if(config.getFirma(e.getPlayer().getUniqueId()) == null) {
			if(!config.checkifFirmaexist(e.getMessage())) {
			if(e.getMessage().length() >=4 && e.getMessage().length() <= 10) {
			config.createFirma(e.getMessage(), e.getPlayer());
			e.getPlayer().sendMessage(Firma.prefix + "Du hast deine Firma §6" + e.getMessage() + " §7erfolgreich erstellt!");
			variables.cantype.put(e.getPlayer().getUniqueId(), null);
			} else {e.getPlayer().sendMessage(Firma.prefix + "Der Firmenname darf maximal 10 und muss mindestens 4 Zeichen lang sein!");}
			}else {
				e.getPlayer().sendMessage(Firma.prefix + "Der Firmenname §6\"" + e.getMessage() +  "\" §7ist bereits vergeben. Bitte gebe einen anderen Namen ein:");
			}
		}  else {
			e.getPlayer().sendMessage(Firma.prefix + "Du arbeitest bereits in der Firma §6\"" + config.getFirma(e.getPlayer().getUniqueId()) + "\"§7. Um einer anderen Firma beitreten oder eine gründen zu können kannst du deine jetzige Firma im §6/Firma§7-Menü verlassen.");
		}
		
		}else if(variables.cantype.get(e.getPlayer().getUniqueId()) == "join") {
			e.setCancelled(true);
			String Eingabe = e.getMessage();
			
			try {
				if(config.checkifFirmaexist(e.getMessage())) {
				config.createBewerbung(e.getPlayer().getUniqueId(), e.getMessage());
				e.getPlayer().sendMessage(Firma.prefix + "Du hast dich erfolgreich bei der Firma §6" + e.getMessage() + " §7beworben!");
				try{
					Bukkit.getPlayer(config.getOwnerOfFirma(e.getMessage())).sendTitle("§a§lNeue Bewerbung!", "§7§l➥ Von: §6" + e.getPlayer().getName());
				}catch(NullPointerException ex) {}
					} else {
					e.getPlayer().sendMessage(Firma.prefix + "Diese Firma existiert nicht!");
				}
			}catch(NullPointerException ex) {
				e.getPlayer().sendMessage(Firma.prefix + "Diese Firma existiert nicht!");
			}
			variables.cantype.put(e.getPlayer().getUniqueId(), null);
		} else if(variables.cantype.get(e.getPlayer().getUniqueId()) == "lohn") {
			e.setCancelled(true);
			

			try {
				if(Integer.valueOf(e.getMessage()) >= 0 && Integer.valueOf(e.getMessage()) <= 100) {
					
					if(variables.newlohn.get(e.getPlayer().getUniqueId()) == "§9§lStart-Gehalt setzen") {
						config.setPlayersLohn(e.getPlayer().getUniqueId(), Integer.valueOf(e.getMessage()));
						e.getPlayer().sendMessage(Firma.prefix + "Du hast das Start-Gehalt deiner Firma erfolgreich auf §6" + e.getMessage() + "% §7gesetzt!");
					} else {
						if(Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(variables.newlohn.get(e.getPlayer().getUniqueId())))) {
						
							config.setPlayersLohn(Bukkit.getPlayer(variables.newlohn.get(e.getPlayer().getUniqueId())).getUniqueId(), Integer.valueOf(e.getMessage()));
						
						}else {	config.setPlayersLohn(Bukkit.getOfflinePlayer(variables.newlohn.get(e.getPlayer().getUniqueId())).getUniqueId(), Integer.valueOf(e.getMessage()));}
					
			e.getPlayer().sendMessage(Firma.prefix + "Du hast das Gehalt des ausgewählten Spielers erfolgreich auf §6" + e.getMessage() + "% §7gesetzt!");
			try{
				Bukkit.getPlayer(variables.newlohn.get(e.getPlayer().getUniqueId())).sendTitle("§a§lGehalts-Änderung!", "§7§l➥ Neuer Gehalt: §6" + Integer.valueOf(e.getMessage()) + "%");
			}catch(NullPointerException ex) {}
					}
			variables.cantype.put(e.getPlayer().getUniqueId(), null);
				GehaltsInv.openGehaltsInv(e.getPlayer());
				}else {e.getPlayer().sendMessage(Firma.prefix + "Die Prozent zahl darf nur zwischen §60 §7und §6100 liegen!");}
				
			} catch(NumberFormatException ex) {
				e.getPlayer().sendMessage(Firma.prefix + "Bitte gebe nur eine Zahl ein!");
			}

		} else if(variables.cantype.get(e.getPlayer().getUniqueId()) == "background") {
			e.setCancelled(true);
			try{
				
				if(Integer.valueOf(e.getMessage()) < 16 &&
						Integer.valueOf(e.getMessage()) >= 0) {
					designs.setDesignB(e.getPlayer(), Integer.valueOf(e.getMessage()));
					variables.cantype.put(e.getPlayer().getUniqueId(), null);
					DesignInv.openDesignInv(e.getPlayer());
					e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.LEVEL_UP, 1, 1);
				} else {
					e.getPlayer().sendMessage(Firma.prefix + "Deine zahl muss zwischen §60 §7und §615 §7liegen!");
				}
				
			
		} catch(NumberFormatException ex) {
			e.getPlayer().sendMessage(Firma.prefix + "Bitte gebe nur eine Zahl ein!");
		}
			
		}else if(variables.cantype.get(e.getPlayer().getUniqueId()) == "border") {
			e.setCancelled(true);
			try{
				
				if(Integer.valueOf(e.getMessage()) < 16 &&
						Integer.valueOf(e.getMessage()) >= 0) {
					designs.setDesignA(e.getPlayer(), Integer.valueOf(e.getMessage()));
					variables.cantype.put(e.getPlayer().getUniqueId(), null);
					DesignInv.openDesignInv(e.getPlayer());
					e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.LEVEL_UP, 1, 1);
				}else {
					e.getPlayer().sendMessage(Firma.prefix + "Deine zahl muss zwischen §60 §7und §615 §7liegen!");
				}
				
			
		} catch(NumberFormatException ex) {
			e.getPlayer().sendMessage(Firma.prefix + "Bitte gebe nur eine Zahl ein!");
		}
			
	} else if(variables.cantype.get(e.getPlayer().getUniqueId()).equals("einzahlung")){
		e.setCancelled(true);
		try {
			//if(Integer.valueOf(e.getMessage()) < CoinsAPI.getCOINS) {
				config.setFirmasKontoFromFirmaOwner(config.getOwnerOfFirma(config.getFirma(e.getPlayer().getUniqueId())), config.getFirmasKontoFromFirmaOwner(config.getOwnerOfFirma(config.getFirma(e.getPlayer().getUniqueId()))) + Integer.valueOf(e.getMessage()));
				e.getPlayer().sendMessage(Firma.prefix + "Du hast erfolgreich §6" + e.getMessage() + " Gems §7 zu deinem Firmenkonto hinzugefügt!");
				//COINAPI REMOVE Integer.valueOf(e.getMessage() 
				KontoInv.openKontoInv(e.getPlayer());
				// } else {e.getPlayer().sendMessage(Firma.prefix + "Du hast nicht genügend Gems, um dies zu tun!");}
				variables.cantype.put(e.getPlayer().getUniqueId(), null);
		} catch(NumberFormatException ex) {
			e.getPlayer().sendMessage(Firma.prefix + "Bitte gebe nur eine Zahl ein!");
		}
	} else if(variables.cantype.get(e.getPlayer().getUniqueId()).equals("abhebung")) {
		e.setCancelled(true);
		try {
			if(Integer.valueOf(e.getMessage()) <= config.getFirmasKontoFromFirmaOwner(config.getOwnerOfFirma(config.getFirma(e.getPlayer().getUniqueId())))) {
				config.setFirmasKontoFromFirmaOwner(config.getOwnerOfFirma(config.getFirma(e.getPlayer().getUniqueId())), config.getFirmasKontoFromFirmaOwner(config.getOwnerOfFirma(config.getFirma(e.getPlayer().getUniqueId()))) - Integer.valueOf(e.getMessage()));
					if(Integer.valueOf(e.getMessage()) >= 10) {
					e.getPlayer().sendMessage(Firma.prefix + "Du hast erfolgreich §6" + (Integer.valueOf(e.getMessage()) - 10*Integer.valueOf(e.getMessage())/100)  + " Gems §7 von deinem Firmenkonto abgehoben! §7(-10% Steuern)");
					} else {					e.getPlayer().sendMessage(Firma.prefix + "Du hast erfolgreich §6" + (Integer.valueOf(e.getMessage()) - 10*Integer.valueOf(e.getMessage())/100)  + " Gems §7 von deinem Firmenkonto abgehoben!");}
					//CoinsAPI.addCoins(e.getPlayer(), (float)(Integer.valueOf(e.getMessage()) - 10*Integer.valueOf(e.getMessage())/100));
				KontoInv.openKontoInv(e.getPlayer());
			} else {e.getPlayer().sendMessage(Firma.prefix + "Du hast nicht genügend Gems auf dem Firmenkonto, um dies zu tun!");
			}
			
			variables.cantype.put(e.getPlayer().getUniqueId(), null);
			
		} catch(NumberFormatException ex) {
			e.getPlayer().sendMessage(Firma.prefix + "Bitte gebe nur eine Zahl ein!");
		}
		
	}
		
		
		
		
		
		
		
		
		}catch(NullPointerException ex) {
			
		}
	}
}
