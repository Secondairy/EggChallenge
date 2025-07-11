package me.secondairy.eggchallenge.mixin.net.minecraft.client.gui.screen.ingame;

import me.secondairy.eggchallenge.eggchallenge;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.ScreenHandlerProvider;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.text.Text;
import org.apache.logging.log4j.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(HandledScreen.class)
public abstract class HandledScreenMixin<T extends ScreenHandler> extends Screen implements ScreenHandlerProvider<T> {
    @Shadow
    protected Slot focusedSlot;

    protected HandledScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "handleHotbarKeyPressed", at = @At( value = "HEAD",  target = "Lnet/minecraft/client/gui/screen/ingame/HandledScreen;handleHotbarKeyPressed(II)Z"), cancellable = true)
    protected void handleHotbarKeyPressed(int keyCode, int scanCode, CallbackInfoReturnable<Boolean> cir){
        if (this.client.player.inventory.getCursorStack().isEmpty() && this.focusedSlot != null) {
            if (this.client.options.keySwapHands.matchesKey(keyCode, scanCode)) {
                cir.setReturnValue(false);
                cir.cancel();
            }
        }
    }
}
