package com.awesomesauce.minecraft.forge.overhaul2

import net.minecraft.block.material.Material

/**
 * A massive hack to get the wood material to work
 */
object MaterialHack {
  def setWoodMaterial = {
    for (m <- Material.wood.getClass.getMethods) {
      println(m.toString)
    }
    //val method = Material.wood.getClass.getMethod("")
    //method.setAccessible(true)
    //method.invoke(Material.wood)
  }
}
