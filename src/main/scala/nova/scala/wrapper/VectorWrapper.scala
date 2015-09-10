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

import nova.core.util.math.Vector3DUtil
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D

/**
 * @author Calclavia
 */
object VectorWrapper {

	implicit class Vector2DWrapped(underlying: Vector2D) {
		def x = underlying.getX

		def y = underlying.getY

		def xf = underlying.x.toFloat

		def yf = underlying.y.toFloat

		def xi = underlying.x.toInt

		def yi = underlying.y.toInt
	}

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

		def abs = Vector3DUtil.abs(underlying)

		def +(other: Vector3D) = underlying.add(other)

		def +(other: Double) = underlying.add(Vector3DUtil.ONE * other)

		def -(other: Vector3D) = underlying.subtract(other)

		def -(other: Double) = underlying.subtract(Vector3DUtil.ONE * other)

		def min(other: Vector3D) = Vector3DUtil.min(underlying, other)

		def max(other: Vector3D) = Vector3DUtil.max(underlying, other)

		def midpoint(other: Vector3D) = Vector3DUtil.midpoint(underlying, other)

		def unary_- = underlying.negate()

		def unary_/(other: Double) = Vector3DUtil.reciprocal(underlying).scalarMultiply(other)

		def reciprocal = Vector3DUtil.reciprocal(underlying)

		def *(other: Double) = underlying.scalarMultiply(other)

		def *(other: Vector3D) = Vector3DUtil.cartesianProduct(underlying, other)

		def /(other: Double) = underlying.scalarMultiply(1d / other)

		def /(other: Vector3D) = Vector3DUtil.cartesianProduct(underlying, other.reciprocal)

		def floor = Vector3DUtil.floor(underlying)

		def ceil = Vector3DUtil.ceil(underlying)

		def round = Vector3DUtil.round(underlying)
	}

}
