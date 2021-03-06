package com.awesomesauce.minecraft.forge.overhaul2

import com.awesomesauce.minecraft.forge.core.lib.TAwesomeSauceMod
import com.awesomesauce.minecraft.forge.core.lib.util.ItemUtil
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.{FMLInitializationEvent, FMLPostInitializationEvent, FMLPreInitializationEvent}
import cpw.mods.fml.common.eventhandler.SubscribeEvent
import cpw.mods.fml.common.{Mod, ModMetadata}
import net.minecraft.init.{Blocks, Items}
import net.minecraft.item.{Item, ItemSpade, ItemStack}
import net.minecraftforge.common.util.EnumHelper
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent
import net.minecraftforge.oredict.{OreDictionary, ShapedOreRecipe, ShapelessOreRecipe}

/**
 * Created by gjgfuj on 11/1/14.
 */
@Mod(modid = "OverhaulCraft2", modLanguage = "scala")
object OverhaulCraft2 extends TAwesomeSauceMod {

  @Mod.Metadata("OverhaulCraft2")
  var metadata: ModMetadata = null
  var flintHammer: Item = null
  var flintAxe: Item = null
  var dirtBall: Item = null
  var woodCutterTool: Item = null
  var woodHammerTool: Item = null

  @EventHandler
  def aspri(e: FMLPreInitializationEvent) = super.awesomesaucepreinit(e)

  @EventHandler
  def asi(e: FMLInitializationEvent) = super.awesomesauceinit(e)

  @EventHandler
  def aspoi(e: FMLPostInitializationEvent) = super.awesomesaucepostinit(e)

  def getModID = "OverhaulCraft2"

  def getModName = "Overhaul Craft 2"

  def getTextureDomain = "overhaulcraft2"

  def getTabIconItem = () => dirtBall

  def init() = {
    OreDictionary.registerOre("dirt", Blocks.dirt)
    OreDictionary.registerOre("flint", Items.flint)
    OreDictionary.registerOre("stick", Items.stick)
    OreDictionary.registerOre("overhaul2.cutter", Items.flint)
    OreDictionary.registerOre("overhaul2.hammer", flintHammer)
    ItemUtil.addRecipe(this, new ShapedOreRecipe(new ItemStack(Blocks.dirt), "xxx", "xxx", "xxx", Character.valueOf('x'), "dirtBall"))
    ItemUtil.addRecipe(this, new ShapelessOreRecipe(new ItemStack(Items.flint), "dirtBall", "dirtBall", "dirtBall", "dirtBall"))
    ItemUtil.addRecipe(this, new ShapelessOreRecipe(new ItemStack(flintAxe), "flint", "overhaul2.cutter"))
    ItemUtil.addRecipe(this, new ShapelessOreRecipe(new ItemStack(flintHammer), "flint", "flint", "stick"))
  }

  def preInit() = {
    MaterialHack.setWoodMaterial
    val materialFlint = EnumHelper.addToolMaterial("BASICFLINT", 1, 4, 0.5F, 4, 0)
    flintAxe = ItemUtil.makeItem(this, "flintAxe", new ItemOverhaulAxe(materialFlint))
    flintHammer = ItemUtil.makeItem(this, "flintHammer")
    dirtBall = ItemUtil.makeItem(this, "dirtBall", true)
  }

  @SubscribeEvent
  def blockBreak(e: HarvestDropsEvent) = {
    if (e.block == Blocks.dirt) {
      if (e.harvester.getHeldItem == null || (!e.harvester.getHeldItem.getItem.isInstanceOf[ItemSpade])) {
        e.drops.clear()
        e.drops.add(new ItemStack(dirtBall, 2))
      }
    }
  }

  def postInit() = {

  }
}
