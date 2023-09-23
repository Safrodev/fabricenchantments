package safro.fabric.enchantments.mixin;


import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import safro.fabric.enchantments.FabricEnchantments;
import safro.fabric.enchantments.config.FabricEnchantmentsConfig;
import safro.fabric.enchantments.util.FEUtil;

@Mixin(LivingEntity.class)
public class BeheadingMixin {

    @Inject(at = @At("HEAD"), method = "onDeath", cancellable = true)
    public void beheadingKill(DamageSource source, CallbackInfo callbackInfo) {
        if (!(source.getAttacker() instanceof PlayerEntity)) return;
        LivingEntity user = (LivingEntity) source.getAttacker();
        LivingEntity target = (LivingEntity) (Object) this;

        if (user != null && FEUtil.hasEnchantment(user, FabricEnchantments.BEHEADING)) {
            int chance = FabricEnchantmentsConfig.getIntValue("beheading_chance");
            if (user.getRandom().nextInt(100) <= chance) {
                if (target instanceof ZombieEntity) {
                    ItemEntity zombiedrop = new ItemEntity(target.getWorld(), target.getX(), target.getY(), target.getZ(), new ItemStack(Items.ZOMBIE_HEAD, 1));
                    user.getWorld().spawnEntity(zombiedrop);
                } else if (target instanceof CreeperEntity) {
                    ItemEntity creeperdrop = new ItemEntity(target.getWorld(), target.getX(), target.getY(), target.getZ(), new ItemStack(Items.CREEPER_HEAD, 1));
                    user.getWorld().spawnEntity(creeperdrop);
                } else if (target instanceof SkeletonEntity) {
                    ItemEntity skeletondrop = new ItemEntity(target.getWorld(), target.getX(), target.getY(), target.getZ(), new ItemStack(Items.SKELETON_SKULL, 1));
                    user.getWorld().spawnEntity(skeletondrop);
                }
            }
        }
    }
}
