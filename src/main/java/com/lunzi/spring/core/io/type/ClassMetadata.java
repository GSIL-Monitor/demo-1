package com.lunzi.spring.core.io.type;

public interface ClassMetadata {

    String getClassName();
    boolean isAbstract();
    boolean isFinal();
    boolean isInterface();
    String getSuperClassName();
    String[] getInterfaceNames();


}
