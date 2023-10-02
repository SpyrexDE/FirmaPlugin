package inventories;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import api.InventoryAnimation;
import api.ItemCreator;
import data.config;
import data.designs;
import de.spyrex.main.Firma;

public class UpgradesInv {

	public static void openUpgradesInv(Player p) {
		Inventory inv = Bukkit.createInventory(null, 6*9, "§6§l§nUpgrades");
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
				
				inv.setItem(13, ItemCreator.createItem(Material.EMPTY_MAP, 1, 0, "§6§lUpgrades"));
				
				p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
			}
		}, 9L);
		
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§a§L➜KAUFEN");
		ArrayList<ItemStack> l = new ArrayList<>();
		l.add(ItemCreator.createItemWithLore(Material.WOOD_PICKAXE, 1, 0, "§e§lLevel 1",lore));
		l.add(ItemCreator.createItemWithLore(Material.STONE_PICKAXE, 1, 0, "§e§lLevel 2", lore));
		l.add(ItemCreator.createItemWithLore(Material.IRON_PICKAXE, 1, 0, "§e§lLevel 3",lore));
		l.add(ItemCreator.createItemWithLore(Material.GOLD_PICKAXE, 1, 0, "§e§lLevel 4",lore));
		l.add(ItemCreator.createItemWithLore(Material.DIAMOND_PICKAXE, 1, 0, "§e§lLevel 5",lore));
		
		ArrayList<String> gekauft = new ArrayList<>();
		gekauft.add("§2§L✔GEKAUFT");

		
			Bukkit.getScheduler().runTaskLater(Firma.Main, new Runnable() {
			
				@Override
				public void run() {
					int counter = 0;
					for(ItemStack it : l) {
						ItemMeta itm = it.getItemMeta();
						
						int level = config.getFirmasLevelFromFirmaOwner(config.getOwnerOfFirma(config.getFirma(p.getUniqueId())));
						
						if(level >= 1){ 
							if(it.getItemMeta().getDisplayName().equals("§e§lLevel 1")) {
								itm.setLore(gekauft);
							}
						} if(level >=2) {
							if(it.getItemMeta().getDisplayName().equals("§e§lLevel 2")) {
							itm.setLore(gekauft);
							}
						} if(level >=3) {
							if(it.getItemMeta().getDisplayName().equals("§e§lLevel 3")) {
							itm.setLore(gekauft);
							}
						} if(level >=4) {
							if(it.getItemMeta().getDisplayName().equals("§e§lLevel 4")) {
							itm.setLore(gekauft);
							}
						} if(level >=5) {
							if(it.getItemMeta().getDisplayName().equals("§e§lLevel 5")) {
							itm.setLore(gekauft);
							}
						}
										
						

						it.setItemMeta(itm);
						inv.setItem(counter + 29, it);
						
						p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
						counter += 1;
					}
				}
			}, 11L);
		
		
		
		p.openInventory(inv);
	}
	
}
