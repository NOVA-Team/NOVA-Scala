package nova.scala.wrapper

import nova.core.util.Direction

/**
 * Wraps bytes/short/integers to be used for masks
 * @author Calclavia
 */
object BitmaskWrapper {

	/**
	 * Java forwarding
	 */
	def mask(sideMap: Int, direction: Direction): Boolean = sideMap.mask(direction)

	def mask(sideMap: Int, direction: Direction, doEnable: Boolean): Int = sideMap.mask(direction, doEnable)

	implicit class BitmaskInt(val underlying: Int) extends AnyVal {
		def openMask(i: Int) = mask(i, true)

		/**
		 * Sets the bitmask index to be either open or closed
		 * @param i - Index
		 * @param value - True for open
		 * @return - The new bitmask
		 */
		def mask(i: Int, value: Boolean): Int = {
			if (value) {
				return underlying | (1 << i)
			}
			else {
				return underlying & ~(1 << i)
			}
		}

		def closeMask(i: Int) = mask(i, false)

		/**
		 * Forge Direction Alternatives
		 */
		def mask(dir: Direction): Boolean = mask(dir.ordinal())

		/**
		 * Checks if the bitmask is open
		 * @param i - The index of the bitmask
		 * @return True if open
		 */
		def mask(i: Int): Boolean = (underlying & (1 << i)) != 0

		/**
		 * Opens the integer mask at a specific direction
		 * @param dir - The direction
		 * @param value - The value
		 * @return The new value of the mask
		 */
		def mask(dir: Direction, value: Boolean): Int = mask(dir.ordinal(), value)

		def openMask(dir: Direction) = mask(dir.ordinal(), true)

		def closeMask(dir: Direction) = mask(dir.ordinal(), false)

		def invert(): Int = invert(6)

		def invert(k: Int): Int = {
			val mask = (1 << k) - 1
			return ~underlying & mask
		}
	}

}
