
package zwylair.zwym.world.inventory;

import zwylair.zwym.init.UgyfModMenus;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import java.util.HashMap;

public class EnchantSplitterGuiMenu extends ScreenHandler {
	public final static HashMap<String, Object> guistate = new HashMap<>();
	public final World world;
	public final PlayerEntity entity;
	public int x, y, z;
	private BlockPos pos;
	private final Inventory inventory;
	private boolean bound = false;

	public EnchantSplitterGuiMenu(int id, PlayerInventory inv, PacketByteBuf extraData) {
		this(id, inv, new SimpleInventory(3));
		if (extraData != null) {
			pos = extraData.readBlockPos();
			this.x = pos.getX();
			this.y = pos.getY();
			this.z = pos.getZ();
		}
	}

	public EnchantSplitterGuiMenu(int id, PlayerInventory inv, Inventory container) {
		super(UgyfModMenus.ENCHANT_SPLITTER_GUI, id);
		this.entity = inv.player;
		this.world = inv.player.getWorld();
		this.inventory = container;
		this.addSlot(new Slot(inventory, 0, 12, 16) {
			private final int slot = 0;
		});
		this.addSlot(new Slot(inventory, 1, 13, 47) {
			private final int slot = 1;

			@Override
			public boolean canInsert(ItemStack stack) {
				return false;
			}
		});
		this.addSlot(new Slot(inventory, 2, 44, 47) {
			private final int slot = 2;

			@Override
			public boolean canInsert(ItemStack stack) {
				return false;
			}
		});
		for (int si = 0; si < 3; ++si)
			for (int sj = 0; sj < 9; ++sj)
				this.addSlot(new Slot(inv, sj + (si + 1) * 9, 0 + 8 + sj * 18, 0 + 84 + si * 18));
		for (int si = 0; si < 9; ++si)
			this.addSlot(new Slot(inv, si, 0 + 8 + si * 18, 0 + 142));
	}

	@Override
	public boolean canUse(PlayerEntity player) {
		return this.inventory.canPlayerUse(player);
	}

	@Override
	public ItemStack quickMove(PlayerEntity player, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = (Slot) this.slots.get(index);
		if (slot != null && slot.hasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if (index < 3) {
				if (!this.insertItem(itemstack1, 3, this.slots.size(), true))
					return ItemStack.EMPTY;
				slot.onQuickTransfer(itemstack1, itemstack);
			} else if (!this.insertItem(itemstack1, 0, 3, false)) {
				if (index < 3 + 27) {
					if (!this.insertItem(itemstack1, 3 + 27, this.slots.size(), true))
						return ItemStack.EMPTY;
				} else {
					if (!this.insertItem(itemstack1, 3, 3 + 27, false))
						return ItemStack.EMPTY;
				}
				return ItemStack.EMPTY;
			}
			if (itemstack1.isEmpty())
				slot.setStackNoCallbacks(ItemStack.EMPTY);
			else
				slot.markDirty();
			if (itemstack1.getCount() == itemstack.getCount())
				return ItemStack.EMPTY;
			slot.onTakeItem(player, itemstack1);
		}
		return itemstack;
	}

	@Override
	public void onClosed(PlayerEntity playerIn) {
		super.onClosed(playerIn);
	}

	public static void screenInit() {
	}
}
