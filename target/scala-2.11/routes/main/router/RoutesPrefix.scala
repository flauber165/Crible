
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Projects/cribleempresa/conf/routes
// @DATE:Wed Aug 03 20:31:40 GFT 2016


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
