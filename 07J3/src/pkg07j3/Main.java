/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg07j3;

import java.util.Scanner;

/**
 *
 * @author 324624964
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        int n = keyboard.nextInt();
        int[] opened = new int[n];
        int sum = 0;
        int deal;
        for (int i = 0; i < n; i++) {
            opened[i] = keyboard.nextInt();
            switch (opened[i]) {
                case 1:
                    sum += 100;
                    break;
                case 2:
                    sum += 500;
                    break;
                case 3:
                    sum += 1000;
                    break;
                case 4:
                    sum += 5000;
                    break;
                case 5:
                    sum += 10000;
                    break;
                case 6:
                    sum += 25000;
                    break;
                case 7:
                    sum += 50000;
                    break;
                case 8:
                    sum += 100000;
                    break;
                case 9:
                    sum += 500000;
                    break;
                case 10:
                    sum += 1000000;
                    break;
            }
        }
        deal = keyboard.nextInt();
        System.out.println((sum / n) > deal) ? );
    }

}
