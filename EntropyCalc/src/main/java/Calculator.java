import javax.swing.*;

public class Calculator {
    public static double logBase2 (double a) {
        return Math.log10(a) / Math.log10(2);
    }
    public static double sumDividedByMul (int a, int b) {
        return Math.pow(a+b, a+b) / (Math.pow(a, a) * Math.pow(b,b));
    }
    public static double calculateEntropy(int a, int b) {
        double aPlusB = a + b;
        return (1.0/aPlusB) * logBase2(sumDividedByMul(a, b));
    }

    public static double calculateConditionedEntropy(int c, int d, int e, int f) {
        return (1.0/(c+d+e+f)) * logBase2(sumDividedByMul(c, d) * sumDividedByMul(e, f));
    }

    public static double bernoulliEntropy(double a) {
 return 0;
    }

    public static double calculateIG(int c, int d, int e, int f) {
//        return (1.0/(c+d+e+f)) * logBase2(sumDividedByMul(c + e, d + f ) * (1.0 /sumDividedByMul(c, d)) * (1.0/sumDividedByMul(e, f)));
        return calculateEntropy(c + e, d + f) - calculateConditionedEntropy(c,d,e,f);
    }



}
