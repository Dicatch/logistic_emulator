import kotlin.random.Random
import kotlin.random.nextInt

object Channels {
    private var allChannels = listOf(
        "1 - Nickelodeon, ",
        "2 - Animal Planet, ",
        "3 - NBC, ",
        "4 - BBC World News, ",
        "5 - MTV Live, ",
        "6 - BabyTV, ",
        "7 - CNN, ",
        "8 - ABC, ",
        "9 - ABC News, ",
        "10 - RT, ",
        "11 - Discovery, ",
        "12 - Euronews, ",
    )

    fun getAllChannels(): List<String> {
        val channelsAll = allChannels.toMutableList()
        return channelsAll
    }

    fun randomList(): MutableList<String> {
        val randomListChannels = allChannels.take(Random.nextInt(1..allChannels.size)).toMutableList()
        randomListChannels.shuffle()
        return randomListChannels
    }

    fun getIndices(): List<Int> {
        val indices = allChannels.indices.toList()
        return indices
    }

    fun getSize(): Int {
        return  allChannels.size
    }
}