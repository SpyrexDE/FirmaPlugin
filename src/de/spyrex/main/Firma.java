package de.spyrex.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import commands.FrimaCommand;
import data.config;
import data.designs;
import inventories.SellInv;
import listener.ChatListener;
import listener.FirmaBlockBreak;
import listener.InfoInvListener;
import listener.InvClickListener;
import listener.ToolGiver;
import listener.onJoin;


public class Firma extends JavaPlugin{

	public static Firma Main;
	public static String prefix = "§9§l│ §6§lFIRMA§8§l» §7";
	
	public void onEnable(){
		Main=this;
		getCommand("firma").setExecutor(new FrimaCommand());
		getCommand("abgabe").setExecutor(new SellInv());
        Bukkit.getPluginManager().registerEvents(new InvClickListener(), this);
        Bukkit.getPluginManager().registerEvents(new FirmaBlockBreak(), this);
        Bukkit.getPluginManager().registerEvents(new SellInv(), this);
        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
        Bukkit.getPluginManager().registerEvents(new onJoin(), this);
        Bukkit.getPluginManager().registerEvents(new ToolGiver(), this);
        Bukkit.getPluginManager().registerEvents(new InfoInvListener(), this);
        
        config.checkIfStatsDataExists();
        designs.checkIfStatsDataExists();
	}
	
}
