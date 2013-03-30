package gaarnik.hearthstone.common.item;

import gaarnik.hearthstone.client.HearthstoneClientProxy;
import gaarnik.hearthstone.common.ModHearthstone;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class HearthstoneItem extends Item {
	// *******************************************************************
	public static final int HEARTHSTONE_ID = 1500;

	private static final int MAX_DAMAGE = 10;
	private static final int MOTION_SICKNEWW_DURATION = 12000;

	// *******************************************************************

	// *******************************************************************
	public HearthstoneItem() {
		super(HEARTHSTONE_ID);
		
		this.setItemName("hearthstoneItem");
		this.setIconIndex(0);
		this.setMaxDamage(MAX_DAMAGE);
	}

	// *******************************************************************
	/**
	 * Cette fonction est apellée lorsque que le joueur clic sur un block
	 */
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par7, float xRot, float yRot, float zRot) {
		if(world.isRemote) //seulement si l'event viens du client
			return false;

		int blockId = world.getBlockId(x, y, z);

		//si le joueur a cliqué sur un lit
		if(blockId == Block.bed.blockID) {
			
			//on sauvegarde les coordonnées du lit et du joeur dans les données du stack
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

			nbt.setFloat("rotationYaw", player.rotationYaw);
			nbt.setFloat("rotationYawHead", player.rotationYawHead);
			nbt.setFloat("rotationPitch", player.rotationPitch);

			//nbt.setInteger("dimension", world.getWorldInfo().getDimension());

			nbt.setBoolean("initialized", true);

			player.addChatMessage("Hearthstone linked !");
		}

		return true;
	}

	/**
	 * Cette méthode est apellée lorsque le joueur fait un clic droit pour tuliser la pierre
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if(world.isRemote)
			return stack;
		
		if(player.getItemInUseCount() != 0)
			return stack;

		//on vérifie si le joeuur n'a pas le mam des transports
		PotionEffect sicknessEffect = player.getActivePotionEffect(ModHearthstone.heathstonePotion);

		if(sicknessEffect != null) {
			if(sicknessEffect.getDuration() != 0) {
				player.addChatMessage("You have motion sickness !");
				return stack;
			}
			else
				player.removePotionEffect(ModHearthstone.heathstonePotion.id);
		}

		//on récupère les infos stockées dans le stack
		if(stack.hasTagCompound() == false) {
			player.addChatMessage("You have not linked your Hearthstone !");
			return stack;
		}
		
		NBTTagCompound nbt = stack.getTagCompound();
		
		if(nbt.getBoolean("initialized")) {
			nbt.setBoolean("initialized", false);
			return stack;
		}

		int x = nbt.getInteger("bedX");
		int y = nbt.getInteger("bedY");
		int z = nbt.getInteger("bedZ");

		int blockId = world.getBlockId(x, y, z);

		//on vérifie que le  lit est toujours présent
		if(blockId != Block.bed.blockID) {
			player.addChatMessage("Your bed is missing !");
			return stack;
		}

		//on déclenche l'animation de téléportation
		player.addChatMessage("Teleporting to home ...");

		player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
		
		return stack;
	}

	/**
	 * Cette méthode est apellée à la fin de l'animation
	 */
	public ItemStack onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
		if(world.isRemote)
			return stack;
		
		if(stack.hasTagCompound() == false) {
			player.addChatMessage("You have not linked your Hearthstone !");
			return stack;
		}
		
		//on récupère les données du stack pour mettre à jour la position du joueur
		NBTTagCompound nbt = stack.getTagCompound();

		/*int dimension = nbt.getInteger("dimension");
		EntityPlayerMP playerMP = (EntityPlayerMP) player;
		playerMP.travelToDimension(dimension);

		player.rotationYaw = nbt.getFloat("rotationYaw");
		player.rotationYawHead = nbt.getFloat("rotationYawHead");
		player.rotationPitch = nbt.getFloat("rotationPitch");*/

		double xCoord = nbt.getDouble("playerX");
		double yCoord = nbt.getDouble("playerY");
		double zCoord = nbt.getDouble("playerZ");

		player.setPositionAndUpdate(xCoord, yCoord, zCoord);

		if(ModHearthstone.DEBUG == false) {
			stack.damageItem(1, player);
			player.addPotionEffect(new PotionEffect(ModHearthstone.heathstonePotion.id, MOTION_SICKNEWW_DURATION, 0));
		}
		
    	return stack;
    }

	// *******************************************************************

	// *******************************************************************
	public static void init() {
		HearthstoneItems.hearthstoneItem = new HearthstoneItem();

		GameRegistry.registerItem(HearthstoneItems.hearthstoneItem, "hearthstoneItem");
		LanguageRegistry.addName(HearthstoneItems.hearthstoneItem, "Hearthstone");

		ItemStack stack = new ItemStack(HearthstoneItems.hearthstoneItem, 1);

		if(ModHearthstone.DEBUG) {
			ItemStack dirstStack = new ItemStack(Block.dirt, 1);
			GameRegistry.addShapelessRecipe(stack, dirstStack);
		}
	}

	// *******************************************************************
	@Override
	public String getTextureFile() { return HearthstoneClientProxy.ITEMS_TEXTURE; }

	@Override
	public int getItemStackLimit() { return 1; }

	@Override
	public boolean isDamageable() { return true; }

	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack) { return 32; }

	public EnumAction getItemUseAction(ItemStack par1ItemStack) { return EnumAction.bow; }

}
