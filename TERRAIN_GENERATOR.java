package TERRAIN_GENERATOR;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TERRAIN_GENERATOR {
    static double[][] terrain_matrix;
    static double smoothness=0.5;
    static int n;
    
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        System.out.println("Enter N : ");
        n=sc.nextInt();

        terrain_matrix=new double[n][n];

        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                terrain_matrix[i][j]=-1;
            }
        }

        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                terrain_matrix[i][j]=(double)(value_generator(i,j));
                System.out.print("  "+terrain_matrix[i][j]);
            }
            System.out.println();
        }

        MatrixExporter.exportToCSV(terrain_matrix, "terrain_matrix.csv");

        sc.close();
    }

    static double value_generator(int i, int j){
        double sum=0;
        int ran_times=0;

        if (i==0 && j==0){
            return Math.random();
        }
        else{
            for (int k=-1;k<=1;k++){
                for (int l=-1;l<=1;l++){
                    try{
                        if (terrain_matrix[i+k][j+l]!=-1){
                            sum=(sum)+Math.abs((terrain_matrix[i+k][j+l])-(Math.random())*(1-smoothness));
                            ran_times++;
                        }
                    }
                    catch(Exception e){
                        // System.out.println("Exception occured : "+(i+k)+"_"+(j+l));
                    }
                }
            }
            sum=(sum)/(ran_times);
            return sum;
        }
    }
}


class MatrixExporter {
    // FIX: Removed the quotes around 'filename' here.
    public static void exportToCSV(double[][] matrix, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (int i = 0; i < TERRAIN_GENERATOR.n; i++) {
                for (int j = 0; j < TERRAIN_GENERATOR.n; j++) {
                    
                    // Writing your specific terrain matrix values
                    writer.append(String.valueOf(TERRAIN_GENERATOR.terrain_matrix[i][j]));
                    
                    if (j < TERRAIN_GENERATOR.n - 1) {
                        writer.append(","); // Separate columns with commas
                    }
                }
                writer.append("\n"); // Separate rows with new lines
            }
            System.out.println("Matrix exported to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}