class Player {
    var name: String = ""
    var field: Array<CharArray> = Array(10) { CharArray(10) { '.' } }
    var shots: Int = 0
    var hits: Int = 0
    var shipsLeft: Int = 20

    fun printField() {
        println("=== Поле игрока $name ===")
        print("   ")
        for (c in 0..9) print("$c ")
        println()
        for (r in 0..9) {
            print("$r  ")
            for (c in 0..9) print("${field[r][c]} ")
            println()
        }
    }

    fun printStats() {
        println(status())
    }

    fun isAlive(): Boolean {
        return shipsLeft > 0
    }

    fun accuracy(): Double {
        if (shots == 0) return 0.0
        return hits * 100.0 / shots
    }

    fun status(): String {
        return "Игрок $name: палуб осталось $shipsLeft, выстрелов $shots, попаданий $hits, точность ${String.format("%.1f", accuracy())}%"
    }

    fun takeDamage(): Boolean {
        shipsLeft--
        return isAlive()
    }

    fun registerShot(hit: Boolean) {
        shots++
        if (hit) {
            hits++
        }
    }

    fun reset() {
        shots = 0
        hits = 0
        shipsLeft = 20
        for (r in field.indices) {
            for (c in field.indices) {
                field[r][c] = '.'
            }
        }
    }
}