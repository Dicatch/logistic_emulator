import kotlin.concurrent.thread

fun main() {
    val tvLg = Tv(brand = "LG", model = "OLED S", diagonal = 65)
    val tvSamsung = Tv(brand = "Samsung", model = "Smart TV", diagonal = 86)
    val tvSony = Tv(brand = "Sony", model = "Bravia XR", diagonal = 48)

    fun printInfo(tv: Tv) {
        println("Brand: ${tv.brand}")
        println("Model: ${tv.model}")
        println("Diagonal: ${tv.diagonal}")
    }

    println("Распечатка информации о телевизорах: ")
    println()
    printInfo(tvLg)
    println()
    printInfo(tvSamsung)
    println()
    printInfo(tvSony)
    println()

    println("\n\nlg channels: ")
    tvLg.listChannels.forEach { print(it ) }
    println("\n\nSamsung channels: ")
    tvSamsung.listChannels.forEach { print(it ) }
    println("\n\nSony channels: ")
    tvSony.listChannels.forEach { print(it ) }
    println()

    println("\n" +
            "\nТестовый запуск программы для телевизора LG:\n")
    tvLg.tvTurnOn()

    println("\n" +
            "\nПроверка увеличения громкости:\n")
    for (i in 1..9)
        tvLg.increaseVolume()

    println("\n" +
            "\nПроверка уменьшения громкости:\n")
    for (i in 1..9)
        tvLg.decreaseVolume()

    println("\n" +
            "\nПрокрутка каналов вверх:\n")
    for (i in 1..9){tvLg.channelUp()}

    println("\n" +
            "\nПрокрутка каналов вниз:\n")
    for (i in 1..9){tvLg.channelDown()}

    println("\n\nПроверка переключения на заданный канал:\n")

    tvLg.switchTv(2)
    tvLg.switchTv(3)
    tvLg.switchTv(6)
    tvLg.switchTv(1)
    tvLg.switchTv(4)
    tvLg.switchTv(5)
    tvLg.switchTv(70)
    println()

    tvLg.tvTurnOff()
    println()



    println("\n" +
            "\nТестовый запуск программы для телевизора Sony:\n")
    println()

    tvSony.tvTurnOn()

    println("\n" +
            "\nПроверка увеличения громкости:\n")
    for (i in 1..11)
        tvSony.increaseVolume()

    println("\n" +
            "\nПроверка уменьшения громкости:\n")
    for (i in 1..15)
        tvSony.decreaseVolume()

    println("\n" +
            "\nПрокрутка каналов вверх:\n")
    for (i in 1..7){tvSony.channelUp()}

    println("\n" +
            "\nПрокрутка каналов вниз:\n")
    for (i in 1..7){tvSony.channelDown()}

    println("\n\nПроверка переключения на заданный канал:\n")

    tvSony.switchTv(4)
    tvSony.switchTv(6)
    tvSony.switchTv(8)
    tvSony.switchTv(1)
    tvSony.switchTv(5)
    tvSony.switchTv(2)
    tvSony.switchTv(50)
    println()

    tvSony.tvTurnOff()
    println()



    println("\n" +
            "\nТестовый запуск программы для телевизора Samsung:\n")
    tvSamsung.tvTurnOn()

    println("\n" +
            "\nПроверка увеличения громкости:\n")
    for (i in 1..17)
        tvSamsung.increaseVolume()

    println("\n" +
            "\nПроверка уменьшения громкости:\n")
    for (i in 1..20)
        tvSamsung.decreaseVolume()

    println("\n" +
            "\nПрокрутка каналов вверх:\n")
    for (i in 1..8){tvSamsung.channelUp()}

    println("\n" +
            "\nПрокрутка каналов вниз:\n")
    for (i in 1..8){tvSamsung.channelDown()}

    println("\n\nПроверка переключения на заданный канал:\n")

    thread {
        tvSamsung.switchTv(6)
        tvSamsung.switchTv(1)
        Thread.sleep(1000)
        tvSamsung.switchTv(7)
        tvSamsung.switchTv(1)
        tvSamsung.switchTv(2)
        tvSamsung.switchTv(3)
        tvSamsung.switchTv(30)
    }

    println()
    thread {
        Thread.sleep(2000)
        tvSamsung.tvTurnOff()}
    println()
}