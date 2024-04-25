package zwylair.zwym.world.inventory

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventory
import net.minecraft.inventory.SimpleInventory
import net.minecraft.item.ItemStack
import net.minecraft.network.PacketByteBuf
import net.minecraft.screen.ScreenHandler
import net.minecraft.screen.slot.Slot
import net.minecraft.world.World
import zwylair.zwym.client.gui.ModScreens

class EnchantSplitterGuiMenu(
    id: Int,
    inv: PlayerInventory,
    private val inventory: Inventory
) : ScreenHandler(
    ModScreens.ENCHANT_SPLITTER_GUI, id
) {
    private val maxSlots = 3
    private val maxPlayerInvSlots = 27
    val world: World = inv.player.world
    val entity: PlayerEntity = inv.player
    var x = 0
    var y = 0

    constructor(id: Int, inv: PlayerInventory, extraData: PacketByteBuf?) : this(id, inv, SimpleInventory(3)) {
        if (extraData == null) return

        val pos = extraData.readBlockPos()
        x = pos.x
        y = pos.y
    }

    init {
        addSlot(object : Slot(inventory, 0, 12, 16) {} )
        addSlot(object : Slot(inventory, 1, 13, 47) {
            override fun canInsert(stack: ItemStack): Boolean { return false }
        })
        addSlot(object : Slot(inventory, 2, 44, 47) {
            override fun canInsert(stack: ItemStack): Boolean { return false }
        })

        val numRows = 3
        val numCols = 9
        val slotWidth = 18
        val slotHeight = 18

        for (rowIndex in 0 until numRows) {
            for (colIndex in 0 until numCols) {
                val slotIndex = colIndex + (rowIndex + 1) * numCols

                val slotX = 8 + colIndex * slotWidth
                val slotY = 84 + rowIndex * slotHeight

                val slot = Slot(inv, slotIndex, slotX, slotY)
                addSlot(slot)
            }
        }

        for (si in 0..8) addSlot(Slot(inv, si,  8 + si * 18, 0 + 142))
    }

    override fun canUse(player: PlayerEntity): Boolean { return inventory.canPlayerUse(player) }

    override fun quickMove(player: PlayerEntity, index: Int): ItemStack {
        val slot = slots[index]

        val itemStackInSlot = slot.stack
        val itemStackCopy = itemStackInSlot.copy()
        if (itemStackInSlot.isEmpty) return ItemStack.EMPTY

        // moving from slot to player's inventory
        if (index < maxSlots) {
            if (!insertItem(itemStackInSlot, maxSlots, slots.size, true)) return ItemStack.EMPTY
            slot.onQuickTransfer(itemStackInSlot, itemStackCopy)
        } else {
            // moving from player's inventory to slot
            val destinationStart = if (index < maxSlots + maxPlayerInvSlots) maxSlots + maxPlayerInvSlots else maxSlots
            val destinationEnd = if (index < maxSlots + maxPlayerInvSlots) slots.size else maxSlots + maxPlayerInvSlots

            if (!insertItem(itemStackInSlot, destinationStart, destinationEnd, false)) {
                // if failed, try moving from slot to the rest of the inventory
                if (!insertItem(itemStackInSlot, maxSlots, destinationStart, true)) return ItemStack.EMPTY
            }
        }

        if (itemStackInSlot.isEmpty) {
            slot.setStackNoCallbacks(ItemStack.EMPTY)
        } else {
            slot.markDirty()
        }

        if (itemStackInSlot.count == itemStackCopy.count) return ItemStack.EMPTY

        slot.onTakeItem(player, itemStackInSlot)
        return itemStackCopy
    }

    companion object {
        val guiState: HashMap<String, Any> = HashMap()
        fun screenInit() { }
    }
}
