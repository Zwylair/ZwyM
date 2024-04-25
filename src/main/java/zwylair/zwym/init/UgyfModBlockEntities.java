
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package zwylair.zwym.init;

import zwylair.zwym.block.entity.ExampleBlockBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import zwylair.zwym.UgyfMod;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;

public class UgyfModBlockEntities {
	public static BlockEntityType<?> EXAMPLE_BLOCK;

	public static void load() {
		EXAMPLE_BLOCK = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(UgyfMod.MODID, "example_block"), FabricBlockEntityTypeBuilder.create(ExampleBlockBlockEntity::new, UgyfModBlocks.EXAMPLE_BLOCK).build(null));
	}
}
