package gaarnik.hearthstone.common.item;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
