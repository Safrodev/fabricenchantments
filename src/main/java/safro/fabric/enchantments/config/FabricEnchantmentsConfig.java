package safro.fabric.enchantments.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class FabricEnchantmentsConfig {
    private static final HashMap<String, Boolean> ENCHANTS = new HashMap<>();
    private static final HashMap<String, Integer> INT_OPTIONS = new HashMap<>();

    public static boolean getBooleanValue(String key) {
        if (!ENCHANTS.containsKey(key)) {
            System.out.println(key);
        }
        return ENCHANTS.getOrDefault(key, null);
    }

    public static int getIntValue(String key) {
        if (!INT_OPTIONS.containsKey(key)) {
            System.out.println(key);
        }
        return INT_OPTIONS.getOrDefault(key, null);
    }

    public static void init() {
        ENCHANTS.put("auto_smelt", true);
        ENCHANTS.put("beheading", true);
        ENCHANTS.put("consumer", true);
        ENCHANTS.put("double_swing", true);
        ENCHANTS.put("ender_mind", true);
        ENCHANTS.put("experience", true);
        ENCHANTS.put("god_of_thunder", true);
        ENCHANTS.put("ice_aspect", true);
        ENCHANTS.put("nocturnal", true);
        ENCHANTS.put("paralysis", true);
        ENCHANTS.put("poison_aspect", true);
        ENCHANTS.put("rise", true);
        ENCHANTS.put("sniper", true);
        ENCHANTS.put("sugar_rush", true);
        ENCHANTS.put("tank", true);
        ENCHANTS.put("pumpkin_head", true);
        ENCHANTS.put("god_of_the_sea", true);
        ENCHANTS.put("shotgun", true);
        ENCHANTS.put("glimmering", true);
        ENCHANTS.put("charged_bolt", true);
        ENCHANTS.put("pyromania", true);
        ENCHANTS.put("replenish", true);
        ENCHANTS.put("soul_seeker", true);
        ENCHANTS.put("pulse", true);
        ENCHANTS.put("bewitching", true);

        INT_OPTIONS.put("double_swing_chance", 5);
        INT_OPTIONS.put("god_of_thunder_chance", 5);
        INT_OPTIONS.put("ice_aspect_base_duration", 60);
        INT_OPTIONS.put("nocturnal_duration", 60);
        INT_OPTIONS.put("paralysis_duration", 40);
        INT_OPTIONS.put("poison_aspect_duration", 60);
        INT_OPTIONS.put("rise_base_duration", 20);
        INT_OPTIONS.put("beheading_chance", 5);
        INT_OPTIONS.put("charged_bolt_chance", 30);
        INT_OPTIONS.put("consumer_base_chance", 10);
        INT_OPTIONS.put("experience_base_amount", 4);
        INT_OPTIONS.put("sugar_rush_duration", 40);
        INT_OPTIONS.put("shotgun_base_damage", 6);
        INT_OPTIONS.put("soul_seeker_detection_range", 15);
        INT_OPTIONS.put("pulse_damage", 10);
    }

    public static void loadConfig() {
        JsonObject json;
        json = Config.getJsonObject(Config.readFile(new File("config/fabricenchantments/enchantment_config.json5")));
        for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
            ENCHANTS.put(entry.getKey(), entry.getValue().getAsBoolean());
        }

        json = Config.getJsonObject(Config.readFile(new File("config/fabricenchantments/value_config.json5")));
        for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
            INT_OPTIONS.put(entry.getKey(), entry.getValue().getAsInt());
        }
    }

    public static void generateConfigs(boolean overwrite) {
        StringBuilder config = new StringBuilder("{\n");
        int i = 0;
        for (String key : ENCHANTS.keySet()) {
            config.append("  \"").append(key).append("\": ").append(ENCHANTS.get(key));
            ++i;
            if (i < ENCHANTS.size()) {
                config.append(",");
            }
            config.append("\n");
        }
        config.append("}");
        Config.createFile("config/fabricenchantments/enchantment_config.json5", config.toString(), overwrite);

        config = new StringBuilder("{\n");
        i = 0;
        for (String item : INT_OPTIONS.keySet()) {
            config.append("  \"").append(item).append("\": ").append(INT_OPTIONS.get(item));
            ++i;
            if (i < INT_OPTIONS.size()) {
                config.append(",");
            }
            config.append("\n");
        }
        config.append("}");
        Config.createFile("config/fabricenchantments/value_config.json5", config.toString(), overwrite);
    }
}
