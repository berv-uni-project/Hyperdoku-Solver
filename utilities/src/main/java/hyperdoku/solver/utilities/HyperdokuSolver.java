/* Nama/NIM : Bervianto Leo P/13514047 */
/* Nama File : HyperdokuSolver.java */
/* Kelas Hyperdoku */
/* Menggunakan matriks integer sebagai penyimpan angka dan
 * matriks boolean sebagai kunci angka input selain nol */

package hyperdoku.solver.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Main class of solver
 */
public class HyperdokuSolver {
    private static final int NMAX = 9;
    /**
     * NBrs Effective
     */
    public int NBrsEff;
    /**
     * NKol Effective
     */
    public int NKolEff;
    /**
     * Assign time
     */
    public int assign;
    /**
     * Matrix position
     */
    public int[][] Mem = new int[NMAX + 1][NMAX + 1];
    /**
     * Matrix finished
     */
    public boolean[][] MemF = new boolean[NMAX + 1][NMAX + 1];

    // Konstruktor

    /**
     * Main constructor
     */
    public HyperdokuSolver() {
        this.NBrsEff = NMAX;
        this.NKolEff = NMAX;
        for (int i = 1; i <= this.NBrsEff; i++) {
            for (int j = 1; j <= this.NKolEff; j++) {
                this.Mem[i][j] = 0;
                this.MemF[i][j] = false;
            }
        }
    }

    /**\
     * Constructor by row and column
     * @param N row
     * @param M column
     */
    public HyperdokuSolver(int N, int M) {
        this.NBrsEff = N;
        this.NKolEff = M;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                this.Mem[i][j] = 0;
                this.MemF[i][j] = false;
            }
        }
    }

    /**
     * Selector of NBrsEff
     *
     * @return NBrsEff
     */
    public int getNBrsEff() {
        return this.NBrsEff;
    }

    /**
     * Selector of NKolEff
     *
     * @return NKolEff
     */
    public int getNKolEff() {
        return this.NKolEff;
    }

    /**
     * Selector of element
     *
     * @param i row
     * @param j column
     * @return element
     */
    public int getElmt(int i, int j) {
        return this.Mem[i][j];
    }

    /* I/O Matriks */

    /**
     * Print to system
     */
    public void printHyperdoku() {
		/* I.S. sembarang
		   F.S. nilai matriks tercetak pada layar */
        int k;
        for (int i = 1; i <= this.getNBrsEff(); i++) {
            if ((i - 1) % 3 == 0) {
                for (k = 1; k <= 21; k++) {
                    System.out.print("-");
                }
                System.out.println();
            }
            for (int j = 1; j <= this.getNKolEff(); j++) {
                System.out.print(this.getElmt(i, j));
                if (j == this.getNKolEff()) {
                    System.out.println();
                } else if (j % 3 == 0) {
                    System.out.print(" | ");
                } else System.out.print(" ");
            }
        }
        //Penutup
        for (k = 1; k <= 21; k++) {
            System.out.print("-");
        }
        System.out.println();
    }

    /**
     * Read from file
     *
     * @param file_in file location
     * @throws FileNotFoundException file not found
     */
    public void readHyperdokuFromFile(String file_in) throws FileNotFoundException {
		/* I.S. direktori yang tersimpan dalam value file_in berisi sebuah matriks 
		   F.S. Matriks terisi sesuai dengan isi direktori file_in */
        File file = new File(file_in);
        Scanner in = new Scanner(new FileReader(file));
        this.NBrsEff = 9;
        this.NKolEff = 9;

        for (int i = 1; i <= this.getNBrsEff(); i++) {
            for (int j = 1; j <= this.getNKolEff(); j++) {
                this.Mem[i][j] = in.nextInt();
                if (this.Mem[i][j] != 0) {
                    this.MemF[i][j] = true;
                }
            }
        }
        in.close();
    }

    /* Pengecekan Kevalidan Hyperdoku */

    /**
     * Check Baris
     *
     * @param i row
     * @return correct
     */
    public boolean isBarisValid(int i)
    /* i kolom aktif */ {
        boolean found;
        int j, k;
        j = 1;
        found = false;
        while ((j <= this.getNKolEff()) && (!found)) {
            if (this.Mem[i][j] != 0) {
                k = j + 1;
                while ((k <= this.getNKolEff()) && (!found)) {
                    if (this.Mem[i][j] == this.Mem[i][k]) {
                        found = true;
                    } else {
                        k++;
                    }
                }
                if (!found) {
                    j++;
                }
            } else {
                j++;
            }
        }
        return (!found);
    }

    /**
     * Check kolom valid
     *
     * @param j kolom
     * @return valid kolom
     */
    public boolean isKolomValid(int j)
    /* j kolom aktif */ {
        boolean found;
        int i, k;
        i = 1;
        found = false;
        while ((i <= this.getNBrsEff()) && (!found)) {
            if (this.Mem[i][j] != 0) {
                k = i + 1;
                while ((k <= this.getNBrsEff()) && (!found)) {
                    if (this.Mem[i][j] == this.Mem[k][j]) {
                        found = true;
                    } else {
                        k++;
                    }
                }
                if (!found) {
                    i++;
                }
            } else {
                i++;
            }
        }
        return (!found);
    }

    /**
     * Check box valid
     *
     * @param i row
     * @param j column
     * @return is valid box
     */
    public boolean isCurrentKotakValid(int i, int j)
    /* i baris aktif
     * j kolom aktif
     * */ {
        if (((i >= 1) && (i <= 3)) && ((j >= 1) && (j <= 3))) {
            return (this.boxChecking(1, 3, 1, 3));
        } else if ((i >= 4 && i <= 6) && (j >= 1 && j <= 3)) {
            return (this.boxChecking(4, 6, 1, 3));
        } else if ((i >= 7 && i <= 9) && (j >= 1 && j <= 3)) {
            return (this.boxChecking(7, 9, 1, 3));
        } else if ((i >= 1 && i <= 3) && (j >= 4 && j <= 6)) {
            return (this.boxChecking(1, 3, 4, 6));
        } else if ((i >= 1 && i <= 3) && (j >= 7 && j <= 9)) {
            return (this.boxChecking(1, 3, 7, 9));
        } else if ((i >= 4 && i <= 6) && (j >= 4 && j <= 6)) {
            return (this.boxChecking(4, 6, 4, 6));
        } else if ((i >= 4 && i <= 6) && (j >= 7 && j <= 9)) {
            return (this.boxChecking(4, 6, 7, 9));
        } else if ((i >= 7 && i <= 9) && (j >= 4 && j <= 6)) {
            return (this.boxChecking(7, 9, 4, 6));
        } else
            /* ((i>=7 && i<=9) && (j>=7 && j<=9)) */ {
            return (this.boxChecking(7, 9, 7, 9));
        }
    }

    /**
     * Special box checking
     *
     * @param i row
     * @param j column
     * @return is valid special box
     */
    public boolean isSpecialBoxValid(int i, int j) {
        if (((i >= 2) && (i <= 4)) && ((j >= 2) && (j <= 4))) {
            return (this.boxChecking(2, 4, 2, 4));
        } else if ((i >= 2 && i <= 4) && (j >= 6 && j <= 8)) {
            return (this.boxChecking(2, 4, 6, 8));
        } else if ((i >= 6 && i <= 8) && (j >= 2 && j <= 4)) {
            return (this.boxChecking(6, 8, 2, 4));
        } else if ((i >= 6 && i <= 8) && (j >= 6 && j <= 8)) {
            return (this.boxChecking(6, 8, 6, 8));
        } else /* Tidak terjadi perubahan di kotak spesial */ {
            return true;
        }
    }

    /* Pengecekan dasar sebuah kotak 3*3 */

    /**
     * Box checking mechanism
     *
     * @param i start row
     * @param j start column
     * @param k end row
     * @param l end column
     * @return is valid range
     */
    public boolean boxChecking(int i, int j, int k, int l)
    /* i batas bawah baris
     * j batas atas baris
     * k batas bawah kolom
     * l batas atas kolom
     * */ {
        int m, n, o, p;
        boolean found;
        m = i;
        found = false;
        while ((!found) && (m <= j)) {
            n = k;
            while ((!found) && (n <= l)) {
                if (this.Mem[m][n] != 0) {
                    o = m;
                    p = n + 1;
                    while ((o <= j) && (!found)) {
                        while ((p <= l) && (!found)) {
                            if (this.Mem[m][n] == this.Mem[o][p]) {
                                found = true;
                            } else {
                                p++;
                            }
                        }
                        o++;
                        if (p > l) {
                            p = k;
                        }
                    }
                }
                n++;
            }
            m++;
        }
        return (!found);
    }

    /* Pemecah hypedoku */

    /**
     * Main algorithm
     */
    public void solver() {
        int i, j;
        boolean solved;
        solved = true;
        this.assign = 0;
        i = 1;
        while ((solved) && (i <= this.getNBrsEff())) {
            j = 1;
            while ((solved) && (j <= this.getNKolEff())) {
                if (this.MemF[i][j]) {
                    j++;
                } else {
                    if (!(this.solveOne(i, j))) {
                        while ((solved) && (this.Mem[i][j] == 0) || (this.MemF[i][j])) {
                            j--;
                            if (j == 0) {
                                i--;
                                if (i == 0) {
                                    solved = false;
                                    System.out.println("No Solution");
                                } else {
                                    j = 9;
                                }
                            }
                        }
                    } else {
                        j++;
                    }
                }
            }
            i++;
        }
    }

    /* Pengetesan Input */

    /**
     * Solving one step
     *
     * @param m row
     * @param n column
     * @return get the solve
     */
    public boolean solveOne(int m, int n) {
        int i, j;
        i = m;
        j = n;
        this.Mem[i][j]++;
        if (this.Mem[i][j] > 9) {
            this.Mem[i][j] = 0;
            return false;
        } else {
            this.assign++; //Menambahkan jumlah assignment
            if ((this.isBarisValid(i)) && (this.isKolomValid(j)) && (this.isCurrentKotakValid(i, j)) && (this.isSpecialBoxValid(i, j))) {
                return true;
            } else {
                return (this.solveOne(i, j));
            }
        }
    }
}
