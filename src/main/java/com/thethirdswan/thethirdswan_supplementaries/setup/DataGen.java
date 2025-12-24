package com.thethirdswan.thethirdswan_supplementaries.setup;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = "thethirdswan_supplementaries", bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGen {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        if (event.includeServer()) {
            generator.addProvider(new Recipes(generator));
//            generator.addProvider(new TutLootTables(generator));
            BlockTags blockTags = new BlockTags(generator, event.getExistingFileHelper());
            generator.addProvider(blockTags);
            generator.addProvider(new ItemTags(generator, blockTags, event.getExistingFileHelper()));
        }
        if (event.includeClient()) {
//            generator.addProvider(new TutBlockStates(generator, event.getExistingFileHelper()));
            generator.addProvider(new ItemModels(generator, event.getExistingFileHelper()));
            generator.addProvider(new LanguageProviders(generator, "en_us"));
        }
    }
}