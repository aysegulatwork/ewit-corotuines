# What is a Coroutine?

### Lightweight threads
![img.png](images/threads.png)

Coroutines are more lightweight compared to threads, as they don't require their own stack, resulting in lower memory overhead and faster context switching.



## Suspension and resumption
![img.png](images/doctor.png)

They can suspend execution at a certain point, allowing other code to run, and then resume from where they left off, enabling cooperative multitasking.


## Structural concurrency


![img.png](images/highway.png)

Coroutines allow multiple tasks to run concurrently within the same thread or make use of multiple threads at the same time without losing the connection of parent and children coroutines. 
