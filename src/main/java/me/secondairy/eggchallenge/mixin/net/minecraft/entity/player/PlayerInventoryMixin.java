package me.secondairy.eggchallenge.mixin.net.minecraft.entity.player;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Nameable;
import net.minecraft.util.collection.DefaultedList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerInventory.class)
public abstract class PlayerInventoryMixin implements Inventory, Nameable {

    @Shadow
    public DefaultedList<ItemStack> offHand;

    @Inject(method = "<init>", at = @At(value = "TAIL", target = "Lnet/minecraft/entity/player/PlayerInventory;<init>(Lnet/minecraft/entity/player/PlayerEntity;)V"))
    private void offHand(PlayerEntity player, CallbackInfo ci)
    {
        ItemStack egg = new ItemStack(Items.EGG);

        this.offHand = DefaultedList.ofSize(1, egg);
    }

}
