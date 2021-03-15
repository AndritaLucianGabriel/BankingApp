import java.lang.*;

public class Main {
    public static void main(String[] args) {
        /*
        //Chestii cu clasa User
        User user=new User("Marian","Gusatu",60,"52414626");
        User user1=new User("Marian","Gusatu",60,"52414626");
        User user2=user;

        User user3=new User();
        System.out.println(user3.toString());

        /*
        //Testare equals vs ==
        System.out.println(user1==user);
        System.out.println(user2==user);
        System.out.println();
        System.out.println(user1.equals(user));
        System.out.println(user2.equals(user));

        System.out.println(user.toString());
         */

        //Chestii cu clasa Cont
        Cont cont=new Cont("RO42RZBR15215321","12-09-2021",null,1203.4,"Lei");
        System.out.println(cont.toString());
    }
}