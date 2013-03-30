package gaarnik.hearthstone.common.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class HearthstoneItem extends Item {
	// *******************************************************************
	public static final int HEARTHSTONE_ID = 1500;

	// *******************************************************************

	// *******************************************************************
	public HearthstoneItem() {
		super(HEARTHSTONE_ID);
		
		this.setItemName("hearthstoneItem");
		this.setIconIndex(0);
	}

	// *******************************************************************
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
		if(world.isRemote)
			return false;
		
		int blockId = world.getBlockId(x, y, z);
		
		if(blockId == Block.bed.blockID) {
			if(stack.hasTagCompound() == false) {
				NBTTagCompound nbt = new NBTTagCompound();
				stack.setTagCompound(nbt);
			}
			
			NBTTagCompound nbt = stack.getTagCompound();
			nbt.setInteger("bedX", x);
			nbt.setInteger("bedY", y);
			nbt.setInteger("bedZ", z);

			Vec3 position = player.getPosition(1.0f);
			
			nbt.setDouble("playerX", position.xCoord);
			nbt.setDouble("playerY", position.yCoord);
			nbt.setDouble("playerZ", position.zCoord);
			
			nbt.setBoolean("initialized", true);
			
			player.addChatMessage("Hearthstone linked !");
		}
		
		return true;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if(world.isRemote)
			return stack;
		
		if(stack.hasTagCompound() == false)
			return stack;
		
		NBTTagCompound nbt = stack.getTagCompound();
		
		if(nbt.getBoolean("initialized")) {
			nbt.setBoolean("initialized", false);
			return stack;
		}
		
		int x = nbt.getInteger("bedX");
		int y = nbt.getInteger("bedY");
		int z = nbt.getInteger("bedZ");
		
		int blockId = world.getBlockId(x, y, z);
		
		if(blockId != Block.bed.blockID) {
			player.addChatMessage("Your bed is missing !");
			return stack;
		}
		
		player.addChatMessage("Teleporting to home ...");
		
		double xCoord = nbt.getDouble("playerX");
		double yCoord = nbt.getDouble("playerY");
		double zCoord = nbt.getDouble("playerZ");
		
		player.setPositionAndUpdate(xCoord, yCoord, zCoord);
		
		return stack;
	}

	// *******************************************************************

	// *******************************************************************
	public static void init() {
		HearthstoneItems.hearthstoneItem = new HearthstoneItem();
		
		GameRegistry.registerItem(HearthstoneItems.hearthstoneItem, "hearthstoneItem");
		LanguageRegistry.addName(HearthstoneItems.hearthstoneItem, "Hearthstone");
		
		ItemStack stack = new ItemStack(HearthstoneItems.hearthstoneItem, 1);
		ItemStack dirstStack = new ItemStack(Block.dirt, 1);
		
		GameRegistry.addShapelessRecipe(stack, dirstStack);
	}

	// *******************************************************************
	@Override
	public String getTextureFile() {
		// TODO Auto-generated method stub
		return super.getTextureFile();
	}

}
