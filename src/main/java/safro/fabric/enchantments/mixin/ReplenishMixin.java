package safro.fabric.enchantments.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import safro.fabric.enchantments.FabricEnchantments;

@Mixin(Block.class)
public class ReplenishMixin {

    @Inject(method = "afterBreak", at = @At("HEAD"), cancellable = true)
    private void replenishEnchantment(World world, PlayerEntity player, BlockPos pos, BlockState state, BlockEntity blockEntity, ItemStack stack, CallbackInfo ci) {
        Block block = (Block) (Object) this;
        boolean discardedSeed = false;

        if (stack.getItem() instanceof HoeItem && EnchantmentHelper.getLevel(FabricEnchantments.REPLENISH, stack) >= 1) {
            if (block instanceof CropBlock crop && crop.isMature(state)) {
                if (world instanceof ServerWorld) {
                    for (ItemStack drop : Block.getDroppedStacks(state, (ServerWorld) world, pos, blockEntity, player, stack)) {
                        if (drop.getItem() == crop.getSeedsItem() && !discardedSeed) {
                            discardedSeed = true;
                        } else {
                            Block.dropStack(world, pos, drop);
                        }
                    }
                    state.onStacksDropped((ServerWorld)world, pos, stack, true);
                }

                world.setBlockState(pos, crop.withAge(0));
                player.incrementStat(Stats.MINED.getOrCreateStat(block));
                player.addExhaustion(0.005F);
                stack.damage(1, player, c -> c.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
                ci.cancel();
            }
        }
    }
}
