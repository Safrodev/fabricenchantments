package safro.fabric.enchantments.util;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;

public class FEUtil {
    public static boolean hasEnchantment(LivingEntity player, Enchantment enchantment) {
        return EnchantmentHelper.getLevel(enchantment, player.getMainHandStack()) >= 1 || EnchantmentHelper.getLevel(enchantment, player.getOffHandStack()) >= 1;
    }

    // Should only be used after checking "hasEnchantment"
    public static int getLevel(LivingEntity player, Enchantment enchantment) {
        if (EnchantmentHelper.getLevel(enchantment, player.getMainHandStack()) >= 1) {
            return EnchantmentHelper.getLevel(enchantment, player.getMainHandStack());
        }
        if (EnchantmentHelper.getLevel(enchantment, player.getOffHandStack()) >= 1) {
            return EnchantmentHelper.getLevel(enchantment, player.getOffHandStack());
        }
        return 0;
    }
}
