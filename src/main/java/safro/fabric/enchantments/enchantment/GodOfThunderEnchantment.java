package safro.fabric.enchantments.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.*;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.registry.Registry;
import safro.fabric.enchantments.config.FabricEnchantmentsConfig;

public class GodOfThunderEnchantment extends Enchantment {

    public GodOfThunderEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentTarget.DIGGER, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});

        if (FabricEnchantmentsConfig.getBooleanValue("god_of_thunder")) {
            Registry.register(Registries.ENCHANTMENT, new Identifier("fabricenchantments", "god_of_thunder"), this);
        }
    }

    @Override
    public int getMinPower(int level) { return 10; }

    @Override
    public boolean isTreasure() {
        return true;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        int hitchance = FabricEnchantmentsConfig.getIntValue("god_of_thunder_chance");

        if (user.getRandom().nextInt(100) <= hitchance) {
            BlockPos blockPos = target.getBlockPos();
            LightningEntity lightningEntity = (LightningEntity)EntityType.LIGHTNING_BOLT.create(user.getEntityWorld());
            lightningEntity.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(blockPos));
            user.getEntityWorld().spawnEntity(lightningEntity);
        }
    }

    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof AxeItem;
    }
}
