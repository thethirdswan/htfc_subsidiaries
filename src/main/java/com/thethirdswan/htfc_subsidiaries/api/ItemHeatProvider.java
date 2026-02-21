package com.thethirdswan.htfc_subsidiaries.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class ItemHeatProvider implements DataProvider {
    private final DataGenerator gen;
    private final Map<String, IHeatProperty> heatProperties = new HashMap<>();
    public ItemHeatProvider(DataGenerator gen) {
        super();
        this.gen = gen;
    }

    public void registerHeatItems(String itemId, float heat, int forging, int welding) {
        heatProperties.put(itemId, new IHeatProperty(heat, forging, welding));
    }

    @Override
    public void run(HashCache cache) throws IOException {
        registerHeatItems("htfc_subsidiaries:wrought_iron_spindle_head", 2.857f, 921, 1228);
        registerHeatItems("htfc_subsidiaries:wrought_iron_spindle", 2.857f, 921, 1228);

        for (Map.Entry<String, IHeatProperty> entry : heatProperties.entrySet()) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonObject jsonObject = new JsonObject();
            JsonObject ingredientObject = new JsonObject();
            ingredientObject.addProperty("item", entry.getKey());
            jsonObject.add("ingredient", ingredientObject);
            jsonObject.addProperty("heat_capacity", entry.getValue().heat());
            jsonObject.addProperty("forging_temperature", entry.getValue().forging());
            jsonObject.addProperty("welding_temperature", entry.getValue().welding());

            String filename = entry.getKey().replace("htfc_subsidiaries:", "") + ".json";
            Path path = gen.getOutputFolder().resolve("data/htfc_subsidiaries/tfc/item_heats/metal/" + filename);

            DataProvider.save(gson, cache, jsonObject, path);
        }
    }

    @Override
    public String getName() {
        return "Item Heats";
    }
}
