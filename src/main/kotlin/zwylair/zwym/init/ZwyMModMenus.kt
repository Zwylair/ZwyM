/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package zwylair.zwym.init

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.network.PacketByteBuf
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.util.Identifier
import zwylair.zwym.ZwyM
import zwylair.zwym.world.inventory.EnchantSplitterGuiMenu

object ZwyMModMenus {
    var ENCHANT_SPLITTER_GUI: ScreenHandlerType<EnchantSplitterGuiMenu>? = null

    fun load() {
        ENCHANT_SPLITTER_GUI = Registry.register(
            Registries.SCREEN_HANDLER,
            Identifier(ZwyM.MODID, "enchant_splitter_gui"),
            ExtendedScreenHandlerType { id: Int, inv: PlayerInventory, extraData: PacketByteBuf? ->
                EnchantSplitterGuiMenu(
                    id,
                    inv,
                    extraData
                )
            })
        EnchantSplitterGuiMenu.screenInit()
    }
}