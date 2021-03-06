/*
You are processing plane seat reservations. The plane has N rows of seats, numbered from
1 to N. There are ten seats in each row(labelled from A to K, with letter I omitted).

Some of the seats are already reserved. The list of reserved seats is given as a
string S(of length M) containing seat numbers separated by single spaces:
For example, “1A 3C 2B 4G 5A”. The reserved seats can be listed in S in any order.
Your task is to allocate seats for as many three-person families as possible. A
three-person family occupies three seats that are next to each other in the same row. Seats
across aisle (such as 2C and 2D) are not considered to be next to each other. Obviously,
each available seat cannot be taken by more than one family.
Write a function:
Int solution (int N, NSString *S);
That, given the number of rows N and a list of reserved seats as string S, returns the
maximum number of three-person families that can be seated in the remaining unreserved
seats.
Note:
● N is an integer within the range [1...50];
● N is an integer within the range [0...1,909];
● String S consists of valid seat names separated with space
*/

//Kotlin solution 
fun solution(N: Int, S: String?): Int {
    val seatMap: MutableMap<Int, String?> = HashMap()
    var maxUnReservedSeatFamily = 0
    if (S == null || S.isEmpty()) {
        return N * 3
    }
    //Z for the Aisles
    for (i in 1..N) {
        seatMap[i] = "ABCZDEFGZHJK"
    }
    val occupiedSeatsArray = S.split(" ".toRegex()).toTypedArray()
    for (occupied in occupiedSeatsArray) {
        val rowNo = occupied[0]
        val seatLabel = occupied[1]
        val row = Character.getNumericValue(rowNo)
        val currentSeatLabel = seatMap[row]
        val newSeatLabel = currentSeatLabel?.replace(seatLabel, 'X')
        seatMap[row] = newSeatLabel
    }
    for ((_, occupiedSeatMap) in seatMap) {
        if (occupiedSeatMap != null && occupiedSeatMap.contains("X")) {
            val tempSeatLabel = occupiedSeatMap.split("X".toRegex()).toTypedArray()
            for (combinedSeats in tempSeatLabel) {
                if (combinedSeats.length <= 3) continue
                if (combinedSeats.length >= 3 && !combinedSeats.contains("Z")) maxUnReservedSeatFamily++ else if (combinedSeats.length >= 3 && combinedSeats.contains(
                        "Z"
                    )
                ) {
                    val temp = combinedSeats.split("Z".toRegex()).toTypedArray()
                    for (t1 in temp) if (t1.length >= 3) maxUnReservedSeatFamily++
                }
            }
        } else maxUnReservedSeatFamily += 3
    }
    return maxUnReservedSeatFamily
}
