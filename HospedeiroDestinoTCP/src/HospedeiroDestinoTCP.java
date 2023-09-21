package HospedeiroDestinoTCP.src;

import java.io.*;

import java.net.*;



public class HospedeiroDestinoTCP {

    public static void main(String[] args) {

        try {

            // Cria um novo servidor na porta 7777

            ServerSocket server = new ServerSocket(7777);

            System.out.println("Servidor iniciado na porta 7777");


            // Aceita uma conexão de um cliente

            Socket socket = server.accept();

            System.out.println("Conexão estabelecida");

            // Cria um DataInputStream para ler os dados enviados pelo cliente

            DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

            // Lê o nome do arquivo que o cliente quer

            String namefile = in.readUTF();


            // Cria um objeto File para o arquivo que será enviado

            File file = new File(namefile);

            // Cria um array de bytes para armazenar os dados do arquivo

            byte[] byteArray = new byte[(int) file.length()];


            // Cria um FileInputStream para ler os dados do arquivo

            FileInputStream fis = new FileInputStream(file);

            // Cria um BufferedInputStream para ler os dados do FileInputStream

            BufferedInputStream bis = new BufferedInputStream(fis);

            // Lê os dados do arquivo para o array de bytes

            bis.read(byteArray, 0, byteArray.length);


            // Cria um OutputStream para enviar os dados para o cliente

            OutputStream os = socket.getOutputStream();


            // Registra o tempo inicial antes de enviar o arquivo

            long startTime = System.nanoTime();

            // Envia os dados do arquivo para o cliente

            os.write(byteArray, 0, byteArray.length);

            // Limpa o OutputStream, garantindo que todos os dados sejam enviados

            os.flush();

            // Registra o tempo final após enviar o arquivo

            long endTime = System.nanoTime();


            // Calcula a duração da transferência em segundos

            double durationInSeconds = (endTime - startTime) / 1e9;

            // Calcula o tamanho do arquivo em bits

            double fileSizeInBits = file.length() * 8;

            // Calcula a taxa de transferência em Mbps

            double transferRateMbps = fileSizeInBits / durationInSeconds / 1e6;


            System.out.println("Arquivo enviado!");

            

             // printa a taxa de transferência

             System.out.println("Taxa de transferência: " + transferRateMbps + " Mbps");

        } catch (IOException e) {

             e.printStackTrace();

        }

    }

}

