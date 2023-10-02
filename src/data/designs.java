package data;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class designs {
	
	public static File data = new File("plugins/Firma", "designs.yml");
	public static FileConfiguration conf = YamlConfiguration.loadConfiguration(data);
	
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
	
	
	//DESIGN VERWALTUNG
	
	public static void setDesignA(Player p, int i) {
		
		conf.set("Players." + p.getUniqueId() + ".border", i);
		saveData();
		
	}
	public static void setDesignB(Player p, int i) {
		conf.set("Players." + p.getUniqueId() + ".background", i);
		saveData();
	}	
	
	public static int getDesignA(Player p) {
		return conf.getInt("Players." + p.getUniqueId() + ".border");
	}
	
	public static int getDesignB(Player p) {
		return conf.getInt("Players." + p.getUniqueId() + ".background");
	}
	
	public static boolean checkifPlayerexist(Player p) {
		boolean a = false;
	  try {
		for(String s: conf.getConfigurationSection("Players").getKeys(false)) {
			if(s.equals(p.getUniqueId())) {
				a = true; break;
			} else {
				a = false;
			}
		}

		return a;
	  } catch (NullPointerException ex) {
		  return true;
	  }
	}

}
