package inventories;

import java.util.List;
import java.util.UUID;

import javax.persistence.GenerationType;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Dye;

import api.ItemCreator;
import data.config;
import de.spyrex.main.Firma;

public class SellInv implements Listener,CommandExecutor{

	
	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		Inventory inv = Bukkit.createInventory(null, 45, "§6§lItem-Abgabge");
		
		//List<String> l = null;
		//l.add("§7Lege hier deine Erfarmten Rohstoffe");
		//l.add("§7hinein und schließe das Inventar anschließend.");
		//l.add("§7Du wirst deine Juwelen anschließend erhalten!");
		//inv.setItem(44, ItemCreator.createItem(Material.SIGN, 1, 0, "§eInfo"));
		
		((Player)arg0).openInventory(inv);
		return false;
	}
	
	
	@EventHandler
	public static void onClose(InventoryCloseEvent e) {
		Player p = (Player) e.getPlayer();
		if(e.getInventory().getTitle().equals("§6§lItem-Abgabge")) {
	        
			double preis = 0;
		

			for(ItemStack i: e.getInventory().getContents()) {
	
		          	if (i != null){

				if(i.getType() == Material.COAL) {
					preis += 1*i.getAmount();
		        }else
				if(i.getType() == Material.IRON_ORE) {
					preis += 5*i.getAmount();
		        }else
				if(i.getType() == Material.DIAMOND) {
					preis += 10*i.getAmount();
		        }else
				if(i.getType() == Material.GOLD_ORE) {
					preis += 3*i.getAmount();
		        }else
				if(i.getType() == Material.WHEAT) {
					preis += 0.1*i.getAmount();
		        }else
				if(i.getType() == Material.LOG) {
					preis += 0.4*i.getAmount();
		        }else
					if(i.getType() == Material.EMERALD) {
						preis += 12*i.getAmount();
			        }else
				if(i.getType() == Material.INK_SACK) {
					preis += 0.1*i.getAmount();
		        }else {
		        	p.getInventory().addItem(i);
		        }
		        } 
			}
			
			if(!isInvEmpty(e.getInventory())) {
			((Player) e.getPlayer()).playSound(e.getPlayer().getLocation(), Sound.LEVEL_UP, 0, 0);
			
			//LOHN BERECHNUNG
			UUID firmaowner =config.getOwnerOfFirma(config.getFirma(p.getUniqueId()));
			double firmakonto = config.getFirmasKontoFromFirmaOwner(firmaowner);
			double firmakontospäter = firmakonto + preis * (100-config.getFirmaLohn(p.getUniqueId())) /100;
			
			config.setFirmasKontoFromFirmaOwner(firmaowner, firmakontospäter);
			preis = preis * config.getFirmaLohn(p.getUniqueId()) /100;
			//CoinSystem.addCoins(p, preis); //COINS API ZU DOUBLE MACHEN!!!
			
			
			//LOHN BERECHNUNG
			

			p.sendMessage(Firma.prefix + "Lohn:§6 " + preis + "❂ ");
			
			}
		}
		
		
		
	}
	
	public static boolean isInvEmpty(Inventory inv) {
	    for(ItemStack it : inv.getContents())
	    {
	        if(it != null) return false;
	    }
	    return true;
	}
	
}
