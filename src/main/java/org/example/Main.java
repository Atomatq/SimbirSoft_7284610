package org.example;



public class Main {
    static int getFibonacci(int n) {
        if (n <= 2) return n-1;
        else return getFibonacci(n-1) + getFibonacci(n-2);
    }

    public static void main(String args[]) {
        System.out.println(getFibonacci(-1));
    }
}