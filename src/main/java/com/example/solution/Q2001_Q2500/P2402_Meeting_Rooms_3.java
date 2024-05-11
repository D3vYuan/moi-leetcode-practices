package com.example.solution.Q2001_Q2500;

import java.util.Arrays;
import java.util.PriorityQueue;

public class P2402_Meeting_Rooms_3 {

    /**
     * Reference:
     * https://leetcode.com/problems/meeting-rooms-iii/editorial/?envType=daily-question&envId=2024-05-05
     * 
     * 
     * Strategy:
     * 1. Create two priority queues, unused_rooms and used_rooms, representing the
     * available and currently used rooms, respectively. Create an array
     * meeting_count of size n to keep track of the number of meetings held in each
     * room.
     * 2. Use the heapify function to convert unused_rooms into a min heap, ensuring
     * the room with the lowest number is at the top.
     * 3. Iterate through the meetings sorted by start times.
     * 4. While there are used rooms (used_rooms) and the first room's meeting has
     * already concluded (meeting end time <= current meeting start time), remove
     * the room from used_rooms and add it back to unused_rooms.
     * 5. Check if there are available rooms (unused_rooms). If available, pop the
     * room with the lowest number from unused_rooms and allocate the meeting to
     * that room. Update used_rooms with the meeting end time and the room number.
     * 6. If no available rooms, pop the room with the earliest availability time
     * from used_rooms. Adjust the availability time for the room to accommodate the
     * delayed meeting. Update used_rooms with the adjusted availability time and
     * room number.
     * 7. Increment the meeting count for the allocated room.
     * 8. After processing all meetings, return the index of the room with the
     * maximum meeting count using. If there are multiple rooms with the same
     * maximum meeting count, return the room with the lowest index.
     */
    public int mostBooked(int n, int[][] meetings) {
        int[] meetingCount = new int[n];
        PriorityQueue<long[]> usedRooms = new PriorityQueue<long[]>(
                (a, b) -> a[0] != b[0] ? Long.compare(a[0], b[0]) : Long.compare(a[1], b[1]));
        PriorityQueue<Integer> unusedRooms = new PriorityQueue<Integer>();

        for (int i = 0; i < n; i++) {
            unusedRooms.offer(i);
        }

        Arrays.sort(meetings, (a, b) -> a[0] != b[0] ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1]));

        for (int[] meeting : meetings) {
            int start = meeting[0], end = meeting[1];

            while (!usedRooms.isEmpty() && usedRooms.peek()[0] <= start) {
                int room = (int) usedRooms.poll()[1];
                unusedRooms.offer(room);
            }

            if (!unusedRooms.isEmpty()) {
                int room = unusedRooms.poll();
                usedRooms.offer(new long[] { end, room });
                meetingCount[room]++;
            } else {
                long roomAvailabilityTime = usedRooms.peek()[0];
                int room = (int) usedRooms.poll()[1];
                usedRooms.offer(new long[] { roomAvailabilityTime + end - start, room });
                meetingCount[room]++;
            }
        }

        int maxMeetingCount = 0, maxMeetingCountRoom = 0;
        for (int i = 0; i < n; i++) {
            if (meetingCount[i] > maxMeetingCount) {
                maxMeetingCount = meetingCount[i];
                maxMeetingCountRoom = i;
            }
        }

        return maxMeetingCountRoom;
    }
}
