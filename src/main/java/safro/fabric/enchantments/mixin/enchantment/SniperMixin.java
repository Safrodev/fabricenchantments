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
public abstract class SniperMixin extends Entity {

    public SniperMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "onEntityHit", at = @At("TAIL"))
    private void SniperHit(EntityHitResult entityHitResult, CallbackInfo ci){
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
        if (mainHandStack != null && (EnchantmentHelper.getLevel(FabricEnchantments.SNIPER, mainHandStack) >= 1)) {
            int level = EnchantmentHelper.getLevel(FabricEnchantments.SNIPER, mainHandStack);
            double startDamage = persistentProjectileEntity.getDamage();
            double modifier = 1;
            if (level == 1) {
                modifier = 1.3D;
            } else if (level == 2) {
                modifier = 1.6D;
            } else if (level > 2) {
                // In case you got one of those mods that makes the enchantment levels higher
                modifier = 1.8D;
            }
            double distance = attacker.squaredDistanceTo(target);
            if (distance >= 60 && distance <= 100) {
                persistentProjectileEntity.setDamage(startDamage * modifier);
            } else if (distance > 100) {
                persistentProjectileEntity.setDamage(startDamage * modifier + 0.5D);
            }
        }
    }
}
