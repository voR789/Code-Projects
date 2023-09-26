import java.util.*;
public class FindingRoots {
    public static void main(String[] args){
        /*In basic calculus, students learn about IVT, or intermediate value theorem. This states that within a continuous interval of a function,
        [a,b], any number between f(a) and f(b) is guaranteed to be a value on the function. How can this be applied in real life?

        One such function is by finding the root of a function throughout an interval. By receiving negative and positive bounds left and right of a
        root within a function, by binary searching for where it strikes zero (guaranteed by IVT), you can find the root of a function.
        */

        Scanner input = new Scanner(System.in);
        int functionDegree;
        double[] functionCoefficients;
        long startX;
        long endX;


        System.out.print("Enter the desired degree of your function: ");
        functionDegree = input.nextInt();
        functionCoefficients = new double[functionDegree+1];

        for(int i = 0; i <= functionDegree; i++){
            System.out.println("Enter coefficients: ");
            System.out.println("_x" + superscript(""+(functionDegree-i)));
            functionCoefficients[i] = input.nextDouble();
        }
        System.out.println(Arrays.toString(functionCoefficients));
        for(int i = 0; i < functionDegree; i++){
            System.out.print(functionCoefficients[i] + "x" + superscript(""+(functionDegree-i)) + "+ ");
        }
        System.out.println(functionCoefficients[functionDegree] + "x" + superscript(""+(0)) );

        System.out.println("Enter starting x bound: ");
        startX = input.nextInt();

        System.out.println("Enter ending x bound: ");
        endX = input.nextInt();

        // can change amount of computer approximation
        if(findRoot(functionCoefficients,startX,endX) != -1)
            System.out.format("%.6f",findRoot(functionCoefficients,startX,endX));
        else
            System.out.println("No root in bounds");


    }
    // superscript function borrowed from user: AvrDragon on stackOverflow
    public static String superscript(String str) {
        str = str.replaceAll("0", "⁰");
        str = str.replaceAll("1", "¹");
        str = str.replaceAll("2", "²");
        str = str.replaceAll("3", "³");
        str = str.replaceAll("4", "⁴");
        str = str.replaceAll("5", "⁵");
        str = str.replaceAll("6", "⁶");
        str = str.replaceAll("7", "⁷");
        str = str.replaceAll("8", "⁸");
        str = str.replaceAll("9", "⁹");
        return str;
    }

    // binary search to emulate IVT
    public static double findRoot(double[] functionCoefficients, double startX, double endX){
        double startY = findValue(functionCoefficients, startX);
        double endY = findValue(functionCoefficients, endX);
        double midY = findValue(functionCoefficients, (startX+endX)/2);
        /*
        Debugging Statements
        System.out.println("ran " + (startX+endX)/2 + " " + midY + " start " +startX + " " + startY + " end " + endX + " "+ endY +"\n" + roundToDecimal(midY,3) + " " + roundToDecimal(startY,3) + " " + roundToDecimal(endY,3));*/
        if(Math.abs((startX+endX)/2-startX)<.0000001)
            return (startX+endX)/2;
        if(startY > 0 & midY < 0){
            return findRoot(functionCoefficients,startX,(startX+endX)/2);
        } else if(endY > 0 & midY < 0){
            return findRoot(functionCoefficients,endX,(startX+endX)/2);
        } else if(startY < 0 & midY > 0){
            return findRoot(functionCoefficients,startX,(startX+endX)/2);
        } else if(endY < 0 & midY > 0){
            return findRoot(functionCoefficients,endX,(startX+endX)/2);
        }
        return -1;
        // no intercept or root
    }

    public static double findValue(double[] functionCoefficients, double x){
        double y = 0;
        for(int i = 0; i <= functionCoefficients.length-1; i++){
            y += functionCoefficients[i] * Math.pow(x,functionCoefficients.length-1-i);
        }
        return y;
    }

}
