
To understand multithreading and concurrency first we need to understand about 
Process & Thread 

# Process:
A list of processes as displayed by htop
In computing, a process is the instance of a computer program that is being executed. It contains the program code and its activity. Depending on the operating system (OS), a process may be made up of multiple threads of execution that execute instructions concurrently. from - https://en.wikipedia.org/wiki/Process_(computing)
? etc. 
# Thread:
A process with two threads of execution, running on one processor
In computer science, a thread of execution is the smallest sequence of programmed instructions that can be managed independently by a scheduler, which is typically a part of the operating system. The implementation of threads and processes differs between operating systems, but in most cases a thread is a component of a process. Multiple threads can exist within one process, executing concurrently and sharing resources such as memory, while different processes do not share these resources. In particular, the threads of a process share its executable code and the values of its dynamically allocated variables and non-thread-local global variables at any given time.

Also we can say - 
Thread can be called lightweight process. Thread requires less resources to create and exists in the process, thread shares the process resources.

# Java Thread Example
Every java application has at least one thread – main thread. Although there are so many other java threads running in background like memory management, system management, signal processing etc. But from application point of view – main is the first java thread and we can create multiple threads from it.

Multithreading refers to two or more threads executing concurrently in a single program. A computer single core processor can execute only one thread at a time and time slicing is the OS feature to share processor time between different processes and threads.

# Advantages of multithreading:
	Java Threads are lightweight compared to processes, it takes less time and resource to create a thread.
	Threads share their parent process data and code
	Context switching between threads is usually less expensive than between processes.
	Thread intercommunication is relatively easy than process communication.
	Enhanced performance by decreased development time
	Simplified and streamlined program coding
	Improvised GUI responsiveness
	Simultaneous and parallelized occurrence of tasks
	Better use of cache storage by utilization of resources
	Decreased cost of maintenance
	Better use of CPU resource
	Multithreading does not only provide you with benefits, it has its disadvantages too. Let us go through some common 

# Disadvantages of multithreading: 
	Complex debugging and testing processes
	Overhead switching of context
	Increased potential for deadlock occurrence
	Increased difficulty level in writing a program
	Unpredictable results
	
# How can we create thread programmatically
	1. Implementing the java.lang.Runnable interface.
	2. Extending the java.lang.Thread class.

Solution is in the above code. Just check the project out. 

# Question: What is the difference between Runnable vs Thread?
# Answer: 
If your class provides more functionality rather than just running as Thread, you should implement Runnable interface to provide a way to run it as Thread. If your class only goal is to run as Thread, you can extend Thread class.

Implementing Runnable is preferred because java supports implementing multiple interfaces. If you extend Thread class, you can’t extend any other classes.

**From Java 8 onwards, Runnable is a functional interface and we can use lambda expressions to provide it’s implementation rather than using anonymous class.**
Like below - 
	
	`Runnable task2 = () -> { System.out.println("Task #2 is running"); };
	
	// start the thread
	
	new Thread(task2).start();`