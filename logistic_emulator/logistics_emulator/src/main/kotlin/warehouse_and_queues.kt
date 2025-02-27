import kotlinx.coroutines.channels.Channel

val warehouse = mutableMapOf(
    "food" to mutableListOf<Product>(),
    "small goods" to mutableListOf<SmallGoods>(),
    "medium goods" to mutableListOf<MediumGoods>(),
    "large goods" to mutableListOf<LargeGoods>(),
)

val unloadQueue1 = Channel<TruckForFood>(Channel.UNLIMITED)
val unloadQueue2 = Channel<TruckForSmallGoods>(Channel.UNLIMITED)
val unloadQueue3 = Channel<TruckForMediumGoods>(Channel.UNLIMITED)
val unloadQueue4 = Channel<TruckForLargeGoods>(Channel.UNLIMITED)

val loadQueue1 = Channel<TruckForFood>(Channel.UNLIMITED)
val loadQueue2 = Channel<TruckForSmallGoods>(Channel.UNLIMITED)
val loadQueue3 = Channel<TruckForMediumGoods>(Channel.UNLIMITED)
val loadQueue4 = Channel<TruckForLargeGoods>(Channel.UNLIMITED)