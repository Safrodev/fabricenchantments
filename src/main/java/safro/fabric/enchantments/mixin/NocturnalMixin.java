package safro.fabric.enchantments.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import safro.fabric.enchantments.FabricEnchantments;
import safro.fabric.enchantments.util.FEUtil;

@Mixin(LivingEntity.class)
public class NocturnalMixin {

    @Inject(method = "tick", at = @At("TAIL"))
    private void tickNoctournal(CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;
        if (entity.getWorld().isNight() && entity.getWorld().getTime() % 100 == 0 && FEUtil.getLevelArmor(entity, FabricEnchantments.NOCTURNAL) > 0) {
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 105, 0, true, false));
        }
    }
}
