package safro.fabric.enchantments.mixin.enchantment;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import safro.fabric.enchantments.FabricEnchantments;
import safro.fabric.enchantments.config.FabricEnchantmentsConfig;
import safro.fabric.enchantments.util.FEUtil;

@Mixin(LivingEntity.class)
public class SugarRushMixin {

    @Inject(at = @At("HEAD"), method = "onDeath", cancellable = true)
    public void sugarRushKill(DamageSource source, CallbackInfo callbackInfo) {
        if(!(source.getAttacker() instanceof PlayerEntity)) return;
        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        if (user != null && FEUtil.hasEnchantment(user, FabricEnchantments.SUGAR_RUSH)) {
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, FabricEnchantmentsConfig.getIntValue("sugar_rush_duration"), 0, true, false));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, FabricEnchantmentsConfig.getIntValue("sugar_rush_duration"), 1, true, false));
        }
    }
}
