package safro.fabric.enchantments.mixin;


import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import safro.fabric.enchantments.FabricEnchantments;

@Mixin(LivingEntity.class)
public class BeheadingMixin {

    @Shadow
    @Nullable
    protected PlayerEntity attackingPlayer;

    @Inject(at = @At("HEAD"), method = "onDeath", cancellable = true)

    public void BeheadingKill(DamageSource source, CallbackInfo callbackInfo) {
        if (!(source.getAttacker() instanceof PlayerEntity)) return;
        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;
        ItemStack mainHandStack = null;
        if (user != null) {
            mainHandStack = user.getMainHandStack();
        }

        if (mainHandStack != null && (EnchantmentHelper.getLevel(FabricEnchantments.BEHEADING_ENCHANTMENT, mainHandStack) >= 1)) {

            float HeadChance = 0.05F;
            float RollRandom = user.getRandom().nextFloat();
            if (RollRandom <= HeadChance) {
                if (target instanceof ZombieEntity) {
                    ItemEntity zombiedrop = new ItemEntity(target.world, target.getX(), target.getY(),
                            target.getZ(),
                            new ItemStack(Items.ZOMBIE_HEAD, 1));
                    user.world.spawnEntity(zombiedrop);
                } else if (target instanceof CreeperEntity) {
                    ItemEntity creeperdrop = new ItemEntity(target.world, target.getX(), target.getY(),
                            target.getZ(),
                            new ItemStack(Items.CREEPER_HEAD, 1));
                    user.world.spawnEntity(creeperdrop);
                } else if (target instanceof SkeletonEntity) {
                    ItemEntity skeletondrop = new ItemEntity(target.world, target.getX(), target.getY(),
                            target.getZ(),
                            new ItemStack(Items.SKELETON_SKULL, 1));
                    user.world.spawnEntity(skeletondrop);
                }
            }
        }
    }
}
