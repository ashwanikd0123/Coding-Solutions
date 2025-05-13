import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MeetingRoomThree {
    class Solution {
        public int mostBooked(int n, int[][] meetings) {
            Arrays.sort(meetings, Comparator.comparingInt(a -> a[0]));

            PriorityQueue<Integer> availableRooms = new PriorityQueue<>();

            int[] endTime = new int[n];
            PriorityQueue<Integer> inUseRooms = new PriorityQueue<>((a, b) -> {
                if (endTime[a] == endTime[b]) {
                    return a - b;
                }
                return endTime[a] - endTime[b];
            });

            int[] meetCount = new int[n];

            for (int i = 0; i < n; i++) {
                availableRooms.add(i);
            }

            for (int[] m : meetings) {
                while (!inUseRooms.isEmpty() && endTime[inUseRooms.peek()] <= m[0]) {
                    availableRooms.add(inUseRooms.poll());
                }

                int room;
                if (availableRooms.isEmpty()) {
                    room = inUseRooms.poll();
                    endTime[room] += m[1] - m[0];
                } else {
                    room = availableRooms.poll();
                    endTime[room] = m[1];
                }
                meetCount[room]++;

                inUseRooms.add(room);
            }

            int maxRoom = 0;
            int count = meetCount[maxRoom];
            for (int i = 0; i < n; i++) {
                if (meetCount[i] > count) {
                    count = meetCount[i];
                    maxRoom = i;
                }
            }

            return maxRoom;
        }
    }
}
