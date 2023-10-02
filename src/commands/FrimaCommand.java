package commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import data.config;
import de.spyrex.main.Firma;
import inventories.InfoInv;
import inventories.OwnerInv;
import inventories.Unsetted;

public class FrimaCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		
	if(args.length == 0) {
		if(config.getFirma(p.getUniqueId()) == null) {
		
		Unsetted.openInv(p);
		
		} else if(config.isOwnerOfFirma(p.getUniqueId())) {
			
			OwnerInv.openInv(p);
			
		} else {
			
			InfoInv.openInfoInv(p);
			
		}
	} else if(args.length == 1) {
		if(args[0].equals("reload")) {
			if(p.hasPermission("Firma.Staff")) {
				p.sendMessage(Firma.prefix + "Lade Plugin neu...");
				Firma.Main.getPluginLoader().disablePlugin(Firma.Main);
				Firma.Main.getPluginLoader().enablePlugin(Firma.Main);
				p.sendMessage(Firma.prefix + "§aDas Plugin wurde erfolgreich neu geladen!");
			} else {
				p.sendMessage(Firma.prefix + "Du hast nicht die benötigten Berechtigungen dies zu tun :(");
			}
		}
	}
		
		return false;
	}

}
