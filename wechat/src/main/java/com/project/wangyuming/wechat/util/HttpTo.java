package com.project.wangyuming.wechat.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


public class HttpTo {
    /**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            /*for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }*/
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    public static InputStream sendGetRequestFile(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            /*for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }*/
            // 定义 BufferedReader输入流来读取URL的响应
            
            return connection.getInputStream();
           
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }
    
    public static InputStream getInputStream(String url) {
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
    
    public void saveImage(String picUrl){
		
		InputStream inputStream = this.sendGetRequestFile(picUrl, "");
		
		try {
			File file = new File("E:\\weixin\\image", "12123" + ".jpg");
			
			OutputStream os = new FileOutputStream(file);
    		byte[] buffer = new byte[1024];
    		int length = 0;
    		
    		while (-1 != (length = inputStream.read(buffer))) 
    			os.write(buffer);
    		
    		inputStream.close();
    		os.flush();
    		os.close();
    		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }    
    
   
    public static void main(String[] args)
    {
 
        String Access_token = "";
        // String open_id = "oe7rSjlz1flhx7HP3-DnlgrpobqM";
        
        String token = "5_GncB2J--I66X2A7cS_s2oiE6CDBVDEu-IPAOqXcgKIgrTP7wlPNusG3bZWMpPZmHqatcSukb6gp92iP2fcCLLiDveolZfdyPgiBoMqCGCHVvQskCVTEYnifcWsHn1LGmjbj1DW-DQSBAeeylZUTeAHAJED";
       /* String strJson = "{\"touser\" :\"oj3TO1K7ziZOpfiUrIE50v4_sFRc\",";
        strJson += "\"msgtype\":\"text\",";
        strJson += "\"text\":{";
        strJson += "\"content\":\"Hello World\"";
        strJson += "}}";
        String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?&body=0&access_token=" + token;
 
        System.out.println(url);*/
        /*postModelMessage(token, "oj3TO1K7ziZOpfiUrIE50v4_sFRc","测试流程审批", "admin", 2L, "测试机构",
        		"待审批");*/
        
        String url = "https://open.qingtui.im/v1/message/mass/send?access_token=" + token;
        String json = "{touser\":[\"e2679b2d60b14221b9c3233c2aed60a8\"],\"text\":{\"content\":\"测试\""
        +"},\"msgtype\":\"text\"}";
        
        //postModelMessage(token,"oj3TO1K7ziZOpfiUrIE50v4_sFRc","2","2",1l,"s", "s");
        Map<String, String> map = new HashMap<String, String>();
        map.put("PicUrl", "http://mmbiz.qpic.cn/mmbiz_jpg/sr5ko3q7p9fV79oXn4rlCLuSFbP3WiaOib2h3QNpRIbCG7UAFubY1lvdicm7PLq0AxEjqcicxa4VUfUpQZzDZiaR8icg/0");
        map.put("MsgId", "12312321");
        
        String url2 = "http://mmbiz.qpic.cn/mmbiz_jpg/sr5ko3q7p9cGFPH68zuTUg5vBmf6oGnYxXwJZiawOKPtnm3dH7Nkjw6JWfFrssGDTyyQJEKOY2LIZazIkMzAaqA/0";
        
        InputStream in = getInputStream(url2);
        saveData(in, new File("e:\\weixin\\image\\qqq.jpg"));
    }
 

    
    /**
     * 发送消息
     * */
    public static void post(String url, String json)
    {
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        try
        {
            StringEntity s = new StringEntity(json);
            s.setContentEncoding("utf-8");
            s.setContentType("application/json");
            post.setEntity(s);
 
            HttpResponse res = client.execute(post);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
            {
                HttpEntity entity = res.getEntity();
                System.out.println(EntityUtils.toString(entity, "utf-8"));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public static void publicstaticvoidsend_Json(String params,String url){
        StringBuffer bufferRes =new StringBuffer();
        try {
                URL realUrl = new URL(url);
                HttpURLConnection conn = (HttpURLConnection)realUrl.openConnection();
                // 请求方式
                conn.setDoOutput(true);
                conn.setDoInput(true);
                conn.setRequestMethod("GET");
                conn.setUseCaches(false);
                conn.setInstanceFollowRedirects(true);
                conn.setRequestProperty("Content-Type","application/json");
                conn.connect();
                // 获取URLConnection对象对应的输出流
                OutputStreamWriter out =new OutputStreamWriter(conn.getOutputStream(),"UTF-8");
                // 发送请求参数
                //out.write(URLEncoder.encode(params,"UTF-8"));
                //发送json包
                out.write(params);
                out.flush();
                out.close();
                InputStream in =conn.getInputStream();
                BufferedReader read =new BufferedReader(new InputStreamReader(in,"UTF-8"));
                String valueString =null;
                while ((valueString=read.readLine())!=null){
                        bufferRes.append(valueString);
                }
                //输出返回的json
                System.out.println(bufferRes);
                in.close();
                if (conn !=null){
                    // 关闭连接
                    conn.disconnect();
                }
	        } catch (Exception e) {
	                e.printStackTrace();
	        }
    	}
    
    public static void saveImageFile(Map<String, String> map) throws Exception{    
        int one=1;  
        int two=1; 
        int i = 0;
        while(true){  
          
	        //new一个URL对象    
	        URL url = new URL(map.get("PicUrl")+"");    
	        //打开链接    
	        HttpURLConnection conn = (HttpURLConnection)url.openConnection();    
	        //设置请求方式为"GET"    
	        conn.setRequestMethod("GET");    
	        //超时响应时间为5秒    
	       // conn.setConnectTimeout(5 * 1000);    
	        //通过输入流获取图片数据    
	        InputStream inStream=null;  
	        try{  
	        	inStream = conn.getInputStream();    
	        }catch (Exception e) {  
               System.out.println("Error"+one+","+two);  
               System.out.println("///");  
               if(two==20){  
            	   one++;  
            	   two=1;  
               }else{  
            	   two++;  
               }  
               if(one==21){  
            	   break;  
               }  
               continue;  
	        }  
	        //得到图片的二进制数据，以二进制封装得到数据，具有通用性    
	        byte[] data = readInputStream(inStream);    
	        //new一个文件对象用来保存图片，默认保存当前工程根目录    
	        File imageFile = new File("E:\\weixin\\image", map.get("MsgId") + ".jpg");    
         
	        //创建输出流    
	        FileOutputStream outStream = new FileOutputStream(imageFile);    
	        //写入数据    
	        outStream.write(data);    
	        //关闭输出流    
	        outStream.close();    
          
	        if(two==20){  
	        	one++;  
	        	two=1;  
	        }else{  
	        	two++;  
	        }  
	        if(one==20){  
	        	break;  
	        }  
          System.out.println(++i);
        } 
        System.out.println("success");
          
    }    
    public static byte[] readInputStream(InputStream inStream) throws Exception{    
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
    
    /** 
	   * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址, 
	   * 
	   * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？ 
	   * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。 
	   * 
	   * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130, 
	   * 192.168.1.100 
	   * 
	   * 用户真实IP为： 192.168.1.110 
	   * 
	   * @param request 
	   * @return 
	   */
	  public static String getIpAddress(HttpServletRequest request) { 
	    String ip = request.getHeader("x-forwarded-for"); 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("Proxy-Client-IP"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("WL-Proxy-Client-IP"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("HTTP_CLIENT_IP"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("HTTP_X_FORWARDED_FOR"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getRemoteAddr(); 
	    } 
	    return ip; 
	  } 

}
