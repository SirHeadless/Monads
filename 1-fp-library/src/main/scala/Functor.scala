
/*
 * Projekt: Detact
 *
 * Copyright(c) Symate GmbH. Unerlaubtes Kopieren und Nutzen untersagt.
 */

trait Functor[F[_]] {
  def map[A, B](fa: F[A])(ab: A => B): F[B]
}
