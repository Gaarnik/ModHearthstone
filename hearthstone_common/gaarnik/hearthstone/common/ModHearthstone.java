package gaarnik.hearthstone.common;

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
	@SidedProxy(clientSide = "gaarnik.hearthstone.client.HearthstoneClientProxy", serverSide = "gaarnik.hearthstone.server.HearthstoneServerProxy")
	public static HearthstoneCommonProxy proxy;
	
	@Instance("ModHearthstone")
	public static ModHearthstone instance = new ModHearthstone();

	// *******************************************************************

	// *******************************************************************
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		//charger le fichier de config
	}
	
	@Init
	public void init(FMLInitializationEvent event) {
		proxy.initTextures();
		
		HearthstoneItems.init();
	}
	
	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
		
	}
	
	@ServerStarting
	public void serverStarting(FMLServerStartingEvent event) {
		
	}

	// *******************************************************************

}
