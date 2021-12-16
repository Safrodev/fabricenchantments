package safro.fabric.enchantments.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;

public class DisarmEnchantment extends Enchantment {
    public DisarmEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[] {EquipmentSlot.MAINHAND});

    }

    @Override
    public int getMinPower(int level) {
        return 10 + 20 - 1;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean canAccept(Enchantment other) {
        return super.canAccept(other) && other != Enchantments.KNOCKBACK;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if(target instanceof LivingEntity) {
            if (!((LivingEntity) target).getMainHandStack().isEmpty()) {
                float EnchantChance = 0.50F;
                float DisarmRandom = user.getRandom().nextFloat();
                if (DisarmRandom <= EnchantChance) {
                    user.dropStack(((LivingEntity) target).getMainHandStack());
                    ((LivingEntity) target).getMainHandStack().decrement(1);
                }
            }
        }

        super.onTargetDamaged(user, target, level);
    }
}
