package data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import de.spyrex.main.Firma;


public class config {

	public static File data = new File("plugins/Firma", "data.yml");
	public static FileConfiguration conf = YamlConfiguration.loadConfiguration(data);
	
	public static void setLocation(String LocationName,Player p){
		
		conf.set("Warps." + LocationName+".X", p.getLocation().getBlockX());
		conf.set("Warps." + LocationName+".Y", p.getLocation().getBlockY());
		conf.set("Warps." + LocationName+".Z", p.getLocation().getBlockZ());
		conf.set("Warps." + LocationName+".Yaw", p.getLocation().getYaw());
		conf.set("Warps." + LocationName+".Pitch", p.getLocation().getPitch());
		conf.set("Warps." + LocationName+".World", p.getWorld().getName());
		
		try {
			conf.save(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static Location getLocation(String LocationName) {
		
		double x = conf.getDouble("Warps." + LocationName+".X");
		double y = conf.getDouble("Warps." + LocationName+".Y");
		double z = conf.getDouble("Warps." + LocationName+".Z");
		float yaw = (float) conf.getDouble("Warps." + LocationName+".Yaw");
		float pitch = (float) conf.getDouble("Warps." + LocationName+".Pitch");
		String world = conf.getString("Warps." + LocationName+".World");
		World w = Bukkit.getWorld(world);
		Location l = new Location(w, x, y, z, yaw, pitch);
		return l;
	}
	
	public static void deleteLocation(String LocationName){
		conf.set("Warps." + LocationName+".X", null);
		conf.set("Warps." + LocationName+".Y", null);
		conf.set("Warps." + LocationName+".Z", null);
		conf.set("Warps." + LocationName+".Yaw", null);
		conf.set("Warps." + LocationName+".Pitch", null);
		conf.set("Warps." + LocationName+".World", null);
	}
	
	public static void checkIfStatsDataExists(){
	if(data.exists()){
		return;
	}else{
		try {
			data.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}
	
	public static void saveData() {
		try {
			conf.save(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//FIRMEN-VERWALTUNG
	//ALLES VOM SPIELER AUS
	public static void removePlayerFromBewerbungen(String firma, UUID p) {
		List<String> l = conf.getStringList("Players." + getOwnerOfFirma(firma) + ".Bewerbungen");
		l.remove(p.toString());
		conf.set("Players." + getOwnerOfFirma(firma) + ".Bewerbungen", l);
		saveData();
	}
	public static List<String> getBewerbungen(String firma) {
		

			List<String> l = conf.getStringList("Players." + getOwnerOfFirma(firma) + ".Bewerbungen");

		
		return l;
	}
	
	public static void sendTitleToAllPlayersInFirma(String firma, String title1, String title2) {
		for(Player p: Bukkit.getOnlinePlayers()) {
			try {
			if(config.getFirma(p.getUniqueId()).equals(firma)) {
			p.sendTitle(title1, title2);
			}
			}catch(NullPointerException ex) { ex.printStackTrace();}
		}
	}
	public static void createBewerbung(UUID p, String firma) {
		List<String> l = conf.getStringList("Players." + getOwnerOfFirma(firma) + ".Bewerbungen");
		if(!l.contains(p.toString())) {
		l.add(p.toString());
		}
		conf.set("Players." + getOwnerOfFirma(firma) + ".Bewerbungen", l);
		saveData();
	}
	public static int getFirmasLevelFromFirmaOwner(UUID p) {
		return conf.getInt("Players." + p + ".Level");
	}
	
	public static void setFirmasLevelFromFirmaOwner(UUID p, int level) {
		conf.set("Players." + p + ".Level", level);
		saveData();
	}
	public static double getFirmasKontoFromFirmaOwner(UUID p) {
		return (double)conf.get("Players." + p + ".Konto");
	}
	
	public static void setFirmasKontoFromFirmaOwner(UUID p, double f) {
		conf.set("Players." + p + ".Konto", f);
		saveData();
	}
	
	public static String getFirma(UUID p) {
		return (String) conf.get("Players." + p + ".Firma");
	}
	public static Integer getFirmaLohn(UUID p) {
		return (Integer) conf.get("Players." + p + ".Lohn");
	}
	
	public static void createFirma(String name, Player p) {
	
		conf.set("Players." + p.getUniqueId() + ".Firma", name);
		conf.set("Players." + p.getUniqueId() + ".Owner", true);
		conf.set("Players." + p.getUniqueId() + ".Lohn", 50);
		conf.set("Players." + p.getUniqueId() + ".Konto", 0.0);
		conf.set("Players." + p.getUniqueId() + ".Level", 1);
		
		saveData();
	}
	public static void DeleteFirma(String name) {
		for(String s: conf.getConfigurationSection("Players").getKeys(false)) {
			if(conf.get("Players." + s + ".Firma").equals(name)) {
			conf.set("Players." + s + ".Firma", null);
			conf.set("Players." + s + ".Owner", null);
			conf.set("Players." + s + ".Lohn", null);
			conf.set("Players." + s + ".Konto", null);
			conf.set("Players." + s + ".Level", null);
			deleteLocation(name);
			saveData();
			
			}
		}
		saveData();
	}
	public static void setPlayersFirma(UUID p,String name) {
		conf.set("Players." + p + ".Firma", name);
		conf.set("Players." + p + ".Lohn", getFirmaLohn(getOwnerOfFirma(name)));
		conf.set("Players." + p + ".Owner", false);
		saveData();
	}
	public static int getPlayersLohn(UUID p) {
		return conf.getInt("Players." + p.toString() + ".Lohn");
	}
	
	public static void setPlayersLohn(UUID p, int lohn) {
		conf.set("Players." + p + ".Lohn", lohn);
		saveData();
	}
	public static List<String> getPlayersOfFirma(String name) {
		ArrayList<String> players = new ArrayList<>();
		try {
		for(String s : conf.getConfigurationSection("Players").getKeys(false)){
			if(conf.getString("Players." + s + ".Firma").equals(name)) {
				players.add(s);
			}
		}
		} catch(NullPointerException ex) {
			
		}
		return players;
	}
	
	public static void deleteFirmasPlayer(UUID p) {
		conf.set("Players." + p + ".Firma", null);
		conf.set("Players." + p + ".Owner", null);
		conf.set("Players." + p + ".Lohn", null);
		saveData();
	}
	
	public static void changeOwnerOfFirma(Player vorher, Player nachher) {
		if(isOwnerOfFirma(vorher.getUniqueId())) {
			if(getFirma(nachher.getUniqueId()) == null) {
				conf.getString("Players").replaceAll(vorher.getUniqueId().toString(), nachher.getUniqueId().toString());
				saveData();
				vorher.sendMessage(Firma.prefix + "Du hast deine Firma erfolgreich an §6" + nachher.getName() + " §7abgegeben.");
			} else {vorher.sendMessage(Firma.prefix + "Der Spieler §6" + nachher.getName() + " §7ist zur Zeit in einer Firma. Bitte ihn, sie zu verlassen, damit er neuer Besitzer deiner Firma werden kann.");}
		} else {vorher.sendMessage(Firma.prefix + "Du bist nicht besitzer dieser Firma!");}
		
	}
	
	public static UUID getOwnerOfFirma(String name) {
		UUID ownerUUID = null;
		try {
			for(String s: conf.getConfigurationSection("Players").getKeys(false)) {
				if(conf.getBoolean("Players." + s + ".Owner") == true && conf.get("Players." + s + ".Firma").equals(name)) {
					ownerUUID =  UUID.fromString(s);
				}
			}
		} catch(NullPointerException ex) {
			ex.printStackTrace();
		}
		return ownerUUID;
	}
	public static boolean isOwnerOfFirma(UUID p) {
		if(conf.getBoolean("Players." + p + ".Owner") == true) {
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean checkifFirmaexist(String name) {
		boolean a = false;
	  try {
		for(String s: conf.getConfigurationSection("Players").getKeys(false)) {
			if(conf.get("Players." + s + ".Firma").equals(name)) {
				a = true; break;
			} else {
				a = false; break;
			}
		}

		return a;
	  } catch (NullPointerException ex) {
		  return false;
	  }
	}
}
