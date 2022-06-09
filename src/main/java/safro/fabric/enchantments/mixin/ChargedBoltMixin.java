package safro.fabric.enchantments.mixin;

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
import safro.fabric.enchantments.config.FabricEnchantmentsConfig;
import safro.fabric.enchantments.util.FEUtil;

@Mixin(PersistentProjectileEntity.class)
public abstract class ChargedBoltMixin {

    @Inject(method = "onEntityHit", at = @At("TAIL"))
    private void chargedBoltHit(EntityHitResult entityHitResult, CallbackInfo ci) {
        PersistentProjectileEntity entity = (PersistentProjectileEntity) (Object) this;
        LivingEntity attacker = (LivingEntity) entity.getOwner();

        if (entityHitResult.getEntity() instanceof LivingEntity target && attacker != null) {
            if (FEUtil.hasEnchantment(attacker, FabricEnchantments.CHARGED_BOLT)) {
                if (attacker.getRandom().nextInt(100) <= FabricEnchantmentsConfig.getIntValue("charged_bolt_chance")) {
                    BlockPos blockPos = target.getBlockPos();
                    LightningEntity lightningEntity = (LightningEntity) EntityType.LIGHTNING_BOLT.create(attacker.getEntityWorld());
                    lightningEntity.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(blockPos));
                    attacker.getEntityWorld().spawnEntity(lightningEntity);
                }
            }
        }
    }
}
