package com.arthas;

import com.lunzi.spring.stereotypa.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Component
public class DeadLock {

    Object lockA = new Object();
    Object lockB = new Object();

    public void methodA() {
        synchronized (lockA) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB) {
                System.out.println("methodA run...");
            }
        }
    }

    public void methodB() {
        synchronized (lockB) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockA) {
                System.out.println("methodB run...");
            }
        }
    }

    public static void main(String[] args) {
        DeadLock deadLock = new DeadLock();
        new Thread(() -> deadLock.methodA()).start();
        new Thread(() -> deadLock.methodB()).start();
    }



}
