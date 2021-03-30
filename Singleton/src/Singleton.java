import java.io.*;
import java.lang.reflect.Constructor;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Singleton implements Serializable, Cloneable {
    // private static Singleton singleton = new Singleton(); //eager
    private static Singleton singleton = null; // lazy
    private Singleton(){}

    public static Singleton getSingleton(){
        if(Objects.isNull(singleton)){
            singleton = new Singleton();
        }
        return  singleton;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
class SingletonDemo{
    public static void main(String[] args) throws Exception {

        //Breaking with Threads
        breakWithThreading();

        //Normal work Flow
        Singleton s1 = Singleton.getSingleton();
        Singleton s2 = Singleton.getSingleton();

        System.out.println("s1 hashcode :- "+s1.hashCode());
        System.out.println("s2 hashcode :- "+s2.hashCode());

        System.out.println("\nAfter Reflection");

        //Breaking with Reflection
        Class clazz = Class.forName("Singleton");
        Constructor<Singleton> s3Constructor = clazz.getDeclaredConstructor();
        s3Constructor.setAccessible(true);
        Singleton s3 = s3Constructor.newInstance();
        System.out.println("s3 hashcode :- "+s3.hashCode());

        System.out.println("\nAfter Serialization");
        //Breaking with Serialization/Deserialization
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:\\Temp\\temp.cer"));
        oos.writeObject(Singleton.getSingleton());

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D:\\Temp\\temp.cer"));
        Singleton s4 =(Singleton) ois.readObject();

        System.out.println("s4 hashcode :- "+s4.hashCode());

        System.out.println("\nAfter Cloning");
        //Breaking with Cloneable
        Singleton s5 = (Singleton) Singleton.getSingleton().clone();
        System.out.println("s5 hashcode :- "+s5.hashCode());

        //Breaking with threading
        breakWithThreading();
    }

    private static void breakWithThreading() {
        System.out.println("\nAfter Threading");
        ExecutorService threadService = Executors.newFixedThreadPool(2);
        threadService.submit(SingletonDemo::ThreadBody);
        threadService.submit(SingletonDemo::ThreadBody);

        threadService.shutdown();
    }

    static void ThreadBody(){
        Singleton thread = Singleton.getSingleton();
        System.out.println("Thread hashcode :- "+thread.hashCode());
    }
}