package com.project.wangyuming.wechat.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Map;

public class SaveImage implements Runnable {
	private Map<String, String> map;
	
	public SaveImage(Map<String, String> map){
		this.map = map;
	}
	
	@Override
	public void run() {
		try {
			 File imageFile = new File("C:\\weixin\\image", map.get("MsgId") + ".jpg");
			 InputStream in = getInputStream(map.get("PicUrl")+"");
			 
			 saveData(in, imageFile);
			 System.out.println("保存文件成功！" + new Date().toLocaleString());
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}

	}
	
	private InputStream getInputStream(String url) {
    	try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url)
                    .openConnection();
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = conn.getInputStream();
                return inputStream;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    	
    }
	
	public static void saveData(InputStream is, File file) {
        try (BufferedInputStream bis = new BufferedInputStream(is);
                BufferedOutputStream bos = new BufferedOutputStream(
                        new FileOutputStream(file));) {
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
                bos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
    private byte[] readInputStream(InputStream inStream) throws Exception{    
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();    
        //创建一个Buffer字符串    
        byte[] buffer = new byte[1024];    
        //每次读取的字符串长度，如果为-1，代表全部读取完毕    
        int len = 0;    
        //使用一个输入流从buffer里把数据读取出来    
        while( (len=inStream.read(buffer)) != -1 ){    
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度    
            outStream.write(buffer, 0, len);    
        }    
        //关闭输入流    
        inStream.close();    
        //把outStream里的数据写入内存    
        return outStream.toByteArray();    
    } 

}
