package de.aoc.day11


fun day11_a(inputAsString: String): Int {

    var plane = parseSeatMap(inputAsString)
    while (true) {
        val newPlane = Plane(HashMap(plane.seats))
        for (seat in plane.seats.keys) {
            newPlane.seats[seat] = plane.nextSeatState(seat)
        }
        if (newPlane == plane) {
            break;
        }
        plane = newPlane
    }

    return plane.seats.filter { plane.isSeatOccupied(it.key) }.size
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

    fun isSeatOccupied(seatNumber: SeatNumber): Boolean {
        if (seats.containsKey(seatNumber)) {
            return seats.get(seatNumber)!!
        }
        return false
    }

    fun nextSeatState(seat: SeatNumber): Boolean {
        val adjacentSeatNumbers = getAdjacentSeatNumbers(seat)
        val occupiedAdjacentSeats = adjacentSeatNumbers.filter { isSeatOccupied(it) }

        if (seatIsOccupied(seat) && occupiedAdjacentSeats.size >= 4) {
            return false
        }

        if (!seatIsOccupied(seat) && occupiedAdjacentSeats.isEmpty()) {
            return true
        }
        return isSeatOccupied(seat)
    }

}

data class SeatNumber(val row: Int, val column: Int)