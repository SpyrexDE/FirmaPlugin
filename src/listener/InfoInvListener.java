package listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import api.InventoryAnimation;
import data.config;
import data.designs;
import de.spyrex.main.Firma;
import inventories.ArbeiterArbeiterInv;
import inventories.DesignInv;
import inventories.InfoInv;
import inventories.SellInv;

public class InfoInvListener implements Listener{

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		try {
		Player p = (Player)e.getWhoClicked();
		String it = e.getCurrentItem().getItemMeta().getDisplayName();
		String title = e.getClickedInventory().getTitle();

		if(title.equals("§2§l§nFIRMA")) {
			e.setCancelled(true);
			if(it.equals("§a§lArbeiter")) {
				ArbeiterArbeiterInv.openArbeiterArbeiterInv(p);
			} else if(it.equals("§5§lDesign-Einstellungen")) {
				DesignInv.openDesignInv(p);
			} else if(it.equals("§6§lItem-Abgabe")) {
				p.performCommand("abgabe");
			} else if(it.equals("§5§lTeleportiere zum Firmen-Warp")) {
				try {
				p.teleport(config.getLocation(config.getFirma(p.getUniqueId())));
				}catch(NullPointerException ex) {
					p.sendMessage(Firma.prefix + "Der Firman-Warp wurde noch nicht gesetzt! Bitte deinen Arbeitsgeber ihn zu setzen!");
				}
			} else if(it.equals("§c§lFirma verlassen")){
				p.sendMessage(Firma.prefix + "Du hast die Firma §6"+ config.getFirma(p.getUniqueId()	) + " §7erfolgreich verlassen!");
				p.closeInventory();
				try{
					Bukkit.getPlayer(config.getOwnerOfFirma(config.getFirma(e.getWhoClicked().getUniqueId()))).sendTitle("§c§lJemand hat dieFirma verlassen!", "§7§l➥ " + e.getWhoClicked().getName());
				}catch(NullPointerException ex) {}
				config.deleteFirmasPlayer(p.getUniqueId());
			}
		} else if(title.equals("§2§l§nArbeiter")) {
			e.setCancelled(true);
			if(it.equals("§c§lZurück")) {
				InfoInv.openInfoInv(p);
			}
		} 
		
		
	}catch(NullPointerException ignore) {}
	}
}
