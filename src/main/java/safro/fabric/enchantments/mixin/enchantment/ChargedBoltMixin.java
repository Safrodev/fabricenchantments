package safro.fabric.enchantments.mixin.enchantment;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import safro.fabric.enchantments.FabricEnchantments;

@Mixin(PersistentProjectileEntity.class)
public abstract class ChargedBoltMixin {

    @Shadow protected abstract void initDataTracker();

    @Inject(method = "onEntityHit", at = @At("TAIL"))
    private void chargedBoltHit(EntityHitResult entityHitResult, CallbackInfo ci) {
        PersistentProjectileEntity entity = (PersistentProjectileEntity) (Object) this;
        LivingEntity attacker = (LivingEntity) entity.getOwner();
        if (entityHitResult.getEntity() instanceof LivingEntity target && attacker != null) {
            if (EnchantmentHelper.getLevel(FabricEnchantments.CHARGED_BOLT, attacker.getMainHandStack()) >= 1) {
                if (attacker.getRandom().nextFloat() <= 0.3F) {
                    BlockPos blockPos = target.getBlockPos();
                    LightningEntity lightningEntity = (LightningEntity) EntityType.LIGHTNING_BOLT.create(attacker.getEntityWorld());
                    lightningEntity.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(blockPos));
                    attacker.getEntityWorld().spawnEntity(lightningEntity);
                }
            }
        }
    }
}
