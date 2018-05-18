package com.csit.javaweb_02.utils;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.csit.javaweb_02.annotation.Componet;
import com.csit.javaweb_02.annotation.Controller;
import com.csit.javaweb_02.annotation.Inject;
import com.csit.javaweb_02.annotation.Repository;
import com.csit.javaweb_02.annotation.Service;
import com.csit.javaweb_02.web.ContextLoaderListener;



public class ReflectUtil {
	public static List<Class<?>> componets = new ArrayList<Class<?>>();
	public static List<Class<?>> services = new ArrayList<Class<?>>();
	public static List<Class<?>> repositories = new ArrayList<Class<?>>();
	public static List<Class<?>> controllers = new ArrayList<Class<?>>();
	public static String packagePath;
	public ReflectUtil(String packagePath){
		this.packagePath =packagePath;
	}
	/**
	 * 迭代找出所有.class文件，将路径拼成com.csit.java_06.test2.CommonDaoImpl
	 * 
	 * @param ipackage
	 * @param directory
	 */
	public static void toDirectory(String ipackage, File directory) {
		if (ipackage.contains("*"))
			ipackage = ipackage.substring(0, ipackage.lastIndexOf("."));
		// com.csit.java_06
		// ipackage = ipackage.substring(0, ipackage.lastIndexOf("."));
		// if(directory.isDirectory())
		// ipackage = ipackage + "." + directory.getName();
		for (File efile : directory.listFiles()) {

			if (!efile.isDirectory()) {
				if (efile.getName().endsWith(".class")) {
					String clazzName = efile.getName().substring(0, efile.getName().length() - 6);
					// ipackage = ipackage + "." + clazzName;
					clazzName = ipackage + "." + clazzName;
					// //System.out.println(clazzName);
					refluct(clazzName);
				}
			} else {
				// com.csit.java_06.test2
				// ipackage = ipackage.substring(0, ipackage.lastIndexOf("."));
				// ipackage = ipackage + "." + efile.getName();
				ipackage = packagePath + "." + efile.getName();
				toDirectory(ipackage, efile);
			}

		}

	}

	public static void refluct(String clazzName) {
		try {
			Class clazz = Class.forName(clazzName);
			Componet componet = (Componet) clazz.getAnnotation(Componet.class);
			if (componet != null) {
				componets.add(clazz);
			}
			Controller controllerComponet = (Controller) clazz.getAnnotation(Controller.class);
			if (controllerComponet != null) {
				controllers.add(clazz);
			}
			Service Servicecomponet = (Service) clazz.getAnnotation(Service.class);
			if (Servicecomponet != null) {
				services.add(clazz);
			}
			Repository repositoryComponet = (Repository) clazz.getAnnotation(Repository.class);
			if (repositoryComponet != null) {
				repositories.add(clazz);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Object get(Class clazz) {
		Object ob = null;
		try {
			ob = clazz.newInstance();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			Inject inject = field.getAnnotation(Inject.class);
			if (inject != null) {
				for (Class<?> clazn : componets) {
					if(field.getType().getName().equals("com.csit.javaweb_02.utils.JdbcUtil")){
						try {
							field.setAccessible(true);
							field.set(ob, ContextLoaderListener.jdbcUtil);
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						}
						break;
					}
					else if(clazn == field.getType()) {
						try {
							field.setAccessible(true);
							field.set(ob, ReflectUtil.get(clazn));
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						}
						break;
					}
				}
				for (Class<?> clazn : controllers) {
					if (clazn == field.getType()) {
						try {
							field.setAccessible(true);
							field.set(ob, ReflectUtil.get(clazn));
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} 
						break;
					}
				}
				for (Class<?> clazn : services) {
					if (field.getType().isAssignableFrom(clazn)) {
						try {
							field.setAccessible(true);
							field.set(ob, new TransationProxy().bind(ReflectUtil.get(clazn)));
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						}
						break;
					}
				}
				for (Class<?> clazn : repositories) {
					if (clazn == field.getType()) {
						try {
							field.setAccessible(true);
							field.set(ob, ReflectUtil.get(clazn));
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} 
						break;
					}
				}

			}
		}
		System.out.println(ob);
		return ob;
	}

}
