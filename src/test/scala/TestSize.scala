package edu.luc.cs.laufer.cs371.shapes

import org.scalatest.funsuite.AnyFunSuite
import TestFixtures.*
import Shape.*

class TestSize extends AnyFunSuite:

  def testSize(description: String, s: Shape, expected: Int): Unit =
    test(description):
      assert(size(s) == expected)

  testSize("simple rectangle", simpleRectangle, 1)
  testSize("simple ellipse", simpleEllipse, 1)
  testSize("simple location", simpleLocation, 1)
  testSize("basic group", basicGroup, 2)
  testSize("simple group", simpleGroup, 2)
  testSize("complex group", complexGroup, 5)

end TestSize
