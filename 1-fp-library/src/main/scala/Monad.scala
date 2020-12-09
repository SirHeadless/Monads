
/*
 * Projekt: Detact
 *
 * Copyright(c) Symate GmbH. Unerlaubtes Kopieren und Nutzen untersagt.
 */

trait Monad[C[_]] extends Applicative[C] with Functor[C] with FlatMap[C]{
  override def map[A, B](ca: C[A])(ab: A => B): C[B] =
    flatMap(ca)(a => pure(ab(a)))
  def flatten[A,B](ca: C[C[A]]): C[A] =
    flatMap(ca)(ca => ca)

}

object Monad {
  def apply[C[_]: Monad]: Monad[C] = implicitly[Monad[C]]
}
