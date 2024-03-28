import java.io.Serializable;

public class Record implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public String A,B,C,D,E,F,G,H,I,J,K,L,M,N;

	public Record() {
		this.A = "Game Title";
		this.B="Game Poster";
		this.C="Game Release Date";
		this.D="Game Developer";
		this.E="Genre";
		this.F="Platforms";
		this.G="Product Rating";
		this.H="Overall Metascore";
		this.I="Overall User Rating";
		this.J="Reviewer Name";
		this.K="Reviewer Type";
		this.L="Rating Given By The Reviewer";
		this.M="Review Date";
		this.N="Review Text";
	}

	public String getA() {
		return A;
	}

	public void setA(String a) {
		A = a;
	}

	public String getB() {
		return B;
	}

	public void setB(String b) {
		B = b;
	}

	public String getC() {
		return C;
	}

	public void setC(String c) {
		C = c;
	}

	public String getD() {
		return D;
	}

	public void setD(String d) {
		D = d;
	}

	public String getE() {
		return E;
	}

	public void setE(String e) {
		E = e;
	}

	public String getF() {
		return F;
	}

	public void setF(String f) {
		F = f;
	}

	public String getG() {
		return G;
	}

	public void setG(String g) {
		G = g;
	}

	public String getH() {
		return H;
	}

	public void setH(String h) {
		H = h;
	}

	public String getI() {
		return I;
	}

	public void setI(String i) {
		I = i;
	}

	public String getJ() {
		return J;
	}

	public void setJ(String j) {
		J = j;
	}

	public String getK() {
		return K;
	}

	public void setK(String k) {
		K = k;
	}

	public String getL() {
		return L;
	}

	public void setL(String l) {
		L = l;
	}

	public String getM() {
		return M;
	}

	public void setM(String m) {
		M = m;
	}

	public String getN() {
		return N;
	}

	public void setN(String n) {
		N = n;
	}
}
