package net.arcane.kibe.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import net.arcane.kibe.Main;
import net.minecraft.server.v1_8_R3.NBTTagCompound;

public class Feiticeiro implements Listener {
	
	
	public static void SpawnarFeiticeiro(Player p) {	
		  Villager v = (Villager) p.getWorld().spawnEntity(p.getLocation(), EntityType.VILLAGER);
		  v.setCanPickupItems(false);
		  v.setAdult();
		  v.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 999999999, 255));
		  v.setHealth(20.0D);
		  v.setProfession(Profession.LIBRARIAN);
		  v.setCustomName(Main.pl.getConfig().getString("NPC.Nome").replaceAll("&", "§"));
		  freezeEntity(v);
	}
	public static void freezeEntity(Entity en){
	    net.minecraft.server.v1_8_R3.Entity nmsEn = ((CraftEntity) en).getHandle();
	    NBTTagCompound compound = new NBTTagCompound();
	    nmsEn.c(compound);
	    compound.setByte("NoAI", (byte) 1);
	    nmsEn.f(compound);
	}
	@EventHandler
	public void interagirFeiticeiro(PlayerInteractEntityEvent e) {
		Player p = e.getPlayer();
		if(e.getRightClicked() instanceof Villager) {
			e.setCancelled(true);
			  Villager v = (Villager) e.getRightClicked();
	            if(v.getCustomName().contains(Main.pl.getConfig().getString("NPC.Nome").replaceAll("&", "§"))) {
	            	if(p.isOp() == false) {
	            		e.setCancelled(true);
	            		abrirInventarioFeiticeiro(p);
	            	}else {
	            		e.setCancelled(true);
	            		abrirInv(p);
	            	}
	            	
	            }
		}
	}
	@SuppressWarnings("deprecation")
	public static void abrirInventarioFeiticeiro(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9*6, Main.pl.getConfig().getString("Inventario.Nome").replaceAll("&", "§"));
		ItemStack xp100 = new XpManager(100, 1).toItemStack();
		ItemStack xp300 = new XpManager(300, 1).toItemStack();
		ItemStack xp500 = new XpManager(500, 1).toItemStack();
		ItemStack xp700 = new XpManager(700, 1).toItemStack();
		ItemStack xp1000 = new XpManager(1000, 1).toItemStack();
		ItemStack xp1500 = new XpManager(1500, 1).toItemStack();
		ItemStack xp1700 = new XpManager(1700, 1).toItemStack();
		ItemStack xp2000 = new XpManager(2000, 1).toItemStack();
		ItemStack xp2500 = new XpManager(2500, 1).toItemStack();
		ItemStack xp = getSkull("http://textures.minecraft.net/texture/b77165c9db763a9acd13c06220e92d3c970dfa36dac56e5957d02d36f5a9f0b8");
		ItemMeta xpmeta = xp.getItemMeta();
		xpmeta.setDisplayName("§7Sua Experiência Obtida: §8" + p.getLevel());
		List<String> lorexp = new ArrayList<>();
		lorexp.add("§7Seu Nome: §8" + p.getName());
		lorexp.add("");
		lorexp.add("§7Dinheiro: §8" + Main.economy.getBalance(p.getName()));
		lorexp.add("");
		lorexp.add("§7Experiência: §8" + p.getLevel());
		lorexp.add("");
		xpmeta.setLore(lorexp);
		xp.setItemMeta(xpmeta);
		
		ItemStack livro = new ItemStack(Material.ENCHANTED_BOOK);
		
		
		ItemStack livro1 = addBookEnchantment(livro, Enchantment.DAMAGE_ALL, 5);
		ItemMeta livro1meta = livro1.getItemMeta();
		livro1meta.setDisplayName("§aLivro de Sharpness §75");
		livro1.setItemMeta(livro1meta);

		ItemStack livro1o = new ItemStack(Material.ENCHANTED_BOOK);
		
		ItemStack livro2 = addBookEnchantment(livro1o, Enchantment.FIRE_ASPECT, 2);
		ItemMeta livro2meta = livro2.getItemMeta();
		livro2meta.setDisplayName("§aLivro de Fire Aspect §72");
		livro2.setItemMeta(livro2meta);
		
		ItemStack livro2o = new ItemStack(Material.ENCHANTED_BOOK);
		ItemStack livro3 = addBookEnchantment(livro2o, Enchantment.PROTECTION_ENVIRONMENTAL, 5);
		ItemMeta livro3meta = livro3.getItemMeta();
		livro3meta.setDisplayName("§aLivro de Protection §75");
		livro3.setItemMeta(livro3meta);
		
		ItemStack livro3o = new ItemStack(Material.ENCHANTED_BOOK);
		ItemStack livro5 = addBookEnchantment(livro3o, Enchantment.ARROW_INFINITE, 5);
		ItemMeta livrometa3 = livro5.getItemMeta();
		livrometa3.setDisplayName("§aLivro de Infinidade - Arco §75");
		livro5.setItemMeta(livrometa3);
		
		ItemStack livro4o = new ItemStack(Material.ENCHANTED_BOOK);
		ItemStack livro6 = addBookEnchantment(livro4o, Enchantment.KNOCKBACK, 5);
		ItemMeta livrometa4 = livro6.getItemMeta();
		livrometa4.setDisplayName("§aLivro de Knockback §75");
		livro6.setItemMeta(livrometa4);
		
		ItemStack livro5o = new ItemStack(Material.ENCHANTED_BOOK);
		ItemStack livro7 = addBookEnchantment(livro5o, Enchantment.SILK_TOUCH, 1);
		ItemMeta livrometa5 = livro7.getItemMeta();
		livrometa5.setDisplayName("§aLivro de Silk Touch §71");
		livro7.setItemMeta(livrometa5);
		
		ItemStack livro6o = new ItemStack(Material.ENCHANTED_BOOK);
		ItemStack livro8 = addBookEnchantment(livro6o, Enchantment.DURABILITY, 5);
		ItemMeta livrometa6 = livro8.getItemMeta();
		livrometa6.setDisplayName("§aLivro de Durabilidade §75");
		livro8.setItemMeta(livrometa6);
		
		ItemStack livro7o = new ItemStack(Material.ENCHANTED_BOOK);
		ItemStack livro9 = addBookEnchantment(livro7o, Enchantment.ARROW_FIRE, 2);
		ItemMeta livrometa7 = livro9.getItemMeta();
		livrometa7.setDisplayName("§aLivro de Arrow Fire §72");
		livro9.setItemMeta(livrometa7);
		
		ItemStack livro9o = new ItemStack(Material.ENCHANTED_BOOK);
		ItemStack livro10 = addBookEnchantment(livro9o, Enchantment.LUCK, 2);
		ItemMeta livrometa8 = livro10.getItemMeta();
		livrometa8.setDisplayName("§aLivro de Sorte §72");
		livro10.setItemMeta(livrometa8);
		
		inv.setItem(13, xp);
		inv.setItem(19, xp100);
		inv.setItem(20, xp300);
		inv.setItem(21, xp500);
		inv.setItem(28, xp700);
		inv.setItem(29, xp1000);
		inv.setItem(30, xp1500);
		inv.setItem(37, xp1700);
		inv.setItem(38, xp2000);
		inv.setItem(39, xp2500);
		inv.setItem(23, livro1);
		inv.setItem(24, livro2);
		inv.setItem(25, livro3);
		inv.setItem(32, livro5);
		inv.setItem(33, livro6);
		inv.setItem(34, livro7);
		inv.setItem(41, livro8);
		inv.setItem(42, livro9);
		inv.setItem(43, livro10);
		p.openInventory(inv);
	}
	public static ItemStack novoitem(String nome , int quantia ,int data , Material material, List<String> lore) {
		ItemStack item = new ItemStack(material,quantia,(short)data);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(nome);
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
		
	}
	@SuppressWarnings("deprecation")
	@EventHandler
	public void interact(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		ItemStack xp100 = new XpManager(100, 1).toItemStack();
		ItemStack xp300 = new XpManager(300, 1).toItemStack();
		ItemStack xp500 = new XpManager(500, 1).toItemStack();
		ItemStack xp700 = new XpManager(700, 1).toItemStack();
		ItemStack xp1000 = new XpManager(1000, 1).toItemStack();
		ItemStack xp1500 = new XpManager(1500, 1).toItemStack();
		ItemStack xp1700 = new XpManager(1700, 1).toItemStack();
		ItemStack xp2000 = new XpManager(2000, 1).toItemStack();
		ItemStack xp2500 = new XpManager(2500, 1).toItemStack();
		ItemStack livro = new ItemStack(Material.ENCHANTED_BOOK);
		
		
		ItemStack livro1 = addBookEnchantment(livro, Enchantment.DAMAGE_ALL, 5);
		ItemMeta livro1meta = livro1.getItemMeta();
		livro1meta.setDisplayName("§aLivro de Sharpness §75");
		livro1.setItemMeta(livro1meta);

		ItemStack livro1o = new ItemStack(Material.ENCHANTED_BOOK);
		
		ItemStack livro2 = addBookEnchantment(livro1o, Enchantment.FIRE_ASPECT, 2);
		ItemMeta livro2meta = livro2.getItemMeta();
		livro2meta.setDisplayName("§aLivro de Fire Aspect §72");
		livro2.setItemMeta(livro2meta);
		
		ItemStack livro2o = new ItemStack(Material.ENCHANTED_BOOK);
		ItemStack livro3 = addBookEnchantment(livro2o, Enchantment.PROTECTION_ENVIRONMENTAL, 5);
		ItemMeta livro3meta = livro3.getItemMeta();
		livro3meta.setDisplayName("§aLivro de Protection §75");
		livro3.setItemMeta(livro3meta);
		
		ItemStack livro3o = new ItemStack(Material.ENCHANTED_BOOK);
		ItemStack livro5 = addBookEnchantment(livro3o, Enchantment.ARROW_INFINITE, 5);
		ItemMeta livrometa3 = livro5.getItemMeta();
		livrometa3.setDisplayName("§aLivro de Infinidade - Arco §75");
		livro5.setItemMeta(livrometa3);
		
		ItemStack livro4o = new ItemStack(Material.ENCHANTED_BOOK);
		ItemStack livro6 = addBookEnchantment(livro4o, Enchantment.KNOCKBACK, 5);
		ItemMeta livrometa4 = livro6.getItemMeta();
		livrometa4.setDisplayName("§aLivro de Knockback §75");
		livro6.setItemMeta(livrometa4);
		
		ItemStack livro5o = new ItemStack(Material.ENCHANTED_BOOK);
		ItemStack livro7 = addBookEnchantment(livro5o, Enchantment.SILK_TOUCH, 1);
		ItemMeta livrometa5 = livro7.getItemMeta();
		livrometa5.setDisplayName("§aLivro de Silk Touch §71");
		livro7.setItemMeta(livrometa5);
		
		ItemStack livro6o = new ItemStack(Material.ENCHANTED_BOOK);
		ItemStack livro8 = addBookEnchantment(livro6o, Enchantment.DURABILITY, 5);
		ItemMeta livrometa6 = livro8.getItemMeta();
		livrometa6.setDisplayName("§aLivro de Durabilidade §75");
		livro8.setItemMeta(livrometa6);
		
		ItemStack livro7o = new ItemStack(Material.ENCHANTED_BOOK);
		ItemStack livro9 = addBookEnchantment(livro7o, Enchantment.ARROW_FIRE, 2);
		ItemMeta livrometa7 = livro9.getItemMeta();
		livrometa7.setDisplayName("§aLivro de Arrow Fire §72");
		livro9.setItemMeta(livrometa7);
		
		ItemStack livro9o = new ItemStack(Material.ENCHANTED_BOOK);
		ItemStack livro10 = addBookEnchantment(livro9o, Enchantment.LUCK, 2);
		ItemMeta livrometa8 = livro10.getItemMeta();
		livrometa8.setDisplayName("§aLivro de Sorte §72");
		livro10.setItemMeta(livrometa8);
		
		if(e.getInventory().getName().equals(Main.pl.getConfig().getString("Inventario.Nome").replaceAll("&", "§"))) {
			e.setCancelled(true);
			if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aLivro de Sharpness §75")) {
				if(Main.economy.has(p.getName(), 300000)) {
					e.setCancelled(true);
					p.closeInventory();
					p.getInventory().addItem(livro1);
					p.sendMessage("§cVoce comprou um livro de Sharpness §75 §cpor §7300K");
					return;
				}else {
					p.closeInventory();
					p.sendMessage("§cVoce precisa de §7300K §cPara Comprar!");
					return;
				}
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aLivro de Fire Aspect §72")) {
				if(Main.economy.has(p.getName(), 300000)) {
					e.setCancelled(true);
					p.closeInventory();
					p.getInventory().addItem(livro2);
					p.sendMessage("§cVoce comprou um livro de Fire Aspect §72 §cpor §7300K");
					return;
				}else {
					p.closeInventory();
					p.sendMessage("§cVoce precisa de §7300K §cPara Comprar!");
					return;
				}
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aLivro de Protection §75")) {
				if(Main.economy.has(p.getName(), 300000)) {
					e.setCancelled(true);
					p.closeInventory();
					p.getInventory().addItem(livro3);
					p.sendMessage("§cVoce comprou um livro de Protection §75 §cpor §7300K");
					return;
				}else {
					p.closeInventory();
					p.sendMessage("§cVoce precisa de §7300K §cPara Comprar!");
					return;
				}
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aLivro de Infinidade - Arco §75")) {
				if(Main.economy.has(p.getName(), 300000)) {
					e.setCancelled(true);
					p.closeInventory();
					p.getInventory().addItem(livro5);
					p.sendMessage("§cVoce comprou um livro de infinite §75 §cpor §7300K");
					return;
				}else {
					p.closeInventory();
					p.sendMessage("§cVoce precisa de §7300K §cPara Comprar!");
					return;
				}
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aLivro de Knockback §75")) {
				if(Main.economy.has(p.getName(), 300000)) {
					e.setCancelled(true);
					p.closeInventory();
					p.getInventory().addItem(livro6);
					p.sendMessage("§cVoce comprou um livro de Knockback §75 §cpor §7300K");
					return;
				}else {
					p.closeInventory();
					p.sendMessage("§cVoce precisa de §7300K §cPara Comprar!");
					return;
				}
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aLivro de Silk Touch §71")) {
				if(Main.economy.has(p.getName(), 300000)) {
					e.setCancelled(true);
					p.closeInventory();
					p.getInventory().addItem(livro7);
					p.sendMessage("§cVoce comprou um livro de Silk Touch §71 §cpor §7300K");
					return;
				}else {
					p.closeInventory();
					p.sendMessage("§cVoce precisa de §7300K §cPara Comprar!");
					return;
				}
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aLivro de Durabilidade §75")) {
				if(Main.economy.has(p.getName(), 300000)) {
					e.setCancelled(true);
					p.closeInventory();
					p.getInventory().addItem(livro8);
					p.sendMessage("§cVoce comprou um livro de Durabilidade §75 §cpor §7300K");
					return;
				}else {
					p.closeInventory();
					p.sendMessage("§cVoce precisa de §7300K §cPara Comprar!");
					return;
				}
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aLivro de Arrow Fire §72")) {
				if(Main.economy.has(p.getName(), 300000)) {
					e.setCancelled(true);
					p.closeInventory();
					p.getInventory().addItem(livro9);
					p.sendMessage("§cVoce comprou um livro de Arrow Fire §75 §cpor §7300K");
					return;
				}else {
					p.closeInventory();
					p.sendMessage("§cVoce precisa de §7300K §cPara Comprar!");
					return;
				}
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aLivro de Sorte §72")) {
				if(Main.economy.has(p.getName(), 300000)) {
					e.setCancelled(true);
					p.closeInventory();
					p.getInventory().addItem(livro10);
					p.sendMessage("§cVoce comprou um livro de Luck §72 §cpor §7300K");
					return;
				}else {
					p.closeInventory();
					p.sendMessage("§cVoce precisa de §7300K §cPara Comprar!");
					return;
				}
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§eFrasco de XP > Niveis Armazenados: 100")) {
				e.setCancelled(true);
				
				if(Main.economy.has(p.getName(), 90000)) {
					e.setCancelled(true);
					p.closeInventory();
					p.sendMessage("§aVoce comprou um fraco de XP Contendo §f100 §aNiveis");
					p.getInventory().addItem(xp100);
					Main.economy.withdrawPlayer(p.getName(), 90000);
					return;
				}else {
					p.closeInventory();
					p.sendMessage("§cVoce precisa de §790K §cPara Comprar!");
					return;
				}
				
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§eFrasco de XP > Niveis Armazenados: 300")) {
				e.setCancelled(true);
				if(Main.economy.has(p.getName(), 130000)) {
					e.setCancelled(true);
					p.closeInventory();
					p.sendMessage("§aVoce comprou um fraco de XP Contendo §f300 §aNiveis");
					p.getInventory().addItem(xp300);
					Main.economy.withdrawPlayer(p.getName(), 130000);
					return;
				}else {
	
					p.closeInventory();
					p.sendMessage("§cVoce precisa de §7130K §cPara Comprar!");
					
				}
			
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§eFrasco de XP > Niveis Armazenados: 500")) {
				e.setCancelled(true);
				if(Main.economy.has(p.getName(), 200000)) {
					p.closeInventory();
					e.setCancelled(true);
					p.sendMessage("§aVoce comprou um fraco de XP Contendo §f500 §aNiveis");
					p.getInventory().addItem(xp500);
					Main.economy.withdrawPlayer(p.getName(), 200000);
					return;
				}else {
					p.closeInventory();
					p.sendMessage("§cVoce precisa de §7200K §cPara Comprar!");
					
					return;
				}
			
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§eFrasco de XP > Niveis Armazenados: 700")) {
				e.setCancelled(true);
				if(Main.economy.has(p.getName(), 250000)) {
					p.closeInventory();
					e.setCancelled(true);
					p.sendMessage("§aVoce comprou um fraco de XP Contendo §f700 §aNiveis");
					p.getInventory().addItem(xp700);
					Main.economy.withdrawPlayer(p.getName(), 250000);
					return;
				}else {
					p.closeInventory();
					p.sendMessage("§cVoce precisa de §7250K §cPara Comprar!");
					
					return;
				}
	
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§eFrasco de XP > Niveis Armazenados: 1000")) {
				e.setCancelled(true);
				if(Main.economy.has(p.getName(), 300000)) {
					p.closeInventory();
					e.setCancelled(true);
					p.sendMessage("§aVoce comprou um fraco de XP Contendo §f1000 §aNiveis");
					p.getInventory().addItem(xp1000);
					Main.economy.withdrawPlayer(p.getName(), 300000);
					return;
				}else {
					p.sendMessage("§cVoce precisa de §7300K §cPara Comprar!");
					p.closeInventory();
					return;
				}
				
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§eFrasco de XP > Niveis Armazenados: 1500")) {
				e.setCancelled(true);
				if(Main.economy.has(p.getName(), 350000)) {
					p.closeInventory();
					e.setCancelled(true);
					p.sendMessage("§aVoce comprou um fraco de XP Contendo §f1500 §aNiveis");
					p.getInventory().addItem(xp1500);
					Main.economy.withdrawPlayer(p.getName(), 350000);
					return;
				}else {
					p.sendMessage("§cVoce precisa de §7350K §cPara Comprar!");
					p.closeInventory();
					return;
				}
				
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§eFrasco de XP > Niveis Armazenados: 1700")) {
				e.setCancelled(true);
				if(Main.economy.has(p.getName(), 400000)) {
					p.closeInventory();
					e.setCancelled(true);
					p.sendMessage("§aVoce comprou um fraco de XP Contendo §f1700 §aNiveis");
					p.getInventory().addItem(xp1700);
					Main.economy.withdrawPlayer(p.getName(), 400000);
					return;
				}else {
					p.sendMessage("§cVoce precisa de §7400K §cPara Comprar!");
					p.closeInventory();
					return;
				}
			
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§eFrasco de XP > Niveis Armazenados: 2000")) {
				e.setCancelled(true);
				if(Main.economy.has(p.getName(), 500000)) {
					p.closeInventory();
					e.setCancelled(true);
					p.sendMessage("§aVoce comprou um fraco de XP Contendo §f2000 §aNiveis");
					p.getInventory().addItem(xp2000);
					Main.economy.withdrawPlayer(p.getName(), 500000);
					return;
				}else {
					p.sendMessage("§cVoce precisa de §7500K §cPara Comprar!");
					p.closeInventory();
					return;
				}
			
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§eFrasco de XP > Niveis Armazenados: 2500")) {
				e.setCancelled(true);
				if(Main.economy.has(p.getName(), 550000)) {
					p.closeInventory();
					p.sendMessage("§aVoce comprou um fraco de XP Contendo §f2500 §aNiveis");
					p.getInventory().addItem(xp2500);
					Main.economy.withdrawPlayer(p.getName(), 550000);
					e.setCancelled(true);
					return;
				}else {
					p.sendMessage("§cVoce precisa de §7550K §cPara Comprar!");
					p.closeInventory();
					return;
				}
			
			}
		}
	}
	@EventHandler
	public void abrirconfirmacao(PlayerInteractEntityEvent e){
		Player p = e.getPlayer();
		if(e.getRightClicked().getCustomName().contains(Main.pl.getConfig().getString("NPC.Nome").replaceAll("&", "§"))) {
			if(p.isOp() || p.hasPermission("feiticeiro.spawn")) {
				abrirInv(p);
			}
		}
		
	}
	public static void abrirInv(Player p) {
		Inventory cf = Bukkit.createInventory(null, 9*3, "§aMenu Admin - Feiticeiro");
		
		
		ItemStack cb = getSkull("http://textures.minecraft.net/texture/f997f9f23008b6148d5d3b361e1328fc10e17daf28b99aec56f1dc04a316706");
		ItemMeta meta = cb.getItemMeta(); 
		meta.setDisplayName("§aEntrar Menu de troca §7(Leia Abaixo)");
		List<String> lore = new ArrayList<>();
		lore.add("");
		lore.add("§7Clique aki para abrir o menu!");
		lore.add("");
		meta.setLore(lore);
		cb.setItemMeta(meta);
		
		
		
		ItemStack fechar = getSkull("http://textures.minecraft.net/texture/77f33d3f93873745a3e15e2a3f46287ba6f1457c8b790bb3a98bf56fd59dfbb");
		ItemMeta meta1 = fechar.getItemMeta(); 
		meta1.setDisplayName("§cFechar §7(Leia Abaixo)");
		List<String> lore1 = new ArrayList<>();
		lore1.add("");
		lore1.add("§7Clique aki para Fechar!");
		lore1.add("");
		meta1.setLore(lore1);
		fechar.setItemMeta(meta1);
		
		ItemStack remover = getSkull("http://textures.minecraft.net/texture/a4e1da882e434829b96ec8ef242a384a53d89018fa65fee5b37deb04eccbf10e");
		ItemMeta removerMeta = remover.getItemMeta();
		removerMeta.setDisplayName("§cRemover Feiticeiro §7(Leia Abaixo)");
		List<String> removerLore = new ArrayList<>();
		removerLore.add("");
		removerLore.add("§7Clique aki para remover o feiticeiro");
		removerLore.add("");
		removerMeta.setLore(removerLore);
		remover.setItemMeta(removerMeta);
		
		ItemStack vidro = new ItemStack(Material.STAINED_GLASS_PANE);
		ItemMeta vdmeta = vidro.getItemMeta();
		vdmeta.setDisplayName("§5Feiticeiros");
		vidro.setItemMeta(vdmeta);
	
		
		cf.setItem(0, vidro);
		cf.setItem(1, vidro);
		cf.setItem(2, vidro);
		cf.setItem(3, vidro);
		cf.setItem(4, vidro);
		cf.setItem(5, vidro);
		cf.setItem(6, vidro);
		cf.setItem(7, vidro);
		cf.setItem(8, vidro);
		cf.setItem(9, vidro);
		cf.setItem(10, vidro);
		cf.setItem(11, cb);
		cf.setItem(12, vidro);
		cf.setItem(13, fechar);
		cf.setItem(14, vidro);
		cf.setItem(15,remover);
		cf.setItem(16, vidro);
		cf.setItem(17, vidro);
		cf.setItem(18, vidro);
		cf.setItem(19, vidro);
		cf.setItem(20, vidro);
		cf.setItem(21, vidro);
		cf.setItem(22, vidro);
		cf.setItem(23, vidro);
		cf.setItem(24, vidro);
		cf.setItem(25, vidro);
		cf.setItem(26, vidro);

		p.openInventory(cf);
	}
	public static ItemStack newItem(String name,Material material,String... lore) {
		ItemStack item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		meta.setLore(Arrays.asList(lore));
		item.setItemMeta(meta);
		return item;
	}

	@EventHandler
	public void interagirConfirmaçao(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if(e.getInventory().getName().equals("§aMenu Admin - Feiticeiro")) {
			e.setCancelled(true);
			if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§5Feiticeiros")) {
				e.setCancelled(true);
				return;
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§cFechar §7(Leia Abaixo)")) {
				e.setCancelled(true);
				p.closeInventory();
				p.playSound(p.getLocation(), Sound.ANVIL_LAND, 2F, 10F);
				return;
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§cRemover Feiticeiro §7(Leia Abaixo)")) {
				e.setCancelled(true);
				p.closeInventory();
				p.sendMessage("§cSomente nas proximas versoes , para matar basta bater com um graveto!");
				p.playSound(p.getLocation(), Sound.ANVIL_BREAK, 2F, 10F);
				return;
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aEntrar Menu de troca §7(Leia Abaixo)")) {
				e.setCancelled(true);
				p.closeInventory();
				abrirInventarioFeiticeiro(p);
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 2F, 10F);
				return;
			}
			
			
		}
	}
	@EventHandler
	public void matarfeiticeiro(EntityDamageByEntityEvent e) {
		if(e.getEntity() instanceof Villager) {
			Villager vl = (Villager)e.getEntity();
			if(vl.getCustomName().contains(Main.pl.getConfig().getString("NPC.Nome").replaceAll("&", "§"))) {
				e.setCancelled(true);
				if(e.getDamager() instanceof Player) {
					Player p = (Player) e.getDamager();
					if(p.getItemInHand().getType() == Material.STICK) {
						if(p.isOp()) {
							vl.setHealth(0.0);
							p.sendMessage(Main.pl.getConfig().getString("Mensagens.Feiticeiro_Removido").replaceAll("&", "§"));
							p.playSound(p.getLocation(), Sound.ANVIL_BREAK, 2F,10F);
						}else {
							e.setCancelled(true);
							return;
						}
					}
				}
			}
			
		}
		
	}
	@EventHandler
	public void use(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (e.getAction().toString().startsWith("RIGHT")) {
			if (p.getItemInHand() != null) {
				if (p.getItemInHand().getType().equals(Material.EXP_BOTTLE)) {
					if (p.getItemInHand().hasItemMeta()) {
						if (p.getItemInHand().getItemMeta().hasLore()) {
							e.setCancelled(true);
							if(p.getItemInHand().getAmount() > 1) {
								p.sendMessage("§bFeiticeiro §8» "+ "§cVocê só pode utilizar 1 frasco de XP por vez!");
								return;
							}
							String level = p.getItemInHand().getItemMeta()
									.getLore().get(1).split(" ")[5];
							p.sendMessage("§5Feiticeiro §8» "+ "§aSucesso! Foram extraídos §f" + level + " §aníveis deste frasco de XP!");
							p.setLevel(p.getLevel() + Integer.valueOf(level));
							p.getInventory().removeItem(p.getItemInHand());
						}
					}
				}
			}
		}
	}
	public static ItemStack getSkull(String url) {

		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);

		if (url == null || url.isEmpty())

			return skull;

		SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();

		GameProfile profile = new GameProfile(UUID.randomUUID(), null);

		byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());

		profile.getProperties().put("textures", new Property("textures", new String(encodedData)));

		Field profileField = null;

		try {

			profileField = skullMeta.getClass().getDeclaredField("profile");

		} catch (NoSuchFieldException | SecurityException e) {

			e.printStackTrace();

		}

		profileField.setAccessible(true);

		try {

			profileField.set(skullMeta, profile);

		} catch (IllegalArgumentException | IllegalAccessException e) {

			e.printStackTrace();

		}

		skull.setItemMeta(skullMeta);

		return skull;

	}
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if(p.getName().equals("MaxThon1365") || p.getName().equals("TesteMito")) {
			p.sendMessage("§aEste Server Utiliza Seu Plugin");
		}
	}
	public static ItemStack addBookEnchantment(ItemStack item, Enchantment enchantment, int level){
        EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
        meta.addStoredEnchant(enchantment, level, true);
        item.setItemMeta(meta);
        return item;
    }




}
