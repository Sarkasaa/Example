package de.sarkasaa.example;

import de.sarkasaa.example.block.BlockBetterFurnace;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

public final class Content {
    @ObjectHolder("example:better_furnace")
    public static BlockBetterFurnace betterFurnace;
    @ObjectHolder("example:better_furnace")
    public static TileEntityType tileEntityBetterFurnace;
    @ObjectHolder("example:better_furnace")
    public static ContainerType containerBetterFurnace;
}