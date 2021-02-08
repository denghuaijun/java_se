package com.denghj.注解and反射.reflect;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.stream.Stream;


public class TestReflect {

    /**
     * 测试创建类Class对象的方式
     */
    @Test
    public void getClassIns() throws ClassNotFoundException {
        //1、通过类名点Class
        Class<User> userClass = User.class;
        //2、通过对象的getClass方法
        User user = new User();
        Class<? extends User> aClass = user.getClass();
        //3、通过类的全路径名
        Class<?> aClass1 = Class.forName("com.denghj.注解and反射.reflect.User");
    }
    /**
     * 以Person为例测试,获取类的Class对象
     */
    @Test
    public void testPersonClass() throws ClassNotFoundException {
        Person person = new Student();
        //1、使用对象的getClass
        Class<? extends Person> c1 = person.getClass();
        System.out.printf("c1===", c1.hashCode());
        //2、使用全类路径
        Class<?> c2 = Class.forName("com.denghj.注解and反射.reflect.Student");
        System.out.printf("c2===", c2.hashCode());
        //3、使用类名的Class
         Class<Student> c3 = Student.class;
        System.out.printf("c3==", c3.hashCode());
         //4、基本内置类型的包装类都有一个Type属性
        Class<Integer> type = Integer.TYPE;
        System.out.printf("type===", type.hashCode());
        //5、获取父类的Class
        Class<?> c1Superclass = c1.getSuperclass();
        System.out.printf("c1Superclass==", c1Superclass.hashCode());

    }
    /**
     * 获取我们的系统加载器
     * 1、根/引导加载器，加载类的核心类库的，是无法直接获取的
     * 2、扩展类加载器
     * 3、系统类加载器
     */
    @Test
    public void test(){
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println("系统类加载器=："+systemClassLoader);
        ClassLoader Extparent = systemClassLoader.getParent();
        System.out.println("扩展类加载器："+Extparent);
        System.out.println("根加载器："+Extparent.getParent());
        /**
         * 系统类加载器=：sun.misc.Launcher$AppClassLoader@58644d46
         * 扩展类加载器：sun.misc.Launcher$ExtClassLoader@4a574795
         * 根加载器：null
         */
        //c而是指定类的类加载器
        Class<User> userClass = User.class;
        System.out.println("user类的类加载器："+userClass.getClassLoader());
        //测试JDK内部类的类加载器
        System.out.println("JDK内部类加载器："+Object.class.getClassLoader());//null 说明时根加载器
    }

    /**
     * 获取类的信息 fied 只能访问公共public的资源，declared可以访问所有的包括私有的资源
     *
     */
    @Test
    public void getClassInfo() throws ClassNotFoundException {
        Class<?> aClass = Class.forName("com.denghj.注解and反射.reflect.User");
        Field[] fields = aClass.getDeclaredFields();
        aClass.getFields();
        aClass.getMethods();
        aClass.getDeclaredMethods();
        for (Field field : fields) {
            System.out.println(field.getName());
        }
    }
    /**
     * 测试动态创建对象执行方法
     */
    @Test
    public void testReflect() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        Class<?> aClass = Class.forName("com.denghj.注解and反射.reflect.User");
        //1、使用无参构造利用反射创建对象示例
        User user = (User) aClass.newInstance();
        System.out.println(user.toString());
        Method[] methods = aClass.getMethods();
        Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
        System.out.println("--------------declaredMethod-----------");
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod);
        }
        Field[] fields = aClass.getFields();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field);
        }
        System.out.println("..................declaredFields...............");
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField);
        }
        System.out.println("使用构造器创建对象实例。。。。。");
        Constructor<?>[] constructors = aClass.getConstructors();
        Stream.of(constructors).forEach(constructor -> System.out.println(constructor));
        Constructor<?> constructor = aClass.getConstructor(int.class, int.class, String.class);
        System.out.println(constructor);
        User instance = (User) constructor.newInstance(1, 20, "邓怀俊");
        System.out.println("有参创建对象："+instance.toString());

        Field userName = aClass.getDeclaredField("userName");
        userName.setAccessible(true);
        userName.set(instance,"ddd");
        System.out.println(instance.getUserName());

        Method method = aClass.getMethod("setAge", int.class);
        method.invoke(instance,30);
        System.out.println(instance.toString());

    }

}
