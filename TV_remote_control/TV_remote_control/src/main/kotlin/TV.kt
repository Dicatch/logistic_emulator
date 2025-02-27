

class Tv (val brand: String, val model: String, val diagonal: Int,) {

    var listChannels = Channels.randomList()
    private var isTvOn: Boolean = false
    private var currentVolume = 20
    private var channelsSize = Channels.getSize()
    private var currentChannel: Int = 1
    private var previousChannel: Int = 1
    private var channelsIndices = Channels.getIndices()
    private var channels = Channels.getAllChannels()

    fun tvTurnOn() {
        isTvOn = true
        println("Телевизор включен")
    }

    fun tvTurnOff() {
        isTvOn = false
        println("Телевизор выключен")
    }

    fun increaseVolume(){
        currentVolume = minOf(currentVolume + 1, Companion.MAX_VOLUME)
        println("Увеличние громкости. Текущая громкость  $currentVolume")
    }

    fun decreaseVolume(){
        currentVolume = maxOf(currentVolume - 1, 0)
        println("Уменьшение громкости. Текущая громкость  $currentVolume")
    }

    fun channelUp() {
        if (!isTvOn) {
            tvTurnOn()
            currentChannel = previousChannel
        } else {
            currentChannel = (currentChannel + 1) % channelsSize
        }
        println("Переключение вверх на канал ${channelsIndices[currentChannel]}")
        previousChannel = currentChannel
    }


    fun channelDown() {
        if (!isTvOn) {
            tvTurnOn()
            currentChannel = previousChannel
        } else {
            currentChannel = if (currentChannel - 1 < 0) {
                channelsSize - 1
            } else {
                currentChannel - 1
            }
        }
        println("Переключение вниз на канал ${channelsIndices[currentChannel]}")
        previousChannel = currentChannel
    }

    fun switchTv(channelIndex: Int) {
        if (channelIndex in 0..<channelsSize) {
            if (channelsIndices.contains(channelIndex)) {
                if (!isTvOn) {
                    isTvOn = true
                }
                previousChannel = currentChannel
                currentChannel = channelIndex
                println("Переключение на канал ${channelIndex + 1}")
            } else {
                println("Ошибка. Канал $channelIndex не найден")
            }
        } else {
            println("Ошибка. Некорректный номер канала.")
        }

    }

    companion object{
        const val MAX_VOLUME = 100
    }

}