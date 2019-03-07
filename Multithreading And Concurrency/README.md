
# To understand multithreading and concurrency first we need to understand #Process And #Thread

# Process:
A list of processes as displayed by htop
In computing, a process is the instance of a computer program that is being executed. It contains the program code and its activity. Depending on the operating system (OS), a process may be made up of multiple threads of execution that execute instructions concurrently. from - https://en.wikipedia.org/wiki/Process_(computing)

# Thread:
A process with two threads of execution, running on one processor
In computer science, a thread of execution is the smallest sequence of programmed instructions that can be managed independently by a scheduler, which is typically a part of the operating system. The implementation of threads and processes differs between operating systems, but in most cases a thread is a component of a process. Multiple threads can exist within one process, executing concurrently and sharing resources such as memory, while different processes do not share these resources. In particular, the threads of a process share its executable code and the values of its dynamically allocated variables and non-thread-local global variables at any given time.

Also we can say - 
Thread can be called lightweight process. Thread requires less resources to create and exists in the process, thread shares the process resources.

# Java Thread Example
Every java application has at least one thread – main thread. Although there are so many other java threads running in background like memory management, system management, signal processing etc. But from application point of view – main is the first java thread and we can create multiple threads from it.

Multithreading refers to two or more threads executing concurrently in a single program. A computer single core processor can execute only one thread at a time and time slicing is the OS feature to share processor time between different processes and threads.

# Advantages of multithreading:

	1. Java Threads are lightweight compared to processes, it takes less time and resource to create a thread.
	2. Threads share their parent process data and code
	3. Context switching between threads is usually less expensive than between processes.
	4. Thread intercommunication is relatively easy than process communication.
	5. Enhanced performance by decreased development time
	6. Simplified and streamlined program coding
	7. Improvised GUI responsiveness
	8. Simultaneous and parallelized occurrence of tasks
	9. Better use of cache storage by utilization of resources
	10. Decreased cost of maintenance
	11. Better use of CPU resource
	12. Multithreading does not only provide you with benefits, it has its disadvantages too. Let us go through some common 


# Disadvantages of multithreading: 
	1. Complex debugging and testing processes
	2. Overhead switching of context
	3. Increased potential for deadlock occurrence
	4. Increased difficulty level in writing a program
	5. Unpredictable results
	
# How can we create thread programmatically
	1. Implementing the java.lang.Runnable interface.
	2. Extending the java.lang.Thread class.

Solution is in the above code. Just check the project out. 

# Question: What is the difference between Runnable vs Thread?
# Answer: 
If your class provides more functionality rather than just running as Thread, you should implement Runnable interface to provide a way to run it as Thread. If your class only goal is to run as Thread, you can extend Thread class.

Implementing Runnable is preferred because java supports implementing multiple interfaces. If you extend Thread class, you can’t extend any other classes.

**From Java 8 onwards, Runnable is a functional interface and we can use lambda expressions to provide it’s implementation rather than using anonymous class.**
Code below - 
	
	Runnable task2 = () -> { System.out.println("Task #2 is running"); };
	// start the thread
	new Thread(task2).start();


# Life Cycle of Thread in Java –
Understanding lifecycle of thread is important when you're going to deal with Multithreading programming. 
We can create a thread in java and start it but how the thread states change from **Runnable to Running to Blocked** depends on the OS implementation of thread scheduler and java doesn’t have full control on that.

# Let's discuss little bit details about thread life cycle states
# New
When we create a new Thread object using new operator, thread state is New Thread. At this point, thread is not alive and it’s a state internal to Java programming.

# Runnable
When we call *start()* function on Thread object, it’s state is changed to Runnable. The control is given to Thread scheduler to finish it’s execution. Whether to run this thread instantly or keep it in runnable thread pool before running, depends on the OS implementation of thread scheduler.

# Running
When thread is *executing*, it’s state is changed to Running. Thread scheduler picks one of the thread from the runnable thread pool and change it’s state to Running. Then CPU starts executing this thread. A thread can change state to Runnable, Dead or Blocked from running state depends on time slicing, thread completion of *run()* method or waiting for some resources.

# Blocked/Waiting/Timed Waiting
A thread can be waiting for other thread to finish using thread join or it can be *waiting for some resources to available*. For example producer consumer problem or waiter notifier implementation or IO resources, then it’s state is changed to Waiting. Once the thread wait state is over, it’s state is changed to Runnable and it’s moved back to runnable thread pool.

# Dead / Terminated
Once the thread finished executing, it’s state is changed to Dead and it’s considered to be not alive.

# Let's discuss about some useful methods of thread
# Thread.sleep in Java
Thread.sleep() method can be used to pause the execution of current thread for specified time in milliseconds. The argument value for **milliseconds can’t be negative**, else it throws **IllegalArgumentException**.

# Important about Thread.sleep()
    1. It always pause the current thread execution.
    2. The actual time thread sleeps before waking up and start execution depends on system timers and schedulers. For a quiet system, the actual time for sleep is near to the specified sleep time but for a busy system it will be little bit more.
    3. Thread sleep doesn’t lose any monitors or locks current thread has acquired.
    4. Any other thread can interrupt the current thread in sleep, in that case **InterruptedException** is thrown.
    

# How Does Thread Sleep Works?
Thread.sleep() interacts with the thread scheduler to put the current thread in wait state for specified period of time. Once the wait time is over, thread state is changed to runnable state and wait for the CPU for further execution. So the actual time that current thread sleep depends on the thread scheduler that is part of operating system.

# Java Thread Join Example
**Java Thread join** method can be used to pause the current thread execution until unless the specified thread is dead. There are three overloaded join functions.

*public final void join():* This java thread join method puts the current thread on wait until the thread on which it’s called is dead. If the thread is interrupted, it throws InterruptedException.

*public final synchronized void join(long millis):* This java thread join method is used to wait for the thread on which it’s called to be dead or wait for specified milliseconds. Since thread execution depends on OS implementation, it doesn’t guarantee that the current thread will wait only for given time.

*public final synchronized void join(long millis, int nanos):* This java thread join method is used to wait for thread to die for given milliseconds plus nanoseconds.

# wait, notify and notifyAll in thread
# wait()
Object wait methods has three variance, one which waits indefinitely for any other thread to call notify or notifyAll method on the object to wake up the current thread. Other two variances puts the current thread in wait for specific amount of time before they wake up.

# notify()
*notify()* method wakes up only one thread waiting on the object and that thread starts execution. So if there are multiple threads waiting for an object, this method will wake up only one of them. The choice of the thread to wake depends on the OS implementation of thread management.

# notifyAll()
*notifyAll()* method wakes up all the threads waiting on the object, although which one will process first depends on the OS implementation.

# To see the implementation of wait(), notify(), notifyAll() take a look at @ThreadBasicMethods example


# Thread Safety in Java
Thread Safety in Java is a very important topic. Java provide multi-threaded environment support using Java Threads, we know that multiple threads created from same Object share object variables and this can lead to data inconsistency when the threads are used to read and update the shared data.

# Thread Safety?
The reason for data inconsistency is because updating any field value is not an atomic process, it requires three steps; first to read the current value, second to do the necessary operations to get the updated value and third to assign the updated value to the field reference.

**Check @ThreadSafety project from above**

# Thread Safety in Java
Thread safety in java is the process to make our program safe to use in multithreaded environment, there are different ways through which we can make our program thread safe.

    1. Synchronization is the easiest and most widely used tool for thread safety in java.
    2. Use of Atomic Wrapper classes from java.util.concurrent.atomic package. For example AtomicInteger
    3. Use of locks from java.util.concurrent.locks package.
    4. Using volatile keyword with variables to make every thread read the data from memory, not read from thread cache.
    
   
# Java synchronized
**Synchronization** is the tool using which we can achieve thread safety, JVM guarantees that synchronized code will be executed by only one thread at a time. java keyword synchronized is used to create synchronized code and internally it uses locks on Object or Class to make sure only one thread is executing the synchronized code.

    * Java synchronization works on locking and unlocking of resource, before any thread enters into synchronized code, it has to acquire lock on the Object and when code execution ends, it unlocks the resource that can be locked by other threads. In the mean time other threads are in wait state to lock the synchronized resource.
    * We can use synchronized keyword in two ways, one is to make a complete method synchronized and other way is to create synchronized block.
    * When a method is synchronized, it locks the Object, if method is static it locks the Class, so it’s always best practice to use synchronized block to lock the only sections of method that needs synchronization.
    * While creating synchronized block, we need to provide the resource on which lock will be acquired, it can be XYZ.class or any Object field of the class.
    * synchronized(this) will lock the Object before entering into the synchronized block.
    * You should use the lowest level of locking, for example if there are multiple synchronized block in a class and one of them is locking the Object, then other synchronized blocks will also be not available for execution by other threads. When we lock an Object, it acquires lock on all the fields of the Object.
    * Java Synchronization provides data integrity on the cost of performance, so it should be used only when it’s absolutely necessary.
    * Java Synchronization works only in the same JVM, so if you need to lock some resource in multiple JVM environment, it will not work and you might have to look after some global locking mechanism.
    * Java Synchronization could result in deadlocks, check this post about deadlock in java and how to avoid them.
    * Java synchronized keyword cannot be used for constructors and variables.
    * It is preferable to create a dummy private Object to use for synchronized block, so that it’s reference can’t be changed by any other code. For example if you have a setter method for Object on which you are synchronizing, it’s reference can be changed by some other code leads to parallel execution of the synchronized block.
    * We should not use any object that is maintained in a constant pool, for example String should not be used for synchronization because if any other code is also locking on same String, it will try to acquire lock on the same reference object from String pool and even though both the codes are unrelated, they will lock each other.
    
    
