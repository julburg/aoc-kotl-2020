package de.aoc.day11


fun day11_a(inputAsString: String): Int {

    var plane = parseSeatMap(inputAsString)
    while (true) {
        val newPlane = Plane(HashMap(plane.seats))
        for (seat in plane.seats.keys) {
            val adjacentSeatNumbers = plane.getAdjacentSeatNumbers(seat)
            newPlane.seats[seat] = plane.nextSeatState(seat, adjacentSeatNumbers, 4)
        }
        if (newPlane == plane) {
            break;
        }
        plane = newPlane
    }

    return plane.seats.filter { plane.isSeatOccupied(it.key) }.size
}


fun day11_b(inputAsString: String): Int {

    var plane = parseSeatMap(inputAsString)
    while (true) {
        printSeats(plane)
        println()
        val newPlane = Plane(HashMap(plane.seats))
        for (seat in plane.seats.keys) {
            val visibleSeatNumbers = plane.getVisibleSeats(seat)
            newPlane.seats[seat] = plane.nextSeatState(seat, visibleSeatNumbers, 5)
        }
        if (newPlane == plane) {
            break;
        }
        plane = newPlane
    }

    return plane.seats.filter { plane.isSeatOccupied(it.key) }.size
}

fun printSeats(plane: Plane) {
    val maxRow = plane.seats.keys.map { it.row }.maxByOrNull { it }!!
    val maxColumn = plane.seats.keys.map { it.column }.maxByOrNull { it }!!

    for (row in 0..maxRow) {
        for (column in 0..maxColumn) {
            if (plane.seats.containsKey(SeatNumber(row, column))) {
                if (plane.seats.getValue(SeatNumber(row, column))) {
                    print("#")
                } else {
                    print("L")
                }
            }
            print(".")
        }
        println()
    }
}

fun parseSeatMap(inputAsString: String): Plane {
    val seats = hashMapOf<SeatNumber, Boolean>()
    val rows = inputAsString.split("\n")
    for ((rowIndex, row) in rows.withIndex()) {
        val columns = row.split("").filter { it.isNotBlank() }
        for ((columnIndex, seat) in columns.withIndex()) {
            if (seat == "L") {
                seats.put(SeatNumber(rowIndex, columnIndex), false)
            }
            if (seat == "#") {
                seats.put(SeatNumber(rowIndex, columnIndex), true)
            }
        }
    }
    return Plane(seats)
}


data class Plane(val seats: HashMap<SeatNumber, Boolean>) {

    fun getAdjacentSeatNumbers(seat: SeatNumber): List<SeatNumber> {
        val adjacentSeats = ArrayList<SeatNumber>()
        // direct neighbours
        adjacentSeats.add(SeatNumber(seat.row, seat.column + 1))
        adjacentSeats.add(SeatNumber(seat.row, seat.column - 1))
        adjacentSeats.add(SeatNumber(seat.row + 1, seat.column))
        adjacentSeats.add(SeatNumber(seat.row - 1, seat.column))

        //diagonal neighbours
        adjacentSeats.add(SeatNumber(seat.row - 1, seat.column + 1))
        adjacentSeats.add(SeatNumber(seat.row - 1, seat.column - 1))
        adjacentSeats.add(SeatNumber(seat.row + 1, seat.column + 1))
        adjacentSeats.add(SeatNumber(seat.row + 1, seat.column - 1))

        return adjacentSeats;
    }

    private fun seatIsOccupied(seat: SeatNumber): Boolean {
        return seats.getValue(seat)
    }

    fun getVisibleSeats(seat: SeatNumber): List<SeatNumber> {
        val visibleSeatNumbers = ArrayList<SeatNumber>()

        //Seats above
        addSeatMaxRow(visibleSeatNumbers, seat, 0.0)
        addSeatMaxRow(visibleSeatNumbers, seat, 45.0)
        addSeatMaxRow(visibleSeatNumbers, seat, 315.0)

        //Seats below
        addSeatMinRow(visibleSeatNumbers, seat, 180.0)
        addSeatMinRow(visibleSeatNumbers, seat, 225.0)
        addSeatMinRow(visibleSeatNumbers, seat, 135.0)

        addSeatMaxColumn(visibleSeatNumbers, seat, 270.0)
        addSeatMinColumn(visibleSeatNumbers, seat, 90.0)

        return visibleSeatNumbers
    }

    private fun addSeatMaxColumn(visibleSeatNumbers: ArrayList<SeatNumber>, seat: SeatNumber, degree: Double) {
        val seat0Degrees = seats.keys.filter { it != seat && calcRotationAngleInDegrees(seat, it) == degree }.maxByOrNull { it.column }
        if (seat0Degrees != null) {
            visibleSeatNumbers.add(seat0Degrees)
        }
    }

    private fun addSeatMinColumn(visibleSeatNumbers: ArrayList<SeatNumber>, seat: SeatNumber, degree: Double) {
        val seat0Degrees = seats.keys.filter { it != seat && calcRotationAngleInDegrees(seat, it) == degree }.minByOrNull { it.column }
        if (seat0Degrees != null) {
            visibleSeatNumbers.add(seat0Degrees)
        }
    }


    private fun addSeatMaxRow(visibleSeatNumbers: ArrayList<SeatNumber>, seat: SeatNumber, degree: Double) {
        val seat0Degrees = seats.keys.filter { it != seat && calcRotationAngleInDegrees(seat, it) == degree }.maxByOrNull { it.row }
        if (seat0Degrees != null) {
            visibleSeatNumbers.add(seat0Degrees)
        }
    }

    private fun addSeatMinRow(visibleSeatNumbers: ArrayList<SeatNumber>, seat: SeatNumber, degree: Double) {
        val seat0Degrees = seats.keys.filter { it != seat && calcRotationAngleInDegrees(seat, it) == degree }.minByOrNull { it.row }
        if (seat0Degrees != null) {
            visibleSeatNumbers.add(seat0Degrees)
        }
    }


    //copied from stackoverflow
    fun calcRotationAngleInDegrees(centerSeat: SeatNumber, targetSeat: SeatNumber): Double {
        var theta = Math.atan2((targetSeat.row - centerSeat.row).toDouble(), (targetSeat.column - centerSeat.column).toDouble())
        theta += Math.PI / 2.0
        var angle = Math.toDegrees(theta)
        if (angle < 0) {
            angle += 360.0
        }

        return angle
    }

    fun isSeatOccupied(seatNumber: SeatNumber): Boolean {
        if (seats.containsKey(seatNumber)) {
            return seats.get(seatNumber)!!
        }
        return false
    }

    fun nextSeatState(seat: SeatNumber, adjacentSeatNumbers: List<SeatNumber>, seatCount: Int): Boolean {
        val occupiedAdjacentSeats = adjacentSeatNumbers.filter { isSeatOccupied(it) }

        if (seatIsOccupied(seat) && occupiedAdjacentSeats.size >= seatCount) {
            return false
        }

        if (!seatIsOccupied(seat) && occupiedAdjacentSeats.isEmpty()) {
            return true
        }
        return isSeatOccupied(seat)
    }

}

data class SeatNumber(val row: Int, val column: Int)