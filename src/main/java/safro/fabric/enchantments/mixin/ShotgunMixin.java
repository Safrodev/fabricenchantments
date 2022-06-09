package safro.fabric.enchantments.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import safro.fabric.enchantments.FabricEnchantments;
import safro.fabric.enchantments.config.FabricEnchantmentsConfig;
import safro.fabric.enchantments.util.FEUtil;

@Mixin(PersistentProjectileEntity.class)
public abstract class ShotgunMixin extends Entity {

    public ShotgunMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "onEntityHit", at = @At("TAIL"))
    private void shotgunHit(EntityHitResult entityHitResult, CallbackInfo ci) {
        PersistentProjectileEntity projectile = (PersistentProjectileEntity) (Object) this;
        if (entityHitResult.getEntity() instanceof LivingEntity target && projectile.getOwner() instanceof LivingEntity attacker) {
            if (FEUtil.hasEnchantment(attacker, FabricEnchantments.SHOTGUN)) {
                int level = FEUtil.getLevel(attacker, FabricEnchantments.SHOTGUN);
                float distance = attacker.distanceTo(target);
                int damage = FabricEnchantmentsConfig.getIntValue("shotgun_base_damage") + (level * 2);
                if (distance <= 5.0F) {
                    target.damage(DamageSource.mobProjectile(projectile, attacker), damage);
                } else if (distance <= 10.0F) {
                    int damage1 = FabricEnchantmentsConfig.getIntValue("shotgun_base_damage") + (level);
                    target.damage(DamageSource.mobProjectile(projectile, attacker), damage1);
                }
            }
        }
    }
}
