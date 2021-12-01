package safro.fabric.enchantments.mixin.enchantment;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import safro.fabric.enchantments.FabricEnchantments;

@Mixin(PersistentProjectileEntity.class)
public abstract class ShotgunMixin extends Entity {

    public ShotgunMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "onEntityHit", at = @At("TAIL"))
    private void shotgunHit(EntityHitResult entityHitResult, CallbackInfo ci){
        if (!(entityHitResult.getEntity() instanceof LivingEntity)) {
            return;
        }
        PersistentProjectileEntity persistentProjectileEntity = (PersistentProjectileEntity) (Object) this;
        Entity target = entityHitResult.getEntity();
        LivingEntity attacker = (LivingEntity) persistentProjectileEntity.getOwner();
        ItemStack mainHandStack = null;
        if (attacker != null) {
            mainHandStack = attacker.getMainHandStack();
        }
            if (mainHandStack != null && (EnchantmentHelper.getLevel(FabricEnchantments.SHOTGUN, mainHandStack) >= 1)) {
                int level = EnchantmentHelper.getLevel(FabricEnchantments.SHOTGUN, mainHandStack);
                double startDamage = persistentProjectileEntity.getDamage();
                double damageModifier = 0;
                if (level == 1) damageModifier = 1.3D;
                if (level == 2) damageModifier = 1.6D;
                double squareDistanceTo = attacker.distanceTo(target);
                double distance = Math.sqrt(squareDistanceTo);
                double distanceTraveledModifier = distance * 0.1;
                if (distance <= 5) {
                    persistentProjectileEntity.setDamage(startDamage * damageModifier);
                }
            }
    }
}
