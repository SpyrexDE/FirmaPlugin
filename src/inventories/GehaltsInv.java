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

public class GehaltsInv {

	public static void openGehaltsInv(Player p) {
		Inventory inv = Bukkit.createInventory(null, 6*9, "§9§l§nGehalts-Einstellungen");
		InventoryAnimation.StartAnimation3(inv, designs.getDesignB(p), designs.getDesignA(p));
		
		
		
		Bukkit.getScheduler().runTaskLater(Firma.Main, new Runnable() {
			
			@Override
			public void run() {
				inv.setItem(43, ItemCreator.createItem(Material.BARRIER, 1, 0, "§c§lZurück"));
				inv.setItem(37, ItemCreator.createItem(Material.RECORD_8, 1, 0, "§9§lStart-Gehalt setzen"));
			}
		}, 2L); 
		
		
		Bukkit.getScheduler().runTaskLater(Firma.Main, new Runnable() {
			
			@Override
			public void run() {
				
				inv.setItem(13, ItemCreator.createItem(Material.EMPTY_MAP, 1, 0, "§a§lListe der Gehälter deiner Arbeiter"));
				
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
				for(String s : config.getPlayersOfFirma(config.getFirma(p.getUniqueId()))) {
					if(!config.isOwnerOfFirma(UUID.fromString(s))) {
					inv.addItem(ItemCreator.createSkullWithLore(Bukkit.getOfflinePlayer(UUID.fromString(s)).getName(), "§a§l" + Bukkit.getOfflinePlayer(UUID.fromString(s)).getName(), "§7§lGehalt: §6§l" + config.getPlayersLohn(UUID.fromString(s))+ "%"));
					p.playSound(p.getLocation(), Sound.ITEM_PICKUP, 1, 1);
					} 
				}
			}
		}, 11L);
		

		
		
		p.openInventory(inv);
	}
	
}
