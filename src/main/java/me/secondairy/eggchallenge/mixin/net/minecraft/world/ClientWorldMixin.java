package me.secondairy.eggchallenge.mixin.net.minecraft.world;

import me.secondairy.eggchallenge.eggchallenge;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientWorld.class)
public class ClientWorldMixin {
    @Shadow @Final private ClientWorld.Properties clientWorldProperties;

    @Inject(method = "getLevelProperties*", at = @At(value = "HEAD", target = "Lnet/minecraft/client/world/ClientWorld;getLevelProperties()V"), cancellable = true)
    public void getLevelProperties(CallbackInfoReturnable<ClientWorld.Properties> cir){

        if (eggchallenge.hasFailed)  {
            ClientWorld.Properties CWProp = this.clientWorldProperties;
            ClientWorld.Properties newCWProp = new ClientWorld.Properties(CWProp.getDifficulty(),true,false);
            cir.setReturnValue(newCWProp);
            cir.cancel();
        }
    }

}
