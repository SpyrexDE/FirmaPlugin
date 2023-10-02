package inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import api.InventoryAnimation;
import api.ItemCreator;
import de.spyrex.main.Firma;

public class deleteQuestion {
	static boolean gerade = true;
	public static void openDeleteQuestion(Player p) {
		
		Inventory inv = Bukkit.createInventory(null, 3*9, "�4�l�n[!]FIRMA WIRKLICH L�SCHEN?");
		InventoryAnimation.StartAnimation2(inv, 15, 14);
		
		inv.setItem(11, ItemCreator.createItem(Material.REDSTONE_BLOCK, 1, 0, "�4�l�nL�SCHEN"));
		
		for(int i = 0; i <= 50; i ++) {
			
			Bukkit.getScheduler().runTaskLater(Firma.Main, new Runnable() {

				@Override
				public void run() {
					if(gerade == true) {
					inv.setItem(11, ItemCreator.createItem(Material.TNT, 1, 0, "�4�l�nL�SCHEN"));
					gerade = false;
					}else if(gerade == false){
						inv.setItem(11, ItemCreator.createItem(Material.REDSTONE_BLOCK, 1, 0, "�4�l�nL�SCHEN"));
						gerade = true;
					}
					
				}
				}, 10L*i);
		}
		
		
		
		inv.setItem(15, ItemCreator.createItem(Material.BARRIER, 1, 0, "�c�lAbbrechen"));
		
		
		p.openInventory(inv);
	}
	
}
