package safro.fabric.enchantments.util;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public class FEUtil {
    public static boolean hasEnchantment(LivingEntity entity, Enchantment enchantment) {
        return EnchantmentHelper.getLevel(enchantment, entity.getMainHandStack()) >= 1 || EnchantmentHelper.getLevel(enchantment, entity.getOffHandStack()) >= 1;
    }

    // Should only be used after checking "hasEnchantment"
    public static int getLevel(LivingEntity entity, Enchantment enchantment) {
        if (EnchantmentHelper.getLevel(enchantment, entity.getMainHandStack()) >= 1) {
            return EnchantmentHelper.getLevel(enchantment, entity.getMainHandStack());
        }
        if (EnchantmentHelper.getLevel(enchantment, entity.getOffHandStack()) >= 1) {
            return EnchantmentHelper.getLevel(enchantment, entity.getOffHandStack());
        }
        return 0;
    }

    public static int getLevelArmor(LivingEntity entity, Enchantment enchantment) {
        for (ItemStack stack : entity.getArmorItems()) {
            int i = EnchantmentHelper.getLevel(enchantment, stack);
            if (i > 0) {
                return i;
            }
        }
        return 0;
    }
}
