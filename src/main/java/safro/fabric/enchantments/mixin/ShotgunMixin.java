package safro.fabric.enchantments.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import safro.fabric.enchantments.FabricEnchantments;
import safro.fabric.enchantments.config.FabricEnchantmentsConfig;
import safro.fabric.enchantments.util.FEUtil;

@Mixin(PersistentProjectileEntity.class)
public abstract class ShotgunMixin extends Entity {

    @Shadow private double damage;

    public ShotgunMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "onEntityHit", at = @At("HEAD"))
    private void shotgunHit(EntityHitResult entityHitResult, CallbackInfo ci) {
        PersistentProjectileEntity projectile = (PersistentProjectileEntity) (Object) this;
        if (entityHitResult.getEntity() instanceof LivingEntity target && projectile.getOwner() instanceof LivingEntity attacker) {
            if (FEUtil.hasEnchantment(attacker, FabricEnchantments.SHOTGUN)) {
                int level = FEUtil.getLevel(attacker, FabricEnchantments.SHOTGUN);
                float distance = attacker.distanceTo(target);
                if (distance <= 5.0F) {
                    float base = FabricEnchantmentsConfig.getIntValue("shotgun_base_damage");
                    base += distance <= 1.0F ? level * 1.5F : distance <= 3.0F ? level * 1.25F : level;
                    this.damage += base;
                    FabricEnchantments.LOGGER.info("DISTANCE LESS");
                }
            }
        }
    }
}
