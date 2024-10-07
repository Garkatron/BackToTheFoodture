package deus.bttf.DevTools.Utils;


import deus.bttf.DevTools.Debug.Debug;

import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemFood;
import turniplabs.halplibe.helper.CreativeHelper;

import java.lang.reflect.Field;

import static deus.bttf.Items.BTTFItems.GenericItemBuilder;


public class ItemUtils {

	public static ItemFood makeFood(int id, String name, int healAmount, int ticksPerHeal, boolean favouriteWolfMeat, int maxStackSize) {
		return GenericItemBuilder
			.build(new ItemFood(
				name,
				id,
				healAmount,
				ticksPerHeal,
				favouriteWolfMeat,
				maxStackSize
			));
	}

	public static void assignPriorities(Class<?> c) {
		int initialPriority = 1001;
		try {
			String[] staticFieldNames = StaticFieldsExtractor.extractor(c);
			for (String fieldName : staticFieldNames) {
				Field field = c.getDeclaredField(fieldName);
				field.setAccessible(true);
				Object value = field.get(null);

				if (value instanceof Item) {
					Item item = (Item) value;
					CreativeHelper.setPriority(item, initialPriority);
					initialPriority++;
				}
			}
		} catch (NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

}
