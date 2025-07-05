package deus.bttf.Config;

import deus.bttf.DevTools.Debug.Debug;
import turniplabs.halplibe.util.ConfigHandler;
import turniplabs.halplibe.util.TomlConfigHandler;
import turniplabs.halplibe.util.toml.Toml;

import java.util.Locale;
import java.util.Properties;

import static deus.bttf.BTTFMain.MOD_ID;
public class ModConfig {

	private int BLOCK_ID;
	private int ITEM_ID;

	private static final TomlConfigHandler config;

	static {
		Toml toml = new Toml(MOD_ID.toUpperCase(Locale.ROOT));

		toml.addCategory("IDs")
			.addEntry("startBlockId", 14000)
			.addEntry("startItemId", 26000);

		config = new TomlConfigHandler(MOD_ID, toml);

	}

	public ModConfig() {
		BLOCK_ID = config.getInt("IDs.startBlockId");
		ITEM_ID = config.getInt("IDs.startItemId");
	}

	public TomlConfigHandler getConfig() {
		return config;
	}

	public int newBlockID() {
		BLOCK_ID = BLOCK_ID + 1;
		return BLOCK_ID;
	}

	public int newItemID() {
		ITEM_ID = ITEM_ID + 1;
		return ITEM_ID;
	}
}
