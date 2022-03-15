package safro.fabric.enchantments.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import safro.fabric.enchantments.FabricEnchantments;
import safro.fabric.enchantments.config.FabricEnchantmentsConfig;

public class DoubleSwingEnchantment extends Enchantment {

    public DoubleSwingEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.DIGGER, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});

        if (FabricEnchantmentsConfig.getBooleanValue("double_swing")) {
            Registry.register(Registry.ENCHANTMENT, new Identifier("fabricenchantments", "double_swing"), this);
        }
    }

    @Override
    public int getMinPower(int level) { return 20; }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        super.onTargetDamaged(user, target, level);
        if (user instanceof PlayerEntity player && player.getRandom().nextInt(100) <= FabricEnchantmentsConfig.getIntValue("double_swing_chance")) {
            target.damage(DamageSource.player(player), player.getStackInHand(Hand.MAIN_HAND).getDamage());
            player.swingHand(Hand.MAIN_HAND);
        }
    }

    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof AxeItem;
    }
}
