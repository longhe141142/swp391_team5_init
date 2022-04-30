package com.fu.swp391.binding.entiity.exception;

import com.fu.swp391.common.enumConstants.ExceptionClassMessaging;

public class PrincipalBuildException extends Exception{

  String message;

  public PrincipalBuildException(){
    super();
    this.message = ExceptionClassMessaging.AUTH_PRINCIPAL_BUILD_FAILED;
  }

}
