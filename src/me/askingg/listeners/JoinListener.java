package me.askingg.listeners;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.askingg.stattrack.Main;

public class JoinListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = (Player) event.getPlayer();
		File file = new File("plugins/StatTrack", "data.yml");
		FileConfiguration conf = YamlConfiguration.loadConfiguration(file);
		if (file.exists()) {
			if (conf.getString("Users." + player.getName() + ".Blocks") == null) {
				try {
					conf.set("Users." + player.getName() + ".Blocks", 0);
					conf.set("Users." + player.getName() + ".OreTracker", true);
					conf.save(file);
					Main.consoleMessage("&aSuccessfully&f created entrance for &a" + player.getName());
				} catch (Exception e) {
				}
			}
		}
	}
}
