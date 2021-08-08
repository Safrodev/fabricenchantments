package safro.fabric.enchantments.mixin.enchantment;


import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import safro.fabric.enchantments.FabricEnchantments;


// caution is advised while using this code - boom shot has not been added

/* @Mixin(PersistentProjectileEntity.class)

public abstract class BoomShotMixin extends Entity {

    @Shadow protected abstract void onEntityHit(EntityHitResult entityHitResult);

    public BoomShotMixin(EntityType<?> type, World world) {
        super(type, world);
    }


    @Inject(method = "onEntityHit", at = @At("TAIL"))
    public void BoomShot(EntityHitResult entityHitResult, CallbackInfo callbackInfo) {
        if (!(entityHitResult.getEntity() instanceof LivingEntity)) {
            return;
        }

        PersistentProjectileEntity persistentProjectileEntity = (PersistentProjectileEntity) (Object) this;
        LivingEntity target = (LivingEntity) entityHitResult.getEntity();
        LivingEntity attacker = (LivingEntity) persistentProjectileEntity.getOwner();
        ItemStack mainHandStack = null;
        if (attacker != null) {
            mainHandStack = attacker.getMainHandStack();
        }

        if (mainHandStack != null && (EnchantmentHelper.getLevel(FabricEnchantments.BOOM_SHOT_ENCHANTMENT, mainHandStack) == 1)) {
            float f = (float) persistentProjectileEntity.getVelocity().length();
            int BoomShotDamage = MathHelper.ceil(MathHelper.clamp((double) f * persistentProjectileEntity.getDamage(), 0.0D, 2.147483647E9D));



        }
    }


}
*/
