package com.thethirdswan.htfc_subsidiaries.mixin;

import com.lumintorious.tfcambiental.modifier.TempModifierStorage;
import com.thethirdswan.htfc_subsidiaries.pnc.handlers.TemperatureHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TempModifierStorage.class)
public class MixinTempModStorage {
    @Inject(method = "getTargetTemperature", at = @At("RETURN"), cancellable = true, remap = false)
    private void onGetTargetTemperature(CallbackInfoReturnable<Float> cir) {
        if (TemperatureHandler.upgradeToggle) {
            cir.setReturnValue(TemperatureHandler.targetTemp);
        }
    }
}
