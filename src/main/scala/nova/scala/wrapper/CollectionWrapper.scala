package nova.scala.wrapper

import java.util.{Collection, List => JList}

/**
 * Wraps java non-generic lists to auto-cast generic for Scala compatibility.
 * @author Calclavia
 */
object CollectionWrapper {

	implicit class ListWithGenericAdd[T](list: JList[T]) {
		def add(value: Any) = list.add(value.asInstanceOf[T])

		def addAll(value: Collection[_]): Boolean = list.addAll(value.asInstanceOf[Collection[T]])
	}

}
