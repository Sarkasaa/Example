package de.sarkasaa.example.items;

import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameters;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DebuggerItem extends Item {


    public DebuggerItem() {
        super(new Properties().maxStackSize(1).group(ItemGroup.TOOLS));
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new StringTextComponent(this.getMode(stack).name()).setStyle(new Style().setColor(TextFormatting.GRAY)));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        if (playerIn.isSneaking()) {
            byte mode = (byte) this.getMode(stack).ordinal();
            mode = (byte) ((mode + 1) % Mode.values().length);
            this.setMode(stack, mode);
            playerIn.sendStatusMessage(new StringTextComponent(this.getMode(stack).name()).setStyle(new Style().setColor(TextFormatting.BLUE)), true);
            return new ActionResult<>(ActionResultType.SUCCESS, stack);
        }
        return new ActionResult<>(ActionResultType.FAIL, stack);
    }

    public Mode getMode(ItemStack stack) {

        CompoundNBT tag = stack.getOrCreateTag();

        if (tag.contains("mode"))
            return Mode.values()[tag.getByte("mode")];

        return Mode.values()[this.setMode(stack, (byte) 0)];

    }

    public byte setMode(ItemStack stack, byte mode) {
        if (mode < 0 || mode > Mode.values().length)
            mode = 0;

        CompoundNBT tag = stack.getOrCreateTag();

        tag.putByte("mode", mode);

        return mode;
    }


    @Override
    public void onCreated(ItemStack stack, World worldIn, PlayerEntity playerIn) {
        if (!stack.getOrCreateTag().contains("mode")) {
            this.setMode(stack, (byte) 0);
        }
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {


        if (!context.getWorld().isRemote()) {
            BlockState state = context.getWorld().getBlockState(context.getPos());
            ServerWorld world = (ServerWorld) context.getWorld();
            PlayerEntity player = context.getPlayer();
            Set<String> set = new HashSet<>();
            player.sendStatusMessage(new StringTextComponent("Debugging:").setStyle(new Style().setBold(true).setColor(TextFormatting.GRAY)), false);
            player.sendStatusMessage(new StringTextComponent("Block: ").appendText(state.getBlock().getRegistryName().toString()), false);
            Mode mode = this.getMode(context.getItem());
            for (int i = 0; i < 10000; i++) {
                LootContext.Builder loottable = new LootContext.Builder(world).withParameter(LootParameters.POSITION, context.getPos()).withParameter(LootParameters.TOOL, mode.stack).withLuck(1F);
                List<ItemStack> list = state.getDrops(loottable);
                for (ItemStack stack : list)
                    set.add(stack.getItem().getRegistryName().toString());

            }

            player.sendStatusMessage(new StringTextComponent("drops: ").setStyle(new Style().setBold(true)), false);

            for (String drop : set) {
                player.sendStatusMessage(new StringTextComponent("         " + drop), false);
            }


            player.sendStatusMessage(new StringTextComponent("using: ").appendText(mode.name()).setStyle(new Style().setBold(true)), false);


        }
        return ActionResultType.SUCCESS;
    }

    public enum Mode {

        NONE(ItemStack.EMPTY),
        SILKTOUCH(silk()),
        SHEARS(new ItemStack(Items.SHEARS));

        ItemStack item = new ItemStack(Items.SHEARS);

        public final ItemStack stack;

        Mode(ItemStack stack) {
            this.stack = stack;
        }

        private static ItemStack silk() {
            ItemStack item = new ItemStack(Items.DIAMOND_PICKAXE);
            item.addEnchantment(Enchantments.SILK_TOUCH, 1);
            return item;
        }
    }

}
