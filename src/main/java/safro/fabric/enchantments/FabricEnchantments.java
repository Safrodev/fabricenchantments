package safro.fabric.enchantments;

import net.fabricmc.api.ModInitializer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import safro.fabric.enchantments.enchantment.*;


public class FabricEnchantments implements ModInitializer {

    public static Enchantment CONSUMER_ENCHANTMENT = Registry.register(
            Registry.ENCHANTMENT,
            new Identifier("fabricenchantments", "consumer"),
            new ConsumerEnchantment()
    );

    public static Enchantment ICE_ASPECT_ENCHANTMENT = Registry.register(
            Registry.ENCHANTMENT,
            new Identifier("fabricenchantments", "ice_aspect"),
            new IceAspectEnchantment()
    );

    public static Enchantment POISON_ASPECT_ENCHANTMENT = Registry.register(
            Registry.ENCHANTMENT,
            new Identifier("fabricenchantments", "poison_aspect"),
            new PoisonAspectEnchantment()
    );

    /* public static Enchantment BOOM_SHOT_ENCHANTMENT = Registry.register(
            Registry.ENCHANTMENT,
            new Identifier("fabricenchantments", "boom_shot"),
            new BoomShotEnchantment()
    );

     */

    public static Enchantment RISE_ENCHANTMENT = Registry.register(
            Registry.ENCHANTMENT,
            new Identifier("fabricenchantments", "rise"),
            new RiseEnchantment()
    );

    public static Enchantment NOCTURNAL_ENCHANTMENT = Registry.register(
            Registry.ENCHANTMENT,
            new Identifier("fabricenchantments", "nocturnal"),
            new NocturnalEnchantment()
    );

    public static Enchantment TANK_ENCHANTMENT = Registry.register(
            Registry.ENCHANTMENT,
            new Identifier("fabricenchantments", "tank"),
            new TankEnchantment()
    );

    public static Enchantment ENDER_MIND_ENCHANTMENT = Registry.register(
            Registry.ENCHANTMENT,
            new Identifier("fabricenchantments", "ender_mind"),
            new EnderMindEnchantment()
    );

    public static Enchantment SUGAR_RUSH_ENCHANTMENT = Registry.register(
            Registry.ENCHANTMENT,
            new Identifier("fabricenchantments", "sugar_rush"),
            new SugarRushEnchantment()
    );

    public static Enchantment EXPERIENCE_ENCHANTMENT = Registry.register(
            Registry.ENCHANTMENT,
            new Identifier("fabricenchantments", "experience"),
            new ExperienceEnchantment()
    );

    public static Enchantment DOUBLE_SWING_ENCHANTMENT = Registry.register(
            Registry.ENCHANTMENT,
            new Identifier("fabricenchantments", "double_swing"),
            new DoubleSwingEnchantment()
    );

    public static Enchantment SNIPER_ENCHANTMENT = Registry.register(
            Registry.ENCHANTMENT,
            new Identifier("fabricenchantments", "sniper"),
            new SniperEnchantment()
    );

    public static Enchantment BEHEADING_ENCHANTMENT = Registry.register(
            Registry.ENCHANTMENT,
            new Identifier("fabricenchantments", "beheading"),
            new BeheadingEnchantment()
    );

    /* public static Enchantment DISARM_ENCHANTMENT = Registry.register(
            Registry.ENCHANTMENT,
            new Identifier("fabricenchantments", "disarm"),
            new DisarmEnchantment()
    );
     */

    public static Enchantment PARALYSIS_ENCHANTMENT = Registry.register(
            Registry.ENCHANTMENT,
            new Identifier("fabricenchantments", "paralysis"),
            new ParalysisEnchantment()
    );

    public static Enchantment GOT_ENCHANTMENT = Registry.register(
            Registry.ENCHANTMENT,
            new Identifier("fabricenchantments", "god_of_thunder"),
            new GodOfThunderEnchantment()
    );

    @Override
    public void onInitialize() {


    }
}