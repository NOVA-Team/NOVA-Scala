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
