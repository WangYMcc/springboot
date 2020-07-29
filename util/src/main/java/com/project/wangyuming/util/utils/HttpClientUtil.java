package com.project.wangyuming.util.utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HttpClientUtil {
    public static void main(String[] args) {
        send("http://localhost:8888", "hello");
    }

    public static void send(String urlStr, String jsonContent){
        DataOutputStream out = null;

        try {
            URL url = new URL(urlStr);
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            //connection.setRequestProperty("connection", "close");
            //connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("Charset", "UTF-8");
            connection.setDoOutput(true);
            connection.connect();

            out = new DataOutputStream(connection.getOutputStream());

            out.write(jsonContent.getBytes());
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(out != null) {
                    out.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
    class ScoketThread implements  Runnable{
        private Socket socket;

        public ScoketThread(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
            DataInputStream inputStream = null;
            StringBuffer sb = new StringBuffer();

            try{
                while (!socket.isClosed()){
                    sb.delete(0, sb.length());
                    inputStream = new DataInputStream(socket.getInputStream());

                    byte[] bytes = new byte[1024];

                    while(inputStream.read(bytes) != -1){
                        sb.append(new String(bytes));
                    }

                   if(sb.length() > 0){
                       System.out.println(sb.toString());
                   }
                    StringBuffer response = new StringBuffer();

                    //响应状态
                    response.append("HTTP/1.1 200 OK\r\n");

                    //响应头
                    response.append("Content-type:text/html\r\n\r\n");

                    //要返回的内容(当前时间)
                    response.append("CurrentTime: ").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

                   socket.getOutputStream().write(response.toString().getBytes());
                   socket.getOutputStream().flush();
                   socket.getOutputStream().close();
                   inputStream.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                try{
                    if(inputStream != null){
                        inputStream.close();
                    }

                    if(socket != null){
                        socket.close();
                    }
                }catch (Exception e){

                }
                System.out.println("socket close");
            }
        }
    }

    public void testHttpClient(){
        ServerSocket serverSocket = null;

        try{
            serverSocket = new ServerSocket(8888);
            System.out.println("start server socket, port 8888");
            while (true){
                Socket socket = serverSocket.accept();

                new Thread(new ScoketThread(socket)).start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("end server socket, port 8888");
    }
}
