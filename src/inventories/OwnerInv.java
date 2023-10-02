package inventories;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.spigotmc.CaseInsensitiveMap;

import api.InventoryAnimation;
import api.ItemCreator;
import data.config;
import data.designs;
import de.spyrex.main.Firma;

public class OwnerInv {
	
	public static ArrayList<String> l = new ArrayList<>();
	
	
	
	public static void openInv(Player p) {
		Inventory inv = Bukkit.createInventory(null, 6*9, "ßaßlßnFIRMA");
		InventoryAnimation.StartAnimation3(inv, designs.getDesignB(p), designs.getDesignA(p));
		
		Bukkit.getScheduler().runTaskLater(Firma.Main, new Runnable() {
			
			@Override
			public void run() {
				String arbeiteranzahl = String.valueOf(config.getPlayersOfFirma(config.getFirma(p.getUniqueId())).size()-1);
				ItemStack it = new ItemStack(Material.SIGN, 1);
				ItemMeta itm = it.getItemMeta();
				itm.setDisplayName("ß6ßlStatistiken:");
				l.clear();
				l.add("ß7Name der Firma:  ß6" + config.getFirma(p.getUniqueId()));
				l.add("ß7Firmen-Konto:  ß6" + config.getFirmasKontoFromFirmaOwner(p.getUniqueId()));
				l.add("ß7Firmen-Level:  ß6" + config.getFirmasLevelFromFirmaOwner(config.getOwnerOfFirma(config.getFirma(p.getUniqueId()))));
				l.add("ß7Besitzer:  ß6" + Bukkit.getPlayer(config.getOwnerOfFirma(config.getFirma(p.getUniqueId()))).getName());
				l.add("ß7Start-Gehalt:  ß6" + config.getFirmaLohn(p.getUniqueId()) + "%") ;
				l.add("ß7Arbeiter:  ß6" + arbeiteranzahl + "ß7/ß610");
				itm.setLore(l);
				it.setItemMeta(itm);
				
				
				inv.setItem(13, it);
				p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
			}
		}, 9L);
		
		
		Bukkit.getScheduler().runTaskLater(Firma.Main, new Runnable() {
			
			@Override
			public void run() {
				inv.setItem(43, ItemCreator.createItem(Material.BARRIER, 1, 0, "ßcßlSchlieﬂen"));
				inv.setItem(37, ItemCreator.createItem(Material.REDSTONE, 1, 0, "ß4ßlFirma aufgeben"));
			}
		}, 2L);
		
		
		
		Bukkit.getScheduler().runTaskLater(Firma.Main, new Runnable() {
			
			@Override
			public void run() {
				
				inv.setItem(30, ItemCreator.createItem(Material.GOLD_NUGGET, 1, 0, "ß9ßlGehalts-Einstellungen"));
				inv.setItem(32, ItemCreator.createItem(Material.BOOK_AND_QUILL, 1, 0, "ßaßlBewerbungen"));
				p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
			}
		}, 11L);
		
		
		Bukkit.getScheduler().runTaskLater(Firma.Main, new Runnable() {
			
			@Override
			public void run() {
				inv.setItem(20, ItemCreator.createSkullItem(p.getName(), "ßaßlArbeiter"));
				inv.setItem(24, ItemCreator.createItem(Material.BEACON, 1, 0, "ß6ßlFinanzen"));
				p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
			}
		}, 13L);
		
		
		Bukkit.getScheduler().runTaskLater(Firma.Main, new Runnable() {
		@Override
		public void run() {
		inv.setItem(49, ItemCreator.createItem(Material.RECORD_5, 1, 0, "ß5ßlDesign-Einstellungen"));
			}
		}, 9L);
		
		

		
		p.openInventory(inv);
	}
}
