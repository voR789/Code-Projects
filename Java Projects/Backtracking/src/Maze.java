import java.util.*;
public class Maze {
    public static void main(String[] args){
        // Goal - Design an algorithm that can accurately navigate a maze and return the path length from the entrance to exit.
        int[][] maze1 = {
                {1,0,0,0,0,0,0,0,0,0,0,0},
                {1,1,0,0,0,1,0,0,1,1,1,0},
                {0,1,0,0,1,1,1,1,1,0,1,0},
                {0,1,0,0,1,0,0,1,0,0,0,0},
                {0,1,1,1,1,0,0,1,0,0,0,0},
                {0,0,0,1,0,0,0,1,1,1,1,1},
                {0,0,0,0,0,0,0,0,0,0,0,1},
        };// length 22
        int[][] maze2 = {
                {1,0,0,0,0,0,0,0,0,0,0,0},
                {1,1,0,0,0,1,0,0,1,1,1,1},
                {0,1,1,1,1,1,1,1,1,0,1,0},
                {0,0,0,0,0,0,0,1,0,0,0,0},
        };// length 15
        int[][] maze3 = {
                {1,0,0,0,0,0,0,0,0,0,0,0},
                {1,1,0,0,0,1,0,0,1,1,1,0},
                {0,1,1,1,1,1,1,1,1,0,1,0},
                {0,1,0,0,0,0,0,1,0,0,0,0},
                {0,1,0,0,0,0,0,1,0,0,0,0},
                {0,1,0,0,0,0,1,1,0,0,0,0},
                {0,0,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,1,1,1,0,0,0,0,0},
                {0,0,0,0,1,0,0,0,0,0,0,1},
                {0,0,0,0,1,1,1,1,1,1,1,1},
                {0,0,0,0,0,0,0,0,0,0,0,0}
        };// length 28
        String visualMaze = (Arrays.deepToString(maze1)).replace("], [","\n");
        visualMaze = visualMaze.substring(2,visualMaze.length()-2);
        visualMaze = visualMaze.replace("0","■");
        visualMaze = visualMaze.replace("1","0");
        visualMaze = visualMaze.replace(",", "");
        System.out.println(visualMaze + "\n");
        System.out.println(findLength(0,0,maze1,0));
    }
    public static int findLength(int x, int y, int[][] maze, int length){
        if(inBounds(x,y,maze)){
            length++;
            maze[y][x] = 0;
            return Math.max(findLength(x+1,y,maze,length),Math.max(findLength(x,y-1,maze,length),(Math.max(findLength(x,y+1,maze,length),findLength(x-1,y,maze,length)))));
        } else
            length--;
        return length+1;
    }

    public static boolean inBounds(int x, int y, int[][] maze){
        int boundy1 = 0;
        int boundx1 = 0;
        int boundy2 =maze.length-1;
        int boundx2 = maze[0].length-1;

        return (x>= boundx1 && y>= boundy1 && x <= boundx2 && y <= boundy2 && maze[y][x] == 1);
    }
}
