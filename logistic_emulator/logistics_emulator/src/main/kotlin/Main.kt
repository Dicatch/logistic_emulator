import kotlinx.coroutines.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking


fun main() = runBlocking {
    val jobs = listOf(
        launch { generateTrucks() },
        launch { runUnloadPorts() },
        launch { runLoadPorts() }
    )

    delay(60000)

    unloadQueue1.close()
    unloadQueue2.close()
    unloadQueue3.close()
    unloadQueue4.close()
    loadQueue1.close()
    loadQueue2.close()
    loadQueue3.close()
    loadQueue4.close()

    jobs.forEach { it.cancelAndJoin() }
    println("The program is complete")
}