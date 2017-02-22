/*
 * Copyright (c) 2015 NOVA, All rights reserved.
 * This library is free software, licensed under GNU Lesser General Public License version 3
 *
 * This file is part of NOVA.
 *
 * NOVA is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * NOVA is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with NOVA.  If not, see <http://www.gnu.org/licenses/>.
 */

package nova.scala.wrapper

import java.util.function._

import nova.core.event.bus.EventListener

/**
 * Implicitly converts Scala methods to Java methods.
 *
 * @deprecated Since Scala 2.12, any method which accepts a valid Java 8 functional interface
 * can have a Scala lambda passed to it directly and all Scala FunctionN traits
 * are now valid Java 8 functional interfaces, making this class unnecessary boilerplate.
 * @author anti344
 */
@deprecated
object FunctionalWrapper {

	import scala.language.implicitConversions

	@deprecated
	implicit def biConsumer[T1, T2](f: (T1, T2) => Any): BiConsumer[T1, T2] = new BiConsumer[T1, T2] {
		def accept(t1: T1, t2: T2) = f(t1, t2)
	}

	@deprecated
	implicit def biFunc[R, T1, T2](f: (T1, T2) => R): BiFunction[T1, T2, R] = new BiFunction[T1, T2, R] {
		def apply(t1: T1, t2: T2): R = f(t1, t2)
	}

	@deprecated
	implicit def biOp[T](f: (T, T) => T): BinaryOperator[T] = new BinaryOperator[T] {
		def apply(t1: T, t2: T): T = f(t1, t2)
	}

	@deprecated
	implicit def biPredicate[T1, T2](f: (T1, T2) => Boolean): BiPredicate[T1, T2] = new BiPredicate[T1, T2] {
		def test(t1: T1, t2: T2) = f(t1, t2)
	}

	@deprecated
	implicit def boolSupplier(f: () => Boolean): BooleanSupplier = new BooleanSupplier {
		def getAsBoolean: Boolean = f()
	}

	@deprecated
	implicit def consumer[T](f: T => Any): Consumer[T] = new Consumer[T] {
		def accept(t: T) = f(t)
	}

	@deprecated
	implicit def doubleBiOp(f: (Double, Double) => Double): DoubleBinaryOperator = new DoubleBinaryOperator {
		def applyAsDouble(d1: Double, d2: Double): Double = f(d1, d2)
	}

	@deprecated
	implicit def doubleConsumer(f: Double => Any): DoubleConsumer = new DoubleConsumer {
		def accept(d: Double) = f(d)
	}

	@deprecated
	implicit def doubleFunc[R](f: Double => R): DoubleFunction[R] = new DoubleFunction[R] {
		def apply(d: Double): R = f(d)
	}

	@deprecated
	implicit def doublePredicate(f: Double => Boolean): DoublePredicate = new DoublePredicate {
		def test(d: Double): Boolean = f(d)
	}

	@deprecated
	implicit def doubleSupplier(f: () => Double): DoubleSupplier = new DoubleSupplier {
		def getAsDouble: Double = f()
	}

	@deprecated
	implicit def doubleToIntFunc(f: Double => Int): DoubleToIntFunction = new DoubleToIntFunction {
		def applyAsInt(d: Double): Int = f(d)
	}

	@deprecated
	implicit def doubleToLongFunc(f: Double => Long): DoubleToLongFunction = new DoubleToLongFunction {
		def applyAsLong(d: Double): Long = f(d)
	}

	@deprecated
	implicit def doubleUnaryOp(f: Double => Double): DoubleUnaryOperator = new DoubleUnaryOperator {
		def applyAsDouble(d: Double): Double = f(d)
	}

	@deprecated
	implicit def func[T, R](f: T => R): Function[T, R] = new Function[T, R] {
		def apply(t: T): R = f(t)
	}

	@deprecated
	implicit def intBiOp(f: (Int, Int) => Int): IntBinaryOperator = new IntBinaryOperator {
		def applyAsInt(i1: Int, i2: Int): Int = f(i1, i2)
	}

	@deprecated
	implicit def intConsumer(f: Int => Any): IntConsumer = new IntConsumer {
		def accept(i: Int) = f(i)
	}

	@deprecated
	implicit def intFunc[R](f: Int => R): IntFunction[R] = new IntFunction[R] {
		def apply(i: Int): R = f(i)
	}

	@deprecated
	implicit def intPredicate(f: Int => Boolean): IntPredicate = new IntPredicate {
		def test(i: Int): Boolean = f(i)
	}

	@deprecated
	implicit def intSupplier(f: () => Int): IntSupplier = new IntSupplier {
		def getAsInt: Int = f()
	}

	@deprecated
	implicit def intToDoubleFunc(f: Int => Double): IntToDoubleFunction = new IntToDoubleFunction {
		def applyAsDouble(i: Int): Double = f(i)
	}

	@deprecated
	implicit def intToLongFunc(f: Int => Long): IntToLongFunction = new IntToLongFunction {
		def applyAsLong(i: Int): Long = f(i)
	}

	@deprecated
	implicit def intUnaryOp(f: Int => Int): IntUnaryOperator = new IntUnaryOperator {
		def applyAsInt(i: Int): Int = f(i)
	}

	@deprecated
	implicit def longBiOp(f: (Long, Long) => Long): LongBinaryOperator = new LongBinaryOperator {
		def applyAsLong(i1: Long, i2: Long): Long = f(i1, i2)
	}

	@deprecated
	implicit def longConsumer(f: Long => Any): LongConsumer = new LongConsumer {
		def accept(i: Long) = f(i)
	}

	@deprecated
	implicit def longFunc[R](f: Long => R): LongFunction[R] = new LongFunction[R] {
		def apply(i: Long): R = f(i)
	}

	@deprecated
	implicit def longPredicate(f: Long => Boolean): LongPredicate = new LongPredicate {
		def test(i: Long): Boolean = f(i)
	}

	@deprecated
	implicit def longSupplier(f: () => Long): LongSupplier = new LongSupplier {
		def getAsLong: Long = f()
	}

	@deprecated
	implicit def longToDoubleFunc(f: Long => Double): LongToDoubleFunction = new LongToDoubleFunction {
		def applyAsDouble(i: Long): Double = f(i)
	}

	@deprecated
	implicit def longToIntFunc(f: Long => Int): LongToIntFunction = new LongToIntFunction {
		def applyAsInt(i: Long): Int = f(i)
	}

	@deprecated
	implicit def longUnaryOp(f: Long => Long): LongUnaryOperator = new LongUnaryOperator {
		def applyAsLong(i: Long): Long = f(i)
	}

	@deprecated
	implicit def objDoubleConsumer[T](f: (T, Double) => Any): ObjDoubleConsumer[T] = new ObjDoubleConsumer[T] {
		def accept(t: T, d: Double) = f(t, d)
	}

	@deprecated
	implicit def objIntConsumer[T](f: (T, Int) => Any): ObjIntConsumer[T] = new ObjIntConsumer[T] {
		def accept(t: T, d: Int) = f(t, d)
	}

	@deprecated
	implicit def objLongConsumer[T](f: (T, Long) => Any): ObjLongConsumer[T] = new ObjLongConsumer[T] {
		def accept(t: T, d: Long) = f(t, d)
	}

	@deprecated
	implicit def predicate[T](f: T => Boolean): Predicate[T] = new Predicate[T] {
		def test(t: T): Boolean = f(t)
	}

	@deprecated
	implicit def supplier[T](f: () => T): Supplier[T] = new Supplier[T] {
		def get(): T = f()
	}

	@deprecated
	implicit def toDoubleBiFunc[T1, T2](f: (T1, T2) => Double): ToDoubleBiFunction[T1, T2] = new ToDoubleBiFunction[T1, T2] {
		def applyAsDouble(t1: T1, t2: T2): Double = f(t1, t2)
	}

	@deprecated
	implicit def toDoubleFun[T](f: T => Double): ToDoubleFunction[T] = new ToDoubleFunction[T] {
		def applyAsDouble(d: T): Double = f(d)
	}

	@deprecated
	implicit def toIntBiFunc[T1, T2](f: (T1, T2) => Int): ToIntBiFunction[T1, T2] = new ToIntBiFunction[T1, T2] {
		def applyAsInt(t1: T1, t2: T2): Int = f(t1, t2)
	}

	@deprecated
	implicit def toIntFun[T](f: T => Int): ToIntFunction[T] = new ToIntFunction[T] {
		def applyAsInt(d: T): Int = f(d)
	}

	@deprecated
	implicit def toLongBiFunc[T1, T2](f: (T1, T2) => Long): ToLongBiFunction[T1, T2] = new ToLongBiFunction[T1, T2] {
		def applyAsLong(t1: T1, t2: T2): Long = f(t1, t2)
	}

	@deprecated
	implicit def toLongFun[T](f: T => Long): ToLongFunction[T] = new ToLongFunction[T] {
		def applyAsLong(d: T): Long = f(d)
	}

	@deprecated
	implicit def unaryOp[T](f: T => T): UnaryOperator[T] = new UnaryOperator[T] {
		def apply(t: T): T = f(t)
	}

	@deprecated
	implicit def inverseSupplier[T](f: Supplier[T]): () => T = () => f.get()

	@deprecated
	implicit def runnable(f: () => Unit): Runnable = new Runnable {
		override def run(): Unit = f()
	}

	//NOVA
	@deprecated
	implicit def eventListener[T](f: T => Unit): EventListener[T] = new EventListener[T] {
		def onEvent(t: T) = f(t)
	}
}