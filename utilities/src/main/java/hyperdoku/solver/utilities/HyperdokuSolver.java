/* Nama/NIM : Bervianto Leo P/13514047 */
/* Nama File : HyperdokuSolver.java */
/* Kelas Hyperdoku */
/* Menggunakan matriks integer sebagai penyimpan angka dan 
 * matriks boolean sebagai kunci angka input selain nol */

package hyperdoku.solver.utilities;

import java.util.*;
import java.io.*;

public class HyperdokuSolver {
	private static final int NMAX = 9;
	public int NBrsEff;
	public int NKolEff;
	public int assign;
	public int[][] Mem = new int[NMAX+1][NMAX+1];
	public boolean[][] MemF = new boolean[NMAX+1][NMAX+1];

	// Konstruktor
	public HyperdokuSolver()
	{
		this.NBrsEff = this.NMAX;
		this.NKolEff = this.NMAX;
		for(int i=1;i<=this.NBrsEff;i++)
		{
			for(int j=1;j<=this.NKolEff;j++)
			{
				this.Mem[i][j] = 0;
				this.MemF[i][j] = false;
			}
		}
	}

	public HyperdokuSolver(int N, int M)
	{
		this.NBrsEff = N;
		this.NKolEff = M;
		for(int i=1;i<=N;i++)
		{
			for(int j=1;j<=M;j++)
			{
				this.Mem[i][j] = 0;
				this.MemF[i][j] = false;
			}
		}
	}
	
	// Selektor
	public int GetNBrsEff() { return this.NBrsEff; }
	public int GetNKolEff() { return this.NKolEff; }
	public int GetElmt(int i, int j) { return this.Mem[i][j]; }

	/* I/O Matriks */
	public void PrintHyperdoku()
	{
		/* I.S. sembarang
		   F.S. nilai matriks tercetak pada layar */ 
		int k;
		for(int i=1;i<=this.GetNBrsEff();i++)
		{
			if ((i-1) % 3 == 0 ) {
				for (k=1;k<=21;k++)
				{
					System.out.print("-");
				}
				System.out.println();
			}
			for(int j=1;j<=this.GetNKolEff();j++)
			{
				System.out.print(this.GetElmt(i,j));
				if(j==this.GetNKolEff())
				{
					System.out.println();
				}
				else if (j % 3 == 0)
				{
					System.out.print(" | ");
				} 
				else System.out.print(" ");
			}
		}
		//Penutup
		for (k=1;k<=21;k++)
		{
			System.out.print("-");
		}
		System.out.println();
	}

	public void ReadHyperdokuFromFile(String file_in) throws FileNotFoundException
	{
		/* I.S. direktori yang tersimpan dalam value file_in berisi sebuah matriks 
		   F.S. Matriks terisi sesuai dengan isi direktori file_in */ 
		File file = new File(file_in);
		Scanner in = new Scanner(new FileReader(file));
		this.NBrsEff = 9;
		this.NKolEff = 9;

		for(int i=1;i<=this.GetNBrsEff();i++)
		{
			for(int j=1;j<=this.GetNKolEff();j++)
			{
				this.Mem[i][j] = in.nextInt();
				if (this.Mem[i][j]!=0)
				{
					this.MemF[i][j]=true;
				}
			}
		}
		in.close();
	}
	
	/* Pengecekan Kevalidan Hyperdoku */
	public boolean IsBarisValid(int i) 
	/* i kolom aktif */
	{
		boolean found;
		int j,k;
		j=1;
		found=false;
		while ((j<=this.GetNKolEff()) && (!found))
		{
			if (this.Mem[i][j]!=0)
			{
				k=j+1;
				while ((k<=this.GetNKolEff())&&(!found))
				{
					if (this.Mem[i][j]==this.Mem[i][k])
					{
						found=true;
					}
					else
					{
						k++;
					}
				}
				if (!found)
				{
					j++;
				}
			} else
			{
				j++;
			}
		}
		return (!found);
	}
	
	public boolean IsKolomValid(int j) 
	/* j kolom aktif */
	{
		boolean found;
		int i,k;
		i=1;
		found=false;
		while ((i<=this.GetNBrsEff()) && (!found))
		{
			if (this.Mem[i][j]!=0)
			{
				k=i+1;
				while ((k<=this.GetNBrsEff())&&(!found))
				{
					if (this.Mem[i][j]==this.Mem[k][j])
					{
						found=true;
					}
					else
					{
						k++;
					}
				}
				if (!found)
				{
					i++;
				}
			} else
			{
				i++;
			}
		}
		return (!found);
	}
	
	public boolean IsCurrentKotakValid(int i, int j)
	/* i baris aktif
	 * j kolom aktif
	 * */
	{
		if (((i>=1) && (i<=3)) && ((j>=1) && (j<=3)))
		{
			return (this.BoxChecking(1,3,1,3));
		}
		else if ((i>=4 && i<=6) && (j>=1 && j<=3))
		{
			return (this.BoxChecking(4,6,1,3));
		}
		else if ((i>=7 && i<=9) && (j>=1 && j<=3))
		{
			return (this.BoxChecking(7,9,1,3));
		}
		else if ((i>=1 && i<=3) && (j>=4 && j<=6))
		{
			return (this.BoxChecking(1,3,4,6));
		}
		else if ((i>=1 && i<=3) && (j>=7 && j<=9))
		{
			return (this.BoxChecking(1,3,7,9));
		}
		else if ((i>=4 && i<=6) && (j>=4 && j<=6))
		{
			return (this.BoxChecking(4,6,4,6));
		}
		else if ((i>=4 && i<=6) && (j>=7 && j<=9))
		{
			return (this.BoxChecking(4,6,7,9));
		}
		else if ((i>=7 && i<=9) && (j>=4 && j<=6))
		{
			return (this.BoxChecking(7,9,4,6));
		}
		else
		/* ((i>=7 && i<=9) && (j>=7 && j<=9)) */
		{
			return (this.BoxChecking(7,9,7,9));
		}
	}
	
	public boolean IsSpecialBoxValid(int i,int j)
	{
		if (((i>=2) && (i<=4)) && ((j>=2) && (j<=4)))
		{
			return (this.BoxChecking(2,4,2,4));
		}
		else if ((i>=2 && i<=4) && (j>=6 && j<=8))
		{
			return (this.BoxChecking(2,4,6,8));
		}
		else if ((i>=6 && i<=8) && (j>=2 && j<=4))
		{
			return (this.BoxChecking(6,8,2,4));
		}
		else if ((i>=6 && i<=8) && (j>=6 && j<=8))
		{
			return (this.BoxChecking(6,8,6,8));
		}
		else /* Tidak terjadi perubahan di kotak spesial */
		{
			return true;
		}
	}
	
	/* Pengecekan dasar sebuah kotak 3*3 */
	public boolean BoxChecking(int i,int j,int k,int l)
	/* i batas bawah baris 
	 * j batas atas baris
	 * k batas bawah kolom
	 * l batas atas kolom
	 * */
	{
		int m,n,o,p;
		boolean found;
		m=i;
		found=false;
		while ((!found) && (m<=j))
		{
			n=k;
			while ((!found)&&(n<=l))
			{
				if (this.Mem[m][n]!=0)
				{
					o=m;
					p=n+1;
					while ((o<=j)&&(!found))
					{
						while ((p<=l)&&(!found))
						{
							if (this.Mem[m][n]==this.Mem[o][p])
							{
								found=true;
							}
							else
							{
								p++;
							}
						}
						o++;
						if (p>l)
						{
							p=k;
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
	public void Solver() {
		int i,j;
		boolean solved;
		solved=true;
		this.assign=0;
		i=1;
		while ((solved) && (i<=this.GetNBrsEff()))
		{
			j=1;
			while ((solved) && (j<=this.GetNKolEff()))
			{
				if (this.MemF[i][j])
				{
					j++;
				}
				else
				{
					if (!(this.SolveOne(i,j)))
					{
						while((solved)&&(this.Mem[i][j]==0) || (this.MemF[i][j]))
						{
							j--;
							if(j==0)
							{
								i--;
								if (i==0)
								{
									solved=false;
									System.out.println("No Solution");
								}
								else
								{
									j=9;
								}
							}
						}
					}
					else
					{
						j++;
					}
				}
			}
			i++;
		}
	}
	
	/* Pengetesan Input */
	public boolean SolveOne(int m,int n) {
		int i,j;
		i=m;
		j=n;
		this.Mem[i][j]++;
		if (this.Mem[i][j]>9)
		{
			this.Mem[i][j]=0;
			return false;
		}
		else
		{
			this.assign++; //Menambahkan jumlah assignment
			if ((this.IsBarisValid(i)) && (this.IsKolomValid(j)) && (this.IsCurrentKotakValid(i,j)) && (this.IsSpecialBoxValid(i,j)))
			{
				return true;
			}
			else
			{
				return (this.SolveOne(i,j));
			}
		}
	}
}
