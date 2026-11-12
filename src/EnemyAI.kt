import kotlin.random.Random

enum class Direction {
    LEFT,
    RIGHT,
    UP,
    DOWN
}

class EnemyAI(private val target: Player) {
    private val hits = mutableListOf<Pair<Int, Int>>()
    private var hunting = false
    private val triedDirections = mutableSetOf<Direction>()
    private var lastDirection: Direction? = null

    // Домашка: счетчик потопленных
    private var sunkCount = 0

    fun chooseShot(): Pair<Int, Int> {
        return if (hunting) {
            chooseHuntShot()
        } else {
            chooseSearchShot()
        }
    }

    private fun chooseSearchShot(): Pair<Int, Int> {
        var r: Int
        var c: Int
        var attempts = 0
        do {
            r = Random.nextInt(0, 10)
            c = Random.nextInt(0, 10)
            attempts++
            if (attempts > 1000) return Pair(0, 0)
        } while (!target.canShootAt(r, c))
        return Pair(r, c)
    }

    private fun chooseHuntShot(): Pair<Int, Int> {
        if (hits.isEmpty()) {
            resetHunt()
            return chooseSearchShot()
        }

        if (hits.size == 1) {
            val r = hits[0].first
            val c = hits[0].second
            val neighbors = listOf(
                Pair(r - 1, c),
                Pair(r + 1, c),
                Pair(r, c - 1),
                Pair(r, c + 1)
            )
            for (p in neighbors) {
                if (target.canShootAt(p.first, p.second)) {
                    return p
                }
            }
            resetHunt()
            return chooseSearchShot()
        }

        val r1 = hits[0].first
        val c1 = hits[0].second
        val r2 = hits[1].first
        val c2 = hits[1].second
        val horizontal = (r1 == r2)

        if (horizontal) {
            var minCol = c1
            var maxCol = c1
            val row = r1
            for (pair in hits) {
                val hc = pair.second
                if (hc < minCol) minCol = hc
                if (hc > maxCol) maxCol = hc
            }

            val tryLeftFirst = Direction.RIGHT in triedDirections
            if (tryLeftFirst) {
                val leftCol = minCol - 1
                if (target.canShootAt(row, leftCol)) {
                    lastDirection = Direction.LEFT
                    return Pair(row, leftCol)
                }
                val rightCol = maxCol + 1
                if (target.canShootAt(row, rightCol)) {
                    lastDirection = Direction.RIGHT
                    return Pair(row, rightCol)
                }
            } else {
                val rightCol = maxCol + 1
                if (target.canShootAt(row, rightCol)) {
                    lastDirection = Direction.RIGHT
                    return Pair(row, rightCol)
                }
                val leftCol = minCol - 1
                if (target.canShootAt(row, leftCol)) {
                    lastDirection = Direction.LEFT
                    return Pair(row, leftCol)
                }
            }
        } else {
            var minRow = r1
            var maxRow = r1
            val col = c1
            for (pair in hits) {
                val hr = pair.first
                if (hr < minRow) minRow = hr
                if (hr > maxRow) maxRow = hr
            }

            val tryUpFirst = Direction.DOWN in triedDirections
            if (tryUpFirst) {
                val upRow = minRow - 1
                if (target.canShootAt(upRow, col)) {
                    lastDirection = Direction.UP
                    return Pair(upRow, col)
                }
                val downRow = maxRow + 1
                if (target.canShootAt(downRow, col)) {
                    lastDirection = Direction.DOWN
                    return Pair(downRow, col)
                }
            } else {
                val downRow = maxRow + 1
                if (target.canShootAt(downRow, col)) {
                    lastDirection = Direction.DOWN
                    return Pair(downRow, col)
                }
                val upRow = minRow - 1
                if (target.canShootAt(upRow, col)) {
                    lastDirection = Direction.UP
                    return Pair(upRow, col)
                }
            }
        }

        resetHunt()
        return chooseSearchShot()
    }

    private fun resetHunt() {
        // Домашка: если сбрасываем охоту, значит корабль потоплен
        if (hits.isNotEmpty()) {
            sunkCount++
        }
        hunting = false
        hits.clear()
        triedDirections.clear()
        lastDirection = null
    }

    fun onShotResult(row: Int, col: Int, hit: Boolean) {
        if (hit) {
            hits.add(Pair(row, col))
            hunting = true
            lastDirection = null
        } else {
            if (hunting && lastDirection != null) {
                triedDirections.add(lastDirection!!)
            }
            lastDirection = null
        }
    }

    // Домашка: вывод потопленных
    fun getSunkCount(): Int {
        return sunkCount
    }

    // Домашка: сброс
    fun reset() {
        hunting = false
        hits.clear()
        triedDirections.clear()
        lastDirection = null
        sunkCount = 0
    }
}