package com.msb.bigdata.scala

//match  swtich
object Lesson06_match {

  def main(args: Array[String]): Unit = {
    val tup: (Double, Int, String, Boolean, Int) = (1.0,88,"abc",false,44)
//    val tup: (Double, Int, String, Boolean, Int) = (1.0,88,"abc",false,99)

    val iter: Iterator[Any] = tup.productIterator

    val res: Iterator[Unit] = iter.map(
      (x) => {
        x match {
          //下面方法执行完之后，返回的是unit，空，所以while执行打印函数的时候是（）
          case 1 => println(s"$x...is 1")
          case 88 => println(s"$x ...is 88")
          case false => println(s"$x...is false")
          case w: Int if w > 50 => println(s"$w...is  > 50")
          case _ => println("wo ye bu zhi dao sha lei xing ")
        }
      }
    )
    while(res.hasNext)  println(res.next())

  }






}
