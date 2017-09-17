public class JNIwrapper
{

  static { System.loadLibrary("JNIpm"); }
 
 native int create_PM();
 native int destroy_PM();
 native int Manage_PM(String pname,int pramtye,int requir);
 native int hasValue_PM(String pname);
 native int parseFrom_PM(String filename);
 native String next_PL(String[] pname);
 native String getstring(String pname);
 native String[] getlist(String pname);
 
 public static void main(String[] args)
 {
   JNIwrapper gen = new JNIwrapper();
   gen.create_PM();
   gen.Manage_PM("title",2,1);
   gen.Manage_PM("fields",3,1);
   gen.Manage_PM("buttons",3,1);
   
   if(gen.parseFrom_PM("test2.config")==0)
   {
		System.out.println("Parse ERROR");
   }
   
   String[] string=gen.getlist("fields");
   String[] buttonzz = gen.getlist("buttons");
   
   System.out.println("title: "+gen.getstring("title"));
   
   for(String x: string)
   {
		System.out.println("fields: " + x);
   }
   
   for(String y: buttonzz)
   {
		System.out.println("buttons: " + y);
   }
   
    gen = new JNIwrapper();
   
   for(String g: string)
   {
	gen.Manage_PM(g,2,1);
   }
   
   for(String h: buttonzz)
   {
	gen.Manage_PM(h,2,1);
   }
   
   if(gen.parseFrom_PM("test2.config")==0)
   {
		System.out.println("Parse ERROR");
   }
   else
   {
   
    for(String g: string)
    {
        System.out.println(g+": "+gen.getstring(g));
    }
   
    for(String h: buttonzz)
    {
	System.out.println(h+": "+gen.getstring(h));
    }
   }
   gen.destroy_PM();
 }

}
