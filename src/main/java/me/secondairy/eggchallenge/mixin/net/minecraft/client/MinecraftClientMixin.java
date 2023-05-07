package me.secondairy.eggchallenge.mixin.net.minecraft.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.options.GameOptions;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Final
    @Shadow
    public GameOptions options;

    @Inject(method = "handleInputEvents", at = @At(value = "HEAD", target = "Lnet/minecraft/client/MinecraftClient;handleInputEvents()V"), cancellable = true)
    public void handleInputEvents(CallbackInfo ci){
        while(this.options.keySwapHands.wasPressed()) {
                ci.cancel();
        }
    }
}
