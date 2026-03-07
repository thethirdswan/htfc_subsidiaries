package com.thethirdswan.htfc_subsidiaries.setup;
import blusunrize.immersiveengineering.common.blocks.multiblocks.StaticTemplateManager;
import com.thethirdswan.htfc_subsidiaries.api.ItemHeatProvider;
import com.thethirdswan.htfc_subsidiaries.data.MultiblockStates;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = "htfc_subsidiaries", bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGen {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();

        ExistingFileHelper exHelper = event.getExistingFileHelper();
        StaticTemplateManager.EXISTING_HELPER = exHelper;

        if (event.includeServer()) {
            generator.addProvider(new HTFCSRecipeProvider(generator));
            generator.addProvider(new ItemHeatProvider(generator));
            HTFCSBlockTags HTFCSBlockTags = new HTFCSBlockTags(generator, event.getExistingFileHelper());
            generator.addProvider(HTFCSBlockTags);
            generator.addProvider(new HTFCSItemTags(generator, HTFCSBlockTags, event.getExistingFileHelper()));
        }
        if (event.includeClient()) {
//            generator.addProvider(new TutBlockStates(generator, event.getExistingFileHelper()));
            generator.addProvider(new MultiblockStates(generator, "htfc_subsidiaries", event.getExistingFileHelper()));
            generator.addProvider(new HTFCSItemModels(generator, event.getExistingFileHelper()));
            generator.addProvider(new HTFCSLanguageProviders(generator, "en_us"));
        }
    }
}