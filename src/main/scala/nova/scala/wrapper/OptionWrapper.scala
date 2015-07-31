package nova.scala.wrapper

import java.util.Optional


object OptionWrapper {
	implicit class EnchancedOptional[T](optional: Optional[T]) {
		def toOption = OptionWrapper.toOption(optional)
	}

	implicit def toOption[T](optional: Optional[T]): Option[T] = {
		if (optional.isPresent)
			Some(optional.get)
		else
			None
	}

	implicit def toOptional[T](option: Option[T]): Optional[T] = option match {
		case Some(x) => Optional.of(x)
		case None => Optional.empty()
	}
}
