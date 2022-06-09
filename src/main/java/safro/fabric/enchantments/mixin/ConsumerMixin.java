package safro.fabric.enchantments.mixin;


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
public class ConsumerMixin {

    @Inject(at = @At("HEAD"), method = "onDeath", cancellable = true)
    public void consumerKill(DamageSource source, CallbackInfo callbackInfo) {
        if(!(source.getAttacker() instanceof PlayerEntity)) return;
        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        if (user != null && FEUtil.hasEnchantment(user, FabricEnchantments.CONSUMER)) {
            int level = FEUtil.getLevel(user, FabricEnchantments.CONSUMER);
            int chance = FabricEnchantmentsConfig.getIntValue("consumer_base_chance") * level;
            if (user.getRandom().nextInt(100) <= chance) {
                if (target instanceof LivingEntity){
                    ((LivingEntity) source.getAttacker()).addStatusEffect(new StatusEffectInstance(StatusEffects.SATURATION, 10, 0, true, false));
                }
            }
        }
    }
}
