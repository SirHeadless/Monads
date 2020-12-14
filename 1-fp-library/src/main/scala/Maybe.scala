import Maybe.{Just, Nothing}

/*
 * Projekt: Detact
 *
 * Copyright(c) Symate GmbH. Unerlaubtes Kopieren und Nutzen untersagt.
 */


sealed trait Maybe[+T] extends Product with Serializable { // Optioin
  def getOrElse[Super >: T](elseValue: => Super): Super

  def mapOrElse[S](ts: T => S)(elseValue: S): S = this match {
    case Just(value) => ts(value)
    case Nothing => elseValue
  }
}



object Maybe {

  case class Just[T](value: T) extends Maybe[T] { // Some
    override def getOrElse[Super >: T](elseValue: => Super): Super = value
  }

  case object Nothing extends Maybe[scala.Nothing] {
    override def getOrElse[Super >: Nothing](elseValue: => Super): Super = elseValue
  }
  def create[A](a: => A): Maybe[A] = Just(a)


  implicit val maybeMonad: Monad[Maybe] = new Monad[Maybe] {
    override def pure[A](a: A): Maybe[A] =
      create(a)

    override def flatMap[A, B](ca: Maybe[A])(adb: A => Maybe[B]): Maybe[B] = ca match {
      case Just(v) => adb(v)
      case Nothing => Nothing
    }

    override def map[A, B](ca: Maybe[A])(ab: A => B): Maybe[B] = ca match {
      case Just(v) => pure(ab(v))
      case Nothing => Nothing
    }

  }
}
