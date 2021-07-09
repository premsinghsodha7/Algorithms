/*
You are processing plane seat reservations. The plane has N rows of seats, numbered from
1 to N. There are ten seats in each row(labelled from A to K, with letter I omitted), as shown
in the figure below:
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

//Java
public static  int solution(int N,String S){
    Map<Integer, String> seatMap = new HashMap<>();
    int maxUnReservedSeatFamily = 0;
    if (S == null || S.isEmpty()) {
        return N * 3;
    }
    //Z for the Aisles
    for (int i = 1; i <= N; i++) {
        seatMap.put(i, new String("ABCZDEFGZHJK"));
    }
    
    String[] occupiedSeatsArray = S.split(" ");
    
    for (String occupied : occupiedSeatsArray) {
        char rowNo = occupied.charAt(0);
        char seatLabel = occupied.charAt(1);
        int row = Character.getNumericValue(rowNo);
        String currentSeatLabel = seatMap.get(row);

        String newSeatLabel = currentSeatLabel != null ? currentSeatLabel.replace(seatLabel, 'X') : null;
        seatMap.put(row, newSeatLabel);
    }

    for (Map.Entry<Integer, String> entry : seatMap.entrySet()) {
        String occupiedSeatMap = entry.getValue();
        
        if (occupiedSeatMap != null && occupiedSeatMap.contains("X")) {
            String[] tempSeatLabel = entry.getValue().split("X");
            for (String combinedSeats : tempSeatLabel) {
                if (combinedSeats.length() <= 3) 
                    continue;
                
                if (combinedSeats.length() >= 3 && !combinedSeats.contains("Z"))
                    maxUnReservedSeatFamily++;
                else if (combinedSeats.length() >= 3 && combinedSeats.contains("Z")) {
                    String[] temp = combinedSeats.split("Z");
                    for (String t1 : temp) 
                        if (t1.length() >= 3)
                            maxUnReservedSeatFamily++;
                }

            }
        } else 
            maxUnReservedSeatFamily += 3;
    }
    return maxUnReservedSeatFamily;
}
