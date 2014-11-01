package com.awesomesauce.minecraft.forge.overhaul2

import com.awesomesauce.minecraft.forge.core.components.AwesomeSauceComponents
import com.awesomesauce.minecraft.forge.core.lib.TAwesomeSauceMod
import com.awesomesauce.minecraft.forge.core.lib.util.ItemUtil
import cpw.mods.fml.common.{ModMetadata, Mod}
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.{FMLPostInitializationEvent, FMLInitializationEvent, FMLPreInitializationEvent}
import net.minecraft.block.material.Material
import net.minecraft.init.{Blocks, Items}
import net.minecraft.item.{ItemAxe, ItemTool, ItemStack, Item}
import net.minecraftforge.common.util.EnumHelper
import net.minecraftforge.oredict.{OreDictionary, ShapelessOreRecipe, ShapedOreRecipe}

/**
 * Created by gjgfuj on 11/1/14.
 */
@Mod(modid="OverhaulCraft2", modLanguage="scala")
object OverhaulCraft2 extends TAwesomeSauceMod {

  @EventHandler
  def aspri(e: FMLPreInitializationEvent) = super.awesomesaucepreinit(e)

  @EventHandler
  def asi(e: FMLInitializationEvent) = super.awesomesauceinit(e)

  @EventHandler
  def aspoi(e: FMLPostInitializationEvent) = super.awesomesaucepostinit(e)

  def getModID = "OverhaulCraft2"

  def getModName = "Overhaul Craft 2"

  def getTextureDomain = "overhaulcraft2"

  def getTabIconItem = () => AwesomeSauceComponents.ingotPureAwesomeite

  @Mod.Metadata("OverhaulCraft2")
  var metadata: ModMetadata = null

  var flintHammer : Item = null
  var flintAxe : Item = null
  var cutterTool : Item = null
  var hammerTool : Item = null
  def init() = {
    OreDictionary.registerOre("dirt", Blocks.dirt)
    OreDictionary.registerOre("flint", Items.flint)
    OreDictionary.registerOre("overhaul2.cutter", Items.flint)
    OreDictionary.registerOre("overhaul2.hammer", flintHammer)
    ItemUtil.addRecipe(this, new ShapelessOreRecipe(new ItemStack(Items.flint), "dirt", "dirt", "dirt", "dirt"))
    ItemUtil.addRecipe(this, new ShapelessOreRecipe(new ItemStack(flintAxe), "flint", "overhaul2.cutter"))
    ItemUtil.addRecipe(this, new ShapelessOreRecipe(new ItemStack(flintHammer), "flint", "flint", "stick"))

  }
  def preInit() = {
    MaterialHack.setWoodMaterial
    val materialFlint = EnumHelper.addToolMaterial("BASICFLINT", 1, 4, 0.5F, 4, 0, 1)
    flintAxe = ItemUtil.makeItem(this, "flintAxe", new ItemOverhaulAxe(materialFlint))
    flintHammer = ItemUtil.makeItem(this, "flintHammer")
  }
  def postInit() = {

  }
}
