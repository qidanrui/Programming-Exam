interface marea{
	 double carea();
}
class rec implements marea{
	int edge;
	public rec(int a){
		edge=a;
	}
	public double carea(){
		return edge*edge;
		
	}
}

class cir implements marea{
	int r;
	public cir(int a){
		r=a;
	}
	
	public double carea(){
		return r*r*Math.PI;
		
	}
}



public class fanxing <T extends marea>{

	/**
	 * @param args
//	 */
	double area;
	public fanxing(T t){
		area = t.carea();
		
	}
	public void express(){
		
		System.out.println(area);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		cir c= new cir(2);
		rec r= new rec(3);
		fanxing<cir> f = new fanxing<cir>(c);
		fanxing<rec> g = new fanxing<rec>(r);
		f.express();
		g.express();
	}

}
