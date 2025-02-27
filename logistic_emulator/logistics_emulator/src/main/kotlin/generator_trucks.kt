import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlin.random.Random

fun generateRandomTruck(id: Int): Any {
    val capacity = listOf(200, 300, 400).random()
    return when (Random.nextInt(4)) {
        0 -> TruckForFood(id, capacity, List(Random.nextInt(1, 10)) {
            Product(FoodCategory.entries.random(), Random.nextInt(50, 300), Random.nextLong(10, 50), Random.nextLong(5, 25))
        })
        1 -> TruckForSmallGoods(id, capacity, List(Random.nextInt(1, 10)) {
            SmallGoods(SmallCategory.entries.random(), Random.nextInt(50, 300), Random.nextLong(10, 50), Random.nextLong(5, 25))
        })
        2 -> TruckForMediumGoods(id, capacity, List(Random.nextInt(1, 10)) {
            MediumGoods(MediumCategory.entries.random(), Random.nextInt(300, 1000), Random.nextLong(30, 100), Random.nextLong(15, 50))
        })
        else -> TruckForLargeGoods(id, capacity, List(Random.nextInt(1, 10)) {
            LargeGoods(LargeCategory.entries.random(), Random.nextInt(1000, 5000), Random.nextLong(100, 500), Random.nextLong(50, 200))
        })
    }
}

suspend fun generateTrucks() = coroutineScope {
    var id = 1
    while (isActive) {
        val truck = generateRandomTruck(id++)
        when (truck) {
            is TruckForFood -> {
                println("The new truck ${truck.id} has arrived (food)")
                unloadQueue1.send(truck)
            }
            is TruckForSmallGoods -> {
                println("The new truck ${truck.id} has arrived (small goods)")
                unloadQueue2.send(truck)
            }
            is TruckForMediumGoods -> {
                println("The new truck ${truck.id} has arrived (medium goods)")
                unloadQueue3.send(truck)
            }
            is TruckForLargeGoods -> {
                println("The new truck ${truck.id} has arrived (large goods)")
                unloadQueue4.send(truck)
            }
        }
        delay(3000)
    }
}