
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Projects/Crible/conf/routes
// @DATE:Tue Aug 09 21:02:29 GFT 2016

package presentation.controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final presentation.controllers.ReverseAuthenticationController AuthenticationController = new presentation.controllers.ReverseAuthenticationController(RoutesPrefix.byNamePrefix());
  public static final presentation.controllers.ReverseApplication Application = new presentation.controllers.ReverseApplication(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final presentation.controllers.javascript.ReverseAuthenticationController AuthenticationController = new presentation.controllers.javascript.ReverseAuthenticationController(RoutesPrefix.byNamePrefix());
    public static final presentation.controllers.javascript.ReverseApplication Application = new presentation.controllers.javascript.ReverseApplication(RoutesPrefix.byNamePrefix());
  }

}
