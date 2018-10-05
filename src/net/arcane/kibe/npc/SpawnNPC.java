package net.arcane.kibe.npc;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.arcane.kibe.Main;
import net.arcane.kibe.utils.Feiticeiro;

public class SpawnNPC implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		Player p = (Player)sender;
		if(cmd.getName().equalsIgnoreCase("setarfeiticeiro")) {
			if(!(sender instanceof Player)) {
				Bukkit.getConsoleSender().sendMessage(Main.pl.getConfig().getString("Mensagens.Ser_Jogador").replaceAll("&", "§"));
				return true;
			}
		if(args.length >= 0) {	
			if(p.hasPermission("feiticeiro.spawn") || p.isOp()) {
				Feiticeiro.SpawnarFeiticeiro(p);
				p.sendMessage(Main.pl.getConfig().getString("Mensagens.Feiticeiro_Setado").replaceAll("&", "§"));
				return true;
				
			}else {
				p.sendMessage(Main.pl.getConfig().getString("Mensagens.Sem_Permissao").replaceAll("&", "§"));
				return true;
				}
			}
 		}
		
		return false;
	}

}
