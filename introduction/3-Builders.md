
# Coroutine Builders


### runBlocking
runBlocking is a coroutine builder that bridges the non-coroutine world with coroutine world. (Be careful when using it, check this [link](https://techlab.bol.com/en/blog/coroutine-gotchas-bridging-the-gap-between-coroutine-and-non-coroutine-worlds/) out)

```kotlin
  runBlocking {
      println("Hello, World!")
  }
```

### launch
Creates a coroutine and returns a `Job`. It's good to use with operations where you do not need to return the result. 
```kotlin
  runBlocking {
    val job = launch {
        number1 + number2
    }

    val resultOfJob = job.join() //Unit
  }
```

### async
Creates a coroutine and returns `Deferred`. It is good to use when you need the result of the operation that the coroutine is doing. 
```kotlin
  runBlocking {
    val deferred = async {
        number1 + number2
    }

    val resultOfAsync = deferred.await()  //Int
  }
```

### coroutineScope
This builder creates a coroutine scope and does not complete until all launched children complete. A `coroutineScope` builder can be used inside any suspending function to perform multiple concurrent operations.
Below example shows a suspending function that uses a `coroutineScope`
```kotlin
suspend fun sum(number1: Int, number2: Int) = coroutineScope {
    val deferred = async {
        number1 + number2
    }

    deferred.await()
}
```

