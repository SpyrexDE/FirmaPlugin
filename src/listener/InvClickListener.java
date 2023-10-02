package listener;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import api.ItemCreator;
import data.config;
import data.variables;
import de.spyrex.main.Firma;
import inventories.ArbeiterInv;
import inventories.Bewerbungen;
import inventories.DesignInv;
import inventories.FinanzenInv;
import inventories.GehaltsInv;
import inventories.InfoInv;
import inventories.KontoInv;
import inventories.OwnerInv;
import inventories.UpgradeQuestion;
import inventories.UpgradesInv;
import inventories.deleteQuestion;

public class InvClickListener implements Listener {

	@EventHandler
	public void onClick(InventoryClickEvent e) {
	try {
		try {
		Player p = (Player)e.getWhoClicked();
		String it = e.getCurrentItem().getItemMeta().getDisplayName();
		if(e.getClickedInventory().getTitle().equals("§a§l§nFIRMA") ||
				e.getClickedInventory().getTitle().equals("§4§l§n[!]FIRMA WIRKLICH LÖSCHEN?") ||
				e.getClickedInventory().getTitle().equals("§a§l§nArbeiter") ||
				e.getClickedInventory().getTitle().equals("§9§l§nGehalts-Einstellungen") ||
				e.getClickedInventory().getTitle().equals("§5§l§nDesign-Einstellungen") ||
				e.getClickedInventory().getTitle().equals("§6§l§nFinanzen") ||
				e.getClickedInventory().getTitle().equals("§a§l§nBewerbungen")){
		e.setCancelled(true);
		p.playSound(p.getLocation(), Sound.WOOD_CLICK, 1, 1);

			switch(it) {
			case "§2§l§nFirma erstellen": p.closeInventory(); variables.cantype.put(p.getUniqueId(), "create"); p.sendMessage(Firma.prefix + "Bitte gebe den Namen der zu erstellenden Firma ein:");  break;
			case "§c§lAbbrechen": p.closeInventory(); break;
			case "§5§lFirma beitreten": p.closeInventory(); p.sendMessage(Firma.prefix + "Bitte gebe den Namen der Firma ein, bei der du dich bewerben willst:"); variables.cantype.put(p.getUniqueId(), "join"); break;
			case "§c§lSchließen":p.closeInventory(); break;
			case "§4§lFirma aufgeben": deleteQuestion.openDeleteQuestion(p); break;
			case "§4§l§nLÖSCHEN": p.closeInventory(); config.DeleteFirma(config.getFirma(p.getUniqueId()));break;
			case "§a§lArbeiter": ArbeiterInv.openArbeiterInv(p); break;
			case "§c§lZurück": if(config.isOwnerOfFirma(p.getUniqueId())) {
									OwnerInv.openInv(p); 
								} else {
									InfoInv.openInfoInv(p);
								} break;
			case "§9§lGehalts-Einstellungen": GehaltsInv.openGehaltsInv(p); break;
			case "§5§lDesign-Einstellungen": DesignInv.openDesignInv(p); break;
			case "§9§lHintergrundfarbe ändern": p.closeInventory(); variables.cantype.put(p.getUniqueId(), "background"); p.sendMessage(Firma.prefix + "Bitte gebe eine Zahl von §60 §7bis §615 §7an, um die Glasfarbe zu ändern:"); break;
			case "§9§lRahmenfarbe ändern": p.closeInventory(); variables.cantype.put(p.getUniqueId(), "border"); p.sendMessage(Firma.prefix + "Bitte gebe eine Zahl von §60 §7bis §615 §7an, um die Glasfarbe zu ändern:"); break;
			case "§6§lFinanzen": FinanzenInv.openFinanzenInv(p); break;
			case "§a§lBewerbungen": Bewerbungen.openBewerbungenInv(p); break;
			case "§5§lTeleportationspunkt setzen": if(p.getWorld().getName().equals("Mines")) {
														config.setLocation(config.getFirma(p.getUniqueId()), p); p.closeInventory(); p.sendMessage(Firma.prefix + "Teleportationspunkt erfolgreich gesetzt!"); 
													}else{p.sendMessage(Firma.prefix + "Du musst dich in der Firmen-Welt befinden, um einen Teleportationspunktd erstellen zu können.");}break;
							
			}

		}
		
		} catch(NullPointerException ex) {
			
		}
		
		
		if(e.getClickedInventory().getTitle().equals("§9§l§nGehalts-Einstellungen")) {
			e.setCancelled(true);
			if(!e.getCurrentItem().getItemMeta().getDisplayName().equals("§r") && !e.getCurrentItem().getItemMeta().getDisplayName().equals("§a§lDeine Arbeiter") && !e.getCurrentItem().getItemMeta().getDisplayName().equals("§c§lZurück")) {
				variables.cantype.put(e.getWhoClicked().getUniqueId(), "lohn");
			    variables.newlohn.put(e.getWhoClicked().getUniqueId(), e.getCurrentItem().getItemMeta().getDisplayName().replace("§a§l", ""));
			    if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§9§lStart-Gehalt setzen")) {
				e.getWhoClicked().sendMessage(Firma.prefix + "Bitte gebe den neuen Start-Gehalts-Anteil deiner Firma (OHNE %-ZEICHEN) an:");
			    } else {
					e.getWhoClicked().sendMessage(Firma.prefix + "Bitte gebe den neuen Gehalts-Anteil des Mitarbeiters (OHNE %-ZEICHEN) an:");
			    }
				e.getWhoClicked().closeInventory();
			}
		}
		
		
		if(e.getClickedInventory().getTitle().equals("§a§l§nArbeiter")) {
			if(!e.getCurrentItem().getItemMeta().getDisplayName().equals("§r") && !e.getCurrentItem().getItemMeta().getDisplayName().equals("§a§lDeine Arbeiter") && !e.getCurrentItem().getItemMeta().getDisplayName().equals("§c§lZurück") && !e.getCurrentItem().getItemMeta().getDisplayName().equals("§5§lTeleportationspunkt setzen")) {
			if(Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(e.getCurrentItem().getItemMeta().getDisplayName().replace("§a§l", "")))) {
				config.deleteFirmasPlayer(Bukkit.getPlayer(e.getCurrentItem().getItemMeta().getDisplayName().replace("§a§l", "")).getUniqueId());
			} else { config.deleteFirmasPlayer(Bukkit.getOfflinePlayer(e.getCurrentItem().getItemMeta().getDisplayName().replace("§a§l", "")).getUniqueId()); }
				e.getWhoClicked().sendMessage(Firma.prefix + "Du hast den Arbeiter §6" + e.getCurrentItem().getItemMeta().getDisplayName().replace("§a§l", "") + " §7efolgreich gefeuert!");
				Bukkit.getPlayer(e.getCurrentItem().getItemMeta().getDisplayName().replace("§a§l", "")).sendTitle("§4§lDu wurdest gefeuert!", "§c§l➥ Von: §4" + e.getWhoClicked().getName());
				ArbeiterInv.openArbeiterInv((Player)e.getWhoClicked());
				
			}
		}
		
		if(e.getClickedInventory().getTitle().equals("§6§l§nFinanzen")) {
			if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6§lAktien")) {
				
				e.getInventory().setItem(24, ItemCreator.createItem(Material.BARRIER, 1, 0, "§c§lIn Arbeit"));
				Bukkit.getScheduler().runTaskLater(Firma.Main, new Runnable() {
					
					@Override
					public void run() {
						
						e.getInventory().setItem(24, ItemCreator.createItem(Material.PAPER, 1, 0, "§6§lAktien"));
						
					}
				}, 30L);
				
				
				
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6§lUpgrades")){
				UpgradesInv.openUpgradesInv((Player)e.getWhoClicked());
			} else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6§lKonto verwalten")) {
				KontoInv.openKontoInv((Player)e.getWhoClicked());
			}
		} else if(e.getClickedInventory().getTitle().equals("§6§l§nUpgrades")) {
			e.setCancelled(true);
			
			if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§c§lZurück")) {
				FinanzenInv.openFinanzenInv((Player)e.getWhoClicked());
			}
			if(e.getCurrentItem().getItemMeta().hasLore()) {
					if(e.getCurrentItem().getItemMeta().getLore().get(0).contains("§a")) {
							UpgradeQuestion.openQuestionInv((Player)e.getWhoClicked());
					}
			}
		}else if(e.getClickedInventory().getTitle().equals("§6§l§nKonto")) {
			e.setCancelled(true);
			
			if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§c§lZurück")) {
				FinanzenInv.openFinanzenInv((Player)e.getWhoClicked());
			} else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§a§lEinzahlen")) {
				variables.cantype.put(e.getWhoClicked().getUniqueId(), "einzahlung");
				e.getWhoClicked().closeInventory();
				e.getWhoClicked().sendMessage(Firma.prefix + "Bitte gebe die Anzahl der einzuzahlenden Gems an:");
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§c§lAbheben")) {
				variables.cantype.put(e.getWhoClicked().getUniqueId(), "abhebung");
				e.getWhoClicked().closeInventory();
				e.getWhoClicked().sendMessage(Firma.prefix + "Bitte gebe die Anzahl der abzuhebenden Gems an:");
			}
		} else if (e.getClickedInventory().getTitle().equals("§8§lAuf §4§lLvl. " + (config.getFirmasLevelFromFirmaOwner(config.getOwnerOfFirma(config.getFirma(((Player)e.getWhoClicked()).getUniqueId()))) + 1) +" §8§lupgraden?")) {
			e.setCancelled(true);
			if(e.getCurrentItem().getItemMeta().getDisplayName().contains("§a")) {
				//if COINS ENOUGH (==(config.getFirmasLevelFromFirmaOwner(config.getOwnerOfFirma(config.getFirma(p))) * 1500))
				//ENTFERNE (config.getFirmasLevelFromFirmaOwner(config.getOwnerOfFirma(config.getFirma(p))) * 1500)
				config.setFirmasLevelFromFirmaOwner(config.getOwnerOfFirma(config.getFirma(((Player)e.getWhoClicked()).getUniqueId())), (config.getFirmasLevelFromFirmaOwner(config.getOwnerOfFirma(config.getFirma(((Player)e.getWhoClicked()).getUniqueId()))) + 1));
				((Player)e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), Sound.LEVEL_UP, 1, 1);
				config.sendTitleToAllPlayersInFirma(config.getFirma(e.getWhoClicked().getUniqueId()), "§a§lFIRMEN-UPGRADE!", "§7§l➥ Bitte joine neu!");
				UpgradesInv.openUpgradesInv((Player) e.getWhoClicked());
			} else 			if(e.getCurrentItem().getItemMeta().getDisplayName().contains("§c§lAbbrechen")) {
				UpgradesInv.openUpgradesInv((Player) e.getWhoClicked());
			}
		} else if(e.getClickedInventory().getTitle().equals("§a§l§nBewerbungen")) {
		if(e.getCurrentItem().getItemMeta().getDisplayName().contains("§a")){	
			if(e.getClick().isLeftClick()) {
				try {
				config.removePlayerFromBewerbungen(config.getFirma(((Player)e.getWhoClicked()).getUniqueId()), Bukkit.getPlayer(e.getCurrentItem().getItemMeta().getDisplayName().replace("§a§l", "")).getUniqueId());
				config.setPlayersFirma(Bukkit.getPlayer(e.getCurrentItem().getItemMeta().getDisplayName().replace("§a§l", "")).getUniqueId(), config.getFirma(((Player)e.getWhoClicked()).getUniqueId()));
				Bukkit.getPlayer(e.getCurrentItem().getItemMeta().getDisplayName().replace("§a§l", "")).sendTitle("§a§lDu wurdest angenommen!", "§7§l➥ Neue Firma: §6" + config.getFirma(e.getWhoClicked().getUniqueId()));
				} catch(NullPointerException ex) {
					config.removePlayerFromBewerbungen(config.getFirma(((Player)e.getWhoClicked()).getUniqueId()), Bukkit.getOfflinePlayer(e.getCurrentItem().getItemMeta().getDisplayName().replace("§a§l", "")).getUniqueId());
					config.setPlayersFirma(Bukkit.getOfflinePlayer(e.getCurrentItem().getItemMeta().getDisplayName().replace("§a§l", "")).getUniqueId(), config.getFirma(((Player)e.getWhoClicked()).getUniqueId()));
				}
				Bewerbungen.openBewerbungenInv((Player)e.getWhoClicked());
				((Player)e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), Sound.LEVEL_UP, 1, 1);
			} else if(e.getClick().isRightClick()) {
				try {
				config.removePlayerFromBewerbungen(config.getFirma(((Player)e.getWhoClicked()).getUniqueId()), Bukkit.getPlayer(e.getCurrentItem().getItemMeta().getDisplayName().replace("§a§l", "")).getUniqueId());
				Bukkit.getPlayer(e.getCurrentItem().getItemMeta().getDisplayName().replace("§a§l", "")).sendTitle("§c§lDu wurdest abgelehnt!", "§7§l➥Von: §4" + config.getFirma(e.getWhoClicked().getUniqueId()));
				} catch(NullPointerException ex) {
					config.removePlayerFromBewerbungen(config.getFirma(((Player)e.getWhoClicked()).getUniqueId()), Bukkit.getOfflinePlayer(e.getCurrentItem().getItemMeta().getDisplayName().replace("§a§l", "")).getUniqueId());
				}
				Bewerbungen.openBewerbungenInv((Player)e.getWhoClicked());
				((Player)e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), Sound.ANVIL_BREAK, 1, 1);
			}
		}
		}
	}catch(NullPointerException ignore) {}
	}
}
