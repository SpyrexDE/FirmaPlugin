package inventories;

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

public class FinanzenInv {

	public static void openFinanzenInv(Player p) {
		Inventory inv = Bukkit.createInventory(null, 6*9, "§6§l§nFinanzen");
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
				
				inv.setItem(13, ItemCreator.createItem(Material.EMPTY_MAP, 1, 0, "§6§lFinanzen-Verwaltung"));
				
				p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
			}
		}, 9L);
		
		
		Bukkit.getScheduler().runTaskLater(Firma.Main, new Runnable() {
			
			@Override
			public void run() {
				inv.setItem(31, ItemCreator.createItem(Material.BOOK_AND_QUILL, 1, 0, "§6§lKonto verwalten"));
				p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
			}
		}, 11L);
		
		
		
		Bukkit.getScheduler().runTaskLater(Firma.Main, new Runnable() {
			
			@Override
			public void run() {
				inv.setItem(20, ItemCreator.createItem(Material.EXP_BOTTLE, 1, 0, "§6§lUpgrades"));
				inv.setItem(24, ItemCreator.createItem(Material.PAPER, 1, 0, "§6§lAktien"));
				p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
			}
		}, 13L);
		
		
		
		p.openInventory(inv);
	}
	
}
