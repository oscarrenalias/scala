case class Chainable(val desc:String, val prev:Option[Chainable] = None) {
	def -->(a: Chainable) = new Chainable(a.desc, Some(this))
	def run:Any = {
		prev.map(x=>x.run)
		println("Running: "+ desc)
	}
}