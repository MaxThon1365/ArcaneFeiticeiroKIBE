package net.arcane.kibe;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.arcane.kibe.npc.SpawnNPC;
import net.arcane.kibe.utils.Feiticeiro;
import net.milkbowl.vault.economy.Economy;

public class Main extends JavaPlugin{
	public static Main pl;
	  public static Economy economy = null;
    private void setInstance(Main instance) {
        Main.pl = instance;
    }
    
    private boolean setupEconomy()
    {
      RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(Economy.class);
      if (economyProvider != null) {
        economy = (Economy)economyProvider.getProvider();
      }
      return economy != null;
    }
    
    
	public void onEnable() {
		setupEconomy();
		pl = this;
		setInstance(this);
		this.saveDefaultConfig();
		getCommand("setarfeiticeiro").setExecutor(new SpawnNPC());
		Bukkit.getPluginManager().registerEvents(new Feiticeiro(), this);
		Bukkit.getConsoleSender().sendMessage("§a[Arcane§5Feiticeiro§a] Kibado com sucesso");
		if(Bukkit.getPluginManager().getPlugin("Vault") == null) {		
			Bukkit.getConsoleSender().sendMessage("§cVault Nao detectado! DesKibando");
			Bukkit.getPluginManager().disablePlugin(this);
		}
	}
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("§c[Arcane§5Feiticeiro§c] desKibado com sucesso");
	}

}
