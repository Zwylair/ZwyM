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

class ExampleBlockEntity(
    position: BlockPos?,
    state: BlockState?
) : LootableContainerBlockEntity(
    ModBlockEntities.EXAMPLE_BLOCK,
    position,
    state
), ExtendedScreenHandlerFactory, SidedInventory {
    private var stacks: DefaultedList<ItemStack> = DefaultedList.ofSize(3, ItemStack.EMPTY)
    private val maxStackSize = 1
    private val restrictedToPutSlotsIndexes = listOf(1, 2)

    public override fun getContainerName(): Text { return Text.translatable("example_block") }

    override fun toInitialChunkDataNbt(): NbtCompound { return createNbt() }
    override fun readNbt(compound: NbtCompound) {
        super.readNbt(compound)
        if (!readLootTable(compound)) stacks = DefaultedList.ofSize(size(), ItemStack.EMPTY)
        Inventories.readNbt(compound, stacks)
    }

    public override fun writeNbt(compound: NbtCompound) {
        super.writeNbt(compound)
        if (!this.writeLootTable(compound)) Inventories.writeNbt(compound, stacks)
    }

    override fun canInsert(index: Int, stack: ItemStack, direction: Direction?): Boolean { return false }
    override fun canExtract(index: Int, stack: ItemStack, direction: Direction): Boolean { return false }

    override fun getMaxCountPerStack(): Int { return maxStackSize }
    override fun getAvailableSlots(side: Direction): IntArray { return IntStream.range(0, size()).toArray() }
    override fun method_11282(): DefaultedList<ItemStack> { return stacks }
    override fun setInvStackList(gotStacks: DefaultedList<ItemStack>) { stacks = gotStacks }
    override fun size(): Int { return stacks.size }
    override fun isValid(index: Int, stack: ItemStack): Boolean { return !restrictedToPutSlotsIndexes.contains(index) }
    override fun isEmpty(): Boolean {
        for (itemstack in stacks) if (!itemstack.isEmpty) return false
        return true
    }

    override fun toUpdatePacket(): BlockEntityUpdateS2CPacket? { return BlockEntityUpdateS2CPacket.create(this) }
    override fun writeScreenOpeningData(player: ServerPlayerEntity, buf: PacketByteBuf) { buf.writeBlockPos(pos) }
    public override fun createScreenHandler(id: Int, inventory: PlayerInventory): ScreenHandler {
        return EnchantSplitterGuiMenu(id, inventory, this)
    }
}
