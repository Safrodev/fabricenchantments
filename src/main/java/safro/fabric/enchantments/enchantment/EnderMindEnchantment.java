package safro.fabric.enchantments.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import safro.fabric.enchantments.FabricEnchantments;
import safro.fabric.enchantments.config.EnchantmentConfigs;

public class EnderMindEnchantment extends Enchantment {
    public EnderMindEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentTarget.ARMOR_HEAD, new EquipmentSlot[] {EquipmentSlot.HEAD});

        if (EnchantmentConfigs.getValue("ender_mind")) {
            Registry.register(Registry.ENCHANTMENT, new Identifier("fabricenchantments", "ender_mind"), this);
        }
    }

    @Override
    public int getMinPower(int level) { return 20; }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public void onUserDamaged(LivingEntity user, Entity attacker, int level) {
        World world = user.getEntityWorld();
        if (!world.isClient) {
            if (user.getHealth() <= 8.0F) {
                double d = user.getX();
                double e = user.getY();
                double f = user.getZ();

                for (int i = 0; i < 16; ++i) {
                    double g = user.getX() + (user.getRandom().nextDouble() - 0.5D) * 16.0D;
                    double h = MathHelper.clamp(user.getY() + (double) (user.getRandom().nextInt(16) - 8), 0.0D, (double) (world.getDimensionHeight() - 1));
                    double j = user.getZ() + (user.getRandom().nextDouble() - 0.5D) * 16.0D;
                    if (user.hasVehicle()) {
                        user.stopRiding();
                    }

                    if (user.teleport(g, h, j, true)) {
                        SoundEvent soundEvent = user instanceof FoxEntity ? SoundEvents.ENTITY_FOX_TELEPORT : SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT;
                        world.playSound((PlayerEntity) null, d, e, f, soundEvent, SoundCategory.PLAYERS, 1.0F, 1.0F);
                        user.playSound(soundEvent, 1.0F, 1.0F);
                        break;
                    }
                }
            }
        }
    }

    @Override
    public boolean canAccept (Enchantment other){
        return super.canAccept(other) && other != FabricEnchantments.NOCTURNAL;
    }
}
