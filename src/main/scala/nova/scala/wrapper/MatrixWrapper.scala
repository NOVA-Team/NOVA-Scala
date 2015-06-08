package nova.scala.wrapper

/**
 * @author Calclavia
 */
object MatrixWrapper {

	implicit class MatrixWrapped(underlying: RealMatrix) {
		def apply(x: Int, y: Int) = underlying.getEntry(x, y)

		def update(x: Int, y: Int, value: Double) = underlying.setEntry(x, y, value)
	}

}
