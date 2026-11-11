fun main() {
    val player = Player()
    player.name = "Вы"
    player.shots = 10
    player.hits = 7
    player.shipsLeft = 15

    val enemy = Player()
    enemy.name = "Компьютер"
    enemy.shots = 5
    enemy.hits = 2
    enemy.shipsLeft = 0

    println("--- Проверка игроков ---")
    println(player.status())
    println("Жив ли игрок? ${player.isAlive()}")
    println()
    println(enemy.status())
    println("Жив ли компьютер? ${enemy.isAlive()}")

    println("\n--- Сценарий из задания ---")
    val testPlayer = Player()
    testPlayer.name = "Вы"

    testPlayer.registerShot(true)
    testPlayer.registerShot(false)
    testPlayer.registerShot(true)

    println(testPlayer.status())

    testPlayer.takeDamage()
    testPlayer.takeDamage()
    println(testPlayer.shipsLeft)
    println(testPlayer.isAlive())

    testPlayer.reset()
    println(testPlayer.status())
}