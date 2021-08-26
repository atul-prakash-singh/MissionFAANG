package com.mission.faang.heap;

import java.util.*;

public class SkylineProblem {

    private static class BuildingPoint implements Comparable<BuildingPoint> {
        int x;
        boolean isStart;
        int height;

        @Override
        public int compareTo(BuildingPoint o) {
            //first compare by x.
            //If they are same then use this logic
            //if two starts are compared then higher height building should be picked first
            //if two ends are compared then lower height building should be picked first
            //if one start and end is compared then start should appear before end
            if (this.x != o.x) {
                return this.x - o.x;
            } else {
                return (this.isStart ? -this.height : this.height) - (o.isStart ? -o.height : o.height);
            }
        }
    }

    private static List<List<Integer>> getSkyline(int[][] buildings) {

        //for all start and end of building put them into List of BuildingPoint
        BuildingPoint[] buildingPoints = new BuildingPoint[buildings.length * 2];
        int index = 0;
        for (int[] building : buildings) {
            buildingPoints[index] = new BuildingPoint();
            buildingPoints[index].x = building[0];
            buildingPoints[index].isStart = true;
            buildingPoints[index].height = building[2];

            buildingPoints[index + 1] = new BuildingPoint();
            buildingPoints[index + 1].x = building[1];
            buildingPoints[index + 1].isStart = false;
            buildingPoints[index + 1].height = building[2];
            index += 2;
        }
        Arrays.sort(buildingPoints);

        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        queue.add(0);
        int prevMaxHeight = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (BuildingPoint buildingPoint : buildingPoints) {
            //if it is start of building then add the height to map. If height already exists then increment the value
            if (buildingPoint.isStart) {
                  queue.add(buildingPoint.height);
            } else { //if it is ending of building then decrement or remove the height from map.
                 queue.remove(buildingPoint.height);
            }
            //peek the current height after addition or removal of building x.
            int currentMaxHeight = queue.peek();
            //if height changes from previous height then this building x becomes critical x. So add it to the result.
            if (prevMaxHeight != currentMaxHeight) {
                List<Integer> listBuildingPoints = new ArrayList<>();
                listBuildingPoints.add(buildingPoint.x);
                listBuildingPoints.add(currentMaxHeight);
                result.add(listBuildingPoints);
                prevMaxHeight = currentMaxHeight;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] buildings = {
                {1, 3, 4},
                {3, 4, 4},
                {2, 6, 2},
                {8, 11, 4},
                {7, 9, 3},
                {10, 11, 2}
        };
        List<List<Integer>> criticalPoints = getSkyline(buildings);
        criticalPoints.forEach(cp -> System.out.println(cp.get(0) + " " + cp.get(1)));
    }
}
