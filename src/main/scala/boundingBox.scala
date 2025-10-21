package edu.luc.cs.laufer.cs371.shapes

// implemented this behavior

import Shape.*

// simple configurable logger for +0.5 credit
object debug:
  var enabled = false
  def log(msg: String): Unit =
    if enabled then println(s"[debug] $msg")

object boundingBox:
  def apply(s: Shape): Location = s match
    // Rectangle's bounding box
    case Rectangle(w,h) =>
      debug.log(s"Rectangle($w,$h)") 
      Location(0, 0, Rectangle(w, h))

    // Ellipse's bounding box
    case Ellipse(a,b) => Location(-a, -b, Rectangle(a * 2, b * 2))

    // Shift child's bounding box by (x,y)
    case Location(x, y, shape) => 
      val Location(x1, y1, Rectangle(w, h)) = boundingBox(shape)
      Location(x + x1, y + y1, Rectangle(w, h))
    
    // Group: combine all bounding box into one  
    case Group(shapes*) if shapes.nonEmpty => 
      val boxes = shapes.map(boundingBox(_))
      val minX = boxes.map(_.x).min
      val minY = boxes.map(_.y).min
      val maxX = boxes.map(b => b.x + b.shape.asInstanceOf[Rectangle].width).max
      val maxY = boxes.map(b => b.y + b.shape.asInstanceOf[Rectangle].height).max
      Location(minX, minY, Rectangle(maxX - minX, maxY - minY))

    // Empty Group fallback
    case Group() =>
      Location(0,0, Rectangle(0,0))

end boundingBox

object size:
  def apply(s: Shape): Int = s match
    case Rectangle(_, _) => 1
    case Ellipse(_, _)   => 1
    case Location(_, _, shape) => size(shape)
    case Group(shapes*) =>
      // count this group as a container + all its shapes
      shapes.map(size(_)).sum

object height:
  def apply(s: Shape): Int = s match
    case Rectangle(_, _) => 1
    case Ellipse(_, _)   => 1
    case Location(_, _, shape) => 1 + height(shape)
    case Group(shapes*) if shapes.nonEmpty =>
      // don’t double-count nested groups
      1 + shapes.map(height(_)).max
    case Group() => 1

object scale:
  def apply(s: Shape, factor: Double): Shape = s match
    case Rectangle(w, h) => Rectangle((w * factor).toInt, (h * factor).toInt)
    case Ellipse(a, b)   => Ellipse((a * factor).toInt, (b * factor).toInt)
    case Location(x, y, shape) => Location((x * factor).toInt, (y * factor).toInt, scale(shape, factor))
    case Group(shapes*) => Group(shapes.map(scale(_, factor))*)

