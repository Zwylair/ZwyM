package zwylair.zwym.customevents;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.block.BlockState;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface LightningRodCallback {
    Event<LightningRodCallback> EVENT = EventFactory.createArrayBacked(LightningRodCallback.class,
        (listeners) -> (BlockState state, World world, BlockPos pos) -> {
            for (LightningRodCallback listener : listeners) {
                ActionResult result = listener.setPowered(state, world, pos);
                if (result != ActionResult.PASS) { return result; }
            }

        return ActionResult.PASS;
    });

    ActionResult setPowered(BlockState state, World world, BlockPos pos);
}
