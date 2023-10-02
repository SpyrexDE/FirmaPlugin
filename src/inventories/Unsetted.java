package inventories;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import api.InventoryAnimation;
import api.ItemCreator;
import data.designs;
import de.spyrex.main.Firma;

public class Unsetted {
	
	public static void openInv(Player p) {
		
		Inventory inv = Bukkit.createInventory(null, 3*9, "§a§l§nFIRMA");
		
	
		
		
		
			Bukkit.getScheduler().runTaskLater(Firma.Main, new Runnable() {
				
				@Override
				public void run() {
					inv.setItem(10, ItemCreator.createItem(Material.BEACON, 1, 0, "§2§l§nFirma erstellen"));
					p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
				}
			}, 5L);
			Bukkit.getScheduler().runTaskLater(Firma.Main, new Runnable() {
				
				@Override
				public void run() {
					inv.setItem(13, ItemCreator.createItem(Material.BOOK, 1, 0, "§5§lFirma beitreten"));
					p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
				}
			}, 9L);
			
			Bukkit.getScheduler().runTaskLater(Firma.Main, new Runnable() {
				
				@Override
				public void run() {
					inv.setItem(16, ItemCreator.createItem(Material.BARRIER, 1, 0, "§c§lAbbrechen"));
					p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
				}
			}, 13L);
			
			
			
		p.openInventory(inv);
		InventoryAnimation.StartAnimation2(inv, designs.getDesignB(p), designs.getDesignA(p));
		
	}
	
}
