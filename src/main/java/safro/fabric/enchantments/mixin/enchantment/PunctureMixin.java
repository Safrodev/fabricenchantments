package safro.fabric.enchantments.mixin.enchantment;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import safro.fabric.enchantments.FabricEnchantments;

@Mixin(PersistentProjectileEntity.class)
public abstract class PunctureMixin {

    @Shadow protected abstract void initDataTracker();

    @ModifyVariable(method = "onEntityHit", at = @At(value = "STORE", ordinal = 0))
    private DamageSource addBypassArmor(DamageSource source) {
        PersistentProjectileEntity ppe = (PersistentProjectileEntity) (Object) this;
        if (ppe.getOwner() instanceof LivingEntity entity && EnchantmentHelper.getLevel(FabricEnchantments.PUNCTURE, entity.getMainHandStack()) >= 1) {
            source.setBypassesArmor();
            FabricEnchantments.LOGGER.info("Punctured");
        }
        return source;
    }
}
