package nova.scala.util

/**
 * An object that can handle ticks.
 * @author Calclavia
 */
//TODO: Move to NOVA-Scala, change ticks to time.
trait ExtendedUpdater extends nova.core.component.Updater {

	protected var ticks = 0L

	def getTicks = ticks

	override def update(deltaTime: Double) {
		if (ticks == 0) {
			start()
		}

		if (ticks >= Long.MaxValue) {
			ticks = 1
		}

		ticks += 1
	}

	/**
	 * Called on the first tick.
	 */
	def start() {
	}
}
