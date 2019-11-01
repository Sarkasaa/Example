package de.sarkasaa.example;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nullable;

public class ContainerBetterFurnace extends Container {

    protected ContainerBetterFurnace(PlayerInventory player, TileEntityBetterFurnace tile, int windowId) {
        super(Registry.containerBetterFurnace, windowId);

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(player, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(player, k, 8 + k * 18, 142));
        }

        this.addSlot(new SlotItemHandler(tile.inventory, 0, 10, 10));//coal
        this.addSlot(new SlotItemHandler(tile.inventory, 1, 10, 10));//input0
        this.addSlot(new SlotItemHandler(tile.inventory, 2, 10, 10));//input1
        this.addSlot(new SlotItemHandler(tile.inventory, 3, 10, 10));//output0
        this.addSlot(new SlotItemHandler(tile.inventory, 4, 10, 10));//output1

    }


    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }
}
