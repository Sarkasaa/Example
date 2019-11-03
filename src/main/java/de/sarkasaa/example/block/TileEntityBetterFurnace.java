package de.sarkasaa.example.block;

import de.sarkasaa.example.Content;
import de.sarkasaa.example.block.ContainerBetterFurnace;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class TileEntityBetterFurnace extends TileEntity implements ITickableTileEntity, INamedContainerProvider {
    public final ItemStackHandler inventory = new ItemStackHandler(5);

    public TileEntityBetterFurnace() {
        super(Content.tileEntityBetterFurnace);
    }

    @Override
    public void tick() {
        if (this.world.isRemote)
            return;
    }

    @Override
    public void read(CompoundNBT compound) {
        this.inventory.deserializeNBT(compound.getCompound("inventory"));
        super.read(compound);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("inventory", this.inventory.serializeNBT());
        return super.write(compound);
    }

    @Nullable
    @Override
    public Container createMenu(int windowId, PlayerInventory inv, PlayerEntity player) {
        return new ContainerBetterFurnace(inv, this, windowId);
    }

    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent("Better Furnace");
    }
}
