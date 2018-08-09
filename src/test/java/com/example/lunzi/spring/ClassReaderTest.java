package com.example.lunzi.spring;

import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

/**
 * 主要测试asm 跟 classreader包下面的字节码读取体系
 * @Author suosong
 * @Date 2018/8/8
 */
public class ClassReaderTest implements Serializable{
    @Test
    public void test_asm() throws IOException {
        InputStream inputStream = ClassReaderTest.class.getClassLoader().getResourceAsStream("com/example/controller/TestController.class");

        ClassReader classReader = new ClassReader(inputStream);

        ClassVisitor visitor = new ClassVisitor(Opcodes.ASM4) {
            @Override
            public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
                System.out.println("version="+version);
                System.out.println("access="+access);
                System.out.println("name="+name);
                System.out.println("signature="+signature);
                System.out.println("superName="+superName);
                System.out.println("interfaces="+interfaces);
//                String interfaceName = Type.getType(interfaces[0]).getClassName();
//                System.out.println("interfaceName = "+interfaceName);
            }
        };
        classReader.accept(visitor,ClassReader.SKIP_DEBUG);
    }
}
