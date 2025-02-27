data class Product(val type: FoodCategory, val weight: Int, val unloadTime: Long, val loadTime: Long)
data class SmallGoods(val type: SmallCategory, val weight: Int, val unloadTime: Long, val loadTime: Long)
data class MediumGoods(val type: MediumCategory, val weight: Int, val unloadTime: Long, val loadTime: Long)
data class LargeGoods(val type: LargeCategory, val weight: Int, val unloadTime: Long, val loadTime: Long)