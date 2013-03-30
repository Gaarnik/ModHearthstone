package gaarnik.hearthstone.common;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import net.minecraft.potion.Potion;
import gaarnik.hearthstone.common.item.HearthstoneItems;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = "ModHearthstone")
@NetworkMod(clientSideRequired = true, serverSideRequired = true)
public class ModHearthstone {
	// *******************************************************************
	public static boolean DEBUG = true;

	// *******************************************************************
	@SidedProxy(clientSide = "gaarnik.hearthstone.client.HearthstoneClientProxy", serverSide = "gaarnik.hearthstone.server.HearthstoneServerProxy")
	public static HearthstoneCommonProxy proxy;

	@Instance("ModHearthstone")
	public static ModHearthstone instance = new ModHearthstone();

	// *******************************************************************
	public static Potion heathstonePotion;

	// *******************************************************************
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		Potion[] potionTypes = null;

		for (Field f : Potion.class.getDeclaredFields()) {
			f.setAccessible(true);

			try {
				if (f.getName().equals("potionTypes") || f.getName().equals("field_76425_a")) {
					Field modfield = Field.class.getDeclaredField("modifiers");
					modfield.setAccessible(true);
					modfield.setInt(f, f.getModifiers() & ~Modifier.FINAL);

					potionTypes = (Potion[])f.get(null);
					final Potion[] newPotionTypes = new Potion[256];
					System.arraycopy(potionTypes, 0, newPotionTypes, 0, potionTypes.length);
					f.set(null, newPotionTypes);
				}
			}
			catch (Exception e) {
				System.err.println("Severe error, please report this to the mod author:");
				System.err.println(e);
			}
		}
	}

	@Init
	public void init(FMLInitializationEvent event) {
		proxy.initTextures();

		HearthstoneItems.init();
		
		heathstonePotion = new HearthstonePotion();
	}

	@PostInit
	public void postInit(FMLPostInitializationEvent event) {

	}

	@ServerStarting
	public void serverStarting(FMLServerStartingEvent event) {

	}

	// *******************************************************************

}
