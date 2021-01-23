/* Nama/NIM : Bervianto Leo P/13514047 */
/* Nama file : HyperdokuSolverDriver.java */
/* Hyperdoku Solver Driver */

package hyperdoku.solver.app;

import hyperdoku.solver.utilities.HyperdokuSolver;
import java.util.*;
import java.io.*;

public class App {
	
	public static void main (String []args) throws FileNotFoundException {
		System.out.println("Selamat datang dalam Hyperdoku Solver!");
		//Masukan nama file
		Scanner in = new Scanner(System.in);
		String FileName;
		System.out.println("Masukan nama file dalam direktori yang sama dengan program ");
		System.out.println("Masukan dengan ekstensi. Contoh : soal1.txt ");
		System.out.print("Nama file : ");
		FileName = in.nextLine();
		
		//Memulai inisialisasi hyperdoku
		HyperdokuSolver HS = new HyperdokuSolver(9,9);
		//Membaca File
		HS.ReadHyperdokuFromFile(FileName);
		System.out.println("Open File...");
		//Mencetak hasil baca
		System.out.println("Input Hyperdoku : ");
		HS.PrintHyperdoku();
		//Memulai memecahkan
		double start=System.currentTimeMillis();
		HS.Solver();
		double end=System.currentTimeMillis();
		//Akhir pemecahan
		//Cetak setiap hasil
		System.out.println("Hasilnya adalah : ");
		HS.PrintHyperdoku();
		System.out.println("Jumlah assignment : "+HS.assign+" kali");
		System.out.println("Waktu memecahkan sekitar : "+(end-start)+" ms");
	}
}
