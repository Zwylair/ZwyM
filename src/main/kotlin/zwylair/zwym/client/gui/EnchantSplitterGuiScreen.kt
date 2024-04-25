package zwylair.zwym.client.gui

import com.mojang.blaze3d.systems.RenderSystem
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.ingame.HandledScreen
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import net.minecraft.world.World
import zwylair.zwym.ZwyM
import zwylair.zwym.world.inventory.EnchantSplitterGuiMenu

class EnchantSplitterGuiScreen(container: EnchantSplitterGuiMenu, inventory: PlayerInventory?, text: Text?) :
    HandledScreen<EnchantSplitterGuiMenu?>(container, inventory, text) {
    private val world: World = container.world
    private val entity: PlayerEntity

    init {
        this.x = container.x
        this.y = container.y
        this.entity = container.entity
        this.backgroundWidth = 176
        this.backgroundHeight = 166
    }

    override fun render(guiGraphics: DrawContext, mouseX: Int, mouseY: Int, partialTicks: Float) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks)
        super.render(guiGraphics, mouseX, mouseY, partialTicks)
        this.drawMouseoverTooltip(guiGraphics, mouseX, mouseY)
    }

    override fun drawBackground(guiGraphics: DrawContext, partialTicks: Float, gx: Int, gy: Int) {
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f)
        RenderSystem.enableBlend()
        RenderSystem.defaultBlendFunc()
        guiGraphics.drawTexture(
            texture,
            this.x,
            this.y, 0f, 0f,
            this.backgroundWidth,
            this.backgroundHeight,
            this.backgroundWidth,
            this.backgroundHeight
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

    public override fun handledScreenTick() {
        super.handledScreenTick()
    }

    override fun drawForeground(guiGraphics: DrawContext, mouseX: Int, mouseY: Int) {
    }

    override fun close() {
        super.close()
    }

    public override fun init() {
        super.init()
    }

    companion object {
        private val guistate: HashMap<String, Any> = EnchantSplitterGuiMenu.guistate
        private val texture = Identifier(ZwyM.MODID,"textures/screens/enchant_splitter_gui.png")
    }
}
