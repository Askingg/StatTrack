package me.askingg.stattrack;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.askingg.commands.StatTrackCommands;
import me.askingg.listeners.BlockBreakListener;
import me.askingg.listeners.JoinListener;

public class Main extends JavaPlugin {

	@SuppressWarnings("unused")
	private StatTrackCommands stCommands;
	public static String prefix = "&8(&6StatTrack&8) &3&l» ";

	public void onEnable() {
		createFolders();
		Bukkit.getConsoleSender().sendMessage(colorCodes(prefix + "&fPlugin &asuccessfully &floaded"));
		getServer().getPluginManager().registerEvents(new JoinListener(), this);
		getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
		stCommands = new StatTrackCommands(this);
	}

	public static String colorCodes(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}

	public static void message(Player player, String string) {
		player.sendMessage(colorCodes(prefix + string));
	}
	
	public static void consoleMessage(String string) {
		Bukkit.getConsoleSender().sendMessage(colorCodes(prefix + string));
	}
	
	public static void reloadConfig(File file, FileConfiguration conf) {
		try {
			conf.save(file);
		} catch (Exception e) {
		}
		conf = YamlConfiguration.loadConfiguration(file);
	}

	private void createFolders() {
		File golemsFolder = new File("plugins/StatTrack");
		if (!(golemsFolder.exists())) {
			try {
				golemsFolder.mkdirs();
				consoleMessage("&aSuccessfully&f created the &aStatTrack&f folder");
			} catch (Exception e) {

			}
		}
		File dataFolder = new File("plugins/StatTrack", "data.yml");
		if (!(dataFolder.exists())) {
			try {
				dataFolder.createNewFile();
				consoleMessage("&aSuccessfully&f created the &adata.yml&f file");
			} catch (Exception e) {
			}
		}
	}
}
