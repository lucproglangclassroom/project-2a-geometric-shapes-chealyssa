package edu.luc.cs.laufer.cs371.shapes

import org.scalatest.funsuite.AnyFunSuite
import TestFixtures.*
import Shape.*

class TestHeight extends AnyFunSuite:

  def testHeight(description: String, s: Shape, expected: Int): Unit =
    test(description):
      assert(height(s) == expected)

  testHeight("simple rectangle", simpleRectangle, 1)
  testHeight("simple ellipse", simpleEllipse, 1)
  testHeight("simple location", simpleLocation, 2)
  testHeight("basic group", basicGroup, 2)
  testHeight("simple group", simpleGroup, 3)
  testHeight("complex group", complexGroup, 6)

end TestHeight 