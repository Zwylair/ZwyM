
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package zwylair.zwym.init;

import zwylair.zwym.world.inventory.EnchantSplitterGuiMenu;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import zwylair.zwym.UgyfMod;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;

public class UgyfModMenus {
	public static ScreenHandlerType<EnchantSplitterGuiMenu> ENCHANT_SPLITTER_GUI;

	public static void load() {
		ENCHANT_SPLITTER_GUI = Registry.register(Registries.SCREEN_HANDLER, new Identifier(UgyfMod.MODID, "enchant_splitter_gui"), new ExtendedScreenHandlerType<>(EnchantSplitterGuiMenu::new));
		EnchantSplitterGuiMenu.screenInit();
	}
}
