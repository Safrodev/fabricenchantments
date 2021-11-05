package safro.fabric.enchantments;

import com.google.gson.JsonObject;
import net.fabricmc.api.ModInitializer;
import net.minecraft.enchantment.Enchantment;
import safro.fabric.enchantments.config.Config;
import safro.fabric.enchantments.config.EnchantmentConfigs;
import safro.fabric.enchantments.enchantment.*;

import java.io.File;


public class FabricEnchantments implements ModInitializer {

    public static Enchantment AUTO_SMELT;
    public static Enchantment BEHEADING;
    public static Enchantment CONSUMER;
    public static Enchantment DOUBLE_SWING;
    public static Enchantment ENDER_MIND;
    public static Enchantment EXPERIENCE;
    public static Enchantment GOD_OF_THUNDER;
    public static Enchantment ICE_ASPECT;
    public static Enchantment NOCTURNAL;
    public static Enchantment PARALYSIS;
    public static Enchantment POISON_ASPECT;
    public static Enchantment RISE;
    public static Enchantment SNIPER;
    public static Enchantment SUGAR_RUSH;
    public static Enchantment TANK;
    public static Enchantment PUMPKIN_HEAD;
    public static Enchantment GOD_OF_THE_SEA;
    public static Enchantment SHOTGUN;
    public static Enchantment GLIMMERING;

    @Override
    public void onInitialize() {

        EnchantmentConfigs.init();

        String defaultConfig =
                "{\n" +
                        "  \"regen_enchantment_config_file\": false\n" +
                        "}";

        File configFile = Config.createFile("config/fabricenchantments/backupconfig.json", defaultConfig, false);
        JsonObject json = Config.getJsonObject(Config.readFile(configFile));

        EnchantmentConfigs.generateConfigs(json == null || !json.has("regen_enchantment_config_file") || json.get("regen_enchantment_config_file").getAsBoolean());
        EnchantmentConfigs.loadConfig();

        AUTO_SMELT = new AutoSmeltEnchantment();
        BEHEADING = new BeheadingEnchantment();
        CONSUMER = new ConsumerEnchantment();
        DOUBLE_SWING = new DoubleSwingEnchantment();
        ENDER_MIND = new EnderMindEnchantment();
        EXPERIENCE = new ExperienceEnchantment();
        GOD_OF_THUNDER = new GodOfThunderEnchantment();
        ICE_ASPECT = new IceAspectEnchantment();
        NOCTURNAL = new NocturnalEnchantment();
        PARALYSIS = new ParalysisEnchantment();
        POISON_ASPECT = new PoisonAspectEnchantment();
        RISE = new RiseEnchantment();
        SNIPER = new SniperEnchantment();
        SUGAR_RUSH = new SugarRushEnchantment();
        TANK = new TankEnchantment();
        PUMPKIN_HEAD = new PumpkinHeadEnchantment();
        GOD_OF_THE_SEA = new GodOfTheSeaEnchantment();
        SHOTGUN = new ShotgunEnchantment();
        GLIMMERING = new GlimmeringEnchantment();
    }
}