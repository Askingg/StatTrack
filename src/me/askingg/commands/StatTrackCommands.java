package me.askingg.commands;

import java.io.File;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.askingg.stattrack.Main;

public class StatTrackCommands implements CommandExecutor {

	@SuppressWarnings("unused")
	private Main plugin;

	public StatTrackCommands(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("enchant").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;

		if (args.length == 0) {
			player.sendMessage(Main.colorCodes("&8(&e/StatTrack&8) &3&l» &fView the help list"));
			player.sendMessage(Main.colorCodes("&8(&e/StatTrack Dev&8) &3&l» &fView the developer"));
			player.sendMessage(Main.colorCodes("&8(&e/StatTrack Toggle&8) &3&l» &fToggle the ore&8-&ftracker"));
			return true;
		} else {
			if (args[0].equalsIgnoreCase("dev")) {
				player.sendMessage(Main.colorCodes(Main.prefix + "&fDeveloper &3&l» &aAskingg"));
				return true;
			}
			if (args[0].equalsIgnoreCase("toggle")) {
				File file = new File("plugins/StatTrack", "data.yml");
				if (file.exists()) {
					FileConfiguration conf = YamlConfiguration.loadConfiguration(file);
					if (conf.getBoolean("Users." + player.getName() + ".OreTracker") == true) {
						conf.set("Users." + player.getName() + ".OreTracker", false);
						try {
							Main.reloadConfig(file, conf);
							Main.message(player, "&cDisabled&f the Ore&8-&fTracker");
							return true;
						} catch (Exception e) {
							Main.message(player, "&cSome fatal error occored");
							return true;
						}
					} else if (conf.getBoolean("Users." + player.getName() + ".OreTracker") == false) {
						conf.set("Users." + player.getName() + ".OreTracker", true);
						try {
							Main.reloadConfig(file, conf);
							Main.message(player, "&aEnabled&f the Ore&8-&fTracker");
							return true;
						} catch (Exception e) {
							Main.message(player, "&cSome fatal error occored");
							return true;
						}
					}
				}
			}
		}

		return false;
	}
}
