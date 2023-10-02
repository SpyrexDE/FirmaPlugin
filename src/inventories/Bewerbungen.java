package inventories;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import api.InventoryAnimation;
import api.ItemCreator;
import data.config;
import data.designs;
import de.spyrex.main.Firma;

public class Bewerbungen {

	public static void openBewerbungenInv(Player p) {
		Inventory inv = Bukkit.createInventory(null, 6*9, "§a§l§nBewerbungen");
		InventoryAnimation.StartAnimation3(inv, designs.getDesignB(p), designs.getDesignA(p));
		
		
		
		Bukkit.getScheduler().runTaskLater(Firma.Main, new Runnable() {
			
			@Override
			public void run() {
				inv.setItem(43, ItemCreator.createItem(Material.BARRIER, 1, 0, "§c§lZurück"));
			}
		}, 2L); 
		
		
		Bukkit.getScheduler().runTaskLater(Firma.Main, new Runnable() {
			
			@Override
			public void run() {
				
				inv.setItem(13, ItemCreator.createItem(Material.BOOK, 1, 0, "§a§lBewerbungen"));
				
				p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
			}
		}, 9L);
		
		for(int i=20; i<=33 ;i++) {
			int z = i;
			if(i>= 20 && i<= 24||i>=29&&i<=33) {

					inv.setItem(z, null);
				

			}
		}
		
		
		Bukkit.getScheduler().runTaskLater(Firma.Main, new Runnable() {
			
			@Override
			public void run() {
				for(String s : config.getBewerbungen(config.getFirma(p.getUniqueId()))) {
					if(!config.isOwnerOfFirma(UUID.fromString(s))) {
						
						try {
						
						if(config.getFirma(UUID.fromString(s)) != null) {
							config.removePlayerFromBewerbungen(config.getFirma(p.getUniqueId()), UUID.fromString(s));
						} else {
							inv.addItem(ItemCreator.createSkullWithLore(Bukkit.getOfflinePlayer(UUID.fromString(s)).getName(), "§a§l" + Bukkit.getOfflinePlayer(UUID.fromString(s)).getName(), "§7§lLinksklick: §a§lAnnehmen§7▐ §7§lRechtsklick: §c§lAblehnen"));
							p.playSound(p.getLocation(), Sound.ITEM_PICKUP, 1, 1);
						}
						
						}catch(NullPointerException ex) {
							if(config.getFirma(UUID.fromString(s)) != null) {
								
							}else {
								inv.addItem(ItemCreator.createSkullWithLore(Bukkit.getOfflinePlayer(UUID.fromString(s)).getName(), "§a§l" + Bukkit.getOfflinePlayer(UUID.fromString(s)).getName(), "§7§lLinksklick: §a§lAnnehmen§7▐ §7§lRechtsklick: §c§lAblehnen"));
								p.playSound(p.getLocation(), Sound.ITEM_PICKUP, 1, 1);
							}
						}
					} 
				}
			}
		}, 11L);
		

		
		
		p.openInventory(inv);
	}
	
}
