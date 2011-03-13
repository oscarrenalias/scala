package net.renalias.scalar

import org.gridgain.scalar.scalar._
import org.gridgain.scalar.scalar;

object ShortestMapReduce {
	def main(args: Array[String]) = scalar {
		println("Count is: " + (grid spreadReduce (
						for (w <- args(0).split(" ")) yield () => w.length,
						(s: Seq[Int]) => s.sum
						)))
	}
}