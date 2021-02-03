# 原型模式
## 克隆

比如Acopy到B中，那么A就是原型。若一个A对象创建很复杂，那么如果有业务的情况下需要一个和A一样的类B，那么我们直接使用克隆就会很方便，
然后对自己复制出来的对象进行扩展，这样对于创建复杂的对象可以提高很多效率，如（Cloneable接口 clone()方法）。

## 原型模式的步骤
定义一个类实现Cloneable接口，重写clone方法即可

> 创建原型对象

> 再根据原型对象克隆一个新的对象，以前的方式是重新new一个新的对象指向一个新的引用，现在我们直接调用clone方法即可


### 浅拷贝 demo01
定义： 对象中存在引用类型和基本类型，浅拷贝就是对基本类型进行值拷贝，改变了原型的引用类型的值，
发现复制对象的引用类型也进行拷贝了
### 深克隆 demo02 修改clone方法
定义： 深克隆的方式有序列化或者反序列化及改变clone方法，深克隆的意思就是若对象中存在引用对象，改变原型的引用对象的值，只有原型变化，而复制对象并没有引用引用的对象，而且还是之前复制的引用对象

### 应用场景
springBean :单例模式及原型模式
原型模式经常和工厂模式结合，讲工厂模式中的new对象改为克隆