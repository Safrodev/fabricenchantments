package safro.fabric.enchantments.mixin.enchantment;

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
import safro.fabric.enchantments.FabricEnchantments;

import java.util.Iterator;

@Mixin(HoeItem.class)
public abstract class ReplenishMixin extends MiningToolItem {
    protected ReplenishMixin(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super((float)attackDamage, attackSpeed, material, BlockTags.HOE_MINEABLE, settings);
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        if (!world.isClient && state.getHardness(world, pos) != 0.0F) {
            stack.damage(1, miner, (e) -> {
                e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND);
            });
        }
        if (!world.isClient && EnchantmentHelper.getLevel(FabricEnchantments.REPLENISH, stack) >= 1) {
            if (state.getBlock() instanceof CropBlock crop) {
                boolean planted = false;
                for (ItemStack dropStack : CropBlock.getDroppedStacks(state, (ServerWorld) world, pos, null)) {
                    if (dropStack.getItem() == crop.getSeedsItem() && !planted) {
                        dropStack.decrement(dropStack.getCount());
                        world.setBlockState(pos, crop.withAge(0));
                        planted = true;
                    }
                }
            }
        }
        return true;
    }
}
