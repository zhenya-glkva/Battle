fun printField(player: Player, hideShips: Boolean) {
    print("   ")
    for (c in 0..9) print("$c ")
    println()
    for (r in 0..9) {
        print("$r  ")
        for (c in 0..9) {
            val state = player.field()[r][c]
            val shown = if (hideShips && state == CellState.SHIP) CellState.EMPTY else state
            print("${shown.toChar()} ")
        }
        println()
    }
}

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
        for (c in 0..9) {
            val state = enemy.field()[r][c]
            val shown = if (state == CellState.SHIP) CellState.EMPTY else state
            print("${shown.toChar()} ")
        }
        println()
    }
}

fun printRules() {
    println("===================================")
    println("       МОРСКОЙ БОЙ - ПРАВИЛА")
    println("===================================")
    println("Поле 10x10. Координаты: сначала строка (0-9), потом столбец (0-9).")
    println()
    println("Корабли, которые нужно расставить:")
    println("  1 корабль x 4 палубы")
    println("  2 корабля x 3 палубы")
    println("  3 корабля x 2 палубы")
    println("  4 корабля x 1 палуба")
    println("Итого: 10 кораблей, 20 палуб.")
    println()
    println("Правила расстановки:")
    println("  • Корабли не могут выходить за границы поля.")
    println("  • Корабли не могут пересекаться.")
    println("  • Корабли не могут касаться друг друга даже углами.")
    println()
    println("Как вводить корабли:")
    println("  1. Введите координаты НАЧАЛЬНОЙ палубы: row col")
    println("     Например: 3 4 - строка 3, столбец 4.")
    println("  2. Введите тип корабля: 1, 2, 3 или 4 (число палуб).")
    println("  3. Введите направление:")
    println("     h - горизонтально вправо (→)")
    println("     v - вертикально вниз (↓)")
    println()
    println("Пример: корабль из 3 палуб, начало (2, 5), горизонтально.")
    println("  Ввод: 2 5, затем 3, затем h.")
    println("  Займёт клетки: (2,5), (2,6), (2,7).")
    println()
    println("После каждого корабля поле перерисовывается.")
    println("===================================")
    println()
}