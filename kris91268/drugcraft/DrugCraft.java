package kris91268.drugcraft;

import kris91268.drugcraft.drugs.Blocks.BlockDryingTable;
import kris91268.drugcraft.drugs.Blocks.BlockTobacco;
import kris91268.drugcraft.drugs.Items.ItemCigarette;
import kris91268.drugcraft.drugs.Items.ItemTobaccoDried;
import kris91268.drugcraft.drugs.Tileentity.TileEntityDryingTable;
import kris91268.drugcraft.utils.ModGuiHandler;
import kris91268.drugcraft.utils.SmokingEffects;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 
 * @author Arbiter
 *
 */
@Mod(modid=DrugCraft.MODID, name=DrugCraft.NAME, version=DrugCraft.VERSION, useMetadata=true)
public class DrugCraft
{
	public static final String MODID = "drugcraft", NAME = "kris91268's Drugcraft Mod", VERSION = "1.0";
	@Instance(DrugCraft.MODID)
	public static DrugCraft instance;
	@SidedProxy(clientSide="kris91268.drugcraft.ClientProxy", serverSide="kris91268.drugcraft.CommonProxy")
	public static CommonProxy proxy;

	public static final CreativeTabs tabDrugCraft = new CreativeTabs("tabDrugCraft") {
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return DrugCraft.regularCigarette;
		}
	};
	public static final Block tobaccoCrop = new BlockTobacco();
	public static final Block dryingTable = new BlockDryingTable();
	public static final Item tobaccoSeeds = new ItemSeeds(tobaccoCrop, Blocks.farmland).setUnlocalizedName("tobaccoSeeds").setTextureName("drugcraft:tobaccoSeeds").setCreativeTab(DrugCraft.tabDrugCraft);
	public static final Item tobaccoLeaves = new Item().setUnlocalizedName("tobaccoLeaves").setTextureName("drugcraft:tobaccoLeaves").setCreativeTab(DrugCraft.tabDrugCraft);
	public static final Item tobaccoLeavesDried = new ItemTobaccoDried();
	public static final Item cigaretteButt = new Item().setUnlocalizedName("cigaretteButt").setTextureName("drugcraft:cigaretteButt").setCreativeTab(DrugCraft.tabDrugCraft);
	public static final Item filter = new Item().setUnlocalizedName("filter").setTextureName("drugcraft:filter").setCreativeTab(DrugCraft.tabDrugCraft);
	public static final Item regularCigarette = new ItemCigarette(SmokingEffects.PLAIN, "regularCigarette");
	public static final Item sugarCigarette = new ItemCigarette(SmokingEffects.SUGAR, "sugarCigarette");
	public static final Item pumpkinCigarette = new ItemCigarette(SmokingEffects.PUMPKIN, "pumpkinCigarette");
	public static final Item melonCigarette = new ItemCigarette(SmokingEffects.MELON, "melonCigarette");
	public static final Item chocolateCigarette = new ItemCigarette(SmokingEffects.CHOCOLATE, "chocolateCigarette");
	public static final Item appleCigarette = new ItemCigarette(SmokingEffects.APPLE, "appleCigarette");
	public static final Item carrotCigarette = new ItemCigarette(SmokingEffects.CARROT, "carrotCigarette");
	public static final Item netherCigarette = new ItemCigarette(SmokingEffects.NETHER_WART, "netherCigarette");
	public static final Item goldCigarette = new ItemCigarette(SmokingEffects.GOLD_APPLE, "goldCigarette");
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		GameRegistry.registerBlock(tobaccoCrop, "tobacco");
		GameRegistry.registerBlock(dryingTable, "dryingTable");
		GameRegistry.registerItem(tobaccoSeeds, "tobaccoSeeds");
		GameRegistry.registerItem(tobaccoLeaves, "tobaccoLeaves");
		GameRegistry.registerItem(tobaccoLeavesDried, "tobaccoLeavesDried");
		GameRegistry.registerItem(regularCigarette, "regularCigarette");
		GameRegistry.registerItem(sugarCigarette, "sugarCigarette");
		GameRegistry.registerItem(pumpkinCigarette, "pumpkinCigarette");
		GameRegistry.registerItem(melonCigarette, "melonCigarette");
		GameRegistry.registerItem(chocolateCigarette, "chocolateCigarette");
		GameRegistry.registerItem(appleCigarette, "appleCigarette");
		GameRegistry.registerItem(carrotCigarette, "carrotCigarette");
		GameRegistry.registerItem(netherCigarette, "netherCigarette");
		GameRegistry.registerItem(goldCigarette, "goldCigarette");
		GameRegistry.registerItem(cigaretteButt, "cigaretteButt");
		GameRegistry.registerItem(filter, "filter");
		GameRegistry.registerTileEntity(TileEntityDryingTable.class, "tileEntityDryingTable");
	}
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.registerRenderers();
		initRecipes();
		NetworkRegistry.INSTANCE.registerGuiHandler(DrugCraft.instance, new ModGuiHandler());
	}
	private void initRecipes()
	{
		GameRegistry.addRecipe(new ItemStack(dryingTable), new Object[] {"XXX", "Y Y", "Y Y", 'X', Blocks.log, 'Y', Items.stick});
		GameRegistry.addShapelessRecipe(new ItemStack(tobaccoLeavesDried, 1, 1), new Object[] {new ItemStack(tobaccoLeavesDried, 1, 0), Items.sugar});
		GameRegistry.addShapelessRecipe(new ItemStack(tobaccoLeavesDried, 1, 2), new Object[] {new ItemStack(tobaccoLeavesDried, 1, 0), Blocks.pumpkin});
		GameRegistry.addShapelessRecipe(new ItemStack(tobaccoLeavesDried, 1, 3), new Object[] {new ItemStack(tobaccoLeavesDried, 1, 0), Items.melon});
		GameRegistry.addShapelessRecipe(new ItemStack(tobaccoLeavesDried, 1, 4), new Object[] {new ItemStack(tobaccoLeavesDried, 1, 0), new ItemStack(Items.dye, 1, 3)});
		GameRegistry.addShapelessRecipe(new ItemStack(tobaccoLeavesDried, 1, 5), new Object[] {new ItemStack(tobaccoLeavesDried, 1, 0), Items.apple});
		GameRegistry.addShapelessRecipe(new ItemStack(tobaccoLeavesDried, 1, 6), new Object[] {new ItemStack(tobaccoLeavesDried, 1, 0), Items.carrot});
		GameRegistry.addShapelessRecipe(new ItemStack(tobaccoLeavesDried, 1, 7), new Object[] {new ItemStack(tobaccoLeavesDried, 1, 0), Items.nether_wart});
		GameRegistry.addShapelessRecipe(new ItemStack(tobaccoLeavesDried, 1, 8), new Object[] {new ItemStack(tobaccoLeavesDried, 1, 0), Items.golden_apple});
		GameRegistry.addRecipe(new ItemStack(regularCigarette), new Object[] {"x", "y", "z", 'x', Items.paper, 'y', new ItemStack(tobaccoLeavesDried, 1, 0), 'z', filter});
		GameRegistry.addRecipe(new ItemStack(sugarCigarette), new Object[] {"x", "y", "z", 'x', Items.paper, 'y', new ItemStack(tobaccoLeavesDried, 1, 1), 'z', filter});
		GameRegistry.addRecipe(new ItemStack(pumpkinCigarette), new Object[] {"x", "y", "z", 'x', Items.paper, 'y', new ItemStack(tobaccoLeavesDried, 1, 2), 'z', filter});
		GameRegistry.addRecipe(new ItemStack(melonCigarette), new Object[] {"x", "y", "z", 'x', Items.paper, 'y', new ItemStack(tobaccoLeavesDried, 1, 3), 'z', filter});
		GameRegistry.addRecipe(new ItemStack(chocolateCigarette), new Object[] {"x", "y", "z", 'x', Items.paper, 'y', new ItemStack(tobaccoLeavesDried, 1, 4), 'z', filter});
		GameRegistry.addRecipe(new ItemStack(appleCigarette), new Object[] {"x", "y", "z", 'x', Items.paper, 'y', new ItemStack(tobaccoLeavesDried, 1, 5), 'z', filter});
		GameRegistry.addRecipe(new ItemStack(carrotCigarette), new Object[] {"x", "y", "z", 'x', Items.paper, 'y', new ItemStack(tobaccoLeavesDried, 1, 6), 'z', filter});
		GameRegistry.addRecipe(new ItemStack(netherCigarette), new Object[] {"x", "y", "z", 'x', Items.paper, 'y', new ItemStack(tobaccoLeavesDried, 1, 7), 'z', filter});
		GameRegistry.addRecipe(new ItemStack(goldCigarette), new Object[] {"x", "y", "z", 'x', Items.paper, 'y', new ItemStack(tobaccoLeavesDried, 1, 8), 'z', filter});
	}
}