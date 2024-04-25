
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package zwylair.zwym.init;

import zwylair.zwym.client.gui.EnchantSplitterGuiScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class UgyfModScreens {
	public static void load() {
		HandledScreens.register(UgyfModMenus.ENCHANT_SPLITTER_GUI, EnchantSplitterGuiScreen::new);
	}
}
