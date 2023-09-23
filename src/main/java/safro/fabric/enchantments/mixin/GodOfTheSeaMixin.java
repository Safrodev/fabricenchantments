package safro.fabric.enchantments.mixin;

import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import safro.fabric.enchantments.FabricEnchantments;
import safro.fabric.enchantments.util.FEUtil;

@Mixin(LivingEntity.class)
public class GodOfTheSeaMixin {

    @Inject(at = @At("HEAD"), method = "baseTick")
    public void godOfTheSea(CallbackInfo ci) {
        LivingEntity user = (LivingEntity) (Object) this;
        if (FEUtil.hasEnchantment(user, FabricEnchantments.GOD_OF_THE_SEA)) {
            if (!user.getEntityWorld().getBlockState(BlockPos.ofFloored(user.getX(), user.getEyeY(), user.getZ())).isOf(Blocks.BUBBLE_COLUMN)) {
                if (!((PlayerEntity) user).getAbilities().invulnerable) {
                    user.setAir(user.getMaxAir());
                }
            }
        }
    }
}
