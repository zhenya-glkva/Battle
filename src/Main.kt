fun printBothFields(player: Player, enemy: Player) {
    println("=== Поле игрока ${player.name} ===       === Поле игрока ${enemy.name} ===")
    print("   ")
    for (c in 0..9) print("$c ")
    print("       ")
    print("   ")
    for (c in 0..9) print("$c ")
    println()
    for (r in 0..9) {
        print("$r  ")
        for (c in 0..9) print("${player.field()[r][c].toChar()} ")
        print("       ")
        print("$r  ")
        for (c in 0..9) print("${enemy.field()[r][c].toChar()} ")
        println()
    }
}

enum class GameState {
    PLACING,
    PLAYER_TURN,
    ENEMY_TURN,
    FINISHED
}

fun describe(state: GameState): String {
    return when (state) {
        GameState.PLACING -> "Идет расстановка кораблей"
        GameState.PLAYER_TURN -> "Сейчас ход игрока"
        GameState.ENEMY_TURN -> "Сейчас ход компьютера"
        GameState.FINISHED -> "Игра завершена"
    }
}

fun main() {
    val player = Player("Вы")
    val enemy = Player("Компьютер")

    player.placeShip(3, 2)
    player.placeShip(3, 3)
    player.placeShip(3, 4)
    player.placeShip(3, 5)

    enemy.placeShip(5, 7)
    enemy.placeShip(6, 7)
    enemy.placeShip(7, 7)

    printBothFields(player, enemy)
    println()

    player.registerShot(true)
    player.registerShot(false)
    player.registerShot(true)

    println(player.status())

    println("Выстрелов: ${player.shots}")
    println("Попаданий: ${player.hits}")

    val bigShip = ShipType.BATTLESHIP
    println("Размер линкора: ${bigShip.size}")
    println("Это большой корабль? ${bigShip.isBig()}")

    println(describe(GameState.PLAYER_TURN))

    player.reset()
    println(player.status())
}