package zwylair.zwym.client.gui

import com.mojang.blaze3d.systems.RenderSystem
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.ingame.HandledScreen
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.text.Text
import net.minecraft.world.World
import zwylair.zwym.ZwyM
import zwylair.zwym.world.inventory.EnchantSplitterGuiMenu

class EnchantSplitterGuiScreen(
    container: EnchantSplitterGuiMenu,
    inventory: PlayerInventory?,
    text: Text?
) : HandledScreen<EnchantSplitterGuiMenu?>(
    container,
    inventory,
    text
) {
    private val world: World = container.world
    private val entity: PlayerEntity

    init {
        x = container.x
        y = container.y
        entity = container.entity
        backgroundWidth = 176
        backgroundHeight = 166
    }

    override fun render(guiGraphics: DrawContext, mouseX: Int, mouseY: Int, partialTicks: Float) {
        renderBackground(guiGraphics, mouseX, mouseY, partialTicks)
        super.render(guiGraphics, mouseX, mouseY, partialTicks)
        drawMouseoverTooltip(guiGraphics, mouseX, mouseY)
    }

    override fun drawForeground(guiGraphics: DrawContext, mouseX: Int, mouseY: Int) { }

    override fun drawBackground(guiGraphics: DrawContext, partialTicks: Float, gx: Int, gy: Int) {
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f)
        RenderSystem.enableBlend()
        RenderSystem.defaultBlendFunc()
        guiGraphics.drawTexture(
            texture,
            x, y, 0f, 0f,
            backgroundWidth, backgroundHeight,
            backgroundWidth, backgroundHeight
        )
        RenderSystem.disableBlend()
    }

    override fun keyPressed(key: Int, b: Int, c: Int): Boolean {
        if (key == 256) {
            client!!.player!!.closeHandledScreen()
            return true
        }
        return super.keyPressed(key, b, c)
    }

    companion object {
        private val texture = ZwyM.id("textures/gui/enchant_splitter_gui.png")
    }
}
