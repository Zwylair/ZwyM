/*
 *	MCreator note:
 *
 *	If you lock base mod element files, you can edit this file and the proxy files
 *	and they won't get overwritten. If you change your mod package or modid, you
 *	need to apply these changes to this file MANUALLY.
 *
 *
 *	If you do not lock base mod element files in Workspace settings, this file
 *	will be REGENERATED on each build.
 *
 */
package zwylair.zwym;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import zwylair.zwym.init.UgyfModMenus;
import zwylair.zwym.init.UgyfModItems;
import zwylair.zwym.init.UgyfModBlocks;
import zwylair.zwym.init.UgyfModBlockEntities;

import net.fabricmc.api.ModInitializer;

public class UgyfMod implements ModInitializer {
	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MODID = "ugyf";

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing UgyfMod");

		UgyfModBlocks.load();
		UgyfModItems.load();
		UgyfModBlockEntities.load();

		UgyfModMenus.load();

	}
}
