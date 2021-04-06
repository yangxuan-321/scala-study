package org.moda.program_skill

/**
 * 动态规划，代码并未使用 函数式 编程的思想，只是为了解决问题
 */
object Dp {
  /**
   * leecode 322: 你有三种硬币，2元、5元、7元，每种硬币足够多，买一本书需要27元，用最少的硬币组合
   * 动态规划的几个步骤
   * 1. 确定状态
   *    简单来说，解动态规划的时候需要开一个数组，数组的每个元素f[i]或者f[i][j]代表了什么
   *    解动态规划需要两个意识：
   *      * 最后一步
   *        <1>.最优结果当然是存在K个硬币, sum{A_1, A_2, A_3, ... A_k} = 27
   *      * 子问题
   *        <1>.我们已经知道 最后一枚硬币 是 A_k(虽然我们还暂时不知道A_k的值具体是多少)
   *        <2>.我们将将原问题(最少多少枚(K枚)硬币拼出27) 转化为子问题(最少用多少枚硬币拼出27-k枚硬币)
   *        <3>.很明显, A_k ∈ {2, 3, 5}
   *            f(x)代表 拼出x的最少硬币个数
   *            <3.1>.如果A_k=2 => 则 f(27) = f(27-2)+1 = f(25)+1
   *            <3.2>.如果A_k=5 => 则 f(27) = f(27-5)+1 = f(22)+1
   *            <3.3>.如果A_k=7 => 则 f(27) = f(27-7)+1 = f(20)+1
   *            所以，总结出来 f(27) = min{f(27-2), f(27-5), f(27-7)}+1 泛化为一般情况 f(x) = min{f(x-2), f(x-5), f(x-7)} + 1
   * 2. 转移方程
   *      设 f(x) => 最少用多少枚硬币评出x
   *      对于任意x则: f(x) = min{f(x-2)+1, f(x-5)+1, f(x-7)+1}
   *
   * 3. 初始条件和边界情况
   *      * x-2, x-5, x-7 小于0如何处理？
   *      * 什么时候停下来？
   *      小于0即为找不到，为∞(我们可以选取为Int的最大值)，且f(0)初始化为0
   *      f(x<0) => ∞
   *      f(0) = 0
   *
   *      例如 当 x = 1 时 f(1) = min{f(1-2), f(1-5), f(1-7)}+1 是没有意义的
   *             x = 3 => f(3) = min{f(3-2), f(3-5), f(3-7)}+1  f(1)已经在上一步算出 没有意义，f(-2)和f(-4)都没有意义
   *
   * 4. 计算顺序
   *      依次计算f(1), f(2), f(3) ...
   *      当我们计算 f(x) 的时候， 我们其实 就可以直接用 min{f(x-2)+1, f(x-5)+1, f(x-7)+1} 因为 f(x-2), f(x-5), f(x-7) 我们已经算过了
   */
  def coins(): Either[String, Int] = {
    val coinx = Array(2, 5, 7)
    val sum = 27
    var f = Array.fill(sum+1)(Integer.MAX_VALUE)
    // 切记 下表为 0 的数组 要更新为 0
    f = f.updated(0, 0)

    def minFx(x: Int): Boolean = {
      // 计算 f(x-2), f(x-5), f(x-7)
      val xMin = coinx.map { c =>
        if(x-c < 0) Integer.MAX_VALUE // 如果 f(x-c) x-c为 负值 就返回一个 异常值(Integer.MAX_VALUE)
        else if (f(x-c) == Integer.MAX_VALUE) Integer.MAX_VALUE // 如果f(x-c)为异常值 也返回异常值
        else f(x-c) + 1 // 返回 f(x-c)+1
      }.min // 求取最小值

      f = f.updated(x, xMin) // 更新数组值
      println(xMin)
      xMin != Integer.MAX_VALUE
    }


    (1 to f.length-1).map(minFx)
    // f.map(println)
    if (f.last != Integer.MAX_VALUE) Right(f.last) else Left("拼不出27")
  }

  def main(args: Array[String]): Unit = {
    val r = coins()
    r match {
      case Right(v) =>
        println(s"至少需要${v}枚硬币，拼出27")
      case _ =>
        println("拼不出来")
    }
  }
}
