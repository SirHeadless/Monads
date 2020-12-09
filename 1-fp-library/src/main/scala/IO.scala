
/*
 * Projekt: Detact
 *
 * Copyright(c) Symate GmbH. Unerlaubtes Kopieren und Nutzen untersagt.
 */


final case class IO[+A](unsafeRun: () => A) extends AnyVal {

}

object IO {

//  private type Thunk[A] = () => A
//  private type Description[A] = Thunk[A]

  def create[A](a: => A): IO[A] = IO(() => a)


  implicit val ioMonad: Monad[IO] = new Monad[IO] {
    override def pure[A](a: A): IO[A] =
      create(a)

    override def flatMap[A, B](ca: IO[A])(adb: A => IO[B]): IO[B] = IO.create{
      val a = ca.unsafeRun()

      val db = adb(a)

      val b = db.unsafeRun()
      b
    }

    override def map[A, B](ca: IO[A])(ab: A => B): IO[B] = IO.create{
      val a = ca.unsafeRun()
      
      val b = ab(a)
      
      b
    }



  }

//  def brokenCreate[A]: A => IO[A] = a =>
//    () => a
}
