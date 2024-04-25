package zwylair.zwym.client.gui

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType
import net.minecraft.client.gui.screen.ingame.HandledScreens
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.network.PacketByteBuf
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.util.Identifier
import zwylair.zwym.ZwyM
import zwylair.zwym.world.inventory.EnchantSplitterGuiMenu

object ModScreens {
    lateinit var ENCHANT_SPLITTER_GUI: ScreenHandlerType<EnchantSplitterGuiMenu>

    fun init() {
        ENCHANT_SPLITTER_GUI = register(
            ZwyM.id("enchant_splitter_gui")
        ) { id: Int, inv: PlayerInventory, extraData: PacketByteBuf? -> EnchantSplitterGuiMenu(id, inv, extraData) }
        EnchantSplitterGuiMenu.screenInit()

        HandledScreens.register(ENCHANT_SPLITTER_GUI) { container, inventory, text ->
            EnchantSplitterGuiScreen(container, inventory, text)
        }
    }

    private fun register(
        id: Identifier,
        factory: (Int, PlayerInventory, PacketByteBuf?) -> EnchantSplitterGuiMenu
    ): ScreenHandlerType<EnchantSplitterGuiMenu> {
        return Registry.register(Registries.SCREEN_HANDLER, id, ExtendedScreenHandlerType(factory))
    }
}
