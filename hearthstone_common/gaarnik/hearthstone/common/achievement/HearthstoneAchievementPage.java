package gaarnik.hearthstone.common.achievement;

import gaarnik.hearthstone.common.item.HearthstoneItems;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.registry.LanguageRegistry;

import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class HearthstoneAchievementPage extends AchievementPage {
	// *******************************************************************
	private static final String NAME = "Hearthstone";
	
	// *******************************************************************
	ArrayList<Achievement> achievements;

	public Achievement craft;
	public Achievement use;

	// *******************************************************************
	public HearthstoneAchievementPage() {
		super(NAME);
		
		this.initAchievements();
		
		AchievementPage.registerAchievementPage(this);
	}

	// *******************************************************************

	// *******************************************************************

	// *******************************************************************
	private void initAchievements() {
		this.achievements = new ArrayList<Achievement>();
		
		craft = new Achievement(2001, "Hearthstone.craft", 0, 0, new ItemStack(HearthstoneItems.hearthstoneItem, 1), null);
		LanguageRegistry.instance().addStringLocalization("achievement.Hearthstone.craft", "en_US", "A new Travel !");
		LanguageRegistry.instance().addStringLocalization("achievement.Hearthstone.craft.desc", "en_US", "Craft the Heathstone");
		craft.registerAchievement();
		this.achievements.add(craft);

		use = new Achievement(2002, "Hearthstone.use", -2, -2, new ItemStack(HearthstoneItems.hearthstoneItem, 1), craft);
		LanguageRegistry.instance().addStringLocalization("achievement.Hearthstone.use", "en_US", "Motion sickness !");
		LanguageRegistry.instance().addStringLocalization("achievement.Hearthstone.use.desc", "en_US", "Use the Heathstone");
		use.registerAchievement();
		this.achievements.add(use);
	}

	// *******************************************************************
	@Override
	public List<Achievement> getAchievements() { return this.achievements; }

}
