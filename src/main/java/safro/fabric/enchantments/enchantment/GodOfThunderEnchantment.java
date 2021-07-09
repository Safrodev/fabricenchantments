package safro.fabric.enchantments.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class GodOfThunderEnchantment extends Enchantment {

    public GodOfThunderEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentTarget.DIGGER, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMinPower(int level) { return 10; }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        float hitchance = 0.05F;

        if (user.getRandom().nextFloat() <= hitchance) {
            BlockPos blockPos = target.getBlockPos();
            LightningEntity lightningEntity = (LightningEntity)EntityType.LIGHTNING_BOLT.create(user.getEntityWorld());
            lightningEntity.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(blockPos));
            user.getEntityWorld().spawnEntity(lightningEntity);
        }
    }
}