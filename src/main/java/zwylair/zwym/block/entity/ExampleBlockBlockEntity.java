package zwylair.zwym.block.entity;

import org.jetbrains.annotations.Nullable;
import zwylair.zwym.world.inventory.EnchantSplitterGuiMenu;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import zwylair.zwym.init.UgyfModBlockEntities;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;

import java.util.List;
import java.util.stream.IntStream;

public class ExampleBlockBlockEntity extends LootableContainerBlockEntity implements ExtendedScreenHandlerFactory, SidedInventory {
	private DefaultedList<ItemStack> stacks = DefaultedList.<ItemStack>ofSize(3, ItemStack.EMPTY);

	public ExampleBlockBlockEntity(BlockPos position, BlockState state) {
		super(UgyfModBlockEntities.EXAMPLE_BLOCK, position, state);
	}

	@Override
	public void readNbt(NbtCompound compound) {
		super.readNbt(compound);
		if (!this.readLootTable(compound))
			this.stacks = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);
		Inventories.readNbt(compound, this.stacks);
	}

	@Override
	public void writeNbt(NbtCompound compound) {
		super.writeNbt(compound);
		if (!this.writeLootTable(compound))
			Inventories.writeNbt(compound, this.stacks);
	}

	@Override
	public BlockEntityUpdateS2CPacket toUpdatePacket() {
		return BlockEntityUpdateS2CPacket.create(this);
	}

	@Override
	public NbtCompound toInitialChunkDataNbt() {
		return this.createNbt();
	}

	@Override
	public int size() {
		return stacks.size();
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack itemstack : this.stacks)
			if (!itemstack.isEmpty())
				return false;
		return true;
	}

	@Override
	public Text getContainerName() {
		return Text.literal("example_block");
	}

	@Override
	public int getMaxCountPerStack() {
		return 1;
	}

	@Override
	public ScreenHandler createScreenHandler(int id, PlayerInventory inventory) {
		return new EnchantSplitterGuiMenu(id, inventory, this);
	}

	@Override
	public Text getDisplayName() {
		return Text.literal("Example Block");
	}

	@Override
	protected DefaultedList<ItemStack> method_11282() {
		return this.stacks;
	}

	@Override
	protected void setInvStackList(DefaultedList<ItemStack> stacks) {
		this.stacks = stacks;
	}

	@Override
	public boolean isValid(int index, ItemStack stack) {
		return !List.of(1, 2).contains(index);
	}

	@Override
	public int[] getAvailableSlots(Direction side) {
		return IntStream.range(0, this.size()).toArray();
	}

	@Override
	public boolean canInsert(int index, ItemStack stack, @Nullable Direction direction) {
		return this.isValid(index, stack);
	}

	@Override
	public boolean canExtract(int index, ItemStack stack, Direction direction) {
		return true;
	}

	@Override
	public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
		buf.writeBlockPos(pos);
	}
}
