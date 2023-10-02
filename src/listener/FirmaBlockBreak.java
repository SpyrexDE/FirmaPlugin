package listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import data.config;
import de.spyrex.main.Firma;

public class FirmaBlockBreak implements Listener{

	@EventHandler
	public void onHit(EntityDamageByEntityEvent e) {
		if(e.getEntity().getWorld().getName().equals("Mines")) {
		e.setCancelled(true);
		}
		
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		if(e.getPlayer().getWorld().getName().equals("Mines") 
				&! e.getPlayer().isOp()
				) {
		e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
	if(e.getPlayer().getWorld().getName().equals("Mines")) {
		if(config.getFirma(e.getPlayer().getUniqueId()) !=null) {
		if(e.getPlayer().getItemInHand().getType() != Material.COMMAND) {
			e.setCancelled(true);
		
		if(e.getBlock().getType() == Material.COAL_ORE||
				e.getBlock().getType() == Material.IRON_ORE||
				e.getBlock().getType() == Material.DIAMOND_ORE||
				e.getBlock().getType() == Material.GOLD_ORE||
				e.getBlock().getType() == Material.LAPIS_ORE||
				e.getBlock().getType() == Material.EMERALD_ORE||
				//e.getBlock().getType() == Material.WHEAT||
				e.getBlock().getType() == Material.LOG) {
		e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.NOTE_PLING, 0, 0);
			
		for(ItemStack d :e.getBlock().getDrops()) {
			e.getPlayer().getInventory().addItem(d);
		}
		
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Firma.Main, new BukkitRunnable()
            {
                public void run()
                {
                  e.getBlock().getLocation().getBlock().setType(Material.BEDROCK);
                  
                }
              }, 0L);
            api.setBlockLater.placeLater(e.getBlock().getType(), e.getBlock().getLocation(), 3600L); //3MIN
            
		
		
		
			}
		
		}
		}else {
			e.getPlayer().sendMessage(Firma.prefix + "Du arbeitest zur Zeit für keine Firma! Bewerbe dich bei einer mit §6/Firma§7!");
			e.setCancelled(true);
		}
	} 
	}
	
}
