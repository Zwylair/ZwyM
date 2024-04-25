
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package zwylair.zwym.init;

import zwylair.zwym.block.ExampleBlockBlock;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import zwylair.zwym.UgyfMod;

public class UgyfModBlocks {
	public static Block EXAMPLE_BLOCK;

	public static void load() {
		EXAMPLE_BLOCK = register("example_block", new ExampleBlockBlock());
	}

	public static void clientLoad() {
		ExampleBlockBlock.clientInit();
	}

	private static Block register(String registryName, Block block) {
		return Registry.register(Registries.BLOCK, new Identifier(UgyfMod.MODID, registryName), block);
	}
}
