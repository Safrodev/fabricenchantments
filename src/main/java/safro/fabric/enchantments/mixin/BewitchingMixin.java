package safro.fabric.enchantments.mixin;

import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import safro.fabric.enchantments.util.BewitchingUtil;

@Mixin(PlayerEntity.class)
public class BewitchingMixin {
    @Unique
    private int cooldown = 0;

    @Inject(method = "tick", at = @At("TAIL"))
    private void tickBewitchingEnch(CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;

        if (cooldown > 0) {
            --cooldown;
        } else if (BewitchingUtil.hasBewitching(player)) {
            if (BewitchingUtil.tickBewitching(player)) {
                cooldown = 1200;
            }
        }
    }
}
