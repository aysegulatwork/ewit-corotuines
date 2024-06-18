
# Cancellation



![img.png](images/jobStates.png) 


**CancellationException:**
A cancelled coroutine throws CancellationException in suspension points. This exception is used internally to stop the coroutine's execution. 

**Cancellation Propagation:** 
When a parent coroutine is cancelled, all its child coroutines are also cancelled. This ensures that no leftover work is being done by the child coroutines.

How does cancelling work?

With threads:

```kotlin
val thread = thread {
    //do something
} 
thread.interrupt()
```

With coroutines:

```kotlin
val job = launch {
    //do something
}
job.cancel()
```

Two flavors of cancelling jobs:

```kotlin
job.cancel()
job.cancelAndJoin()
```



<!-- Image from: https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-job/  -->