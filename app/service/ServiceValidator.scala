package service

import com.wix.accord.{Failure, Validator}
import service.exceptions.ValidatorException

package object ServiceValidator {
  def validateAndThrow[T](x: T)( implicit validator: Validator[T] ) = {
    val result = validator(x)
    if(result.isFailure) {
      throw ValidatorException(result.asInstanceOf[Failure])
    }
  }
}