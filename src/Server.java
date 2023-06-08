import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedWriter bufferedWriter = null;
        BufferedReader bufferedReader = null;

        ServerSocket serverSocket = null;

        serverSocket = new ServerSocket(8000);

        while (true){

            try{
                socket = serverSocket.accept();


                inputStreamReader = new InputStreamReader(socket.getInputStream());
                outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
                bufferedReader = new BufferedReader(inputStreamReader);
                bufferedWriter = new BufferedWriter(outputStreamWriter);


                while (true){

                    String msgDoCliente = bufferedReader.readLine();

                    System.out.println("Cliente: " + msgDoCliente);

                    bufferedWriter.write("MSG Recebida.");
                    bufferedWriter.newLine();
                    bufferedWriter.flush();

                    if (msgDoCliente.equalsIgnoreCase("Tchau"))
                        break;
                }

                socket.close();
                inputStreamReader.close();
                outputStreamWriter.close();
                bufferedWriter.close();
                bufferedReader.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
