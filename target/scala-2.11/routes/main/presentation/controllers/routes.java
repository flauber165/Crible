
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/brcerqueira/Documents/GitHub/cribleempresa/conf/routes
// @DATE:Tue Aug 02 16:25:56 BRT 2016

package presentation.controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final presentation.controllers.ReverseApplication Application = new presentation.controllers.ReverseApplication(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final presentation.controllers.javascript.ReverseApplication Application = new presentation.controllers.javascript.ReverseApplication(RoutesPrefix.byNamePrefix());
  }

}
