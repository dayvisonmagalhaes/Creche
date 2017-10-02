package testedelayout.cursoandroid.com.creche;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by u6390869 on 01/10/2017.
 */

public class Conexao {

    public static String postDados(String urlUsuario, String parametrosUsuario){

        URL url;
        HttpURLConnection connection = null;

        try {

            url = new URL(urlUsuario);

            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");

            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

            connection.setRequestProperty("Content-Lenght","" + Integer.toString(parametrosUsuario.getBytes().length));

            connection.setRequestProperty("Content-Language","pt-BR");

            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

//            DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
//            dataOutputStream.writeBytes(parametrosUsuario);
//            dataOutputStream.flush();
//            dataOutputStream.close();

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
            outputStreamWriter.write(parametrosUsuario);
            outputStreamWriter.flush();

            InputStream inputStream = connection.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));

            String linha;
            StringBuffer resposta = new StringBuffer();

            while ((linha = bufferedReader.readLine()) != null){

                resposta.append(linha);
                resposta.append('\r');
            }

            bufferedReader.close();

            return bufferedReader.toString();



        }catch (Exception erro){

            return null;
        } finally {

            if (connection != null)
            connection.disconnect();
        }
    }
}
