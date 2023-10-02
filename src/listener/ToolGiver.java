package listener;

import java.awt.event.ItemEvent;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import api.ItemCreator;
import data.config;
import de.spyrex.main.Firma;
import net.minecraft.server.v1_8_R3.NBTTagCompound;

public class ToolGiver implements Listener{

	@EventHandler
	public void onWorldChange(PlayerChangedWorldEvent e) {
		if(e.getPlayer().getWorld().getName().equals("Mines")) {
			try {
			switch(config.getFirmasLevelFromFirmaOwner(config.getOwnerOfFirma(config.getFirma(e.getPlayer().getUniqueId())))) {
			
			case 1: e.getPlayer().getInventory().setItem(0,ItemCreator.createItem(Material.WOOD_PICKAXE, 1, 0, "§eSpitzhacke")); break;
			case 2: e.getPlayer().getInventory().setItem(0,ItemCreator.createItem(Material.STONE_PICKAXE, 1, 0, "§eSpitzhacke")); break;
			case 3: e.getPlayer().getInventory().setItem(0,ItemCreator.createItem(Material.IRON_PICKAXE, 1, 0, "§eSpitzhacke")); break;
			case 4: e.getPlayer().getInventory().setItem(0,ItemCreator.createItem(Material.GOLD_PICKAXE, 1, 0, "§eSpitzhacke")); break;
			case 5: e.getPlayer().getInventory().setItem(0,ItemCreator.createItem(Material.DIAMOND_PICKAXE, 1, 0, "§eSpitzhacke")); break;
			
			}
		}catch(NullPointerException ex) {
			
		}
		}
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		if(e.getPlayer().getWorld().getName().equals("Mines")) {
			try {
			switch(config.getFirmasLevelFromFirmaOwner(config.getOwnerOfFirma(config.getFirma(e.getPlayer().getUniqueId())))) {
			
			case 1: e.getPlayer().getInventory().setItem(0,ItemCreator.createItem(Material.WOOD_PICKAXE, 1, 0, "§eSpitzhacke")); break;
			case 2: e.getPlayer().getInventory().setItem(0,ItemCreator.createItem(Material.STONE_PICKAXE, 1, 0, "§eSpitzhacke")); break;
			case 3: e.getPlayer().getInventory().setItem(0,ItemCreator.createItem(Material.IRON_PICKAXE, 1, 0, "§eSpitzhacke")); break;
			case 4: e.getPlayer().getInventory().setItem(0,ItemCreator.createItem(Material.GOLD_PICKAXE, 1, 0, "§eSpitzhacke")); break;
			case 5: e.getPlayer().getInventory().setItem(0,ItemCreator.createItem(Material.DIAMOND_PICKAXE, 1, 0, "§eSpitzhacke")); break;
			
			}
			}catch(NullPointerException ex) {
				
			}
		}
	}
	@EventHandler
	public void onDamage(BlockBreakEvent e) {
		if(e.getPlayer().getWorld().getName().equals("Mines")) {
			if(e.getPlayer().getItemInHand().getType().toString().contains("PICKAXE")) {
				e.getPlayer().getItemInHand().setDurability((short) (e.getPlayer().getItemInHand().getDurability() - 1));
			}
		}
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		if(e.getPlayer().getWorld().getName().equals("Mines")) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onDMG(EntityDamageEvent e) {
		if(e.getEntity().getWorld().getName().equals("Mines")) {
			e.setCancelled(true);
		}
	}
	@EventHandler
	public void onFoodChange(FoodLevelChangeEvent e) {
		if(e.getEntity().getWorld().getName().equals("Mines")) {
			e.setFoodLevel(20);
		}
	}
}
