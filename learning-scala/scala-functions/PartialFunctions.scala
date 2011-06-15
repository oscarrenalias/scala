object PartialFunctions {
	
	object Divide extends PartialFunction[(Int, Int), Int] {
			def apply(x:(Int, Int)) = x._1 / x._2
			def isDefinedAt(x:(Int, Int)) = if(x._2 == 0) false; else true
	}
	
	def main(args:Array[String]) = {
		println(Divide.isDefinedAt(4, 2)) // true
		println(Divide.isDefinedAt(4, 0)) // false
	}
}