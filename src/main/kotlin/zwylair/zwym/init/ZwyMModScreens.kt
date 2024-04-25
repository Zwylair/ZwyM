/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package zwylair.zwym.init

import net.minecraft.client.gui.screen.ingame.HandledScreens
import zwylair.zwym.client.gui.EnchantSplitterGuiScreen

object ZwyMModScreens {
    fun load() {
        HandledScreens.register(
            ZwyMModMenus.ENCHANT_SPLITTER_GUI
        ) { container, inventory, text ->
            EnchantSplitterGuiScreen(
                container,
                inventory,
                text
            )
        }
    }
}
