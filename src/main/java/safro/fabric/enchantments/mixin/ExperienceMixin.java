package safro.fabric.enchantments.mixin;


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
public class ExperienceMixin {
    @Shadow
    @Nullable
    protected PlayerEntity attackingPlayer;

    @Inject(at = @At("HEAD"), method = "onDeath", cancellable = true)

    private void ExperienceKill(DamageSource source, CallbackInfo ci) {
        if(!(source.getAttacker() instanceof PlayerEntity)) return;
        LivingEntity user = (LivingEntity) source.getAttacker();
        ItemStack mainHandStack = null;
        if (user != null) {
            mainHandStack = user.getMainHandStack();
        }

            if (mainHandStack != null && (EnchantmentHelper.getLevel(FabricEnchantments.EXPERIENCE, mainHandStack) >= 1)) {
                int level = EnchantmentHelper.getLevel(FabricEnchantments.EXPERIENCE, mainHandStack);
                    if (attackingPlayer != null) {
                        attackingPlayer.addExperience(level * 4);
                    }
            }
        }
    }
