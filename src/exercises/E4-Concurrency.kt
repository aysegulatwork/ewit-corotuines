package exercises

import kotlinx.coroutines.*
import shared.measureTime
import solutions.calculateOrderSus
import solutions.calculateOrderSusAsync

/**
 * 1. Observe the two client calls we made in the previous exercise, are they really parallel?
 *
 * 2. How do we make them parallel?
 *
 * 3. Better yet, how do we make them concurrent? (TIP: use asynchronous clients)
 *
 * Example: Spring MVC vs Spring WebFlux
 *
 */

fun main() {
    measureTime("Blocking function with sync client") {
        calculateOrder("IPHONE", 2)
    }
}