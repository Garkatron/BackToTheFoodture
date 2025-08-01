package deus.bttf.Config;

import turniplabs.halplibe.util.TomlConfigHandler;
import turniplabs.halplibe.util.toml.Toml;

import java.util.Locale;

import static deus.bttf.BTTFMain.MOD_ID;
public class ModConfig {

	private int BLOCK_ID;
	private int ITEM_ID;

	public static final TomlConfigHandler TOML_CONFIG;

	static {
		Toml toml = new Toml(MOD_ID.toUpperCase(Locale.ROOT));

		toml.addCategory("IDs")
			.addEntry("startBlockId", 14000)
			.addEntry("startItemId", 26000);

		toml.addCategory("Drops.chicken_meat")
			.addEntry("min", 1)
			.addEntry("max", 3);

		toml.addCategory("Drops.cow_beef")
			.addEntry("min", 1)
			.addEntry("max", 4);

		toml.addCategory("Drops.sheep_mutton")
			.addEntry("min", 1)
			.addEntry("max", 3);

		toml.addCategory("Drops.spider_eye")
			.addEntry("min", 0)
			.addEntry("max", 1);

		toml.addCategory("Drops.zombie_rotten_flesh")
			.addEntry("min", 0)
			.addEntry("max", 2);

		TOML_CONFIG = new TomlConfigHandler(MOD_ID, toml);

	}

	public ModConfig() {
		BLOCK_ID = TOML_CONFIG.getInt("IDs.startBlockId");
		ITEM_ID = TOML_CONFIG.getInt("IDs.startItemId");
	}

	public TomlConfigHandler getConfig() {
		return TOML_CONFIG;
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
