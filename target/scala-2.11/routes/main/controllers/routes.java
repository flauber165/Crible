
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Projects/cribleempresa/conf/routes
// @DATE:Wed Aug 03 20:31:40 GFT 2016

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseAssets Assets = new controllers.ReverseAssets(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseAssets Assets = new controllers.javascript.ReverseAssets(RoutesPrefix.byNamePrefix());
  }

}