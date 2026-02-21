package com.thethirdswan.htfc_subsidiaries.setup;
import com.thethirdswan.htfc_subsidiaries.api.ItemHeatProvider;
import com.thethirdswan.htfc_subsidiaries.data.MultiblockStates;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = "htfc_subsidiaries", bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGen {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        if (event.includeServer()) {
            generator.addProvider(new Recipes(generator));
            generator.addProvider(new ItemHeatProvider(generator));
            BlockTags blockTags = new BlockTags(generator, event.getExistingFileHelper());
            generator.addProvider(blockTags);
            generator.addProvider(new ItemTags(generator, blockTags, event.getExistingFileHelper()));
        }
        if (event.includeClient()) {
//            generator.addProvider(new TutBlockStates(generator, event.getExistingFileHelper()));
            generator.addProvider(new MultiblockStates(generator, "htfc_subsidiaries", event.getExistingFileHelper()));
            generator.addProvider(new ItemModels(generator, event.getExistingFileHelper()));
            generator.addProvider(new LanguageProviders(generator, "en_us"));
        }
    }
}