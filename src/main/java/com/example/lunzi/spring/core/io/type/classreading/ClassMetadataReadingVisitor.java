package com.example.lunzi.spring.core.io.type.classreading;

import com.example.lunzi.spring.utils.ClassUtils;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * asm的观察者
 * @Author suosong
 * @Date 2018/8/10
 */
public class ClassMetadataReadingVisitor extends ClassVisitor{

    String className;
    boolean isAbstract;
    boolean isFinal;
    boolean isInterface;
    String superClassName;
    String[] interfaces;



    public ClassMetadataReadingVisitor() {
        super(Opcodes.ASM4);//就是这么写死的
    }

    /**
     * 解析类
     * @param version
     * @param access
     * @param name 类路径
     * @param signature
     * @param superName
     * @param interfaces
     */
    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {

        this.className = ClassUtils.convertResourcePathToClassName(name);
        this.isAbstract = (access & Opcodes.ACC_ABSTRACT) != 0;
        this.isFinal = (access & Opcodes.ACC_FINAL) != 0;
        this.isInterface = (access & Opcodes.ACC_INTERFACE) != 0;
        if(superName != null)
            this.superClassName = ClassUtils.convertResourcePathToClassName(superName);

        if(interfaces != null){
            this.interfaces = new String[interfaces.length];
            for(int i = 0 ; i< interfaces.length;i++){
                this.interfaces[i] = ClassUtils.convertResourcePathToClassName(interfaces[i]);
            }
        }

    }

    /**
     * 解析属性
     * @param access
     * @param name
     * @param desc
     * @param signature
     * @param value
     * @return
     */
    @Override
    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        return super.visitField(access, name, desc, signature, value);
    }

    /**
     * 解析方法
     * @param access
     * @param name
     * @param desc
     * @param signature
     * @param exceptions
     * @return
     */
    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        return super.visitMethod(access, name, desc, signature, exceptions);
    }
}
