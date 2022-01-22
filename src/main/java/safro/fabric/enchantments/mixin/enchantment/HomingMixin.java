package safro.fabric.enchantments.mixin.enchantment;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.util.math.Box;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import safro.fabric.enchantments.FabricEnchantments;
import safro.fabric.enchantments.config.FabricEnchantmentsConfig;

@Mixin(PersistentProjectileEntity.class)
public class HomingMixin {

    @Inject(method = "tick", at = @At("TAIL"))
    private void tickHoming(CallbackInfo ci) {
        PersistentProjectileEntity projectile = (PersistentProjectileEntity) (Object) this;
        if (projectile.getOwner() instanceof LivingEntity owner && EnchantmentHelper.getLevel(FabricEnchantments.HOMING, owner.getMainHandStack()) >= 1) {
            if (projectile.isAlive() && !projectile.isOnGround()) {
                Box box = projectile.getBoundingBox().expand(FabricEnchantmentsConfig.getIntValue("homing_detection_range"));
                LivingEntity target = projectile.world.getClosestEntity(LivingEntity.class, TargetPredicate.DEFAULT, null, projectile.getX(), projectile.getY(), projectile.getZ(), box);
                if (target != null && target.isAlive() && !target.isSpectator() && !(target == owner)) {
                    if (projectile.age >= 5) {
                        double x = target.getX() - projectile.getX();
                        double y = target.getEyeY() - projectile.getY();
                        double z = target.getZ() - projectile.getZ();
                        projectile.setVelocity(x, y, z, (float) projectile.getVelocity().length(), 0);
                    }
                }
            }
        }
    }
}
