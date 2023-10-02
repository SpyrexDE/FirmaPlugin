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

public class DesignInv {

	public static void openDesignInv(Player p) {
		Inventory inv = Bukkit.createInventory(null, 6*9, "§5§l§nDesign-Einstellungen");
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
				
				inv.setItem(13, ItemCreator.createItem(Material.EMPTY_MAP, 1, 0, "§a§lWähle dein Design"));
				
				p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
			}
		}, 9L);
		
		
		Bukkit.getScheduler().runTaskLater(Firma.Main, new Runnable() {
			
			@Override
			public void run() {
				
				inv.setItem(20, ItemCreator.createItem(Material.WATER_BUCKET, 1, 0, "§9§lHintergrundfarbe ändern"));
				inv.setItem(24, ItemCreator.createItem(Material.LAVA_BUCKET, 1, 0, "§9§lRahmenfarbe ändern"));
			}
		}, 12L);
		
		
		
		p.openInventory(inv);
	}
	
}
