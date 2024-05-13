package cn.hutool.jackie.algorithm;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

/**
 * @author rcao1
 */
public class CaseRunner {

    private String method;
    private String args;
    List<String> methodList;
    List<JSONArray> argsObject;

    public CaseRunner(String method, String args) {
        this.method = method;
        this.args = args;
        initRunner();
    }

    private void initRunner() {
        this.methodList = JSON.parseArray(this.method, String.class);
        this.argsObject = JSON.parseArray(this.args, JSONArray.class);
    }

    public void run(Class clazz) {
        try {
            Object instance = null;
            JSONArray result = new JSONArray();
            for (int i = 0; i < methodList.size(); i++) {
                String methodName = methodList.get(i);
                JSONArray args = argsObject.get(i);
                if (i == 0) {
                    Constructor constructor = getConstructor(clazz, methodName);
                    instance = constructor.newInstance(buildArgs(args, constructor.getParameterTypes()));
                    result.add(null);
                } else {
                    Method method = getMethod(clazz, methodName);
                    Object rsp = method.invoke(instance, buildArgs(args, method.getParameterTypes()));
                    if (rsp instanceof int[]) {
                        result.add(Arrays.toString((int[]) rsp));
                    } else {
                        result.add(rsp);
                    }
                }
            }
            System.out.println(result);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private Object[] buildArgs(JSONArray args, Class<?>[] parameterTypes) {
        Object[] constructorArgs = new Object[args.size()];
        for (int i = 0; i < args.size(); i++) {
            Object element = args.get(i);
            Class parameterType = parameterTypes[i];
            constructorArgs[i] = JSON.parseObject(JSON.toJSONString(element), parameterType);
        }
        return constructorArgs;
    }

    private Constructor getConstructor(Class clazz, String name) {
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor constructor : constructors) {
            if (constructor.getName().equals(clazz.getName())) {
                return constructor;
            }
        }
        return null;
    }

    private Method getMethod(Class clazz, String name) {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals(name)) {
                return method;
            }
        }
        return null;
    }
}
