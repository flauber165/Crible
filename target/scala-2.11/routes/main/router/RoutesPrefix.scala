
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/brcerqueira/Documents/GitHub/cribleempresa/conf/routes
// @DATE:Tue Aug 02 16:25:56 BRT 2016


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
