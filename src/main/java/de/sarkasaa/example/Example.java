package de.sarkasaa.example;

import de.sarkasaa.example.proxy.ClientProxy;
import de.sarkasaa.example.proxy.IProxy;
import de.sarkasaa.example.proxy.ServerProxy;
import net.minecraft.block.Blocks;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("example")
public class Example {
    // Directly reference a log4j logger.
    public static final String MOD_ID = "example";
    private static final Logger LOGGER = LogManager.getLogger();
    public static final IProxy PROXY = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);

    public Example() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code
        PROXY.init();
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }


}
