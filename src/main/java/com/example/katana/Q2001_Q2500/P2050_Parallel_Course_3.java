package com.example.katana.Q2001_Q2500;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

public class P2050_Parallel_Course_3 {
    public int minimumTime(int n, int[][] relations, int[] time) {
        Map<Integer, Integer> adjacentList = new HashMap<>();
        Map<Integer, List<Integer>> preRequisites = new HashMap<>();
        Queue<Integer> courses = new ArrayDeque<>();

        int[] preRequisiteCompletion = new int[n];
        Arrays.fill(preRequisiteCompletion, Integer.MIN_VALUE);

        if (relations.length == 0) {
            return Arrays.stream(time).reduce(0, Integer::max);
        }

        for (int i = 0; i < relations.length; i++) {
            int[] dependency = relations[i];
            int prevCourse = dependency[0];
            int nextCourse = dependency[1];

            if (!adjacentList.containsKey(prevCourse)) {
                adjacentList.put(prevCourse, 0);
                preRequisites.put(prevCourse, new ArrayList<>());
            }

            if (!adjacentList.containsKey(nextCourse)) {
                adjacentList.put(nextCourse, 0);
                preRequisites.put(nextCourse, new ArrayList<>());
            }
            adjacentList.put(nextCourse, adjacentList.get(nextCourse) + 1);
            preRequisites.get(prevCourse).add(nextCourse);
        }

        adjacentList.entrySet().stream().filter(entry -> entry.getValue() == 0)
                .forEach(entry -> courses.add(entry.getKey()));

        int minimumTime = 0;
        while (!courses.isEmpty()) {
            int currentCourse = courses.poll();
            int currentCourseTime = time[currentCourse - 1];
            int currentCoursePrerequsiteTime = preRequisiteCompletion[currentCourse - 1];
            List<Integer> currentNextCourses = preRequisites.get(currentCourse);

            if (currentNextCourses.isEmpty()) {
                // Else All By Itself
                minimumTime += currentCourseTime;
            } else {
                // Add Prerequisite If there Dependencies
                for (int i = 0; i < currentNextCourses.size(); i++) {
                    int nextCourse = currentNextCourses.get(i);
                    adjacentList.put(nextCourse, adjacentList.get(nextCourse) - 1);
                    if (adjacentList.get(nextCourse) == 0) {
                        courses.offer(nextCourse);
                    }
                    // Save the Maximum Duration For the Course To Complete
                    int currentCourseEndTime = currentCoursePrerequsiteTime == Integer.MIN_VALUE ? currentCourseTime
                            : currentCourseTime + currentCoursePrerequsiteTime;
                    preRequisiteCompletion[nextCourse - 1] = Math.max(preRequisiteCompletion[nextCourse - 1],
                            currentCourseEndTime);
                }
            }

            String currentCoursePreDescription = currentNextCourses.stream().map(String::valueOf)
                    .collect(Collectors.joining(","));
            System.out.println(String.format(
                    "Course %s: Complete Duration: %d | Course Start Period: %d | Affects: %s",
                    currentCourse, currentCourseTime, preRequisiteCompletion[currentCourse - 1],
                    currentCoursePreDescription));
        }

        // TODO: Loop coursesCompletion and add to completionDuration if not
        // Integer.MIN_VALUE
        int courseMinimumCompletionTime = Integer.MIN_VALUE;

        for (int i = 0; i < preRequisiteCompletion.length; i++) {
            int courseStartTime = preRequisiteCompletion[i] == Integer.MIN_VALUE ? 0 : preRequisiteCompletion[i];
            courseMinimumCompletionTime = Math.max(courseMinimumCompletionTime, courseStartTime + time[i]);
        }

        return courseMinimumCompletionTime;
    }
}
