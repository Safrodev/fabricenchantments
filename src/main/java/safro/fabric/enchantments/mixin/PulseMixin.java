package safro.fabric.enchantments.mixin;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import safro.fabric.enchantments.FabricEnchantments;
import safro.fabric.enchantments.config.FabricEnchantmentsConfig;

import java.util.List;

@Mixin(CrossbowItem.class)
public abstract class PulseMixin {

    @Shadow private static void postShoot(World world, LivingEntity entity, ItemStack stack) {
        throw new IllegalStateException("Failed to shadow method in CrossbowItem class");
    }

    @Inject(method = "shootAll", at = @At("HEAD"), cancellable = true)
    private static void pulseEnchantShoot(World world, LivingEntity entity, Hand hand, ItemStack stack, float speed, float divergence, CallbackInfo ci) {
        if (EnchantmentHelper.getLevel(FabricEnchantments.PULSE, stack) > 0) {
            if (!world.isClient && !stack.isEmpty()) {
                List<LivingEntity> targets = world.getNonSpectatingEntities(LivingEntity.class, entity.getBoundingBox().expand(20));
                targets.stream().filter(e -> isValidTarget(e, entity)).filter(e -> entity.isInRange(e, 15, 20)).findFirst().ifPresent(target -> {
                    Vec3d vec3d = entity.getPos().add(0.0D, 1.600000023841858D, 0.0D);
                    Vec3d vec3d2 = target.getEyePos().subtract(vec3d);
                    Vec3d vec3d3 = vec3d2.normalize();

                    for(int i = 1; i < MathHelper.floor(vec3d2.length()) + 7; ++i) {
                        Vec3d vec3d4 = vec3d.add(vec3d3.multiply(i));
                        if (world instanceof ServerWorld sw) sw.spawnParticles(ParticleTypes.SONIC_BOOM, vec3d4.x, vec3d4.y, vec3d4.z, 1, 0.0D, 0.0D, 0.0D, 0.0D);
                    }

                    entity.playSound(SoundEvents.ENTITY_WARDEN_SONIC_BOOM, 3.0F, 1.0F);
                    target.damage(world.getDamageSources().sonicBoom(entity), (float)FabricEnchantmentsConfig.getIntValue("pulse_damage"));
                    double d = 0.5D * (1.0D - target.getAttributeValue(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE));
                    double e = 2.5D * (1.0D - target.getAttributeValue(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE));
                    target.addVelocity(vec3d3.getX() * e, vec3d3.getY() * d, vec3d3.getZ() * e);
                });
                stack.damage(3, entity, e -> e.sendToolBreakStatus(hand));
            }

            postShoot(world, entity, stack);
            ci.cancel();
        }
    }

    @Inject(method = "getPullTime", at = @At("RETURN"), cancellable = true)
    private static void pulseEnchantModifier(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        if (EnchantmentHelper.getLevel(FabricEnchantments.PULSE, stack) > 0) {
            int added = cir.getReturnValue() + 10;
            cir.setReturnValue(added);
        }
    }

    @Unique
    private static boolean isValidTarget(@Nullable Entity entity, LivingEntity user) {
        if (entity instanceof LivingEntity livingEntity && livingEntity != user) {
            if (user.getWorld() == entity.getWorld() && !livingEntity.isInvulnerable() && !livingEntity.isDead() && user.getWorld().getWorldBorder().contains(livingEntity.getBoundingBox())) {
                if (EntityPredicates.EXCEPT_CREATIVE_OR_SPECTATOR.test(entity) && !user.isTeammate(entity) && livingEntity.getType() != EntityType.ARMOR_STAND && livingEntity.getType() != EntityType.WARDEN) {
                    return true;
                }
            }
        }
        return false;
    }
}
