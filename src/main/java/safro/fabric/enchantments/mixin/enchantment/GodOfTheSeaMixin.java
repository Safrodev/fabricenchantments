package safro.fabric.enchantments.mixin.enchantment;

import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import safro.fabric.enchantments.FabricEnchantments;

@Mixin(LivingEntity.class)
public abstract class GodOfTheSeaMixin {

    @Shadow protected abstract int getNextAirUnderwater(int air);

    @Inject(at = @At("HEAD"), method = "baseTick", cancellable = true)

    public void GodOfTheSeaEnchantment(CallbackInfo ci) {
        LivingEntity user = (LivingEntity) (Object) this;
        ItemStack mainHandStack;
        mainHandStack = user.getMainHandStack();

        if (mainHandStack != null && (EnchantmentHelper.getLevel(FabricEnchantments.GOD_OF_THE_SEA, mainHandStack) >= 1)) {
            if (user.isSubmergedIn(FluidTags.WATER) && !user.getEntityWorld().getBlockState(new BlockPos(user.getX(), user.getEyeY(), user.getZ())).isOf(Blocks.BUBBLE_COLUMN)) {
                if (!user.canBreatheInWater() && !((PlayerEntity) user).getAbilities().invulnerable) {
                    user.setAir(user.getMaxAir());

                }
            }
        }
    }
}
