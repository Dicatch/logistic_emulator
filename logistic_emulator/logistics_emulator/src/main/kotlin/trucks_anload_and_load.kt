import kotlinx.coroutines.*
import java.util.concurrent.Semaphore

const val UNLOAD_PORTS = 4
const val LOAD_PORTS = 6

val availableUnloadPorts = Semaphore(UNLOAD_PORTS)

suspend fun unloadTruck(truck: TruckForFood) {
    availableUnloadPorts.acquire()
    println("The truck ${truck.id} (food) at unloading...")
    for (product in truck.cargo) {
        delay(product.unloadTime)
        (warehouse["food"] as MutableList<Product>).add(product)
        println("${product.type} (weight ${product.weight}) unloaded into the warehouse (food)")
    }
    loadQueue1.send(truck)
    println("The truck ${truck.id} drove away empty")
    availableUnloadPorts.release()
}

suspend fun unloadTruck(truck: TruckForSmallGoods) {
    availableUnloadPorts.acquire()
    println("The truck ${truck.id} (small goods) at unloading...")
    for (item in truck.cargo) {
        delay(item.unloadTime)
        (warehouse["small goods"] as MutableList<SmallGoods>).add(item)
        println("${item.type} (weight ${item.weight}) unloaded into the warehouse (small goods)")
    }
    loadQueue2.send(truck)
    println("The truck ${truck.id} drove away empty")
    availableUnloadPorts.release()
}

suspend fun unloadTruck(truck: TruckForMediumGoods) {
    availableUnloadPorts.acquire()
    println("The truck ${truck.id} (medium goods) at unloading...")
    for (item in truck.cargo) {
        delay(item.unloadTime)
        (warehouse["medium goods"] as MutableList<MediumGoods>).add(item)
        println("${item.type} (weight ${item.weight}) unloaded into the warehouse (medium goods)")
    }
    loadQueue3.send(truck)
    println("The truck ${truck.id} drove away empty")
    availableUnloadPorts.release()
}

suspend fun unloadTruck(truck: TruckForLargeGoods) {
    availableUnloadPorts.acquire()
    println("The truck ${truck.id} (large goods) at unloading...")
    for (item in truck.cargo) {
        delay(item.unloadTime)
        (warehouse["large goods"] as MutableList<LargeGoods>).add(item)
        println("${item.type} (weight ${item.weight}) unloaded into the warehouse (large goods)")
    }
    loadQueue4.send(truck)
    println("The truck ${truck.id} drove away empty")
    availableUnloadPorts.release() // Освобождаем порт
}

suspend fun loadTruck(truck: TruckForFood) {
    println("Truck ${truck.id} (food) at loading...")
    val storage = warehouse["food"] as MutableList<Product>
    var remainingCapacity = truck.capacity
    val loadedCargo = mutableListOf<Product>()

    while (remainingCapacity > 0) {
        if (storage.isEmpty()) {
            println("The truck ${truck.id} waiting for the goods...")

            var waited = 0
            while (storage.isEmpty() && waited < 5000) {
                delay(500)
                waited += 500
            }

            if (storage.isEmpty()) {
                println("Грузовик ${truck.id} did not wait for the goods and left empty!")
                return
            }
        }

        val product = storage.removeFirst()
        if (remainingCapacity >= product.weight) {
            delay(product.loadTime)
            loadedCargo.add(product)
            remainingCapacity -= product.weight
        }
    }

    println("The truck ${truck.id} left with ${loadedCargo.size} goods (food)")
}

suspend fun loadTruck(truck: TruckForSmallGoods) {
    println("The truck ${truck.id} (small goods) at loading...")
    val storage = warehouse["small goods"] as MutableList<SmallGoods>
    var remainingCapacity = truck.capacity
    val loadedCargo = mutableListOf<SmallGoods>()

    while (remainingCapacity > 0) {
        if (storage.isEmpty()) {
            println("The truck ${truck.id} waiting for the goods...")

            var waited = 0
            while (storage.isEmpty() && waited < 5000) { // Ждем максимум 5 секунд
                delay(500)
                waited += 500
            }

            if (storage.isEmpty()) {
                println("The truck ${truck.id} did not wait for goods and left empty!")
                return
            }
        }

        val product = storage.removeFirst()
        if (remainingCapacity >= product.weight) {
            delay(product.loadTime)
            loadedCargo.add(product)
            remainingCapacity -= product.weight
        }
    }

    println("The truck ${truck.id} left with ${loadedCargo.size} goods (small goods)")
}

suspend fun loadTruck(truck: TruckForMediumGoods) {
    println("The truck ${truck.id} (medium goods) at loading...")
    val storage = warehouse["medium goods"] as MutableList<MediumGoods>
    var remainingCapacity = truck.capacity
    val loadedCargo = mutableListOf<MediumGoods>()

    while (remainingCapacity > 0) {
        while (storage.isEmpty()) {
            println("The truck ${truck.id} waiting for the goods...")
            delay(1000)
        }

        val product = storage.removeFirst()
        if (remainingCapacity >= product.weight) {
            delay(product.loadTime)
            loadedCargo.add(product)
            remainingCapacity -= product.weight
        }

        if (remainingCapacity > 0 && storage.isEmpty()) {
            println("The truck ${truck.id} waiting for the additional goods...")
            delay(3000)
        }
    }
    println("The truck ${truck.id} left with ${loadedCargo.size} goods (medium goods)")
}

suspend fun loadTruck(truck: TruckForLargeGoods) {
    println("The truck ${truck.id} (large goods) at loading...")
    val storage = warehouse["large goods"] as MutableList<LargeGoods>
    var remainingCapacity = truck.capacity
    val loadedCargo = mutableListOf<LargeGoods>()

    while (remainingCapacity > 0) {
        while (storage.isEmpty()) {
            println("The truck ${truck.id} waiting for the goods...")
            delay(1000)
        }

        val product = storage.removeFirst()
        if (remainingCapacity >= product.weight) {
            delay(product.loadTime)
            loadedCargo.add(product)
            remainingCapacity -= product.weight
        }

        if (remainingCapacity > 0 && storage.isEmpty()) {
            println("The truck ${truck.id} waiting for additional goods..")
            delay(3000)
        }
    }
    println("The truck ${truck.id} left with ${loadedCargo.size} goods (large goods)")
}