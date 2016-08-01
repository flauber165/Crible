
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/BRMCe/Documents/crible/conf/routes
// @DATE:Sun Jul 31 20:52:00 GFT 2016


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
