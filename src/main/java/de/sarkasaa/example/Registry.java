package de.sarkasaa.example;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.IContainerFactory;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public final class Registry {
    public static Block blockBetterFurnace;
    public static TileEntityType tileEntityBetterFurnace;
    public static ContainerType containerBetterFurnace;

    @SubscribeEvent
    public static void registerBlock(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(
                blockBetterFurnace = new BlockBetterFurnace()

        );
    }

    @SubscribeEvent
    public static void registerItem(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(
                new BlockItem(blockBetterFurnace, new Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName(blockBetterFurnace.getRegistryName())

        );
    }

    @SubscribeEvent
    public static void registerTileEntity(RegistryEvent.Register<TileEntityType<?>> event) {
        event.getRegistry().registerAll(
                tileEntityBetterFurnace = TileEntityType.Builder.create(TileEntityBetterFurnace::new, blockBetterFurnace).build(null).setRegistryName("better_furnace")
        );
    }

    @SubscribeEvent
    public static void registerContainerType(RegistryEvent.Register<ContainerType<?>> event) {
        event.getRegistry().registerAll(
                containerBetterFurnace = IForgeContainerType.create((windowId, inv, data) -> {
                    BlockPos pos = data.readBlockPos();
                    World world = Minecraft.getInstance().world;
                    TileEntityBetterFurnace tile = (TileEntityBetterFurnace) world.getTileEntity(pos);
                    return new ContainerBetterFurnace(inv, tile, windowId);
                }).setRegistryName("better_furnace")
        );
    }

}
