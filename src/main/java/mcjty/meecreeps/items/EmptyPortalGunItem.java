package mcjty.meecreeps.items;

import mcjty.meecreeps.MeeCreeps;
import mcjty.meecreeps.actions.ClientActionManager;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class EmptyPortalGunItem extends Item {

    public EmptyPortalGunItem() {
        setRegistryName("emptyportalgun");
        setUnlocalizedName(MeeCreeps.MODID + ".emptyportalgun");
        setMaxStackSize(1);
        setCreativeTab(MeeCreeps.setup.getTab());
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        Collections.addAll(tooltip, StringUtils.split(I18n.format("message.meecreeps.tooltip.emptyportalgun"), "\n"));
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    @Override
    public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
        if (world.isRemote) {
//            GuiBalloon.message = "This gun does not have a cartridge!";
//            player.openGui(MeeCreeps.instance, GuiProxy.GUI_MEECREEP_BALLOON, world, pos.getX(), pos.getY(), pos.getZ());
            ClientActionManager.showProblem("message.meecreeps.missing_cartridge");
            return EnumActionResult.SUCCESS;
        }

        return EnumActionResult.SUCCESS;
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        return EnumActionResult.SUCCESS;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        if (world.isRemote) {
//            GuiBalloon.message = "This gun does not have a cartridge!";
//            BlockPos pos = player.getPosition();
//            player.openGui(MeeCreeps.instance, GuiProxy.GUI_MEECREEP_BALLOON, world, pos.getX(), pos.getY(), pos.getZ());
            ClientActionManager.showProblem("message.meecreeps.missing_cartridge");
        }
        return new ActionResult<>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }
}
