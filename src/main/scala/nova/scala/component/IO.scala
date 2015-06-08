package nova.scala.component

import nova.core.block.Block
import nova.core.component.Component
import nova.core.component.transform.Orientation
import nova.core.event.{Event, EventBus}
import nova.core.network.{Sync, Syncable}
import nova.core.retention.{Store, Storable}
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