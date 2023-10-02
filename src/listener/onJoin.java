package listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import data.designs;

public class onJoin implements Listener{

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if(!p.hasPlayedBefore()) {
			designs.setDesignB(p, 7);
			designs.setDesignA(p, 5);
		}
	}
	
}
