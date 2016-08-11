package service

import com.wix.accord.{Failure, Validator}

package object ServiceValidator {
  def validateAndThrow[T](x: T)( implicit validator: Validator[T] ) = {
    val result = validator(x)
    if(result.isFailure) {
      throw new ValidatorException(result.asInstanceOf[Failure])
    }
  }
}