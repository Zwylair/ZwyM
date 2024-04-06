package zwylair.zwym

import net.minecraft.block.Block
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.registry.RegistryKey
import net.minecraft.util.Identifier

object ModObject {
    open class ModBlock(settings: Settings) : Block(settings) {
        open lateinit var id: Identifier
        open var itemGroupAddTo: RegistryKey<ItemGroup>? = null
        open var glinted: Boolean = false
    }

    open class ModItem(settings: Settings) : Item(settings) {
        open lateinit var id: Identifier
        open var itemGroupAddTo: RegistryKey<ItemGroup>? = null
    }

    open class ModBlockItem(block: ModBlock, settings: Settings) : BlockItem(block, settings) {
        open var id: Identifier = block.id
        open var itemGroupAddTo: RegistryKey<ItemGroup>? = block.itemGroupAddTo
        open var glinted: Boolean = block.glinted
    }

    open class GlintedItem(settings: Settings) : ModItem(settings) {
        override fun hasGlint(stack: ItemStack?): Boolean { return true }
    }

    open class GlintedBlockItem(block: ModBlock, settings: Settings) : ModBlockItem(block, settings) {
        override fun hasGlint(stack: ItemStack?): Boolean { return true }
    }
}
