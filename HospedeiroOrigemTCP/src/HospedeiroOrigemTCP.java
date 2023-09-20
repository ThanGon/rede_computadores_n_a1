import java.net.*;
import java.io.*;

public class HospedeiroOrigemTCP {
    public static void main(String[] args) throws Exception {
        System.out.println("Insira o Ip do hospedeiro de destino:");
        BufferedReader ipDestinoReader = new BufferedReader(new InputStreamReader(System.in));
        InetAddress ipDestino = InetAddress.getByName(ipDestinoReader.readLine());

        System.out.println("Insira a Porta do hospedeiro de destino:");
        BufferedReader portaDestinoReader = new BufferedReader(new InputStreamReader(System.in));
        int portaDestino = Integer.parseInt(portaDestinoReader.readLine());

        System.out.println(ipDestino);

        Socket clienteSocket = new Socket(ipDestino, portaDestino);
        BufferedOutputStream saida = new BufferedOutputStream(clienteSocket.getOutputStream());
        String teste = "Hello World!";
        saida.flush();
        saida.write(teste.getBytes(), 0, teste.getBytes().length);
        saida.close();
    }
}
