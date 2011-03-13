package net.renalias.scalar

import org.gridgain.scalar.scalar._
import org.gridgain.scalar.scalar;

object HelloWorld {
	def main(args: Array[String]) {
		scalar {
		    grid !!! (() => println("Broadcasting!"))

		}
	}
}
