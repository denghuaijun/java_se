# 注解与反射学习
## 注解 java.annotation 注释解释

* Annotation是从JDK5开始引入的技术。
* 注解的作用：
   1. 不是程序本身，可以对程序做出解释。（这一点和注释（comment）没什么区别）
   2. 可以被其他的程序如编译器等读取
* Annotation的格式：
    注解是以“@注释名”在代码中存在的，还可以添加一些参数值，例如：@SuppressWarings（value=“unchecked”）
    
* Annotation在哪里使用？
    可以附加在package，class，method，field 等上面，相当于给他们添加了额外的辅助信息，也可以作为方法的检查和约束，我们可以通过反射机制编程实现对这些元数据的访问。
    
## 注解的分类
  1. 内置注解 @Override @Deprecated @SuppressWarning
  2. 元注解：主要负责注解其它注解
     >  @Target: 用于描述注解的使用范围（即：被描述的注解可以用在什么地方）
                         
     >  @Retention: 表示需要在什么级别保存该注释信息，用于描述注解的生命周期（SOURCE<CLASS< RUNTIME）
     
     >  @Document： 说明该注解将被包含在javadoc中
                                                                                                                                                                                                                                                                             
     >  @Inherited: 说明子类可以继承父类中的该注解                                                                                            
  3. 自定义注解
    
     > 使用@interface自定义注解时，自动继承了java.lang.annotation.Annotation接口
     
     > 分析：
        1. @interface用来声明一个注解，格式：public @interface 注解名{参数类型+参数名+()+default+" "/0/-1}
        2. 其中的每一个方法实际上是声明了一个注解的配置参数
        3. 方法的名称就是注解的参数名称
        4. 返回值类型就是参数类型
        5. 可以通过default来声明参数的默认值
        6. 如果只有一个参数成员，那么一般将参数名定义为value
        7. 注解元素必须要有值，我们定义注解元素时，经常使用空字符串和0作为默认值，如果默认值为-1，代表不存在