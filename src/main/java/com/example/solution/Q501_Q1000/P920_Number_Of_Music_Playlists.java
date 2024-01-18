package com.example.solution.Q501_Q1000;

import java.util.Arrays;

public class P920_Number_Of_Music_Playlists {
    /**
     * Reference:
     * https://leetcode.com/problems/number-of-music-playlists/editorial/?envType=daily-question&envId=2023-11-13
     * 
     * Explanation:
     * 1. table dp[i][j] to represent the number of possible playlists of length i
     * containing exactly j unique songs. Our goal is to calculate dp[goal][n],
     * which represents the number of ways we can make a playlist of length goal
     * using exactly n unique songs
     * 2. dp[0][0]=1. This represents that there's exactly one way to create a
     * playlist of length 0 with 0 unique songs, which is essentially an empty
     * playlist.
     * 3. For all i<j, dp[i][j]=0. This makes sense because we can't form a playlist
     * of length i with j unique songs when i<j. There just aren't enough slots in
     * the playlist to accommodate all the unique songs.
     * 4. If we add a song that we haven't played yet to the playlist, the playlist
     * length increases by 1 (from i−1 to i), and the number of unique songs also
     * increases by 1 (from j−1 to j). Therefore, a playlist of length i with j
     * unique songs can be formed by adding new songs to each playlist of length i−1
     * with j−1 unique songs
     * 4. At this point, we have j−1 unique songs in the playlist. Since there are n
     * unique songs in total, the number of new songs we can add to the playlist is
     * n−(j−1)=n−j+1.
     * 5. Since we have n−j+1 choices of the new song, the number of new playlists
     * we can create by adding a new song is dp[i−1][j−1]⋅(n−j+1). Hence, we add
     * this to dp[i][j].
     * 6. If we replay an old song, the playlist length increases by 1 (from i−1 to
     * i), but the number of unique songs remains the same (still j). Therefore, the
     * number of playlists of length i with j unique songs can be increased by
     * replaying an old song in every playlist of length i−1 with j unique songs.
     * 7. At this point, we have j unique songs in the playlist, so we can choose
     * any of these j songs. However, due to the constraint that we can't replay a
     * song unless k other songs have been played, we can't choose from the last k
     * played songs.
     * 8. So, if j>k, the number of old songs we can replay is j−k.
     * 9. Since we have j−k choices of the old song to replay, the number of new
     * playlists we can create by replaying an old song is dp[i−1][j]⋅(j−k). Hence,
     * if j>k, we add this to dp[i][j].
     * 
     */

    int MOD = 1_000_000_007;
    long[][] dp;

    /**
     * Strategy:
     * 1. Initialize a two-dimensional dynamic programming table, dp[goal+1][n+1],
     * with −1. This table will be used to store the number of possible playlists of
     * length i using exactly j unique songs.
     * 2. Implement a recursive function, numberOfPlaylists(i,j), to calculate the
     * number of playlists of length i with j unique songs.
     * a. If i is equal to 0 and j is equal to 0, return 1. This represents an empty
     * playlist with no unique songs.
     * b. If either i or j is equal to 0, return 0. This represents an impossible
     * scenario where the length of the playlist or the number of unique songs is
     * zero.
     * c. If dp[i][j] is not equal to −1, return dp[i][j]. This indicates that the
     * solution for this subproblem has already been computed and can be directly
     * retrieved from the dynamic programming table.
     * d. Calculate the number of new playlists created by adding a new song to the
     * playlist. This can be done by recursively calling numberOfPlaylists(i−1,j−1)
     * and multiplying it by (n−j+1), which represents the number of new songs
     * available to choose from. Assign dp[i][j] to this value.
     * e. Calculate the number of new playlists created by replaying an old song.
     * This can be done by recursively calling numberOfPlaylists(i−1,j) and
     * multiplying it by (j−k) if j>k. This accounts for the restriction that a song
     * can only be replayed if k other songs have been played before it. If j>k, add
     * this value to dp[i][j].
     * f. Return dp[i][j].
     * 3. Finally, call the numberOfPlaylists(goal,n) function to obtain the total
     * number of possible playlists of length goal using exactly n unique songs.
     * This will be the final answer to the problem.
     * 
     */
    private long solve(int goal, int remainingUniqueSongs, int songRestInterval, int totalSongs) {
        // Base Cases
        if (goal == 0 && remainingUniqueSongs == 0) {
            return 1;
        }

        if (goal == 0 || remainingUniqueSongs == 0) {
            return 0;
        }

        if (dp[goal][remainingUniqueSongs] != -1) {
            return dp[goal][remainingUniqueSongs];
        }

        // DP transition: add a new song or replay an old one
        long addNewSong = (solve(goal - 1, remainingUniqueSongs - 1, songRestInterval, totalSongs)
                * (totalSongs - remainingUniqueSongs + 1)) % MOD;
        dp[goal][remainingUniqueSongs] = addNewSong;

        if (remainingUniqueSongs > songRestInterval) {
            long replayOldSong = (solve(goal - 1, remainingUniqueSongs, songRestInterval, totalSongs)
                    * (remainingUniqueSongs - songRestInterval)) % MOD;
            dp[goal][remainingUniqueSongs] += replayOldSong;
            dp[goal][remainingUniqueSongs] %= MOD;
        }

        return dp[goal][remainingUniqueSongs];
    }

    private int runTopDown(int songs, int goal, int songRestInterval) {
        dp = new long[goal + 1][songs + 1];
        for (long[] row : dp) {
            Arrays.fill(row, -1L);
        }
        return (int) (solve(goal, songs, songRestInterval, songs));
    }

    /**
     * Strategy:
     * 1. Initialize a two-dimensional dynamic programming table,
     * dp[goal+1][n+1], with zeros.
     * 2. Set dp[0][0] to 1, as there is exactly one way to
     * have a playlist of length 0 with 0 unique songs.
     * 3. Iterate i from 1 to goal. (This represents the current
     * length of the playlist).
     * a. Within this loop, iterate j from 1 to min⁡(i,n).
     * (This represents the number of unique songs in the playlist).
     * a1. Calculate the number of new playlists created by adding a new song:
     * dp[i−1][j−1]⋅(n−j+1). Add this value to dp[i][j]
     * under modulo 10^9 + 7.
     * a2. If j>k, calculate the number of new playlists created by
     * replaying an old song: dp[i−1][j]⋅(j−k). Add this value to dp[i][j] under
     * modulo 10^9 + 7.
     * 4. Return the value of dp[goal][n].
     */
    private int runBottomUp(int songs, int goal, int songRestInterval) {
        dp = new long[goal + 1][songs + 1];
        dp[0][0] = 1;

        for (int i = 0; i <= goal; i++) {
            for (int j = 1; j <= Math.min(i, songs); j++) {
                // The i-th song is a new song
                dp[i][j] = dp[i - 1][j - 1] * (songs - j + 1) % MOD;
                // The i-th song is a song we have played before
                if (j > songRestInterval) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j] * (j - songRestInterval)) % MOD;
                }
            }
        }

        return (int) dp[goal][songs];
    }

    public int numMusicPlaylists(int n, int goal, int k) {
        return runTopDown(n, goal, k);
        // return runBottomUp(n, goal, k);
    }
}
