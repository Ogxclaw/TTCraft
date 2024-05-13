package com.kirik.ttcraft.main;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class PlayerConfiguration {

	private UUID uuid;
	private File playerData;
	private FileConfiguration playerDataConfig;
	private TTCraft _instance = TTCraft.instance;

	public PlayerConfiguration(UUID uuid) {
		setUUID(uuid);

		playerData = new File(_instance.getDataFolder() + "/players/", uuid + ".yml");
		playerDataConfig = YamlConfiguration.loadConfiguration(playerData);
	}

	public void createPlayerConfig() {
		try {
			playerData.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void createPlayerDefaults() {
		if (playerData.length() <= 0) {
			playerDataConfig.set("level", 0);
		}
	}

	public FileConfiguration getPlayerConfig() {
		return playerDataConfig;
	}

	public void savePlayerConfig() {
		try {
			getPlayerConfig().save(playerData);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public UUID getUUID() {
		return uuid;
	}

	public void setUUID(UUID uuid) {
		this.uuid = uuid;
	}

}
