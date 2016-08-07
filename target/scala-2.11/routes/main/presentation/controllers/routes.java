
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Projects/Crible/conf/routes
// @DATE:Sun Aug 07 17:43:37 GFT 2016

package presentation.controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final presentation.controllers.ReverseApplication Application = new presentation.controllers.ReverseApplication(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final presentation.controllers.javascript.ReverseApplication Application = new presentation.controllers.javascript.ReverseApplication(RoutesPrefix.byNamePrefix());
  }

}
