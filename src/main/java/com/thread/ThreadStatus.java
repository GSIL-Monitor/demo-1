package com.thread;


import java.util.concurrent.locks.LockSupport;

public class ThreadStatus {
    public enum State {
        /**
         * Thread state for a thread which has not yet started.
         */
        NEW,

        /**
         * 运行中，也可能是准备运行（比如等待cpu资源）
         *
         * Thread state for a runnable thread.  A thread in the runnable
         * state is executing in the Java virtual machine but it may
         * be waiting for other resources from the operating system
         * such as processor.
         */
        RUNNABLE,

        /**
         * 我认为引起的原因只有一个，synchronize关键字
         * 处于BLOCKED状态的线程，即使对其调用 thread.interrupt()也无法改变其阻塞状态，因为interrupt()方法只是设置线程的中断状态，即做一个标记，不能唤醒处于阻塞状态的线程
         * 重要的是，BLOCKED 线程会占用cpu时间，判断是否其获得了锁
         *
         * 当一个线程要进入synchronized语句块/方法时，如果没有获取到锁，会变成BLOCKED
         * 或者在调用Object.wait()后，被notify()唤醒，再次进入synchronized语句块/方法时，如果没有获取到锁，会变成BLOCKED
         *
         * Thread state for a thread blocked waiting for a monitor lock.
         * A thread in the blocked state is waiting for a monitor lock
         * to enter a synchronized block/method or
         * reenter a synchronized block/method after calling
         * {@link Object#wait() Object.wait}.
         */
        BLOCKED,

        /**
         *
         * 无线等待，引起的原因有3个
         * wait()
         * join()
         * LockSupport.park()
         * 重要的是，此状态的线程不会获得cpu时间，只能被动的被唤醒。
         * ReentrantLock.lock()操作后进入的是WAITING状态，其内部调用的是LockSupport.park()方法
         *
         *
         * Thread state for a waiting thread.
         * A thread is in the waiting state due to calling one of the
         * following methods:
         * <ul>
         *   <li>{@link Object#wait() Object.wait} with no timeout</li>
         *   <li>{@link #join() Thread.join} with no timeout</li>
         *   <li>{@link LockSupport#park() LockSupport.park}</li>
         * </ul>
         *
         * <p>A thread in the waiting state is waiting for another thread to
         * perform a particular action.
         *
         * For example, a thread that has called <tt>Object.wait()</tt>
         * on an object is waiting for another thread to call
         * <tt>Object.notify()</tt> or <tt>Object.notifyAll()</tt> on
         * that object. A thread that has called <tt>Thread.join()</tt>
         * is waiting for a specified thread to terminate.
         */
        WAITING,

        /**
         * Thread state for a waiting thread with a specified waiting time.
         * A thread is in the timed waiting state due to calling one of
         * the following methods with a specified positive waiting time:
         * <ul>
         *   <li>{@link #sleep Thread.sleep}</li>
         *   <li>{@link Object#wait(long) Object.wait} with timeout</li>
         *   <li>{@link #join(long) Thread.join} with timeout</li>
         *   <li>{@link LockSupport#parkNanos LockSupport.parkNanos}</li>
         *   <li>{@link LockSupport#parkUntil LockSupport.parkUntil}</li>
         * </ul>
         */
        TIMED_WAITING,

        /**
         * Thread state for a terminated thread.
         * The thread has completed execution.
         */
        TERMINATED;
    }

}
