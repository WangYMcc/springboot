package com.project.wangyuming.wechat.util;

import com.project.wangyuming.wechat.model.ImageMessage;
import com.project.wangyuming.wechat.model.TextMessage;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import net.sf.json.JSONObject;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.io.Writer;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class XMLTo {
	// 请求消息类型：文本
	public static final String REQ_MESSAGE_TYPE_TEXT = "text";
	// 请求消息类型：图片
	public static final String REQ_MESSAGE_TYPE_IMAGE = "image";
	// 请求消息类型：语音
	public static final String REQ_MESSAGE_TYPE_VOICE = "voice";
	// 请求消息类型：视频
	public static final String REQ_MESSAGE_TYPE_VIDEO = "video";
	// 请求消息类型：地理位置
	public static final String REQ_MESSAGE_TYPE_LOCATION = "location";
	// 请求消息类型：链接
	public static final String REQ_MESSAGE_TYPE_LINK = "link";


	// 请求消息类型：事件推送
	public static final String REQ_MESSAGE_TYPE_EVENT = "event";


	// 事件类型：subscribe(订阅)
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
	// 事件类型：unsubscribe(取消订阅)
	public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
	// 事件类型：scan(用户已关注时的扫描带参数二维码)
	public static final String EVENT_TYPE_SCAN = "scan";
	// 事件类型：LOCATION(上报地理位置)
	public static final String EVENT_TYPE_LOCATION = "LOCATION";
	// 事件类型：CLICK(自定义菜单)
	public static final String EVENT_TYPE_CLICK = "CLICK";


	// 响应消息类型：文本
	public static final String RESP_MESSAGE_TYPE_TEXT = "text";
	// 响应消息类型：图片
	public static final String RESP_MESSAGE_TYPE_IMAGE = "image";
	// 响应消息类型：语音
	public static final String RESP_MESSAGE_TYPE_VOICE = "voice";
	// 响应消息类型：视频
	public static final String RESP_MESSAGE_TYPE_VIDEO = "video";
	// 响应消息类型：音乐
	public static final String RESP_MESSAGE_TYPE_MUSIC = "music";
	// 响应消息类型：图文
	public static final String RESP_MESSAGE_TYPE_NEWS = "news";


	/**
	* 解析微信发来的请求（XML）
	* 
	* @param request
	* @return Map<String, String>
	* @throws Exception
	*/
	@SuppressWarnings("unchecked")
	public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
		// 将解析结果存储在HashMap中
		Map<String, String> map = new HashMap<String, String>();
	
	
		// 从request中取得输入流
		InputStream inputStream = request.getInputStream();
		// 读取输入流
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		// 得到xml根元素
		Element root = document.getRootElement();
		// 得到根元素的所有子节点
		List<Element> elementList = root.elements();
	
		// 遍历所有子节点
		for (Element e : elementList)
		map.put(e.getName(), e.getText());
	
	
		// 释放资源
		inputStream.close();
		inputStream = null;
	
	
		return map;
	}


	/**
	* 扩展xstream使其支持CDATA
	*/
	private static XStream xstream = new XStream(new XppDriver() {
	public HierarchicalStreamWriter createWriter(Writer out) {
	return new PrettyPrintWriter(out) {
	// 对所有xml节点的转换都增加CDATA标记
	boolean cdata = true;


	@SuppressWarnings("unchecked")
	public void startNode(String name, Class clazz) {
		super.startNode(name, clazz);
		if(name.equals("CreateTime")){
			cdata = false;
	    }else {
	    	cdata = true;
	    }
	}
	
	protected void writeText(QuickWriter writer, String text) {
		if (cdata) {
			writer.write("<![CDATA[");
			writer.write(text);
			writer.write("]]>");
		} else {
			writer.write(text);
		}
	}
	};
	}
	});




	/**
	* 文本消息对象转换成xml
	* 
	* @param textMessage 文本消息对象
	* @return xml
	*/
	public static String messageToXml(TextMessage textMessage) {
		String xml = 
			"<xml>\n<ToUserName><![CDATA[" + textMessage.getToUserName() + "]]></ToUserName>";
		xml += "\n<FromUserName><![CDATA[" + textMessage.getFrUserName() + "]]></FromUserName>";
		xml += "\n<CreateTime>" + textMessage.getCreateTime() + "</CreateTime>";
		xml += "\n<MsgType><![CDATA[text]]></MsgType>";
		xml += "\n<Content><![CDATA[" + textMessage.getContent() + "]]></Content>\n</xml>";
		return xml;
	}


	/**
	* 图片消息对象转换成xml
	* 
	* @param imageMessage 图片消息对象
	* @return xml
	*/
	public static String messageToXml(ImageMessage imageMessage) {
		
		//ToUserName
		String xml = "<xml>\n<ToUserName><![CDATA[" + imageMessage.getToUserName() + "]]></ToUserName>";
		
		xml += "\n<FromUserName><![CDATA[" + imageMessage.getFrUserName() + "]]></FromUserName>";
		
		xml += "\n<CreateTime>" + imageMessage.getCreateTime() + "</CreateTime>";
		
		xml += "\n<MsgType><![CDATA[image]]></MsgType>";
		
		xml += "\n<Image>\n<MediaId><![CDATA[" + imageMessage.getMediaId() + "]]></MediaId>\n</Image>\n</xml>";
		
		return xml;
	}

	/**
	* 图文消息对象转换成xml
	* 
	* @param imageMessage 图片消息对象
	* @return xml
	*/
	public static String messageAndTextToXml(ImageAndText message) {
		xstream.alias("xml", message.getClass());
		return xstream.toXML(message);
	}

	/**
	* 语音消息对象转换成xml
	* 
	* @param voiceMessage 语音消息对象
	* @return xml
	*/
	/*public static String messageToXml(VoiceMessage voiceMessage) {
	xstream.alias("xml", voiceMessage.getClass());
	return xstream.toXML(voiceMessage);
	}*/


	/**
	* 视频消息对象转换成xml
	* 
	* @param videoMessage 视频消息对象
	* @return xml
	*/
	/*public static String messageToXml(VideoMessage videoMessage) {
	xstream.alias("xml", videoMessage.getClass());
	return xstream.toXML(videoMessage);
	}*/


	/**
	* 音乐消息对象转换成xml
	* 
	* @param musicMessage 音乐消息对象
	* @return xml
	*/
	/*public static String messageToXml(MusicMessage musicMessage) {
	xstream.alias("xml", musicMessage.getClass());
	return xstream.toXML(musicMessage);
	}*/


	/**
	* 图文消息对象转换成xml
	* 
	* @param newsMessage 图文消息对象
	* @return xml
	*/
	public static String messageToXml(ImageAndText message) {
		EntitySet<Item> items = message.getArticles();
		
		
		String xml = "<xml>\n<ToUserName><![CDATA[" + message.getToUserName() + "]]></ToUserName>";
		
		xml += "\n<FromUserName><![CDATA[" + message.getFrUserName() + "]]></FromUserName>";
		
		xml += "\n<CreateTime>" + (new Date().getTime()+"").substring(0, 10) + "</CreateTime>";
		
		xml += "\n<MsgType><![CDATA[news]]></MsgType>";
		
		xml += "\n<ArticleCount>" + items.size()  + "</ArticleCount>\n<Articles>";
		
		for (Item item : items) {
			xml += "\n<item>\n<Title><![CDATA[" + item.getTitle()  + "]]></Title>";
			
			xml += "\n<Description><![CDATA[" + item.getDescription() + "]]></Description>";
			
			xml += "\n<PicUrl><![CDATA[" + item.getPicUrl() + "]]></PicUrl>";
			
			xml += "\n<Url><![CDATA[" + item.getUrl() + "]]></Url>\n</item>";
			
		}
		
		xml += "</Articles></xml>";
		
		return xml;
	}
	
	public static String getDateTime(){
		Date date = new Date();   
		  
		Timestamp timestamp = new Timestamp(date.getTime());
		
		return timestamp.toLocaleString();
	}
	

	public void test(){
		//ImageMessage imageMessage = new ImageMessage();
		
		//System.out.println(messageToXml(imageMessage));
		//TextMessage message = new TextMessage();
		/*ImageAndText message = new ImageAndText();
		System.out.println(messageToXml(message));*/
		/*JSONObject json = new JSONObject();
		json.put("s", "s");
		System.out.println(json.getString("a") == null);*/
	}
	}
