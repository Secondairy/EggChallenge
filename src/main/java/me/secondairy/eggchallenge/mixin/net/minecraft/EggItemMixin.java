package me.secondairy.eggchallenge.mixin.net.minecraft;

import me.secondairy.eggchallenge.eggchallenge;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.EggItem;
import net.minecraft.item.ItemStack;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import me.secondairy.eggchallenge.DamageSource.CustomDamageSources;

@Mixin(EggItem.class)
public class EggItemMixin {
    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    public void use(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir){

        if (hand == Hand.OFF_HAND) {

            IntegratedServer srv = MinecraftClient.getInstance().getServer();
            ServerPlayerEntity player = srv.getPlayerManager().getPlayer(user.getUuid());

            final Float currHealth = player.getHealth();
            player.damage(CustomDamageSources.Egg,Float.MAX_VALUE);
            Float aftHealth = player.getHealth();

            if (currHealth.equals(aftHealth)) {
                TypedActionResult<ItemStack> fail = TypedActionResult.fail(player.getOffHandStack());
                cir.setReturnValue(fail);
                cir.cancel();
            } else {
                eggchallenge.EggChallengeFailed = true;
            }
        }
    }
}
