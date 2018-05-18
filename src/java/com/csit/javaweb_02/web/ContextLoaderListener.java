package com.csit.javaweb_02.web;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.csit.javaweb_02.annotation.RequestMapping;
import com.csit.javaweb_02.utils.JdbcUtil;
import com.csit.javaweb_02.utils.ReflectUtil;

public class ContextLoaderListener implements ServletContextListener {
	private final String DATASOURCE = "datasource";
	private final String SCAN = "context:component-scan";
	private final String JDBCUTIL = "JdbcUtil";
	private final String JDBCURL = "jdbcurl";
	private final String DRIVERCLASS = "driverClass";
	private final String USER = "user";
	private final String PASSWORD = "password";
	public static JdbcUtil jdbcUtil;
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		System.out.println("destory...");
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// 存放初始化对象
		List<HashMap<String, Object>> objectsList = new ArrayList<HashMap<String, Object>>();
		Map<Class<?>, Map<Method, String>> urlmap =null;
		List<Map<Class<?>, Map<Method, String>>> urllist = new ArrayList<Map<Class<?>, Map<Method, String>>>();
		String path = event.getServletContext().getInitParameter("contextConfigLocation");
		ClassLoader clazzLoader = this.getClass().getClassLoader();
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(clazzLoader.getResourceAsStream(path));
			Element element = document.getRootElement();
			List<Element> beans = element.elements("bean");
			Element datasource = null;
			Element JdbcUtil = null;
			for (Element ielement : beans) {
				String name = ielement.attribute("name").getText();
				if (name.equals(DATASOURCE))
					datasource = ielement;
				if (name.equals(JDBCUTIL))
					JdbcUtil = ielement;
			}
			// 获取datasource参数
			String jdbcurl = null;
			String driverclass = null;
			String user = null;
			String password = null;
			String dataSourceClazz = datasource.attributeValue("class");
			List<Element> properties = datasource.elements("property");
			for (Element property : properties) {
				String name = property.attribute("name").getText();
				if (name.equals(JDBCURL))
					jdbcurl = property.attributeValue("value");
				if (name.equals(DRIVERCLASS))
					driverclass = property.attributeValue("value");
				if (name.equals(USER))
					user = property.attributeValue("value");
				if (name.equals(PASSWORD))
					password = property.attributeValue("value");
			}
			// 实例化datasource,并放入objectsList中
			try {
				Class dataSourceClacs = Class.forName(dataSourceClazz);
				Field jdbcurlField = dataSourceClacs.getDeclaredField(JDBCURL);
				Field driverField = dataSourceClacs.getDeclaredField(DRIVERCLASS);
				Field userField = dataSourceClacs.getDeclaredField(USER);
				Field passwordField = dataSourceClacs.getDeclaredField(PASSWORD);
				Object ob = dataSourceClacs.newInstance();
				jdbcurlField.setAccessible(true);
				jdbcurlField.set(ob, jdbcurl);
				driverField.setAccessible(true);
				driverField.set(ob, driverclass);
				userField.setAccessible(true);
				userField.set(ob, user);
				passwordField.setAccessible(true);
				passwordField.set(ob, password);
				HashMap<String, Object> dataSourceMap = new HashMap<String, Object>();
				dataSourceMap.put(DATASOURCE, ob);
				objectsList.add(dataSourceMap);
				//获取JdbcUtil属性
				String jdbcUtilClazz = JdbcUtil.attributeValue("class");
				List<Element> jdbcUtilProperties = JdbcUtil.elements("property");
				Class<?> JdbcUtilClasc = Class.forName(jdbcUtilClazz);
				JdbcUtil job = (com.csit.javaweb_02.utils.JdbcUtil) JdbcUtilClasc.newInstance();
				for(Element iElement:jdbcUtilProperties){
					String fieldName = iElement.attributeValue("name");
					String refName = iElement.attributeValue("ref");
					Field field = JdbcUtilClasc.getDeclaredField(fieldName);
					Object objj = null;
					for(HashMap<String, Object> map:objectsList){
						Set<Entry<String, Object>> set = map.entrySet();
						Entry<String, Object> imap = set.iterator().next();
						if(imap.getKey().equals(refName)){
							field.setAccessible(true);
							field.set(job, imap.getValue());
						}
					}
					
				}
				jdbcUtil=job;
				//获取注入信息
				Element scan = element.element("component-scan");
				String basepath = scan.attributeValue("base-package");
				String basepath2 = basepath.replace(".", "/");
				URL url =clazzLoader.getResource(basepath2);
				URI uri = url.toURI();
				File file = new File(uri);
				ReflectUtil reflectUtil = new ReflectUtil(basepath);
				reflectUtil.toDirectory(basepath, file);
				//迭代controllers
				for (Class<?> controller : reflectUtil.controllers) {
					RequestMapping classRequestMapping = controller.getAnnotation(RequestMapping.class);
					String parenturl = classRequestMapping.url();
					Method[] methods = controller.getDeclaredMethods();
					for (Method method : methods) {
						RequestMapping rm = method.getAnnotation(RequestMapping.class);
						if (rm != null) {
							String iurl = rm.url();
							Map map = new HashMap<Method, String>();
							map.put(method, parenturl+iurl);
							urlmap = new HashMap<Class<?>, Map<Method, String>>();
							urlmap.put(controller, map);
							urllist.add(urlmap);
						}
					}
				}
				event.getServletContext().setAttribute("urllist", urllist);
			} catch (ClassNotFoundException | NoSuchFieldException | SecurityException | InstantiationException
					| IllegalAccessException | URISyntaxException e) {
				e.printStackTrace();
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

}
