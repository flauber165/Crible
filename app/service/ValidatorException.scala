package service

import com.wix.accord.Failure

case class ValidatorException(failure: Failure) extends Exception {
}
