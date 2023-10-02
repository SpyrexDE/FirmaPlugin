package inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import api.ItemCreator;
import data.config;

public class UpgradeQuestion {

	public static void openQuestionInv(Player p) {
		
		Inventory inv = Bukkit.createInventory(null, 9, "§8§lAuf §4§lLvl. " + (config.getFirmasLevelFromFirmaOwner(config.getOwnerOfFirma(config.getFirma(p.getUniqueId()))) + 1) +" §8§lupgraden?");
		
		inv.setItem(0, ItemCreator.createItem(Material.GREEN_RECORD, 1, 0, "§a§lJa, für §6" + (config.getFirmasLevelFromFirmaOwner(config.getOwnerOfFirma(config.getFirma(p.getUniqueId()))) * 1500)+ " Gems §akaufen!"));
		inv.setItem(8, ItemCreator.createItem(Material.BARRIER, 1, 0, "§c§lAbbrechen"));
		
		p.openInventory(inv);
	}
	
}
