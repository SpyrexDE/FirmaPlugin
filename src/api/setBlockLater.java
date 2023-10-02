package api;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;

public class setBlockLater {

    public static void placeLater(Material m, Location loc, Long l) {
    	
        new BukkitRunnable() {
			
			@Override
			public void run() {
				  loc.getBlock().setType(m);
				
			}
		}.runTaskLater(de.spyrex.main.Firma.Main, l);
    	
    }
}
