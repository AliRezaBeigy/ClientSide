import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Main {

    private static Socket client;
    private static ObjectInputStream in;
    private static ObjectOutputStream out;

    private static void startConnection() throws IOException, ClassNotFoundException {
        client = new Socket("127.0.0.1", 15232);
        out = new ObjectOutputStream(client.getOutputStream());
        in = new ObjectInputStream(client.getInputStream());

        sendAnswer(1, 0);
        sendAnswer(2, 1);

        long a1 = 0, a2 = 1, a3;
        for (int i = 3; i < 1000; i++) {
            if (sendAnswer(i, a3 = (a1 + a2)))
                return;
            a1 = a2;
            a2 = a3;
        }
    }

    private static boolean sendAnswer(int level, long answer) throws IOException, ClassNotFoundException {
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Level: " + level + ", Answer: " + answer);
        DataModal dataModal = new DataModal.Builder().level(level).answer(answer).build();
        out.writeObject(dataModal);

        ResponseModal response = (ResponseModal) in.readObject();
        System.out.println("is successful: " + response.isSuccessful() + ", is finished: " + response.isFinished() + ", Message: " + response.getMessage());
        return response.isFinished();
    }

    private static void stopConnection() throws IOException {
        in.close();
        out.close();
        client.close();
    }

    public static void main(String[] args) {
        try {
            startConnection();
            stopConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
