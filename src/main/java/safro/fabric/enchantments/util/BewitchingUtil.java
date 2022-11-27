package safro.fabric.enchantments.util;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import net.minecraft.potion.PotionUtil;
import safro.fabric.enchantments.FabricEnchantments;

import java.util.List;

public class BewitchingUtil {

    public static boolean tickBewitching(PlayerEntity player) {
        if (player.isInLava() && !player.hasStatusEffect(StatusEffects.FIRE_RESISTANCE)) {
            return tryConsume(player, StatusEffects.FIRE_RESISTANCE);
        }

        if (player.fallDistance >= 5.0F && !player.hasStatusEffect(StatusEffects.SLOW_FALLING)) {
            return tryConsume(player, StatusEffects.SLOW_FALLING);
        }

        if (player.getAir() <= 0 && !player.hasStatusEffect(StatusEffects.WATER_BREATHING)) {
            return tryConsume(player, StatusEffects.WATER_BREATHING);
        }

        if (player.getHealth() <= 6.0F) {
            return tryConsume(player, StatusEffects.INSTANT_HEALTH);
        }

        return false;
    }

    public static boolean hasBewitching(LivingEntity entity) {
        return FEUtil.getLevelArmor(entity, FabricEnchantments.BEWITCHING) > 0;
    }

    public static boolean tryConsume(PlayerEntity player, StatusEffect effect) {
        for (int i = 0; i < player.getInventory().size(); i++) {
            ItemStack stack = player.getInventory().getStack(i);
            if (stack.getItem() instanceof PotionItem) {
                List<StatusEffectInstance> potions = PotionUtil.getPotion(stack).getEffects().stream().filter(instance -> instance.getEffectType() == effect).toList();
                if (potions.size() > 0) {
                    potions.forEach(player::addStatusEffect);
                    stack.decrement(1);
                    return true;
                }
            }
        }
        return false;
    }
}
