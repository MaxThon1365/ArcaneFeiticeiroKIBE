package net.arcane.kibe.utils;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class XpManager {

	private int niveis;
	private int quantia;
	
	public XpManager(int niveis, int quantia) {
		this.niveis = niveis;
		this.setQuantia(quantia);
	}

	public int getNiveis() {
		return niveis;
	}

	public void setNiveis(int niveis) {
		this.niveis = niveis;
	}
	
	public ItemStack toItemStack() {
		ItemStack item = new ItemStack(Material.EXP_BOTTLE, quantia);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§eFrasco de XP > Niveis Armazenados: " + niveis);
		ArrayList<String> lore = new ArrayList<>();
		lore.add("");
		lore.add(" §7• §fNíveis de XP:§e " + niveis);
		lore.add(HiddenStringUtils.encodeString(new Random().nextInt(101010100) + " "));
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}

	public int getQuantia() {
		return quantia;
	}

	public void setQuantia(int quantia) {
		this.quantia = quantia;
	}
}
