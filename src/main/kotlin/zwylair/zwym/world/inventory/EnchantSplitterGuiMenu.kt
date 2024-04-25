package zwylair.zwym.world.inventory

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventory
import net.minecraft.inventory.SimpleInventory
import net.minecraft.item.ItemStack
import net.minecraft.network.PacketByteBuf
import net.minecraft.screen.ScreenHandler
import net.minecraft.screen.slot.Slot
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import zwylair.zwym.init.ZwyMModMenus

class EnchantSplitterGuiMenu(id: Int, inv: PlayerInventory, private val inventory: Inventory) :
    ScreenHandler(ZwyMModMenus.ENCHANT_SPLITTER_GUI, id) {
    val world: World = inv.player.world

    val entity: PlayerEntity = inv.player
    var x: Int = 0
    var y: Int = 0
    var z: Int = 0
    private var pos: BlockPos? = null
    private val bound = false

    constructor(id: Int, inv: PlayerInventory, extraData: PacketByteBuf?) : this(id, inv, SimpleInventory(3)) {
        if (extraData != null) {
            val pos = extraData.readBlockPos()
            this.x = pos.x
            this.y = pos.y
            this.z = pos.z
        }
    }

    init {
        this.addSlot(object : Slot(inventory, 0, 12, 16) {
            private val slot = 0
        })
        this.addSlot(object : Slot(inventory, 1, 13, 47) {
            private val slot = 1

            override fun canInsert(stack: ItemStack): Boolean {
                return false
            }
        })
        this.addSlot(object : Slot(inventory, 2, 44, 47) {
            private val slot = 2

            override fun canInsert(stack: ItemStack): Boolean {
                return false
            }
        })
        for (si in 0..2) for (sj in 0..8) this.addSlot(
            Slot(
                inv, sj + (si + 1) * 9,
                0 + 8 + sj * 18, 0 + 84 + si * 18
            )
        )
        for (si in 0..8) this.addSlot(Slot(inv, si, 0 + 8 + si * 18, 0 + 142))
    }

    override fun canUse(player: PlayerEntity): Boolean {
        return inventory.canPlayerUse(player)
    }

    override fun quickMove(player: PlayerEntity, index: Int): ItemStack {
        var itemstack = ItemStack.EMPTY
        val slot = slots[index]
        if (slot != null && slot.hasStack()) {
            val itemstack1 = slot.stack
            itemstack = itemstack1.copy()
            if (index < 3) {
                if (!this.insertItem(itemstack1, 3, slots.size, true)) return ItemStack.EMPTY
                slot.onQuickTransfer(itemstack1, itemstack)
            } else if (!this.insertItem(itemstack1, 0, 3, false)) {
                if (index < 3 + 27) {
                    if (!this.insertItem(itemstack1, 3 + 27, slots.size, true)) return ItemStack.EMPTY
                } else {
                    if (!this.insertItem(itemstack1, 3, 3 + 27, false)) return ItemStack.EMPTY
                }
                return ItemStack.EMPTY
            }
            if (itemstack1.isEmpty) slot.setStackNoCallbacks(ItemStack.EMPTY)
            else slot.markDirty()
            if (itemstack1.count == itemstack.count) return ItemStack.EMPTY
            slot.onTakeItem(player, itemstack1)
        }
        return itemstack
    }

    override fun onClosed(playerIn: PlayerEntity) {
        super.onClosed(playerIn)
    }

    companion object {
        val guistate: HashMap<String, Any> = HashMap()
        fun screenInit() {
        }
    }
}
