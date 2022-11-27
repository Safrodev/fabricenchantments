package safro.fabric.enchantments.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import safro.fabric.enchantments.config.FabricEnchantmentsConfig;
import safro.fabric.enchantments.util.FEUtil;

public class BewitchingEnchantment extends Enchantment {

    public BewitchingEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.ARMOR, FEUtil.ALL_ARMOR);

        if (FabricEnchantmentsConfig.getBooleanValue("bewitching")) {
            Registry.register(Registry.ENCHANTMENT, new Identifier("fabricenchantments", "bewitching"), this);
        }
    }

    public int getMinPower(int level) {
        return 30;
    }

    public int getMaxPower(int level) {
        return 50;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }
}
