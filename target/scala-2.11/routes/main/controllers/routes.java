
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/brcerqueira/Documents/GitHub/cribleempresa/conf/routes
// @DATE:Tue Aug 02 16:25:56 BRT 2016

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseAssets Assets = new controllers.ReverseAssets(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseAssets Assets = new controllers.javascript.ReverseAssets(RoutesPrefix.byNamePrefix());
  }

}
