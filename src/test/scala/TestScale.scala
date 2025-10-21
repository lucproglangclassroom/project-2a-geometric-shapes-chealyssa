package edu.luc.cs.laufer.cs371.shapes

import org.scalatest.funsuite.AnyFunSuite
import TestFixtures.*
import Shape.*

class TestScale extends AnyFunSuite:

  def testScale(description: String, s: Shape, factor: Double, expected: Shape): Unit =
    test(description):
      assert(scale(s, factor) == expected)

  testScale("scale rectangle", simpleRectangle, 2.0, Rectangle(160, 240))
  testScale("scale ellipse", simpleEllipse, 0.5, Ellipse(25, 15))
  testScale("scale location", simpleLocation, 2.0, Location(140, 60, Rectangle(160, 240)))
  testScale(
    "scale group",
    basicGroup,
    0.5,
    Group(Ellipse(25, 15), Rectangle(10, 20))
  )

end TestScale