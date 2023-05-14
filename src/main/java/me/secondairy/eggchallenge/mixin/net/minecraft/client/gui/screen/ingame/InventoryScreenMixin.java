package me.secondairy.eggchallenge.mixin.net.minecraft.client.gui.screen.ingame;

import me.secondairy.eggchallenge.eggchallenge;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import org.apache.logging.log4j.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InventoryScreen.class)
public class InventoryScreenMixin {

    @Inject(method = "onMouseClick", at = @At( value = "HEAD",  target = "Lnet/minecraft/client/gui/screen/ingame/InventoryScreen;onMouseClick(Lnet/minecraft/screen/slot/Slot;IILnet/minecraft/screen/slot/SlotActionType;)V"), cancellable = true)
    protected void onMouseClick(Slot slot, int invSlot, int clickData, SlotActionType actionType, CallbackInfo ci) {
        if (slot != null) {
            if (invSlot == 45) {
                ci.cancel();
            }
        }
    }
}
