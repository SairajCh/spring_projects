package practice;

public class StringHashcodes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String a="a";
		String b = "a";
		
		String d = "A";
		if(a==b) {
			System.out.println("qual");
		}

		if(a.hashCode()==b.hashCode()) {
			System.out.println("1equal");
		}
		
		String c = new String("a");
		
		if(a==c) {
			System.out.println("2equal");
		}
		
		if(a.hashCode()==c.hashCode()) {
			System.out.println("3equal");
		
		}
		
		System.out.println(a.hashCode());
		System.out.println(d.hashCode());
	}

}
