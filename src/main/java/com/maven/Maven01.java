package com.maven;

/**
 * @Author suosong
 * @Date 2018/8/26

 问题1： maven的groupId artifactId 的命名规则是什么，最后打成的jar包的默认命名是什么？

 问题2：maven何如打源码包

 问题3：为什么在test下定义的类，在main下读不到？
    答：对于maven项目， 有两个classpath，test下有单独的classpath，这个classpath包含main的classpath。但是反之不包含。打包时也不会被打到jar包中
        这个也可以解释为什么scope为test的jar包在main下读不到。单元测试只能放在test下。classpath的范围不一样而已。






 scope原理
    在maven中，有三种classpath， 编译主代码classpath，运行主代码classpath，编译和运行测试代码classpath。针对于这三种classpath，有几种scope。分别是
    compile:      默认的，对于以上三种classpath都有效
    test:         只对编译和运行测试代码classpath有效（src/test/java下的代码）
    provided:     对于编译classpath跟测试classpath有效，对于运行classpath无效，典型的是servlet-api jar包，因为运行的时候tomcat已经提供了
    runtime:      对运行classpath跟测试classpath有效，对于编译classpath无效。典型的是jdbc-mysql包.
    system:       对于三种classpath的关系，跟provided一样，唯一的区别就是必须通过systemPath元素显示的指定依赖文件的路径。
                    由于此类依赖不是通过maven仓库解析的，而已往往与本机系统绑定，可能造成构建的不可移植，因此应该谨慎使用。
                    systemPath元素可以使用环境变量，如：
                    <systemPath>${java.home}/lib/rt.jar</systemPath>

 传递性依赖
    把A依赖，记为A->B,  如果A->B  B->C  那么A->C 这个叫传递性依赖。因为有scope的存在，所以传递性依赖也分了很多种情况。
    把A->B 叫做第一直接依赖  B->C 叫做第二直接依赖。 下面的表哥中，第一列为第一直接依赖scope，第一行第二直接依赖scope。
                        compile         runtime         test        provided

    compile             compile         runtime
    runtime             runtime         runtime
    test                test            test
    provided            provided        provided                    provided


    总结规律：当第二直接依赖为compile的时候，传递依赖的scope就是第一直接依赖的scope
            当第二直接依赖为test的时候，不会有传递依赖
            当第二直接依赖为provided的时候，只传递第一直接依赖为provided的依赖。且传递依赖依然为provided
            当第二直接依赖为runtime的时候，其传递范围与第一直接依赖基本一致，只有第一直接依赖compile例外。此时传递性依赖为runtime

 依赖调解
    因为传递性依赖的存在，可以在很多情况下让程序员只关注于直接依赖。但有时候也会出现一些矛盾的地方。比如
    A->B->X(1.0)   A->X(2.0)
    同一个项目，不可能引入两个X jar包，这个会造成依赖重复。
    根据maven依赖调解的第一原则，路径近者优先。X(1.0)的路径是2 ，而 X(2.0)的路径是1。那么X(2.0)会被引入项目中来。
    如果路径一样会怎么办呢，在maven中，有依赖调解第二原则：第一声明者优先。谁先被声明的，谁就是传递性依赖。

 依赖排除
    A->B->X(1.0)  A->C->D->X(2.0)
    按照之前的规则，肯定是传递依赖X(1.0) ，但是我想在不增加第一直接依赖的情况下，使用X(2.0) 。那么可以使用依赖排除
    <exclusions>
        <exclusion>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
        </exclusion>
    </exclusions>

 查看所有依赖
    使用的是maven插件
    mvn dependency:list
 查看依赖树
    可以看到某个jar包到底是哪条路径加入到项目中的
    mvn dependency:tree
 分析依赖
    可以查看使用的非直接依赖包，一般来说，这种情况比较危险。如果我们的代码中import的类是通过传递依赖包含进来的话，那么如果一旦改动依赖的包，
    可能会导致项目一些错误。比如项目使用的是junit-4.12  恰好有个包依赖于此版本。那么我们完全可以不用显示依赖。项目也不会报错
    但是一旦我们更改了依赖的包，而那个包中依赖的是junit-3.8 ，那么我们的项目就报错了。尽量把我们项目中直接使用的jar包显示依赖。
    mvn dependency:analyze

 远程仓库的配置
    远程仓库除了中央仓库，还有另外两个著名的，入jboss与 java.net 。如果要从这些地方下载jar包，就需要在pom文件中配置远程仓库






 */
public class Maven01 {
}
