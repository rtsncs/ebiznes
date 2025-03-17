package models

import play.api.libs.json._

case class Product(id: Int, name: String, categoryId: Int, price: Double)
object Product {
  implicit val format: OFormat[Product] = Json.format[Product]
}

case class Category(id: Int, name: String)
object Category {
  implicit val format: OFormat[Category] = Json.format[Category]
}

case class CartProduct(id: Int, productId: Int, quantity: Int)
object CartProduct {
  implicit val format: OFormat[CartProduct] = Json.format[CartProduct]
}
