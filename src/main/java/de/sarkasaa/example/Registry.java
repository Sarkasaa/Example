package de.sarkasaa.example;

import de.sarkasaa.example.block.BlockBetterFurnace;
import de.sarkasaa.example.block.ContainerBetterFurnace;
import de.sarkasaa.example.block.TileEntityBetterFurnace;
import de.sarkasaa.example.items.DebuggerItem;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.DebugStickItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public final class Registry {

    @SubscribeEvent
    public static void registerBlock(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(
                new BlockBetterFurnace()

        );
    }

    @SubscribeEvent
    public static void registerItem(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(
                new BlockItem(Content.betterFurnace, new Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName(Content.betterFurnace.getRegistryName()),
                new DebuggerItem().setRegistryName("debugger")

        );
    }

    @SubscribeEvent
    public static void registerTileEntity(RegistryEvent.Register<TileEntityType<?>> event) {
        event.getRegistry().registerAll(
                TileEntityType.Builder.create(TileEntityBetterFurnace::new, Content.betterFurnace).build(null).setRegistryName("better_furnace")
        );
    }

    @SubscribeEvent
    public static void registerContainerType(RegistryEvent.Register<ContainerType<?>> event) {
        event.getRegistry().registerAll(
                IForgeContainerType.create((windowId, inv, data) -> {
                    BlockPos pos = data.readBlockPos();
                    World world = Minecraft.getInstance().world;
                    TileEntityBetterFurnace tile = (TileEntityBetterFurnace) world.getTileEntity(pos);
                    return new ContainerBetterFurnace(inv, tile, windowId);
                }).setRegistryName("better_furnace")
        );
    }

}
