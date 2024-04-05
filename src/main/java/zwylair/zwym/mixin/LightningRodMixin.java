package zwylair.zwym.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import zwylair.zwym.customevents.LightningRodCallback;

@Mixin(targets = "net.minecraft.block.LightningRodBlock")
public class LightningRodMixin {
    @Inject(method = "setPowered()V", at = @At(value = "HEAD"))

    public void setPowered(BlockState state, World world, BlockPos pos, CallbackInfo ci) {
        MinecraftClient.getInstance().execute(() -> {
            ActionResult result = LightningRodCallback.EVENT.invoker().setPowered(state, world, pos);
            if(result == ActionResult.FAIL) { ci.cancel(); }
        });
    }
}
