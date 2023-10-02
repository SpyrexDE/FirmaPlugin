package api;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

import de.spyrex.main.Firma;

public class InventoryAnimation {

	public static void StartAnimation1(Inventory inv, int glasscolor1, int glasscolor2) {

		for(int x = 0; x <= inv.getSize()-1; x ++) {
			final int y = x;

					inv.setItem(y, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor1, "§r"));

		}
		for(int z = 0; z <= 4; z ++) {
			final int i = z;
				
				Bukkit.getScheduler().runTaskLater(Firma.Main, new Runnable() {
					
					@Override
					public void run() {
						
						switch(i) {
						case 0: inv.setItem(0, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r"));
								inv.setItem(8, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r"));
								inv.setItem(18, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r"));
								inv.setItem(26, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r")); break;
						case 1:	inv.setItem(1, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r"));
								inv.setItem(7, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r"));
								inv.setItem(19, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r"));
								inv.setItem(17, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r"));
								inv.setItem(9, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r"));
								inv.setItem(25, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r")); break;
						case 2:	inv.setItem(2, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r"));
								inv.setItem(6, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r"));
								inv.setItem(20, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r"));
								inv.setItem(24, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r")); break;
						case 3: inv.setItem(3, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r"));
								inv.setItem(5, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r"));
								inv.setItem(21, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r"));
								inv.setItem(23, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r")); break;
						case 4: inv.setItem(4, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r"));
								inv.setItem(22, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r")); break;
						
						}
						
					}
				}, 2L*z);
		}
				
	}
	
	
	
	public static void StartAnimation2(Inventory inv, int glasscolor1, int glasscolor2) {

		
		for(int x = 0; x <= inv.getSize()-1; x ++) {
			final int y = x;

					inv.setItem(y, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor1, "§r"));

		}
		for(int z = 0; z <= 9; z ++) {
			final int i = z;
				
				Bukkit.getScheduler().runTaskLater(Firma.Main, new Runnable() {
					
					@Override
					public void run() {
						
						switch(i) {
						case 0: inv.setItem(0, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r"));
								inv.setItem(9, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r"));
								inv.setItem(18, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r")); break;
						case 1:	inv.setItem(1, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r"));
								inv.setItem(19, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r")); break;
						case 2:	inv.setItem(2, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r"));
								inv.setItem(20, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r")); break;
						case 3: inv.setItem(3, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r"));
								inv.setItem(21, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r")); break;
						case 4: inv.setItem(4, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r"));
								inv.setItem(22, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r")); break;
						case 5: inv.setItem(5, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r"));
								inv.setItem(23, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r")); break;
						case 6: inv.setItem(6, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r"));
								inv.setItem(24, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r")); break;
						case 7: inv.setItem(7, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r"));
								inv.setItem(25, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r")); break;
						case 8: inv.setItem(8, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r"));
								inv.setItem(17, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r"));
								inv.setItem(26, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r")); break;
						}
						
					}
				}, 2L*z);
		}
				
	}
	
	public static void StartAnimation3(Inventory inv, int glasscolor1, int glasscolor2) {
		
		
		for(int x = 0; x <= inv.getSize()-1; x ++) {
			final int y = x;

					inv.setItem(y, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor1, "§r"));

		}
		for(int z = 0; z <= 4; z ++) {
			final int i = z;
				
				Bukkit.getScheduler().runTaskLater(Firma.Main, new Runnable() {
					
					@Override
					public void run() {
						
						switch(i) {
						case 0: inv.setItem(0, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r"));
								inv.setItem(8, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r"));
								inv.setItem(53, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r"));
								inv.setItem(45, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r")); break;
						case 1:	inv.setItem(1, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r"));
								inv.setItem(7, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r"));
								inv.setItem(17, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r"));
								inv.setItem(36, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r"));
								inv.setItem(46, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r"));
								inv.setItem(44, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r"));
								inv.setItem(52, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r"));
								inv.setItem(9, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r")); break;
						case 2:	inv.setItem(2, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r"));
								inv.setItem(6, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r"));
								inv.setItem(26, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r"));
								inv.setItem(27, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r"));
								inv.setItem(47, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r"));
								inv.setItem(51, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r"));
								inv.setItem(35, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r"));
								inv.setItem(18, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r")); break;
						case 3: inv.setItem(3, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r"));
								inv.setItem(48, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r"));
								inv.setItem(5, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r"));
								inv.setItem(50, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r"));
								inv.setItem(35, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r")); break;
						case 4: inv.setItem(4, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r"));
								inv.setItem(49, ItemCreator.createItem(Material.STAINED_GLASS_PANE, 1, glasscolor2, "§r")); break;
						}
						
					}
				}, 2L*z);
		}
				
	}
	
	
	
	
}
