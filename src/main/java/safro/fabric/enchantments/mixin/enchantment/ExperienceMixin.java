package safro.fabric.enchantments.mixin.enchantment;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import safro.fabric.enchantments.FabricEnchantments;
import safro.fabric.enchantments.config.FabricEnchantmentsConfig;
import safro.fabric.enchantments.util.FEUtil;

@Mixin(LivingEntity.class)
public class ExperienceMixin {

    @Inject(at = @At("HEAD"), method = "onDeath", cancellable = true)
    private void experienceKill(DamageSource source, CallbackInfo ci) {
        if (source.getAttacker() instanceof PlayerEntity player) {
            if (FEUtil.hasEnchantment(player, FabricEnchantments.EXPERIENCE)) {
                int level = FEUtil.getLevel(player, FabricEnchantments.EXPERIENCE);
                player.addExperience(level * FabricEnchantmentsConfig.getIntValue("experience_base_amount"));
            }
        }
    }
}
