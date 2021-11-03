package safro.fabric.enchantments.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class EnchantmentConfigs {

    private static final HashMap<String, Boolean> ENCHANTS = new HashMap<>();

    public static boolean getValue(String key) {
        if (!ENCHANTS.containsKey(key)) {
            System.out.println(key);
        }
        return ENCHANTS.getOrDefault(key, null);
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
    }

    public static void loadConfig() {
        JsonObject json = Config.getJsonObject(Config.readFile(new File("config/fabricenchantments/config.json5")));
        for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
            ENCHANTS.put(entry.getKey(), entry.getValue().getAsBoolean());
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
        Config.createFile("config/fabricenchantments/config.json5", config.toString(), overwrite);
    }
}
