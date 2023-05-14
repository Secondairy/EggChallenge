package me.secondairy.eggchallenge.mixin.net.minecraft.server;

import me.secondairy.eggchallenge.eggchallenge;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.WorldGenerationProgressListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {

    @Inject(method = "isHardcore", at = @At(value = "TAIL", target = "Lnet/minecraft/server/MinecraftServer;isHardcore()Z"), cancellable = true)
    public void isHardcore(CallbackInfoReturnable<Boolean> cir) {

        if (eggchallenge.hasFailed)  {
            cir.setReturnValue(true);
        }

        cir.cancel();
    }
    @Inject(method = "createWorlds", at = @At(value="TAIL", target = "Lnet/minecraft/server/MinecraftServer;createWorlds(Lnet/minecraft/server/WorldGenerationProgressListener;)V"))
    public void createWorlds(WorldGenerationProgressListener worldGenerationProgressListener, CallbackInfo ci) {
        eggchallenge.hasFailed = false;
    }
}
