import java.security.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException{
        DSA dsa = new DSA();
        String message = null;
        LinkedList<byte[]> signatures = new LinkedList<>();

        Scanner in = new Scanner(System.in);
        boolean end = false;
        int choice;

        while(!end){
            System.out.print("\t\t--command list--\n" +
                    "1. enter message\n" +
                    "2. sign message\n" +
                    "3. verify message\n" +
                    "4. display pk\n" +
                    "5. display sk\n" +
                    "0. exit\n" +
                    "you choice: ");
            choice = in.nextInt();
            switch (choice) {
                case 0 -> end = true;
                case 1 -> {
                    System.out.print("enter the message: ");
                    message = in.next();
                }
                case 2, 3 -> {
                    if (message == null) {
                        System.out.println("error, please enter message before signing/verifying!");
                        break;
                    }
                    try {
                        switch (choice) {
                            case 2 -> {
                                byte[] signature = dsa.sign(message.getBytes());
                                signatures.add(signature);
                                System.out.println("\t\t --singing success--\n" +
                                    "message: " + message + "\n" +
                                    "signature: " + Arrays.toString(signature));
                            }
                            case 3 -> {
                                System.out.println("chose the signature for message '" + message + "'");
                                for(int i = 0; i < signatures.size(); i++)
                                    System.out.println(i + ". " + Arrays.toString(signatures.get(i)));
                                System.out.println("your choice: ");
                                choice = in.nextInt();

                                boolean result = dsa.verify(message.getBytes(), signatures.get(choice));
                                if (result) {
                                    System.out.println("verification success and the message can trusted");
                                } else {
                                    System.out.println("verification failed, message has been manipulated");
                                }
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                        System.out.println("process end with failure");
                    }
                }
                case 4 -> {
//                    System.out.println("length: " + dsa.publicKey().length);
                    System.out.println(Arrays.toString(dsa.publicKey()));
                }
                case 5 -> {
//                    System.out.println("length: " + dsa.privateKey().length);
                    System.out.println(Arrays.toString(dsa.privateKey()));
                }
            }
        }

    }
}