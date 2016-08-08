
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Projects/Crible/conf/routes
<<<<<<< HEAD
// @DATE:Sun Aug 07 22:39:32 GFT 2016
=======
// @DATE:Sun Aug 07 17:43:37 GFT 2016
>>>>>>> master


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
