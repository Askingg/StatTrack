package me.askingg.listeners;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.askingg.stattrack.Main;

public class BlockBreakListener implements Listener {
	File file = new File("plugins/StatTrack", "data.yml");
	FileConfiguration conf = YamlConfiguration.loadConfiguration(file);

	@EventHandler
	public void blockBreak(BlockBreakEvent event) {
		Player player = (Player) event.getPlayer();
		ItemStack item = player.getInventory().getItemInMainHand();
		ItemMeta meta = item.getItemMeta();
		Block block = event.getBlock();
		if (conf.getBoolean("Users." + player.getName() + ".OreTracker") == true) {
			if (player.getInventory().getItemInMainHand().getType().toString().contains("PICKAXE")) {
				if (block.getType() == Material.STONE || block.getType() == Material.COAL_ORE
						|| block.getType() == Material.IRON_ORE || block.getType() == Material.GOLD_ORE
						|| block.getType() == Material.DIAMOND_ORE || block.getType() == Material.LAPIS_ORE
						|| block.getType() == Material.REDSTONE_ORE || block.getType() == Material.GLOWING_REDSTONE_ORE
						|| block.getType() == Material.EMERALD_ORE) {
					List<String> lore = new ArrayList<String>();
					if (!meta.hasLore()) {
						addLore(player, item, meta, lore);
						addCounter(player, item, meta, lore, block);
						return;
					} else if (meta.hasLore()) {
						lore.addAll(meta.getLore());
						if (lore.contains(Main.colorCodes("&c &c &fStats &3&l»"))) {
							addCounter(player, item, meta, lore, block);
							return;
						} else {
							addLore(player, item, meta, lore);
							addCounter(player, item, meta, lore, block);
							return;
						}
					}
				}
			}
		} else {
			player.sendMessage(Main.colorCodes("&c"));
			player.sendMessage(Main.colorCodes(
					"&c &c &c &c &3&l» &fDid you know, you can track all of the blocks you mine? Execute &e/ST Toggle&f to enable&8/&fdisable it"));
			player.sendMessage(Main.colorCodes("&c"));
		}
	}

	private void addLore(Player player, ItemStack item, ItemMeta meta, List<String> lore) {
		lore.add(Main.colorCodes("&0"));
		lore.add(Main.colorCodes("&c &c &fStats &3&l»"));
		lore.add(Main.colorCodes("&0"));
		lore.add(Main.colorCodes("&c &c &c &c &7Stone&l » 0"));
		lore.add(Main.colorCodes("&c &c &c &c &8Coal&l » 0"));
		lore.add(Main.colorCodes("&c &c &c &c &fIron&l » 0"));
		lore.add(Main.colorCodes("&c &c &c &c &6Gold&l » 0"));
		lore.add(Main.colorCodes("&c &c &c &c &9Lapis&l » 0"));
		lore.add(Main.colorCodes("&c &c &c &c &cRedstone&l » 0"));
		lore.add(Main.colorCodes("&c &c &c &c &bDiamond&l » 0"));
		lore.add(Main.colorCodes("&c &c &c &c &aEmerald&l » 0"));
		lore.add(Main.colorCodes("&0"));
		meta.setLore(lore);
		item.setItemMeta(meta);
		player.updateInventory();
	}

	private void addCounter(Player player, ItemStack item, ItemMeta meta, List<String> lore, Block block) {
		if (block.getType() == Material.STONE || block.getType() == Material.COAL_ORE
				|| block.getType() == Material.IRON_ORE || block.getType() == Material.GOLD_ORE
				|| block.getType() == Material.DIAMOND_ORE || block.getType() == Material.LAPIS_ORE
				|| block.getType() == Material.REDSTONE_ORE || block.getType() == Material.GLOWING_REDSTONE_ORE
				|| block.getType() == Material.EMERALD_ORE) {
			String lineString = null;
			Integer line = -1;

			for (String str : lore) {
				line = line + 1;
				if (block.getType() == Material.STONE) {
					if (str.contains("Stone")) {
						lineString = str;
						break;
					}
				}
				if (block.getType() == Material.COAL_ORE) {
					if (str.contains("Coal")) {
						lineString = str;
						break;
					}
				}
				if (block.getType() == Material.IRON_ORE) {
					if (str.contains("Iron")) {
						lineString = str;
						break;
					}
				}
				if (block.getType() == Material.GOLD_ORE) {
					if (str.contains("Gold")) {
						lineString = str;
						break;
					}
				}
				if (block.getType() == Material.LAPIS_ORE) {
					if (str.contains("Lapis")) {
						lineString = str;
						break;
					}
				}
				if (block.getType() == Material.REDSTONE_ORE || block.getType() == Material.GLOWING_REDSTONE_ORE) {
					if (str.contains("Redstone")) {
						lineString = str;
						break;
					}
				}
				if (block.getType() == Material.DIAMOND_ORE) {
					if (str.contains("Diamond")) {
						lineString = str;
						break;
					}
				}
				if (block.getType() == Material.EMERALD_ORE) {
					if (str.contains("Emerald")) {
						lineString = str;
						break;
					}
				}
			}

			if (lineString.contains("Stone")) {
				if (block.getType() == Material.STONE) {
					Integer count = -1;
					String[] valueRaw = lineString.split(" » ");
					try {
						Integer value = Integer.parseInt(valueRaw[1]);
						count = value + 1;
					} catch (Exception e) {
						Main.message(player, "&cSome fatal error occored");
						return;
					}
					lore.set(line, Main.colorCodes(valueRaw[0] + "&l » " + count));
					meta.setLore(lore);
					item.setItemMeta(meta);
					player.updateInventory();
					return;
				}
			}

			if (lineString.contains("Coal")) {
				if (block.getType() == Material.COAL_ORE) {
					Integer count = -1;
					String[] valueRaw = lineString.split(" » ");
					try {
						Integer value = Integer.parseInt(valueRaw[1]);
						count = value + 1;
					} catch (Exception e) {
						Main.message(player, "&cSome fatal error occored");
						return;
					}
					lore.set(line, Main.colorCodes(valueRaw[0] + "&l » " + count));
					meta.setLore(lore);
					item.setItemMeta(meta);
					player.updateInventory();
					return;
				}
			}

			if (lineString.contains("Iron")) {
				if (block.getType() == Material.IRON_ORE) {
					Integer count = -1;
					String[] valueRaw = lineString.split(" » ");
					try {
						Integer value = Integer.parseInt(valueRaw[1]);
						count = value + 1;
					} catch (Exception e) {
						Main.message(player, "&cSome fatal error occored");
						return;
					}
					lore.set(line, Main.colorCodes(valueRaw[0] + "&l » " + count));
					meta.setLore(lore);
					item.setItemMeta(meta);
					player.updateInventory();
					return;
				}
			}

			if (lineString.contains("Gold")) {
				if (block.getType() == Material.GOLD_ORE) {
					Integer count = -1;
					String[] valueRaw = lineString.split(" » ");
					try {
						Integer value = Integer.parseInt(valueRaw[1]);
						count = value + 1;
					} catch (Exception e) {
						Main.message(player, "&cSome fatal error occored");
						return;
					}
					lore.set(line, Main.colorCodes(valueRaw[0] + "&l » " + count));
					meta.setLore(lore);
					item.setItemMeta(meta);
					player.updateInventory();
					return;
				}
			}

			if (lineString.contains("Lapis")) {
				if (block.getType() == Material.LAPIS_ORE) {
					Integer count = -1;
					String[] valueRaw = lineString.split(" » ");
					try {
						Integer value = Integer.parseInt(valueRaw[1]);
						count = value + 1;
					} catch (Exception e) {
						Main.message(player, "&cSome fatal error occored");
						return;
					}
					lore.set(line, Main.colorCodes(valueRaw[0] + "&l » " + count));
					meta.setLore(lore);
					item.setItemMeta(meta);
					player.updateInventory();
					return;
				}
			}

			if (lineString.contains("Redstone")) {
				if (block.getType() == Material.REDSTONE_ORE || block.getType() == Material.GLOWING_REDSTONE_ORE) {
					Integer count = -1;
					String[] valueRaw = lineString.split(" » ");
					try {
						Integer value = Integer.parseInt(valueRaw[1]);
						count = value + 1;
					} catch (Exception e) {
						Main.message(player, "&cSome fatal error occored");
						return;
					}
					lore.set(line, Main.colorCodes(valueRaw[0] + "&l » " + count));
					meta.setLore(lore);
					item.setItemMeta(meta);
					player.updateInventory();
					return;
				}
			}

			if (lineString.contains("Diamond")) {
				if (block.getType() == Material.DIAMOND_ORE) {
					Integer count = -1;
					String[] valueRaw = lineString.split(" » ");
					try {
						Integer value = Integer.parseInt(valueRaw[1]);
						count = value + 1;
					} catch (Exception e) {
						Main.message(player, "&cSome fatal error occored");
						return;
					}
					lore.set(line, Main.colorCodes(valueRaw[0] + "&l » " + count));
					meta.setLore(lore);
					item.setItemMeta(meta);
					player.updateInventory();
					return;
				}
			}

			if (lineString.contains("Emerald")) {
				if (block.getType() == Material.EMERALD_ORE) {
					Integer count = -1;
					String[] valueRaw = lineString.split(" » ");
					try {
						Integer value = Integer.parseInt(valueRaw[1]);
						count = value + 1;
					} catch (Exception e) {
						Main.message(player, "&cSome fatal error occored");
						return;
					}
					lore.set(line, Main.colorCodes(valueRaw[0] + "&l » " + count));
					meta.setLore(lore);
					item.setItemMeta(meta);
					player.updateInventory();
					return;
				}
			}
		}
	}
}
