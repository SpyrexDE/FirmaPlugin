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
		Inventory inv = Bukkit.createInventory(null, 6*9, "�a�l�nFIRMA");
		InventoryAnimation.StartAnimation3(inv, designs.getDesignB(p), designs.getDesignA(p));
		
		Bukkit.getScheduler().runTaskLater(Firma.Main, new Runnable() {
			
			@Override
			public void run() {
				String arbeiteranzahl = String.valueOf(config.getPlayersOfFirma(config.getFirma(p.getUniqueId())).size()-1);
				ItemStack it = new ItemStack(Material.SIGN, 1);
				ItemMeta itm = it.getItemMeta();
				itm.setDisplayName("�6�lStatistiken:");
				l.clear();
				l.add("�7Name der Firma:  �6" + config.getFirma(p.getUniqueId()));
				l.add("�7Firmen-Konto:  �6" + config.getFirmasKontoFromFirmaOwner(p.getUniqueId()));
				l.add("�7Firmen-Level:  �6" + config.getFirmasLevelFromFirmaOwner(config.getOwnerOfFirma(config.getFirma(p.getUniqueId()))));
				l.add("�7Besitzer:  �6" + Bukkit.getPlayer(config.getOwnerOfFirma(config.getFirma(p.getUniqueId()))).getName());
				l.add("�7Start-Gehalt:  �6" + config.getFirmaLohn(p.getUniqueId()) + "%") ;
				l.add("�7Arbeiter:  �6" + arbeiteranzahl + "�7/�610");
				itm.setLore(l);
				it.setItemMeta(itm);
				
				
				inv.setItem(13, it);
				p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
			}
		}, 9L);
		
		
		Bukkit.getScheduler().runTaskLater(Firma.Main, new Runnable() {
			
			@Override
			public void run() {
				inv.setItem(43, ItemCreator.createItem(Material.BARRIER, 1, 0, "�c�lSchlie�en"));
				inv.setItem(37, ItemCreator.createItem(Material.REDSTONE, 1, 0, "�4�lFirma aufgeben"));
			}
		}, 2L);
		
		
		
		Bukkit.getScheduler().runTaskLater(Firma.Main, new Runnable() {
			
			@Override
			public void run() {
				
				inv.setItem(30, ItemCreator.createItem(Material.GOLD_NUGGET, 1, 0, "�9�lGehalts-Einstellungen"));
				inv.setItem(32, ItemCreator.createItem(Material.BOOK_AND_QUILL, 1, 0, "�a�lBewerbungen"));
				p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
			}
		}, 11L);
		
		
		Bukkit.getScheduler().runTaskLater(Firma.Main, new Runnable() {
			
			@Override
			public void run() {
				inv.setItem(20, ItemCreator.createSkullItem(p.getName(), "�a�lArbeiter"));
				inv.setItem(24, ItemCreator.createItem(Material.BEACON, 1, 0, "�6�lFinanzen"));
				p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
			}
		}, 13L);
		
		
		Bukkit.getScheduler().runTaskLater(Firma.Main, new Runnable() {
		@Override
		public void run() {
		inv.setItem(49, ItemCreator.createItem(Material.RECORD_5, 1, 0, "�5�lDesign-Einstellungen"));
			}
		}, 9L);
		
		

		
		p.openInventory(inv);
	}
}
