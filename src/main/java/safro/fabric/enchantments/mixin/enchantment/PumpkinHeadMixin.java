package safro.fabric.enchantments.mixin.enchantment;

import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import safro.fabric.enchantments.FabricEnchantments;

@Mixin(EndermanEntity.class)
public class PumpkinHeadMixin {

    /**
     * @author
     */
    @Overwrite
    public boolean isPlayerStaring(PlayerEntity player) {
        ItemStack itemStack = (ItemStack)player.getInventory().armor.get(3);
        EndermanEntity enderman = (EndermanEntity) (Object) this;
        ItemStack stack = player.getEquippedStack(EquipmentSlot.HEAD);
        if (itemStack.isOf(Blocks.CARVED_PUMPKIN.asItem())) {
            return false;
        } else if (stack != null && EnchantmentHelper.getLevel(FabricEnchantments.PUMPKIN_HEAD, stack) >= 1) {
            return false;
        } else {
            Vec3d vec3d = player.getRotationVec(1.0F).normalize();
            Vec3d vec3d2 = new Vec3d(enderman.getX() - player.getX(), enderman.getEyeY() - player.getEyeY(), enderman.getZ() - player.getZ());
            double d = vec3d2.length();
            vec3d2 = vec3d2.normalize();
            double e = vec3d.dotProduct(vec3d2);
            return e > 1.0D - 0.025D / d ? player.canSee(enderman) : false;
        }
    }
}
