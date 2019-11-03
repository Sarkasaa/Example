package de.sarkasaa.example.block;

import de.sarkasaa.example.Content;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerBetterFurnace extends Container {

    public ContainerBetterFurnace(PlayerInventory player, TileEntityBetterFurnace tile, int windowId) {
        super(Content.containerBetterFurnace, windowId);

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(player, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(player, k, 8 + k * 18, 142));
        }

        this.addSlot(new SlotItemHandler(tile.inventory, 0, 56, 53));//coal
        this.addSlot(new SlotItemHandler(tile.inventory, 1, 47, 17));//input0
        this.addSlot(new SlotItemHandler(tile.inventory, 2, 65, 17));//input1
        this.addSlot(new SlotItemHandler(tile.inventory, 3, 109, 35));//output0
        this.addSlot(new SlotItemHandler(tile.inventory, 4, 135, 35));//output1

    }


    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }
}
