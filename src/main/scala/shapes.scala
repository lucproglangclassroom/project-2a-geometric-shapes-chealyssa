package edu.luc.cs.laufer.cs371.shapes

/** data Shape = Rectangle(w, h) | Location(x, y, Shape) */
enum Shape derives CanEqual:
  case Rectangle(width: Int, height: Int)
  case Location(x: Int, y: Int, shape: Shape)
  // added missing cases (see test fixtures)
  case Ellipse(major: Int, minor: Int)
  case Group(shapes: Shape*)
