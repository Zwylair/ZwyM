package zwylair.zwym.blocks.entities

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory
import net.minecraft.block.BlockState
import net.minecraft.block.entity.LootableContainerBlockEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventories
import net.minecraft.inventory.SidedInventory
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.network.PacketByteBuf
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket
import net.minecraft.screen.ScreenHandler
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.text.Text
import net.minecraft.util.collection.DefaultedList
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import zwylair.zwym.world.inventory.EnchantSplitterGuiMenu
import java.util.stream.IntStream

class ExampleBlockEntity(position: BlockPos?, state: BlockState?) :
    LootableContainerBlockEntity(ModBlockEntities.EXAMPLE_BLOCK, position, state), ExtendedScreenHandlerFactory,
    SidedInventory {
    private var stacks: DefaultedList<ItemStack> = DefaultedList.ofSize(3, ItemStack.EMPTY)

    override fun readNbt(compound: NbtCompound) {
        super.readNbt(compound)
        if (!this.readLootTable(compound)) this.stacks = DefaultedList.ofSize(this.size(), ItemStack.EMPTY)
        Inventories.readNbt(compound, this.stacks)
    }

    public override fun writeNbt(compound: NbtCompound) {
        super.writeNbt(compound)
        if (!this.writeLootTable(compound)) Inventories.writeNbt(compound, this.stacks)
    }

    override fun toUpdatePacket(): BlockEntityUpdateS2CPacket? {
        return BlockEntityUpdateS2CPacket.create(this)
    }

    override fun toInitialChunkDataNbt(): NbtCompound { return this.createNbt() }
    override fun size(): Int { return stacks.size }

    override fun isEmpty(): Boolean {
        for (itemstack in this.stacks) if (!itemstack.isEmpty) return false
        return true
    }

    public override fun getContainerName(): Text {
        return Text.literal("example_block")
    }

    override fun getMaxCountPerStack(): Int {
        return 1
    }

    public override fun createScreenHandler(id: Int, inventory: PlayerInventory): ScreenHandler {
        return EnchantSplitterGuiMenu(id, inventory, this)
    }

    override fun getDisplayName(): Text { return Text.literal("Example Block") }
    override fun method_11282(): DefaultedList<ItemStack> { return this.stacks }
    override fun setInvStackList(stacks: DefaultedList<ItemStack>) { this.stacks = stacks }

    override fun isValid(index: Int, stack: ItemStack): Boolean {
        return !listOf(1, 2).contains(index)
    }

    override fun getAvailableSlots(side: Direction): IntArray {
        return IntStream.range(0, this.size()).toArray()
    }

    override fun canInsert(index: Int, stack: ItemStack, direction: Direction?): Boolean { return this.isValid(index, stack) }
    override fun canExtract(index: Int, stack: ItemStack, direction: Direction): Boolean { return true }

    override fun writeScreenOpeningData(player: ServerPlayerEntity, buf: PacketByteBuf) {
        buf.writeBlockPos(pos)
    }
}
