package safro.fabric.enchantments.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import safro.fabric.enchantments.FabricEnchantments;

@Mixin(MiningToolItem.class)
public class ReplenishMixin {

    @Inject(method = "postMine", at = @At("HEAD"), cancellable = true)
    public void replenishPostMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner, CallbackInfoReturnable<Boolean> cir) {
        if (stack.getItem() instanceof HoeItem) {
            int damage = 1;
            if (!world.isClient && state.getHardness(world, pos) != 0.0F) {
                if (EnchantmentHelper.getLevel(FabricEnchantments.REPLENISH, stack) >= 1) {
                    if (state.getBlock() instanceof CropBlock crop) {
                        boolean planted = false;
                        for (ItemStack dropStack : CropBlock.getDroppedStacks(state, (ServerWorld) world, pos, null)) {
                            if (dropStack.getItem() == crop.getSeedsItem() && !planted) {
                                dropStack.decrement(dropStack.getCount());
                                world.setBlockState(pos, crop.withAge(0));
                                planted = true;
                            }
                        }
                        damage = 2;
                    }
                }
                stack.damage(damage, miner, (e) -> {
                    e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND);
                });
            }

            cir.setReturnValue(true);
        }
    }
}
