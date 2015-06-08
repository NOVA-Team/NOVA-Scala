package nova.scala.wrapper

/**
 * @author Calclavia
 */
object VectorWrapper {

	implicit class Vector3DWrapped(underlying: Vector3D) {
		def x = underlying.getX

		def y = underlying.getY

		def z = underlying.getZ

		def xf = underlying.x.toFloat

		def yf = underlying.y.toFloat

		def zf = underlying.z.toFloat

		def xi = underlying.x.toInt

		def yi = underlying.y.toInt

		def zi = underlying.z.toInt

		def +(other: Vector3D) = underlying.add(other)

		def +(other: Double) = underlying.add(Vector3DUtil.ONE * other)

		def -(other: Vector3D) = underlying.subtract(other)

		def -(other: Double) = underlying.subtract(Vector3DUtil.ONE * other)

		def unary_- = underlying.negate()

		def unary_/(other: Double) = Vector3DUtil.reciprocal(underlying).scalarMultiply(other)

		def reciprocal = Vector3DUtil.reciprocal(underlying)

		def *(other: Double) = underlying.scalarMultiply(other)

		def *(other: Vector3D) = Vector3DUtil.cartesianProduct(underlying, other)

		def /(other: Double) = underlying.scalarMultiply(1d / other)

		def /(other: Vector3D) = Vector3DUtil.cartesianProduct(underlying, other.reciprocal)
	}

}
