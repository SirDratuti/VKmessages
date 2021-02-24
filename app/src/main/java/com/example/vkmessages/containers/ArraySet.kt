package com.example.vkmessages.containers

import com.example.vkmessages.vkobjects.VKGroup
import java.util.*
import kotlin.Comparator
import kotlin.NoSuchElementException
import kotlin.collections.ArrayList
import kotlin.math.max

class ArraySet<T>: AbstractSet<T>, NavigableSet<T> {
    private val array: List<T>
    private val comparator: Comparator<in T>?

    constructor() {
        array = Collections.emptyList()
        comparator = null
    }

    override val size: Int
        get() = array.size

    constructor(collection: Collection<T>?) {
        array = ArrayList(TreeSet(collection))
        comparator = null
    }

    constructor(collection: Collection<T>?, comparator: Comparator<in T>?) {
        val tempArray: TreeSet<T> = TreeSet(comparator)
        if (collection != null) {
            tempArray.addAll(collection)
        }
        array = ArrayList(tempArray)
        this.comparator = comparator
    }

    private fun findIndex(element: T, inc: Boolean, fromStart: Boolean): Int {
        var x = 0
        if (fromStart) {
            x++
        }
        val ind: Int = Collections.binarySearch(array, element, comparator)
        if (ind < 0) {
            return ind.inv() - 1 + x
        }
        return if (inc) {
            ind
        } else ind - 1 + 2 * x
    }

    override fun lower(element: T): T? {
        return elementAt(findIndex(element, inc = false, fromStart = false))
    }

    override fun floor(element: T): T? {
        return elementAt(findIndex(element, inc = true, fromStart = false))
    }

    override fun ceiling(element: T): T? {
        return elementAt(findIndex(element, inc = true, fromStart = true))
    }

    override fun higher(element: T): T? {
        return elementAt(findIndex(element, inc = false, fromStart = true))
    }

    private fun elementAt(index: Int): T? {
        return if (index >= 0 && index < array.size) {
            array[index]
        } else null
    }

    override fun subSet(
        firstElement: T,
        firstInc: Boolean,
        secondElement: T,
        secondInc: Boolean
    ): NavigableSet<T> {
        require(comparator!!.compare(firstElement, secondElement) <= 0)
        val firstIndex = findIndex(firstElement, firstInc, true)
        val secondIndex = findIndex(secondElement, secondInc, false) + 1
        return ArraySet(array.subList(firstIndex, max(firstIndex, secondIndex)), comparator)
    }

    override fun headSet(element: T, inc: Boolean): NavigableSet<T> {
        return ArraySet(array.subList(0, findIndex(element, inc, false) + 1), comparator)
    }

    override fun tailSet(element: T, inc: Boolean): NavigableSet<T> {
        return ArraySet(array.subList(findIndex(element, inc, true), size), comparator)
    }

    override fun descendingSet(): NavigableSet<T> {
        val temp: DescendingList<T> = DescendingList(array)
        temp.descend()
        return ArraySet(temp, Collections.reverseOrder(comparator))
    }

    override fun descendingIterator(): Iterator<T> {
        return descendingSet().iterator()
    }

    override fun comparator(): Comparator<in T>? {
        return comparator
    }

    override fun subSet(firstElement: T, secondElement: T): SortedSet<T> {
        return subSet(firstElement, true, secondElement, false)
    }

    override fun headSet(element: T): SortedSet<T> {
        return headSet(element, false)
    }

    override fun tailSet(element: T): SortedSet<T> {
        return tailSet(element, true)
    }

    override fun iterator(): MutableIterator<T> {
        return Collections.unmodifiableList(array).iterator()
    }

    override fun first(): T {
        if (array.isNotEmpty()) {
            return array[0]
        }
        throw NoSuchElementException()
    }

    override fun last(): T {
        if (array.isNotEmpty()) {
            return array[size - 1]
        }
        throw NoSuchElementException()
    }


    override fun pollFirst(): T {
        throw UnsupportedOperationException()
    }

    override fun pollLast(): T {
        throw UnsupportedOperationException()
    }
}
