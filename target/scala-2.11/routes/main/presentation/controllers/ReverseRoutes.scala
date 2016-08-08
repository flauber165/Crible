
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Projects/Crible/conf/routes
<<<<<<< HEAD
// @DATE:Sun Aug 07 22:39:32 GFT 2016
=======
// @DATE:Sun Aug 07 17:43:37 GFT 2016
>>>>>>> master

import play.api.mvc.{ QueryStringBindable, PathBindable, Call, JavascriptLiteral }
import play.core.routing.{ HandlerDef, ReverseRouteContext, queryString, dynamicString }


import _root_.controllers.Assets.Asset

// @LINE:6
package presentation.controllers {

  // @LINE:7
  class ReverseAuthenticationController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:7
    def enter(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "enter")
    }
  
  }

  // @LINE:6
  class ReverseApplication(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:6
    def index(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix)
    }
  
  }


}