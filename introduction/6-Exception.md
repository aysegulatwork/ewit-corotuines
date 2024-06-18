
# Exception

Exceptions will be propagated to parent scope and parent scope will send a cancellation signal to all the other children. 

```kotlin
coroutineScope {
    launch {
        delay(2000)
        println("Will I be printed?")
    }

    launch {
        throw Exception("I want to break free!")
    }
}
```

## SupervisorScope

Instead of coroutineScope, we can use supervisorScope for scoped concurrency. It propagates the cancellation in one direction only and cancels all its children only if it failed itself. It also waits for all children before completion just like coroutineScope does.

```kotlin
supervisorScope {
    launch {
        delay(2000)
        println("Will I be printed?")
    }

    launch {
        throw Exception("I want to break free!")
    }
}
```