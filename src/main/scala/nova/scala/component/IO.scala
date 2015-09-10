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

package nova.scala.component

import nova.core.block.Block
import nova.core.component.Component
import nova.core.component.transform.Orientation
import nova.core.event.bus.{Event, EventBus}
import nova.core.network.{Sync, Syncable}
import nova.core.retention.{Storable, Store}
import nova.core.util.Direction

/**
 * A component that handles input and outputs
 *
 * @author Calclavia
 */
class IO(block: Block) extends Component with Storable with Syncable {

	@Store
	@Sync
	var inputMask = 0x3F

	@Store
	@Sync
	var outputMask = 0x00

	var changeEvent = new EventBus[Event]

	def getIO(dir: Direction): Int = {
		if ((inputMask & (1 << dir.ordinal())) != 0) {
			return 1
		}
		if ((outputMask & (1 << dir.ordinal())) != 0) {
			return 2
		}
		return 0
	}

	/**
	 * Helper method
	 */
	def setIOAlternatingOrientation() {
		val dirMask = 1 << block.get(classOf[Orientation]).orientation.ordinal
		val positiveMask = 0x2A
		val isPositive = (dirMask & positiveMask) != 0

		if (isPositive) {
			inputMask = positiveMask & ~dirMask
			outputMask = inputMask >> 1
		} else {
			val negativeMask = 0x15
			outputMask = negativeMask & ~dirMask
			inputMask = outputMask << 1
		}
	}
}