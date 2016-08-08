
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Projects/Crible/conf/routes
<<<<<<< HEAD
// @DATE:Sun Aug 07 22:39:32 GFT 2016
=======
// @DATE:Sun Aug 07 17:43:37 GFT 2016
>>>>>>> master

import play.api.routing.JavaScriptReverseRoute
import play.api.mvc.{ QueryStringBindable, PathBindable, Call, JavascriptLiteral }
import play.core.routing.{ HandlerDef, ReverseRouteContext, queryString, dynamicString }


import _root_.controllers.Assets.Asset

// @LINE:6
package presentation.controllers.javascript {
  import ReverseRouteContext.empty

  // @LINE:7
  class ReverseAuthenticationController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:7
    def enter: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "presentation.controllers.AuthenticationController.enter",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "enter"})
        }
      """
    )
  
  }

  // @LINE:6
  class ReverseApplication(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:6
    def index: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "presentation.controllers.Application.index",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + """"})
        }
      """
    )
  
  }


}