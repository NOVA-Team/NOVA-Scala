package nova.scala.wrapper

import java.util.{List => JList}

import com.google.common.base.CaseFormat

/**
 * @author Calclavia
 */
object StringWrapper {

	implicit class WrappedString(str: String) {

		def toCamelCase: String = str.toPascalCase.decapitalizeFirst

		def decapitalizeFirst: String = str.substring(0, 1).toLowerCase + str.substring(1)

		def toPascalCase: String = {
			val parts: Array[String] = str.split("_")
			var camelCaseString: String = ""
			for (part <- parts) {
				camelCaseString = camelCaseString + (part.toProperCase)
			}
			return camelCaseString
		}

		def toProperCase: String = str.substring(0, 1).toUpperCase + str.substring(1).toLowerCase

		def camelToLowerUnderscore: String = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, str)

		def camelToReadable: String = str.replaceAll(String.format("%s|%s|%s", "(?<=[A-Z])(?=[A-Z][a-z])", "(?<=[^A-Z])(?=[A-Z])", "(?<=[A-Za-z])(?=[^A-Za-z])"), " ").capitalizeFirst

		def capitalizeFirst: String = str.substring(0, 1).toUpperCase + str.substring(1)

		def underscoreToCamel: String = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, str)
	}

}
