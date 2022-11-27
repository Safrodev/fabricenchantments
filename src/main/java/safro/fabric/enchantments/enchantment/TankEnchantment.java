package safro.fabric.enchantments.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import safro.fabric.enchantments.config.FabricEnchantmentsConfig;

import java.util.UUID;

public class TankEnchantment extends Enchantment {
    public static final UUID ATTRIBUTE_UUID = UUID.fromString("5D6F0BA2-1186-46AC-B896-C61C5CEE99CC");

    public TankEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentTarget.ARMOR_CHEST, new EquipmentSlot[]{EquipmentSlot.CHEST});

        if (FabricEnchantmentsConfig.getBooleanValue("tank")) {
            Registry.register(Registry.ENCHANTMENT, new Identifier("fabricenchantments", "tank"), this);
        }
    }

    @Override
    public int getMinPower(int level) { return (level - 1) * 11; }

    @Override
    public int getMaxLevel() {
        return 5;
    }
}
