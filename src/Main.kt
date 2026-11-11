fun createEmptyField(size: Int = 10): Array<CharArray> {
    return Array(size) { CharArray(size) { '.' } }
}

fun printField(field: Array<CharArray>, title: String, showShips: Boolean = true, debug: Boolean = false) {
    if (debug) {
        println("=== $title (debug) ===")
        val size = field.size
        val border = "+" + "---+".repeat(size)
        println("   $border")
        for (r in field.indices) {
            print("$r |")
            for (c in field.indices) {
                val cell = if (!showShips && field[r][c] == '#') '.' else field[r][c]
                print(" $cell |")
            }
            println()
            println("   $border")
        }
    } else {
        println("=== $title ===")
        print("   ")
        for (c in field.indices) print("$c ")
        println()
        for (r in field.indices) {
            print("$r  ")
            for (c in field.indices) {
                val cell = if (!showShips && field[r][c] == '#') '.' else field[r][c]
                print("$cell ")
            }
            println()
        }
    }
}

fun printBothFields(player: Array<CharArray>, enemy: Array<CharArray>, showEnemyShips: Boolean = false) {
    if (player.size != enemy.size) {
        println("Ошибка: поля должны быть одного размера! (${player.size}x${player.size} и ${enemy.size}x${enemy.size})")
        return
    }

    println("=== Ваше поле ===          === Поле противника ===")

    print("   ")
    for (c in player.indices) print("$c ")
    print("       ")
    print("   ")
    for (c in enemy.indices) print("$c ")
    println()

    for (r in player.indices) {
        print("$r  ")
        for (c in player.indices) print("${player[r][c]} ")

        print("       ")

        print("$r  ")
        for (c in enemy.indices) {
            val cell = if (!showEnemyShips && enemy[r][c] == '#') '.' else enemy[r][c]
            print("$cell ")
        }
        println()
    }
}

fun printFields(fields: Array<Array<CharArray>>, titles: Array<String>, showShips: BooleanArray) {
    if (fields.size != titles.size || fields.size != showShips.size) {
        println("Ошибка: количество полей, заголовков и флагов должно совпадать!")
        return
    }

    val size = fields[0].size
    for (f in fields) {
        if (f.size != size) {
            println("Ошибка: все поля должны быть одного размера!")
            return
        }
    }

    for (i in fields.indices) {
        print("=== ${titles[i]} ===" + " ".repeat(5))
    }
    println()

    for (i in fields.indices) {
        print("   ")
        for (c in 0 until size) print("$c ")
        print("   ")
    }
    println()

    for (r in 0 until size) {
        for (i in fields.indices) {
            print("$r  ")
            for (c in 0 until size) {
                val cell = if (!showShips[i] && fields[i][r][c] == '#') '.' else fields[i][r][c]
                print("$cell ")
            }
            print("   ")
        }
        println()
    }
}

fun main() {
    println("--- Часть 1: Скрытие кораблей противника ---")
    val playerField = createEmptyField()
    playerField[3][2] = '#'
    playerField[3][3] = '#'
    playerField[3][4] = '#'
    playerField[3][5] = '#'

    val enemyField = createEmptyField()
    enemyField[5][7] = '#'
    enemyField[6][7] = '#'
    enemyField[7][7] = '#'
    enemyField[5][5] = 'X'
    enemyField[5][6] = 'O'

    printField(playerField, "Ваше поле")
    println()
    printField(enemyField, "Поле противника", showShips = false)
    println()

    println("--- Часть 2: Гибкий размер ---")
    val smallField = createEmptyField(8)
    printField(smallField, "Маленькое поле 8×8")
    println()
    val bigField = createEmptyField(12)
    printField(bigField, "Большое поле 12×12")
    println()

    println("--- Часть 3: Два поля рядом ---")
    printBothFields(playerField, enemyField)
    println()

    println("--- Часть 4: Режим отладки ---")
    printField(playerField, "Ваше поле", debug = true)
    println()

    println("--- Часть 5: Универсальная печать ---")
    val thirdField = createEmptyField(10)
    val fields = arrayOf(playerField, enemyField, thirdField)
    val titles = arrayOf("Ваше поле", "Противник", "Третье")
    val show = booleanArrayOf(true, false, false)
    printFields(fields, titles, show)
}