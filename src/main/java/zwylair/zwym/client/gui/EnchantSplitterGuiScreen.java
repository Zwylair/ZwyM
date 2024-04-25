
package zwylair.zwym.client.gui;

import zwylair.zwym.world.inventory.EnchantSplitterGuiMenu;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class EnchantSplitterGuiScreen extends HandledScreen<EnchantSplitterGuiMenu> {
	private final static HashMap<String, Object> guistate = EnchantSplitterGuiMenu.guistate;
	private final World world;
	private final int x, y, z;
	private final PlayerEntity entity;

	public EnchantSplitterGuiScreen(EnchantSplitterGuiMenu container, PlayerInventory inventory, Text text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.backgroundWidth = 176;
		this.backgroundHeight = 166;
	}

	private static final Identifier texture = new Identifier("ugyf:textures/screens/enchant_splitter_gui.png");

	@Override
	public void render(DrawContext guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.drawMouseoverTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void drawBackground(DrawContext guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.drawTexture(texture, this.x, this.y, 0, 0, this.backgroundWidth, this.backgroundHeight, this.backgroundWidth, this.backgroundHeight);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.client.player.closeHandledScreen();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	public void handledScreenTick() {
		super.handledScreenTick();
	}

	@Override
	protected void drawForeground(DrawContext guiGraphics, int mouseX, int mouseY) {
	}

	@Override
	public void close() {
		super.close();
	}

	@Override
	public void init() {
		super.init();
	}
}
