package safro.fabric.enchantments.mixin;


import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import safro.fabric.enchantments.FabricEnchantments;

@Mixin(LivingEntity.class)
public class ConsumerMixin {

    @Shadow @Nullable protected PlayerEntity attackingPlayer;

    @Inject(at = @At("HEAD"), method = "onDeath", cancellable = true)

    public void ConsumerKill(DamageSource source, CallbackInfo callbackInfo) {
        if(!(source.getAttacker() instanceof PlayerEntity)) return;
        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;
        ItemStack mainHandStack = null;
        if (user != null) {
            mainHandStack = user.getMainHandStack();
        }

            if (mainHandStack != null && (EnchantmentHelper.getLevel(FabricEnchantments.CONSUMER, mainHandStack) >= 1 )) {
                int level = EnchantmentHelper.getLevel(FabricEnchantments.CONSUMER, mainHandStack);
                float EffectChance = 0.1F * level;
                float ConsumerRandom = user.getRandom().nextFloat();
                if (ConsumerRandom <= EffectChance) {
                    if (target instanceof LivingEntity){
                        ((LivingEntity) source.getAttacker()).addStatusEffect(new StatusEffectInstance(StatusEffects.SATURATION, 10, 0, true, false));
                    }
                }
            }
    }
}
