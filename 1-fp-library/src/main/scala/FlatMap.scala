

/*
 * Projekt: Detact
 *
 * Copyright(c) Symate GmbH. Unerlaubtes Kopieren und Nutzen untersagt.
 */


trait FlatMap[C[_]] {
  def flatMap[A, B](ca: C[A])(acb: A => C[B]): C[B]
  def >=>[A, B](ca: C[A])(acb: A => C[B]): C[B] = flatMap(ca)(acb)
}
