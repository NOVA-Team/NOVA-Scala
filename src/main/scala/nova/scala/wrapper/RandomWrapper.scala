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

import java.util.Random

/**
 * An extension method for Java's random
 * @author Calclavia
 */
object RandomWrapper {

	implicit class RichRandom(val underlying: Random) {
		/**
		 * Generates a random value between -amount to +amount
		 * @param amount - Amount of deviation
		 * @return
		 */
		def deviate(amount: Double = 1) = (underlying.nextDouble() - 0.5) * 2 * amount

		def range(from: Double, to: Double) = underlying.nextDouble() * (to - from) + from
	}

}
