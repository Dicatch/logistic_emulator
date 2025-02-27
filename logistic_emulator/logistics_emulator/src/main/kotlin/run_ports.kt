import kotlinx.coroutines.*

suspend fun runUnloadPorts() = coroutineScope {
    repeat(UNLOAD_PORTS) {
        launch { for (truck in unloadQueue1) unloadTruck(truck) }
        launch { for (truck in unloadQueue2) unloadTruck(truck) }
        launch { for (truck in unloadQueue3) unloadTruck(truck) }
        launch { for (truck in unloadQueue4) unloadTruck(truck) }
    }
}

suspend fun runLoadPorts() = coroutineScope {
    repeat(LOAD_PORTS) {
        launch { for (truck in loadQueue1) loadTruck(truck) }
        launch { for (truck in loadQueue2) loadTruck(truck) }
        launch { for (truck in loadQueue3) loadTruck(truck) }
        launch { for (truck in loadQueue4) loadTruck(truck) }
    }
}