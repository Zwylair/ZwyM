/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package zwylair.zwym.init;

import zwylair.zwym.UgyfMod;
import net.minecraft.client.item.ClampedModelPredicateProvider;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class UgyfModItems {
	public static Item EXAMPLE_BLOCK;

	public static void load() {
		EXAMPLE_BLOCK = register("example_block", new BlockItem(UgyfModBlocks.EXAMPLE_BLOCK, new Item.Settings()));
	}

	public static void clientLoad() {
	}

	private static Item register(String registryName, Item item) {
		return Registry.register(Registries.ITEM, new Identifier(UgyfMod.MODID, registryName), item);
	}

	private static void registerBlockingProperty(Item item) {
		ModelPredicateProviderRegistry.register(item, new Identifier("blocking"), (ClampedModelPredicateProvider) ModelPredicateProviderRegistry.get(Items.SHIELD, new Identifier("blocking")));
	}
}
