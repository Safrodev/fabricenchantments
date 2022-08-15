package safro.fabric.enchantments.mixin;

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
import safro.fabric.enchantments.util.FEUtil;

@Mixin(PersistentProjectileEntity.class)
public class SoulSeekerMixin {

    @Inject(method = "tick", at = @At("TAIL"))
    private void tickHoming(CallbackInfo ci) {
        PersistentProjectileEntity projectile = (PersistentProjectileEntity) (Object) this;
        if (projectile.getOwner() instanceof LivingEntity owner && FEUtil.hasEnchantment(owner, FabricEnchantments.SOUL_SEEKER)) {
            if (projectile.isAlive() && !projectile.isOnGround()) {
                Box box = projectile.getBoundingBox().expand(FabricEnchantmentsConfig.getIntValue("soul_seeker_detection_range"));
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
