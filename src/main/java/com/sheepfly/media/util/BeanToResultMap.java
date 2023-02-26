package com.sheepfly.media.util;

import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.dom4j.tree.DefaultElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.OutputStreamWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * 将bean转换为ResultMap。
 *
 * <p>转换后的resultMap中，属性不一定按声明顺序排列。关联属性需要手动指定property。</p>
 *
 * @author sheepfly
 */
public class BeanToResultMap {
    private static final Logger log = LoggerFactory.getLogger(BeanToResultMap.class);
    private static String packageName;
    private static Class<?> beanClass;

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            log.error("错误");
            System.exit(1);
        }
        packageName = args[0];
        Scanner scanner = new Scanner(System.in);
        log.info("输入全类名:");
        String clazzName = scanner.nextLine();
        log.info("全类名:" + clazzName);
        beanClass = Class.forName(clazzName);
        Element resultMap = clazzToResultMap(beanClass);
        OutputFormat prettyPrint = OutputFormat.createPrettyPrint();
        XMLWriter xmlWriter = new XMLWriter(new OutputStreamWriter(System.out), prettyPrint);
        xmlWriter.write(resultMap);
        xmlWriter.flush();
    }

    /**
     * 将类转换为resultMap。
     *
     * @param clazz 要转换的类
     *
     * @return resultMap
     */
    private static Element clazzToResultMap(Class<?> clazz) {
        Method[] methods = clazz.getMethods();
        Element resultMap;
        if (clazz == beanClass) {
            resultMap = new DefaultElement("resultMap");
            resultMap.addAttribute("id", clazz.getSimpleName());
            resultMap.addAttribute("type", clazz.getName());
        } else {
            resultMap = new DefaultElement("association");
            resultMap.addAttribute("javaType", clazz.getName());
        }
        resultMap.addAttribute("autoMapping", "true");
        List<Element> associationList = new ArrayList<>();
        List<String> methodNameList = new ArrayList<>();
        Arrays.stream(methods).forEach(ele -> methodNameList.add(ele.getName()));
        Stream<String> sortedMethodNames = methodNameList.stream().sorted();
        sortedMethodNames.forEach(methodName -> {
            if (methodName.startsWith("set")) {
                Method method = null;
                for (Method ele : methods) {
                    if (methodName.equals(ele.getName())) {
                        method = ele;
                    }
                }
                Class<?>[] parameterTypes = method.getParameterTypes();
                Class<?> type = parameterTypes[0];
                if (type.getPackage().getName().contains(packageName)) {
                    // 当前属性是嵌套对象
                    associationList.add(clazzToResultMap(type));
                } else {
                    Element result = new DefaultElement("result");
                    String property = getProperty(method.getName());
                    result.addAttribute("property", property);
                    result.addAttribute("column", property);
                    resultMap.add(result);
                }
            }
        });
        associationList.forEach(resultMap::add);
        return resultMap;
    }

    private static String getProperty(String setter) {
        String field = setter.substring(3);
        return field.substring(0, 1).toLowerCase() + field.substring(1);
    }
}
